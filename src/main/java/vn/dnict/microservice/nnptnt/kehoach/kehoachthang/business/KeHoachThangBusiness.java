package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangLogData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuTongHopThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.NhiemVuThangLogService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.utils.Constants;

@Service
public class KeHoachThangBusiness {
	@Autowired
	KeHoachThangService serviceKeHoachThangService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	DmCanBoService serviceDmCanBoService;
	
	@Autowired
	NhiemVuThangService serviceNhiemVuThangService;
	
	@Autowired
	CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	TienDoNhiemVuThangService serviceTienDoNhiemVuThangService;
	
	@Autowired
	FileDinhKemNhiemVuThangService serviceFileDinhKemNhiemVuThangService;
	
	@Autowired
	NhiemVuThangLogService serviceNhiemVuThangLogService;
	
	public Page<KeHoachThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, LocalDate thang,
			String tenNhiemVu, Long canBoThucHienId, LocalDate tuThoiHan, LocalDate denThoiHan, Integer tinhTrang) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachThang> pageKeHoachThang = serviceKeHoachThangService.findAll(donViChuTriId, thang, tenNhiemVu, canBoThucHienId,
				tuThoiHan, denThoiHan, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachThangData> pageKeHoachThangData = pageKeHoachThang
				.map(this::convertToKeHoachThangData);
		return pageKeHoachThangData;
	}

	private KeHoachThangData convertToKeHoachThangData(KeHoachThang keHoachThang) {
		KeHoachThangData keHoachThangData = new KeHoachThangData();
		keHoachThangData.setId(keHoachThang.getId());
		if (Objects.nonNull(keHoachThang.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachThang.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachThangData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachThangData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachThangData.setThang(keHoachThang.getThang());

		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService.findByKeHoachThangIdAndDaXoa(keHoachThang.getId(), false);
		List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<NhiemVuThangData>();
		if (Objects.nonNull(nhiemVuThangs) && !nhiemVuThangs.isEmpty()) {
			for (NhiemVuThang nhiemVuThang : nhiemVuThangs) {
				NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
				nhiemVuThangData.setId(nhiemVuThang.getId());
				nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
				nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
				nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
				nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
				nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
				nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
				nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
				nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
				if (Objects.nonNull(nhiemVuThang.getCanBoThucHienId())) {
					Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVuThangData.setCanBoThucHienId(optionalDmCanBo.get().getId());
						nhiemVuThangData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
					}
				}
				nhiemVuThangDatas.add(nhiemVuThangData);
			}
		}
		keHoachThangData.setNhiemVuThangDatas(nhiemVuThangDatas);
		return keHoachThangData;
	}

	public KeHoachThangData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		KeHoachThangData keHoachThangData = new KeHoachThangData();
		keHoachThangData.setId(keHoachThang.getId());
		if (Objects.nonNull(keHoachThang.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachThang.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachThangData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachThangData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachThangData.setThang(keHoachThang.getThang());

		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService.findByKeHoachThangIdAndDaXoa(keHoachThang.getId(), false);
		List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<NhiemVuThangData>();
		if (Objects.nonNull(nhiemVuThangs) && !nhiemVuThangs.isEmpty()) {
			for (NhiemVuThang nhiemVuThang : nhiemVuThangs) {
				NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
				nhiemVuThangData.setId(nhiemVuThang.getId());
				nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
				nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
				nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
				nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
				nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
				nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
				nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
				nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
				if (Objects.nonNull(nhiemVuThang.getCanBoThucHienId())) {
					Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVuThangData.setCanBoThucHienId(optionalDmCanBo.get().getId());
						nhiemVuThangData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
					}
				}
				nhiemVuThangDatas.add(nhiemVuThangData);
			}
		}
		keHoachThangData.setNhiemVuThangDatas(nhiemVuThangDatas);
		return keHoachThangData;
	}

	public KeHoachThangData findByDonViChuTriIdAndThang(Long donViChuTriId, LocalDate thang) {
		KeHoachThang keHoachThang = new KeHoachThang();
		List<KeHoachThang> keHoachThangs = serviceKeHoachThangService.findByDonViChuTriIdAndThangAndDaXoa(donViChuTriId, thang, false);
		if (Objects.nonNull(keHoachThangs) && !keHoachThangs.isEmpty()) {
			keHoachThang = keHoachThangs.get(0);
		}
		keHoachThang.setDonViChuTriId(donViChuTriId);
		keHoachThang.setThang(thang);
		return this.convertToKeHoachThangData(keHoachThang);
	}

	public KeHoachThangData save(KeHoachThangData keHoachThangData) throws EntityNotFoundException {

		KeHoachThang keHoachThang = new KeHoachThang();
		if (Objects.nonNull(keHoachThangData.getId())) {
			Optional<KeHoachThang> optionalKeHoachThang = serviceKeHoachThangService.findById(keHoachThangData.getId());
			if (optionalKeHoachThang.isPresent()) {
				keHoachThang = optionalKeHoachThang.get();
			}
		}
		keHoachThang.setDaXoa(false);
		if (Objects.nonNull(keHoachThangData.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachThangData.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachThang.setDonViChuTriId(optionalDmDonVi.get().getId());
			}
		}
		keHoachThang.setThang(keHoachThangData.getThang());
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		serviceNhiemVuThangService.setFixedDaXoaForKeHoachThangId(true, keHoachThang.getId());
		List<NhiemVuThangData> nhiemVuThangDatas = keHoachThangData.getNhiemVuThangDatas();
		if (Objects.nonNull(nhiemVuThangDatas) && !nhiemVuThangDatas.isEmpty()) {
			for (NhiemVuThangData nhiemVuThangData : nhiemVuThangDatas) {
				NhiemVuThang nhiemVuThang = new NhiemVuThang();
				if (Objects.nonNull(nhiemVuThang.getId())) {
					Optional<NhiemVuThang> optNhiemVu = serviceNhiemVuThangService.findById(nhiemVuThang.getId());
					if(optNhiemVu.isPresent()) {
						nhiemVuThang = optNhiemVu.get();
				}
				nhiemVuThang.setDaXoa(false);
				nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
				nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
				nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
				nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getId());
				nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
				nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
				nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
				nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
				if (Objects.isNull(nhiemVuThang.getTinhTrang())) {
					nhiemVuThang.setTinhTrang(Constants.QLKH_TINHTRANG_CHUATHUCHIEN);
				}
				nhiemVuThang.setCanBoThucHienId(null);
				if (Objects.nonNull(nhiemVuThangData.getCanBoThucHienId())) {
					Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThangData.getCanBoThucHienId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVuThang.setCanBoThucHienId(optionalDmCanBo.get().getId());
					}
				}
				nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
				}
			}
		}
		return this.convertToKeHoachThangData(keHoachThang);
	}

	public KeHoachThangData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		keHoachThang.setDaXoa(true);
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		return this.convertToKeHoachThangData(keHoachThang);
	}
}
