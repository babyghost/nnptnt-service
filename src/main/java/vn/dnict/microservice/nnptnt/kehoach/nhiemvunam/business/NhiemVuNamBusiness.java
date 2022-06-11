package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.FileDinhKemNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.TienDoNhiemVuNamService;
import vn.dnict.microservice.utils.Constants;

@Service
public class NhiemVuNamBusiness {
	@Autowired
	NhiemVuNamService serviceNhiemVuNamService;
	
	@Autowired
	KeHoachNamService serviceKeHoachNamService;
	
	@Autowired
	DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	TienDoNhiemVuNamService serviceTienDoNhiemVuNamService;
	
	@Autowired
	FileDinhKemNhiemVuNamService serviceFileDinhKemNhiemVuNamService;
	
	public Page<NhiemVuNamData> findAll(int page, int size, String sortBy, String sortDir, Long keHoachId, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.findAll(keHoachId, tenNhiemVu, tuNgay, denNgay,
				PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuNamData> pageNhiemVuNamData = pageNhiemVuNam.map(this::convertToNhiemVuNamData);
		return pageNhiemVuNamData;
	}
	
	private NhiemVuNamData convertToNhiemVuNamData(NhiemVuNam nhiemVuNam) {
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(!optionalKeHoachNam.isPresent()) {
				nhiemVuNamData.setKeHoachNamTen(optionalKeHoachNam.get().getTenKeHoach());
				nhiemVuNamData.setKhDonViChuTriId(optionalKeHoachNam.get().getDonViChuTriId());
				if(optionalKeHoachNam.get().getDonViChuTriId() != null && optionalKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(optionalKeHoachNam.get().getDonViChuTriId());
					if(optionalDmDonVi.isPresent()) {
						nhiemVuNamData.setKhDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
					}
				}
				nhiemVuNamData.setKhNam(optionalKeHoachNam.get().getNam());
			}
		}
		nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
		if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
			Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
			if(optLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
				nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
			}
		}
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService
				.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		if(Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for (TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());
				
				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
		return nhiemVuNamData;
	}
	
	public NhiemVuNamData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(optKeHoachNam.isPresent()) {
				nhiemVuNamData.setKeHoachNamTen(optKeHoachNam.get().getTenKeHoach());
				nhiemVuNamData.setKhDonViChuTriId(optKeHoachNam.get().getDonViChuTriId());
				if(optKeHoachNam.get().getDonViChuTriId() != null && optKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoachNam.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuNamData.setKhDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuNamData.setKhNam(optKeHoachNam.get().getNam());
			}
		}
		nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
		if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
			Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
			if(optLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
				nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
			}
		}
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		if(Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for(TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());;
				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
		return nhiemVuNamData;
	}
	
	public KeHoachNamData findByKeHoachNamId(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if(!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setDonViChuTriId(keHoachNam.getDonViChuTriId());
		if(keHoachNam.getDonViChuTriId() != null && keHoachNam.getDonViChuTriId() > 0) {
			Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if(!optDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriTen(optDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());
		
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<>();
		List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService.findByKeHoachNamIdAndDaXoa(keHoachNam.getId(), false);
		if(Objects.nonNull(nhiemVuNams) && !nhiemVuNams.isEmpty()) {
			for(NhiemVuNam nhiemVuNam : nhiemVuNams) {
				NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
				nhiemVuNamData.setId(nhiemVuNam.getId());
				nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
				nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
				nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
				nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
				nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
				nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
				nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
				if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
					Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
					if(optLoaiNhiemVu.isPresent()) {
						nhiemVuNamData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
						nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
					}
				}
				nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
				
				nhiemVuNamDatas.add(nhiemVuNamData);
			}
		}
		keHoachNamData.setNhiemVuNamDatas(nhiemVuNamDatas);
		return keHoachNamData;
	}
	
	public NhiemVuNam create(NhiemVuNamData nhiemVuNamData) throws MethodArgumentNotValidException {	
		NhiemVuNam nhiemVuNam = new NhiemVuNam();
		nhiemVuNam.setDaXoa(false);
		nhiemVuNam.setKeHoachNamId(nhiemVuNamData.getKeHoachNamId());
		nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
		nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
		nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
		nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
		nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
		nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
		nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
		nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);

		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(false, nhiemVuNam.getId());
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = nhiemVuNamData.getTienDoNhiemVuNamDatas();
		if(Objects.nonNull(tienDoNhiemVuNamDatas) && !tienDoNhiemVuNamDatas.isEmpty()) {
			for(TienDoNhiemVuNamData tienDoNhiemVuNamData : tienDoNhiemVuNamDatas) {
				TienDoNhiemVuNam tienDoNhiemVuNam = new TienDoNhiemVuNam();
				if(Objects.nonNull(tienDoNhiemVuNam.getId())) {
					Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findById(tienDoNhiemVuNamData.getId());
					if(optTienDo.isPresent()) {
						tienDoNhiemVuNam = optTienDo.get();
					}
				}
				tienDoNhiemVuNam.setId(tienDoNhiemVuNamData.getId());
				tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
				tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
				tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
				tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
				tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
				serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
			}
		}		
		return nhiemVuNam;		
	}
	
	public NhiemVuNam update(Long id, NhiemVuNamData nhiemVuNamData) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		nhiemVuNam.setKeHoachNamId(nhiemVuNamData.getKeHoachNamId());
		nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
		nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
		nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
		nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
		nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
		nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
		nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
		nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
		
		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(false, nhiemVuNam.getId());
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = nhiemVuNamData.getTienDoNhiemVuNamDatas();
		if(Objects.nonNull(tienDoNhiemVuNamDatas) && !tienDoNhiemVuNamDatas.isEmpty()) {
			for(TienDoNhiemVuNamData tienDoNhiemVuNamData : tienDoNhiemVuNamDatas) {
				TienDoNhiemVuNam tienDoNhiemVuNam = new TienDoNhiemVuNam();
				if(Objects.nonNull(tienDoNhiemVuNam.getId())) {
					Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findById(tienDoNhiemVuNamData.getId());
					if(optTienDo.isPresent()) {
						tienDoNhiemVuNam = optTienDo.get();
					}
				}
				tienDoNhiemVuNam.setId(tienDoNhiemVuNamData.getId());
				tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
				tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
				tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
				tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
				tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
				serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
			}
		}
		return nhiemVuNam;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public NhiemVuNamData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService.findById(id);
		if (!optionalNhiemVuNam.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNamData.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optionalNhiemVuNam.get();
		nhiemVuNam.setDaXoa(true);
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
		return this.convertToNhiemVuNamData(nhiemVuNam);
	}
}
