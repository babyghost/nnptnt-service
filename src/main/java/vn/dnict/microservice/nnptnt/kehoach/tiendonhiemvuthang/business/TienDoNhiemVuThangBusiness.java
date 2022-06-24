package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.utils.Constants;

@Service
public class TienDoNhiemVuThangBusiness {
	@Autowired
	TienDoNhiemVuThangService serviceTienDoNhiemVuThangService;
	
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	FileDinhKemNhiemVuThangService serviceFileDinhKemNhiemVuThangService;
	
	public Page<TienDoNhiemVuThangData> findAll(int page, int size, String sortBy, String sortDir, String tenNguoiCapNhat,
			Integer mucDoHoanThanh, String ketQua) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<TienDoNhiemVuThang> pageTienDoThang = serviceTienDoNhiemVuThangService.findAll(tenNguoiCapNhat, mucDoHoanThanh,
				ketQua, PageRequest.of(page, size, direction, sortBy));
		final Page<TienDoNhiemVuThangData> pageTienDoThangData = pageTienDoThang.map(this::convertToTienDoNhiemVuThangData);
		return pageTienDoThangData;
	}
	
	public TienDoNhiemVuThangData convertToTienDoNhiemVuThangData(TienDoNhiemVuThang tienDoNhiemVuThang) {
		TienDoNhiemVuThangData tienDoNhiemVuThangData = new TienDoNhiemVuThangData();
		tienDoNhiemVuThangData.setId(tienDoNhiemVuThang.getId());
		tienDoNhiemVuThangData.setTenNguoiCapNhat(tienDoNhiemVuThang.getTenNguoiCapNhat());
		tienDoNhiemVuThangData.setMucDoHoanThanh(tienDoNhiemVuThang.getMucDoHoanThanh());
		tienDoNhiemVuThangData.setKetQua(tienDoNhiemVuThang.getKetQua());
		return tienDoNhiemVuThangData;
	}
	
	public TienDoNhiemVuThangData findById(Long id) throws EntityNotFoundException {
		Optional<TienDoNhiemVuThang> optional = serviceTienDoNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuThang.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuThang tienDoNhiemVuThang = optional.get();
		TienDoNhiemVuThangData tienDoNhiemVuThangData = new TienDoNhiemVuThangData();
		tienDoNhiemVuThangData.setId(tienDoNhiemVuThang.getId());
		tienDoNhiemVuThangData.setTenNguoiCapNhat(tienDoNhiemVuThang.getTenNguoiCapNhat());
		tienDoNhiemVuThangData.setMucDoHoanThanh(tienDoNhiemVuThang.getMucDoHoanThanh());
		tienDoNhiemVuThangData.setKetQua(tienDoNhiemVuThang.getKetQua());
		
		if(Objects.nonNull(tienDoNhiemVuThang)) {
			int type = Constants.DINH_KEM_1_FILE;
			Optional<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService
					.findByTienDoNhiemVuThangId(tienDoNhiemVuThang.getId());
			Long fileDinhKemId = null;
			Long objectId = tienDoNhiemVuThang.getId();
			String appCode = TienDoNhiemVuNam.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemNhiemVuThang.get().getFileDinhKemId(),
					appCode, objectId, type);
			tienDoNhiemVuThangData.setFileDinhKem(fileDinhKem);
			tienDoNhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
		}
		return tienDoNhiemVuThangData;
	}
	
	public TienDoNhiemVuThang create(TienDoNhiemVuThangData tienDoNhiemVuThangData) {
		TienDoNhiemVuThang tienDoNhiemVuThang = new TienDoNhiemVuThang();
		tienDoNhiemVuThang.setDaXoa(false);
		tienDoNhiemVuThang.setId(tienDoNhiemVuThangData.getId());
		tienDoNhiemVuThang.setTenNguoiCapNhat(tienDoNhiemVuThangData.getTenNguoiCapNhat());
		tienDoNhiemVuThang.setMucDoHoanThanh(tienDoNhiemVuThangData.getMucDoHoanThanh());
		tienDoNhiemVuThang.setKetQua(tienDoNhiemVuThangData.getKetQua());
		tienDoNhiemVuThang = serviceTienDoNhiemVuThangService.save(tienDoNhiemVuThang);
		
		serviceFileDinhKemNhiemVuThangService.setFixedDaXoaForTienDoNhiemVuThangId(false, tienDoNhiemVuThang.getId());
		List<Long> fileDinhKemIds = tienDoNhiemVuThangData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tienDoNhiemVuThang.getId();
		String appCode = TienDoNhiemVuThang.class.getSimpleName();
		
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang = new FileDinhKemNhiemVuThang();
					List<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThangs = serviceFileDinhKemNhiemVuThangService
							.findByFileDinhKemIdAndTienDoNhiemVuThangId(fileDinhKemId, tienDoNhiemVuThang.getId());
					if(Objects.nonNull(fileDinhKemNhiemVuThangs) && !fileDinhKemNhiemVuThangs.isEmpty()) {
						fileDinhKemNhiemVuThang = fileDinhKemNhiemVuThangs.get(0);
					}
					fileDinhKemNhiemVuThang.setDaXoa(false);
					fileDinhKemNhiemVuThang.setTienDoNhiemVuThangId(tienDoNhiemVuThang.getId());
					fileDinhKemNhiemVuThang.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService.save(fileDinhKemNhiemVuThang);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
				}
				
				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		return tienDoNhiemVuThang;
	}
	
	public TienDoNhiemVuThang update(Long id, TienDoNhiemVuThangData tienDoNhiemVuThangData) throws EntityNotFoundException {
		Optional<TienDoNhiemVuThang> optional = serviceTienDoNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuThang.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuThang tienDoNhiemVuThang = optional.get();
		tienDoNhiemVuThang.setTenNguoiCapNhat(tienDoNhiemVuThangData.getTenNguoiCapNhat());
		tienDoNhiemVuThang.setMucDoHoanThanh(tienDoNhiemVuThangData.getMucDoHoanThanh());
		tienDoNhiemVuThang.setKetQua(tienDoNhiemVuThangData.getKetQua());
		tienDoNhiemVuThang = serviceTienDoNhiemVuThangService.save(tienDoNhiemVuThang);
		
		serviceFileDinhKemNhiemVuThangService.setFixedDaXoaForTienDoNhiemVuThangId(true, tienDoNhiemVuThang.getId());
		List<Long> fileDinhKemIds = tienDoNhiemVuThangData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tienDoNhiemVuThang.getId();
		String appCode = TienDoNhiemVuThang.class.getSimpleName();
		
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang = new FileDinhKemNhiemVuThang();
					List<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThangs = serviceFileDinhKemNhiemVuThangService
							.findByFileDinhKemIdAndTienDoNhiemVuThangId(fileDinhKemId, tienDoNhiemVuThang.getId());
					if(Objects.nonNull(fileDinhKemNhiemVuThangs) && !fileDinhKemNhiemVuThangs.isEmpty()) {
						fileDinhKemNhiemVuThang = fileDinhKemNhiemVuThangs.get(0);
					}
					fileDinhKemNhiemVuThang.setDaXoa(false);
					fileDinhKemNhiemVuThang.setTienDoNhiemVuThangId(tienDoNhiemVuThang.getId());
					fileDinhKemNhiemVuThang.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService.save(fileDinhKemNhiemVuThang);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
				}
				
				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		return tienDoNhiemVuThang;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public TienDoNhiemVuThangData delete(Long id) throws EntityNotFoundException {
		Optional<TienDoNhiemVuThang> optional = serviceTienDoNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuThang.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuThang tienDoNhiemVuThang = optional.get();
		tienDoNhiemVuThang.setDaXoa(true);
		tienDoNhiemVuThang = serviceTienDoNhiemVuThangService.save(tienDoNhiemVuThang);
		return this.convertToTienDoNhiemVuThangData(tienDoNhiemVuThang);	
	}
}
