package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.utils.Constants;

@Service
public class NhiemVuThangBusiness {
	@Autowired
	NhiemVuThangService serviceNhiemVuThangService;
	
	@Autowired
	KeHoachThangService serviceKeHoachThangService;
	
	@Autowired
	DmCanBoService serviceDmCanBoService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	TienDoNhiemVuThangService serviceTienDoNhiemVuThangService;
	
	@Autowired
	FileDinhKemNhiemVuThangService serviceFileDinhKemNhiemVuThangService;
	
	@Autowired
	CoreAttachmentBusiness coreAttachmentBusiness;
	
	public Page<NhiemVuThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, List<LocalDate> thangs,
			 String tenNhiemVu, Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, tenNhiemVu,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuThangData> pageNhiemVuThangData = pageNhiemVuThang.map(this::convertToNhiemVuThangData);
		return pageNhiemVuThangData;
	}
	
	private NhiemVuThangData convertToNhiemVuThangData(NhiemVuThang nhiemVuThang) {
		NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
		nhiemVuThangData.setId(nhiemVuThang.getId());
		nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
		if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
			Optional<KeHoachThang> optKeHoach = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
			if(optKeHoach.isPresent()) {
				nhiemVuThangData.setKeHoachDonViChuTriId(optKeHoach.get().getDonViChuTriId());
				if(optKeHoach.get().getDonViChuTriId() != null && optKeHoach.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoach.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuThangData.setKeHoachDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuThangData.setKeHoachthang(optKeHoach.get().getThang());
			}
		}
		nhiemVuThangData.setCanBoThucHienId(nhiemVuThang.getCanBoThucHienId());
		if(nhiemVuThang.getCanBoThucHienId() != null && nhiemVuThang.getCanBoThucHienId() > 0) {
			Optional<DmCanBo> optCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
			if(optCanBo.isPresent()) {
				nhiemVuThangData.setCanBoThucHienTen(optCanBo.get().getHoTen());
			}
		}
		nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
		nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
		nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
		nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
		nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
		nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
		nhiemVuThangData.setTienDoNhiemVuId(nhiemVuThang.getTienDoNhiemVuId());
		if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0 ) {
			Optional<TienDoNhiemVuThang> optTienDo = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
			if(optTienDo.isPresent()) {
				nhiemVuThangData.setTienDoNhiemVuId(optTienDo.get().getId());
				nhiemVuThangData.setTienDoMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
				nhiemVuThangData.setTienDoKetQua(optTienDo.get().getKetQua());
				nhiemVuThangData.setTienDoTenNguoiCapNhat(optTienDo.get().getTenNguoiCapNhat());
			}
		}
		return nhiemVuThangData;
	}
	
	public NhiemVuThangData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
		nhiemVuThangData.setId(nhiemVuThang.getId());
		nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
		if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
			Optional<KeHoachThang> optKeHoach = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
			if(optKeHoach.isPresent()) {
				nhiemVuThangData.setKeHoachDonViChuTriId(optKeHoach.get().getDonViChuTriId());
				if(optKeHoach.get().getDonViChuTriId() != null && optKeHoach.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoach.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuThangData.setKeHoachDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuThangData.setKeHoachthang(optKeHoach.get().getThang());
			}
		}
		nhiemVuThangData.setCanBoThucHienId(nhiemVuThang.getCanBoThucHienId());
		if(nhiemVuThang.getCanBoThucHienId() != null && nhiemVuThang.getCanBoThucHienId() > 0) {
			Optional<DmCanBo> optCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
			if(optCanBo.isPresent()) {
				nhiemVuThangData.setCanBoThucHienTen(optCanBo.get().getHoTen());
			}
		}
		nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
		nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
		nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
		nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
		nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
		nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
		nhiemVuThangData.setTienDoNhiemVuId(nhiemVuThang.getTienDoNhiemVuId());
		if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0 ) {
			Optional<TienDoNhiemVuThang> optTienDo = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
			if(optTienDo.isPresent()) {
				nhiemVuThangData.setTienDoNhiemVuId(optTienDo.get().getId());
				nhiemVuThangData.setTienDoMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
				nhiemVuThangData.setTienDoKetQua(optTienDo.get().getKetQua());
				nhiemVuThangData.setTienDoTenNguoiCapNhat(optTienDo.get().getTenNguoiCapNhat());
				
				if(Objects.nonNull(optTienDo)) {
					int type = Constants.DINH_KEM_1_FILE;
					Optional<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService
							.findByTienDoNhiemVuThangId(optTienDo.get().getId());
					Long fileDinhKemId = null;
					Long objectId = optTienDo.get().getId();
					String appCode = TienDoNhiemVuNam.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemNhiemVuThang.get().getFileDinhKemId(), appCode, objectId, type);
					nhiemVuThangData.setFileDinhKem(fileDinhKem);
					nhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
				}
			}
		}
		return nhiemVuThangData;
	}
	
	public NhiemVuThangData create(NhiemVuThangData nhiemVuThangData) throws MethodArgumentNotValidException {
		NhiemVuThang nhiemVuThang = new NhiemVuThang();
		nhiemVuThang.setDaXoa(false);
		nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
		nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getKeHoachThangId());
		nhiemVuThang.setCanBoThucHienId(nhiemVuThangData.getCanBoThucHienId());
		nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
		nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
		nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
		nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
		nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
		nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
		nhiemVuThang.setTienDoNhiemVuId(nhiemVuThangData.getTienDoNhiemVuId());
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
	
	public NhiemVuThangData update(Long id, NhiemVuThangData nhiemVuThangData) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
		nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getKeHoachThangId());
		nhiemVuThang.setCanBoThucHienId(nhiemVuThangData.getCanBoThucHienId());
		nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
		nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
		nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
		nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
		nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
		nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
		nhiemVuThang.setTienDoNhiemVuId(nhiemVuThangData.getTienDoNhiemVuId());
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public NhiemVuThangData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		nhiemVuThang.setDaXoa(true);
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
}
