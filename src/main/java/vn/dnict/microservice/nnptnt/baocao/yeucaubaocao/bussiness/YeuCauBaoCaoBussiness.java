package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.bussiness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.model.ChiTietBaoCao;
import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service.ChiTietBaoCaoService;
import vn.dnict.microservice.nnptnt.baocao.data.YeuCauBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model.YeuCauBaoCao;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.YeuCauBaoCaoService;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;

@Service
public class YeuCauBaoCaoBussiness {
	@Autowired
	YeuCauBaoCaoService serviceYeuCauBaoCaoService;

	@Autowired
	DmLinhVucService serviceDmLinhVucService;

	@Autowired
	ChiTietBaoCaoService serviceChiTietBaoCaoService;

	@Autowired
	DmDonViService serviceDmDonViService;

	public Page<YeuCauBaoCaoData> findAll(int page, int size, String sortBy, String sortDir, String tieuDe,
			LocalDate ngayYeuCauTuNgay, LocalDate ngayYeuCauDenNgay, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay,
			Long linhVucId, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<YeuCauBaoCao> pageYeuCauBaoCao = serviceYeuCauBaoCaoService.findAll(tieuDe, ngayYeuCauTuNgay,
				ngayYeuCauDenNgay, thoiHanTuNgay, thoiHanDenNgay, linhVucId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<YeuCauBaoCaoData> pageYeuCauBaoCaoData = pageYeuCauBaoCao.map(this::convertToYeuCauBaoCaoData);

		return pageYeuCauBaoCaoData;
	}

	public YeuCauBaoCaoData convertToYeuCauBaoCaoData(YeuCauBaoCao yeuCauBaoCao) {
		YeuCauBaoCaoData yeuCauBaoCaoData = new YeuCauBaoCaoData();
		yeuCauBaoCaoData.setId(yeuCauBaoCao.getId());
		yeuCauBaoCaoData.setTieuDe(yeuCauBaoCao.getTieuDe());
		yeuCauBaoCaoData.setTrangThai(yeuCauBaoCao.getTrangThai());
		yeuCauBaoCaoData.setNgayHetHan(yeuCauBaoCao.getNgayHetHan());
		yeuCauBaoCaoData.setNgayVanBan(yeuCauBaoCao.getNgayVanBan());
		yeuCauBaoCaoData.setSoVanBan(yeuCauBaoCao.getSoVanBan());
		yeuCauBaoCaoData.setNgayYeuCau(yeuCauBaoCao.getNgayYeuCau());
		// yeuCauBaoCaoData.getDonViIds()
		if (Objects.nonNull(yeuCauBaoCao.getLinhVucId())) {
			Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(yeuCauBaoCao.getLinhVucId());
			if (optLinhVuc.isPresent()) {
				yeuCauBaoCaoData.setLinhVucId(optLinhVuc.get().getId());
				yeuCauBaoCaoData.setLinhVucTen(optLinhVuc.get().getTen());
			}
		}
		List<Long> donViIds = new ArrayList<Long>();
		List<ChiTietBaoCao> chiTietBaoCaos = serviceChiTietBaoCaoService
				.findByYeuCauBaoCaoIdAndDaXoa(yeuCauBaoCao.getId(), false);
		if (Objects.nonNull(chiTietBaoCaos) && !chiTietBaoCaos.isEmpty()) {
			donViIds = chiTietBaoCaos.stream().filter(e -> e.getDonViId() != null).map(ChiTietBaoCao::getDonViId)
					.distinct().collect(Collectors.toList());

		}
		yeuCauBaoCaoData.setDonViIds(donViIds);
		System.out.println(yeuCauBaoCaoData.getDonViIds()+"*************");
		return yeuCauBaoCaoData;
	}

	public YeuCauBaoCaoData create(YeuCauBaoCaoData yeuCauBaoCaoData) {
		YeuCauBaoCao yeuCauBaoCao = new YeuCauBaoCao();
		yeuCauBaoCao.setDaXoa(false);
		yeuCauBaoCao.setNgayHetHan(yeuCauBaoCaoData.getNgayHetHan());
		yeuCauBaoCao.setNgayVanBan(yeuCauBaoCaoData.getNgayVanBan());
		yeuCauBaoCao.setNgayYeuCau(yeuCauBaoCaoData.getNgayYeuCau());
		yeuCauBaoCao.setLinhVucId(yeuCauBaoCaoData.getLinhVucId());
		yeuCauBaoCao.setTieuDe(yeuCauBaoCaoData.getTieuDe());
		yeuCauBaoCao.setTrangThai(yeuCauBaoCaoData.getTrangThai());
		yeuCauBaoCao.setSoVanBan(yeuCauBaoCaoData.getSoVanBan());

		yeuCauBaoCao = serviceYeuCauBaoCaoService.save(yeuCauBaoCao);

		serviceChiTietBaoCaoService.setFixedDaXoaForYeuCauBaoCaoId(false, yeuCauBaoCao.getId());
		List<Long> donViIds = yeuCauBaoCaoData.getDonViIds();

			for (Long donViId : donViIds) {

				ChiTietBaoCao chiTietBaoCao = new ChiTietBaoCao();
				List<ChiTietBaoCao> chiTietBaoCaos = serviceChiTietBaoCaoService
						.findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(yeuCauBaoCaoData.getId(), donViId, false);
				if (Objects.nonNull(chiTietBaoCaos) && !chiTietBaoCaos.isEmpty()) {
					chiTietBaoCao = chiTietBaoCaos.get(0);
				}
				chiTietBaoCao.setDaXoa(false);
				chiTietBaoCao.setYeuCauBaoCaoId(yeuCauBaoCao.getId());
				chiTietBaoCao.setDonViId(donViId);
				chiTietBaoCao.setTrangThai(yeuCauBaoCaoData.getTrangThai());
				serviceChiTietBaoCaoService.save(chiTietBaoCao);
			}

		

		return yeuCauBaoCaoData;

	}

	public YeuCauBaoCaoData update(Long id, YeuCauBaoCaoData yeuCauBaoCaoData) throws EntityNotFoundException {
		Optional<YeuCauBaoCao> optionalYeuCauBaoCao = serviceYeuCauBaoCaoService.findById(id);
		if (!optionalYeuCauBaoCao.isPresent()) {
			throw new EntityNotFoundException(YeuCauBaoCao.class, "id", String.valueOf(id));
		}
		YeuCauBaoCao yeuCauBaoCao = optionalYeuCauBaoCao.get();
		yeuCauBaoCao.setDaXoa(false);
		yeuCauBaoCao.setNgayHetHan(yeuCauBaoCaoData.getNgayHetHan());
		yeuCauBaoCao.setNgayVanBan(yeuCauBaoCaoData.getNgayVanBan());
		yeuCauBaoCao.setNgayYeuCau(yeuCauBaoCaoData.getNgayYeuCau());
		yeuCauBaoCao.setLinhVucId(yeuCauBaoCaoData.getLinhVucId());
		yeuCauBaoCao.setTieuDe(yeuCauBaoCaoData.getTieuDe());
		yeuCauBaoCao.setTrangThai(yeuCauBaoCaoData.getTrangThai());
		yeuCauBaoCao.setSoVanBan(yeuCauBaoCaoData.getSoVanBan());

		yeuCauBaoCao = serviceYeuCauBaoCaoService.save(yeuCauBaoCao);

		serviceChiTietBaoCaoService.setFixedDaXoaForYeuCauBaoCaoId(false, yeuCauBaoCao.getId());
		List<Long> donViIds = yeuCauBaoCaoData.getDonViIds();
		if (Objects.nonNull(donViIds) && !donViIds.isEmpty()) {

			for (Long donViId : donViIds) {

				ChiTietBaoCao chiTietBaoCao = new ChiTietBaoCao();
				List<ChiTietBaoCao> chiTietBaoCaos = serviceChiTietBaoCaoService
						.findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(yeuCauBaoCaoData.getId(), donViId, false);
				if (Objects.nonNull(chiTietBaoCaos) && !chiTietBaoCaos.isEmpty()) {
					chiTietBaoCao = chiTietBaoCaos.get(0);
				}
				chiTietBaoCao.setDaXoa(false);
				chiTietBaoCao.setYeuCauBaoCaoId(yeuCauBaoCaoData.getId());
				chiTietBaoCao.setDonViId(donViId);
				chiTietBaoCao.setTrangThai(yeuCauBaoCaoData.getTrangThai());
				serviceChiTietBaoCaoService.save(chiTietBaoCao);
			}

		}
		return yeuCauBaoCaoData;
	}

	public YeuCauBaoCaoData findById(Long id) throws EntityNotFoundException {
		Optional<YeuCauBaoCao> optional = serviceYeuCauBaoCaoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(YeuCauBaoCao.class, "id", String.valueOf(id));
		}
		YeuCauBaoCao yeuCauBaoCao = optional.get();
		return this.convertToYeuCauBaoCaoData(yeuCauBaoCao);
	}

	public YeuCauBaoCaoData delete(Long id) throws EntityNotFoundException {
		Optional<YeuCauBaoCao> optional = serviceYeuCauBaoCaoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(YeuCauBaoCao.class, "id", String.valueOf(id));
		}
		YeuCauBaoCao yeuCauBaoCao = optional.get();
		yeuCauBaoCao.setDaXoa(true);
		yeuCauBaoCao = serviceYeuCauBaoCaoService.save(yeuCauBaoCao);
		return this.convertToYeuCauBaoCaoData(yeuCauBaoCao);
	}

}
