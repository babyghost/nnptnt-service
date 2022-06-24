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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
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
	
	public Page<KeHoachThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			LocalDate thang, String tenNhiemVu, Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay,
			Integer tinhTrang) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		final Page<KeHoachThang> pageKeHoachThang = serviceKeHoachThangService.findAll(donViChuTriId, thang, tenNhiemVu,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachThangData> pageKeHoachThangData = pageKeHoachThang.map(this::convertToKeHoachThangData);
		return pageKeHoachThangData;
	}
	
	private KeHoachThangData convertToKeHoachThangData(KeHoachThang keHoachThang) {
		KeHoachThangData keHoachThangData = new KeHoachThangData();
		keHoachThangData.setId(keHoachThang.getId());
		keHoachThangData.setDonViChuTriId(keHoachThang.getDonViChuTriId());
		if(keHoachThang.getDonViChuTriId() != null && keHoachThang.getDonViChuTriId() > 0) {
			Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(keHoachThang.getDonViChuTriId());
			if(optDonVi.isPresent()) {
				keHoachThangData.setDoViChuTriTen(optDonVi.get().getTenDonVi());
			}
		}
		keHoachThangData.setThang(keHoachThang.getThang());

		List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<>();
		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService.findByKeHoachThangIdAndDaXoa(keHoachThang.getId(), false);
		if(Objects.nonNull(nhiemVuThangs) && !nhiemVuThangs.isEmpty()) {
			for(NhiemVuThang nhiemVuThang : nhiemVuThangs) {
				NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
				nhiemVuThangData.setId(nhiemVuThang.getId());
				nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
				nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
				if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
					Optional<KeHoachThang> optKeHoachThang = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
					if(optKeHoachThang.isPresent()) {
						nhiemVuThangData.setKeHoachDonViChuTriId(optKeHoachThang.get().getDonViChuTriId());
						if(optKeHoachThang.get().getDonViChuTriId() != null && optKeHoachThang.get().getDonViChuTriId() > 0) {
							Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoachThang.get().getDonViChuTriId());
							if(optDonVi.isPresent()) {
								nhiemVuThangData.setKeHoachDonViChuTriTen(optDonVi.get().getTenDonVi());
							}
						}
						nhiemVuThangData.setKeHoachthang(optKeHoachThang.get().getThang());
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
				if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0) {
					Optional<TienDoNhiemVuThang> optTienDoThang = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
					if(optTienDoThang.isPresent()) {
						nhiemVuThangData.setTienDoMucDoHoanThanh(optTienDoThang.get().getMucDoHoanThanh());
						nhiemVuThangData.setTienDoKetQua(optTienDoThang.get().getKetQua());
						nhiemVuThangData.setTienDoTenNguoiCapNhat(optTienDoThang.get().getTenNguoiCapNhat());
						
						if(Objects.nonNull(optTienDoThang)) {
							int type = Constants.DINH_KEM_1_FILE;
							Optional<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService
									.findByTienDoNhiemVuThangId(optTienDoThang.get().getId());
							Long fileDinhKemId = null;
							Long objectId = optTienDoThang.get().getId();
							String appCode = TienDoNhiemVuNam.class.getSimpleName();
							FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemNhiemVuThang.get().getFileDinhKemId(), appCode, objectId, type);
							nhiemVuThangData.setFileDinhKem(fileDinhKem);
							nhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
						}
					}
				}
				nhiemVuThangDatas.add(nhiemVuThangData);
			}
			keHoachThangData.setNhiemVuThangDatas(nhiemVuThangDatas);
		}
		return keHoachThangData;
	}
	
	public KeHoachThangData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		KeHoachThangData keHoachThangData = new KeHoachThangData();
		keHoachThangData.setId(keHoachThang.getId());
		System.out.println("ssssss"+keHoachThang.getId());
		keHoachThangData.setDonViChuTriId(keHoachThang.getDonViChuTriId());
		if(keHoachThang.getDonViChuTriId() != null && keHoachThang.getDonViChuTriId() > 0) {
			Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(keHoachThang.getDonViChuTriId());
			if(optDonVi.isPresent()) {
				keHoachThangData.setDoViChuTriTen(optDonVi.get().getTenDonVi());
			}
		}
		keHoachThangData.setThang(keHoachThang.getThang());
		
		List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<>();
		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService.findByKeHoachThangIdAndDaXoa(keHoachThang.getId(), false);
		System.out.println("aaaaaaaaaaaaaaa"+nhiemVuThangs);
		if(Objects.nonNull(nhiemVuThangs) && !nhiemVuThangs.isEmpty()) {
			for(NhiemVuThang nhiemVuThang : nhiemVuThangs) {
				NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
				nhiemVuThangData.setId(nhiemVuThang.getId());
				nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
				nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
				if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
					Optional<KeHoachThang> optKeHoachThang = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
					if(optKeHoachThang.isPresent()) {
						nhiemVuThangData.setKeHoachDonViChuTriId(optKeHoachThang.get().getDonViChuTriId());
						if(optKeHoachThang.get().getDonViChuTriId() != null && optKeHoachThang.get().getDonViChuTriId() > 0) {
							Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoachThang.get().getDonViChuTriId());
							if(optDonVi.isPresent()) {
								nhiemVuThangData.setKeHoachDonViChuTriTen(optDonVi.get().getTenDonVi());
							}
						}
						nhiemVuThangData.setKeHoachthang(optKeHoachThang.get().getThang());
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
				if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0) {
					Optional<TienDoNhiemVuThang> optTienDoThang = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
					if(optTienDoThang.isPresent()) {
						nhiemVuThangData.setTienDoMucDoHoanThanh(optTienDoThang.get().getMucDoHoanThanh());
						nhiemVuThangData.setTienDoKetQua(optTienDoThang.get().getKetQua());
						nhiemVuThangData.setTienDoTenNguoiCapNhat(optTienDoThang.get().getTenNguoiCapNhat());
						
						if(Objects.nonNull(optTienDoThang)) {
							int type = Constants.DINH_KEM_1_FILE;
							Optional<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService
									.findByTienDoNhiemVuThangId(optTienDoThang.get().getId());
							Long fileDinhKemId = null;
							Long objectId = optTienDoThang.get().getId();
							String appCode = TienDoNhiemVuNam.class.getSimpleName();
							FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemNhiemVuThang.get().getFileDinhKemId(), appCode, objectId, type);
							nhiemVuThangData.setFileDinhKem(fileDinhKem);
							nhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
						}
					}
				}
				nhiemVuThangDatas.add(nhiemVuThangData);
			}
			keHoachThangData.setNhiemVuThangDatas(nhiemVuThangDatas);
		}
		return keHoachThangData;
	}
	
	public KeHoachThang create(KeHoachThangData keHoachThangData) throws MethodArgumentNotValidException {
		KeHoachThang keHoachThang = new KeHoachThang();
		keHoachThang.setDaXoa(false);
		keHoachThang.setId(keHoachThangData.getId());
		keHoachThang.setDonViChuTriId(keHoachThangData.getDonViChuTriId());
		keHoachThang.setThang(keHoachThangData.getThang());
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		
		serviceNhiemVuThangService.setFixedDaXoaForKeHoachThangId(false, keHoachThang.getId());		
		List<NhiemVuThangData> nhiemVuThangDatas = keHoachThangData.getNhiemVuThangDatas();
		if(Objects.nonNull(nhiemVuThangDatas) && !nhiemVuThangDatas.isEmpty()) {
			saveNhiemVuThangDatas(nhiemVuThangDatas, keHoachThang, null);
		}

		return keHoachThang;
	}
	
	public KeHoachThang update(Long id, KeHoachThangData keHoachThangData) throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		keHoachThang.setDonViChuTriId(keHoachThangData.getDonViChuTriId());
		keHoachThang.setThang(keHoachThangData.getThang());
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		
		serviceNhiemVuThangService.setFixedDaXoaForKeHoachThangId(true, keHoachThang.getId());		
		List<NhiemVuThangData> nhiemVuThangDatas = keHoachThangData.getNhiemVuThangDatas();
		if(Objects.nonNull(nhiemVuThangDatas) && !nhiemVuThangDatas.isEmpty()) {
			saveNhiemVuThangDatas(nhiemVuThangDatas, keHoachThang, null);
		}
		return keHoachThang;
	}
	
	private void saveNhiemVuThangDatas(List<NhiemVuThangData> nhiemVuThangDatas, KeHoachThang keHoachThang, Long nhiemVuThangTruocId) {
		int sapXep = 0;
		for(NhiemVuThangData nhiemVuThangData : nhiemVuThangDatas) {
			sapXep++;
			NhiemVuThang nhiemVuThang = new NhiemVuThang();
			if(Objects.nonNull(nhiemVuThangData.getId())) {
				Optional<NhiemVuThang> optNhiemVuThang = serviceNhiemVuThangService.findById(nhiemVuThangData.getId());
				if(optNhiemVuThang.isPresent()) {
					nhiemVuThang = optNhiemVuThang.get();
				}
			}
			nhiemVuThang.setDaXoa(false);
			nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
			nhiemVuThang.setKeHoachThangId(keHoachThang.getId());
			nhiemVuThang.setCanBoThucHienId(nhiemVuThangData.getCanBoThucHienId());
			nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
			nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
			nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
			nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
			nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangTruocId);
			nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
			nhiemVuThang.setTienDoNhiemVuId(nhiemVuThangData.getTienDoNhiemVuId());
			nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
			
			List<NhiemVuThangData> children = nhiemVuThangData.getChildren();
			if(Objects.nonNull(children) && !children.isEmpty()) {
				saveNhiemVuThangDatas(children, keHoachThang, nhiemVuThang.getId());
			}
		}
	}
	
	@GetMapping(value = { "/{id}" })
	public KeHoachThangData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		keHoachThang.setDaXoa(true);
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		
		return this.convertToKeHoachThangData(keHoachThang);
	}
}
