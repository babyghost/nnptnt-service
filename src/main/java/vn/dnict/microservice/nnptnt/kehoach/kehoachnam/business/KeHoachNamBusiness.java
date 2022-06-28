package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.business;

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

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;

@Service
public class KeHoachNamBusiness {
	@Autowired
	KeHoachNamService serviceKeHoachNamService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	NhiemVuNamService serviceNhiemVuNamService;
	
	@Autowired
	DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	
	public Page<KeHoachNamData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			Integer nam, String tenKeHoach, String soKyHieu, Boolean trangThai, LocalDate tuNgayBanHanh,
			LocalDate denNgayBanHanh) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachNam> pageKeHoachNam = serviceKeHoachNamService.findAll(donViChuTriId, nam, tenKeHoach,
				soKyHieu, trangThai, tuNgayBanHanh, denNgayBanHanh, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachNamData> pageKeHoachNamData = pageKeHoachNam
				.map(this::convertToKeHoachNamData);
		return pageKeHoachNamData;
	}

	private KeHoachNamData convertToKeHoachNamData(KeHoachNam keHoachNam) {
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());

		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		
		List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService.findByKeHoachNamIdAndDaXoa(keHoachNam.getId(), false);
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
		if(Objects.nonNull(nhiemVuNams) && !nhiemVuNams.isEmpty()) {
			for(NhiemVuNam nhiemVuNam : nhiemVuNams) {
				NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
				nhiemVuNamData.setId(nhiemVuNam.getId());
				nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
				nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
				nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
				nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
				nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
				nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
				if(Objects.nonNull(nhiemVuNam.getLoaiNhiemVuId())) {
					Optional<DmLoaiNhiemVu> optNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
					if(optNhiemVu.isPresent()) {
						nhiemVuNamData.setLoaiNhiemVuTen(optNhiemVu.get().getTen());
					}
				}
				nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
				nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
				nhiemVuNamData.setDanhSo(nhiemVuNam.getDanhSo());
				nhiemVuNamDatas.add(nhiemVuNamData);
			}
		}
		keHoachNamData.setNhiemVuNamDatas(nhiemVuNamDatas);
		return keHoachNamData;
	}

	public KeHoachNamData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optional = serviceKeHoachNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optional.get();
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());

		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService.findByKeHoachNamIdAndDaXoa(keHoachNam.getId(), false);
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
		if(Objects.nonNull(nhiemVuNams) && !nhiemVuNams.isEmpty()) {
			for(NhiemVuNam nhiemVuNam : nhiemVuNams) {
				NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
				nhiemVuNamData.setId(nhiemVuNam.getId());
				nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
				nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
				nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
				nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
				nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
				nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
				nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
				if(Objects.nonNull(nhiemVuNam.getLoaiNhiemVuId())) {
					Optional<DmLoaiNhiemVu> optNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
					if(optNhiemVu.isPresent()) {
						nhiemVuNamData.setLoaiNhiemVuTen(optNhiemVu.get().getTen());
					}
				}
				nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
				nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
				nhiemVuNamData.setDanhSo(nhiemVuNam.getDanhSo());
				nhiemVuNamDatas.add(nhiemVuNamData);
			}
		}
		keHoachNamData.setNhiemVuNamDatas(nhiemVuNamDatas);
		return keHoachNamData;
	}

	public KeHoachNam create(KeHoachNamData keHoachNamData) throws EntityNotFoundException {

		KeHoachNam keHoachNam = new KeHoachNam();
		keHoachNam.setDaXoa(false);
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		if (Objects.nonNull(keHoachNamData.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNamData.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNam.setDonViChuTriId(optionalDmDonVi.get().getId());
			}
		}
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachNamId(false, keHoachNam.getId());
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
		if(Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
			for(NhiemVuNamData nhiemVuNamData : nhiemVuNamDatas) {
				NhiemVuNam nhiemVuNam = new NhiemVuNam();
				if(Objects.nonNull(nhiemVuNam.getId())) {
					Optional<NhiemVuNam> optNhiemVu = serviceNhiemVuNamService.findById(nhiemVuNamData.getId());
					if(optNhiemVu.isPresent()) {
						nhiemVuNam = optNhiemVu.get();
					}
				}
				nhiemVuNam.setId(nhiemVuNamData.getId());
				nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
				nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
				nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
				nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
				nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
				nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
				nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
				nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
				nhiemVuNam.setDanhSo(nhiemVuNamData.getDanhSo());
				serviceNhiemVuNamService.save(nhiemVuNam);
			}
		}

		return keHoachNam;
	}

	public KeHoachNam update(Long id, KeHoachNamData keHoachNamData) throws EntityNotFoundException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if (!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachNamId(false, keHoachNam.getId());
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
		if(Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
			for(NhiemVuNamData nhiemVuNamData : nhiemVuNamDatas) {
				NhiemVuNam nhiemVuNam = new NhiemVuNam();
				if(Objects.nonNull(nhiemVuNam.getId())) {
					Optional<NhiemVuNam> optNhiemVu = serviceNhiemVuNamService.findById(nhiemVuNamData.getId());
					if(optNhiemVu.isPresent()) {
						nhiemVuNam = optNhiemVu.get();
					}
				}
				nhiemVuNam.setId(nhiemVuNamData.getId());
				nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
				nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
				nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
				nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
				nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
				nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
				nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
				nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
				nhiemVuNam.setDanhSo(nhiemVuNamData.getDanhSo());
				serviceNhiemVuNamService.save(nhiemVuNam);
			}
		}
		return keHoachNam;
	}

	public KeHoachNamData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optional = serviceKeHoachNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optional.get();
		keHoachNam.setDaXoa(true);
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		return this.convertToKeHoachNamData(keHoachNam);
	}
}
