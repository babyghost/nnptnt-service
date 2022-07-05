package vn.dnict.microservice.nnptnt.ocop.sanpham.bussiness;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangService;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomService;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.DmPhanHangService;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.DmPhanNhomService;
import vn.dnict.microservice.nnptnt.ocop.chuthe.bussiness.view.MyExcelViewChuThe;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.DoanhNghiepService;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.DanhGiaSanPhamService;
import vn.dnict.microservice.nnptnt.ocop.data.DoanhNghiepData;
import vn.dnict.microservice.nnptnt.ocop.data.SanPhamData;
import vn.dnict.microservice.nnptnt.ocop.sanpham.bussiness.view.MyExcelViewSanPham;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.SanPhamService;
import vn.dnict.microservice.utils.Constants;
@Slf4j
@Service
public class SanPhamBussiness {
	@Autowired
	SanPhamService serviceSanPhamService;
	
	@Autowired
	DmNganhHangService serviceNganhHangService;
	
	@Autowired
	DmPhanNhomService servicePhanNhomService;
	
	@Autowired
	DmNhomService serviceNhomService;
	
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	private DoanhNghiepService serviceDoanhNghiepService;
	
	@Autowired
	DanhGiaSanPhamService serviceDanhGiaSanPhamService;
	
	@Autowired
	DmPhanHangService servicePhanHangService;
	
	public Page<SanPhamData> findAll(int page, int size, String sortBy, String sortDir,String ten, String chuThe, Long nganhHangId, Long phanNhomId, Long phanHangId,
			Integer trangThai, String quyetDinh, Long nhomId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<SanPham> pageSanPham= serviceSanPhamService.findAll(ten, chuThe, nganhHangId, phanNhomId, phanHangId, trangThai, quyetDinh, nhomId, 
				PageRequest.of(page, size, direction, sortBy));
		final Page<SanPhamData> pageSanPhamData = pageSanPham.map(this::convertToSanPhamData);
		return pageSanPhamData;
	}
	
	
	public SanPhamData findById(Long id) throws EntityNotFoundException {
		Optional<SanPham> optional = serviceSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(SanPham.class, "id", String.valueOf(id));
		}
		return this.convertToSanPhamData(optional.get());
	}
	
	
	
	public SanPhamData convertToSanPhamData(SanPham sanPham) {
		
		SanPhamData sanPhamData = new SanPhamData();
		sanPhamData.setId(sanPham.getId());
		sanPhamData.setTen(sanPham.getTen());
		sanPhamData.setMoTa(sanPham.getMoTa());
		sanPhamData.setTrangThai(sanPham.getTrangThai());	
		sanPhamData.setQuyetDinh(sanPham.getQuyetDinh());
		
		if (Objects.nonNull(sanPham.getNganhHangId())){
			Optional<DmNganhHang> optNganhHang = serviceNganhHangService.findById(sanPham.getNganhHangId());
			if (optNganhHang.isPresent()) {
			sanPhamData.setNganhHangId(optNganhHang.get().getId());
			sanPhamData.setNganhHangTen(optNganhHang.get().getTen());	
			}
		}
		if (Objects.nonNull(sanPham.getDoanhNghiepId())){
			Optional<DoanhNghiep> optDoanhNghiep = serviceDoanhNghiepService.findById(sanPham.getDoanhNghiepId());
			if (optDoanhNghiep.isPresent()) {
			sanPhamData.setDoanhNghiepId(optDoanhNghiep.get().getId());
			sanPhamData.setDoanhNghiepTen(optDoanhNghiep.get().getTen());	
			}
		}
		if (Objects.nonNull(sanPham.getPhanNhomId())){
			Optional<DmPhanNhom> optPhanNhom = servicePhanNhomService.findById(sanPham.getPhanNhomId());
			if (optPhanNhom.isPresent()) {
			sanPhamData.setPhanNhomId(optPhanNhom.get().getId());
			sanPhamData.setPhanNhomTen(optPhanNhom.get().getTen());	
		
			}
		}
		if (Objects.nonNull(sanPham.getNhomId())){
			Optional<DmNhom> optNhom = serviceNhomService.findById(sanPham.getNhomId());
			if (optNhom.isPresent()) {
			sanPhamData.setNhomId(optNhom.get().getId());
			sanPhamData.setNhomTen(optNhom.get().getTen());	
				}
			}
		
		Long phanHangId = serviceDanhGiaSanPhamService.getPhanHangBySanPhamId(sanPham.getId());
		System.out.println(phanHangId+"---------------------------------");
		if (Objects.nonNull(phanHangId)) {
			Optional<DmPhanHang> optPhanHang = servicePhanHangService.findById(phanHangId);
			if(optPhanHang.isPresent()) {
				sanPhamData.setPhanHangId(optPhanHang.get().getId());
				sanPhamData.setPhanHangTen(optPhanHang.get().getTen());
			}
		}
		
		if (Objects.nonNull(sanPham)) {
			int type = Constants.DINH_KEM_1_FILE;			
			Long fileDinhKemId = null;
			Long objectId = sanPham.getId();
			String appCode = SanPham.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( sanPham.getFileDinhKemId(), appCode, objectId, type);
			sanPhamData.setListFileDinhKem(fileDinhKem.getFileLists());
			sanPhamData.setFileDinhKemIds(fileDinhKem.getIds());
		}
		return sanPhamData;
	}
	
	
	public SanPham create(SanPhamData sanPhamData) {
		SanPham sanPham = new SanPham();
		
		sanPham.setDaXoa(false);
		sanPham.setTen(sanPhamData.getTen());
		sanPham.setMoTa(sanPhamData.getMoTa());
		sanPham.setTrangThai(sanPhamData.getTrangThai());
		sanPham.setDoanhNghiepId(sanPhamData.getDoanhNghiepId());
		sanPham.setNganhHangId(sanPhamData.getNganhHangId());
		sanPham.setQuyetDinh(sanPhamData.getQuyetDinh());
		sanPham.setPhanNhomId(sanPhamData.getPhanNhomId());
		sanPham.setNhomId(sanPhamData.getNhomId());
		sanPham= serviceSanPhamService.save(sanPham);
		List<Long> fileDinhKemIds = sanPhamData.getFileDinhKemIds();
	int	type = Constants.DINH_KEM_1_FILE;
	long	objectId = sanPham.getId();
	String	appCode = SanPham.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					sanPham.setFileDinhKemId(fileDinhKemId);
					serviceSanPhamService.save(sanPham);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		
		
		return sanPham;
		
	}
	
	public SanPham update(Long id, SanPhamData sanPhamData) throws EntityNotFoundException {
		Optional<SanPham> optional = serviceSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(SanPham.class, "id", String.valueOf(id));
		}
		SanPham sanPham = optional.get();
		sanPham.setDaXoa(false);
		sanPham.setTen(sanPhamData.getTen());
		sanPham.setMoTa(sanPhamData.getMoTa());
		sanPham.setTrangThai(sanPhamData.getTrangThai());
		sanPham.setDoanhNghiepId(sanPhamData.getDoanhNghiepId());
		sanPham.setNganhHangId(sanPhamData.getNganhHangId());
		sanPham.setQuyetDinh(sanPhamData.getQuyetDinh());
		sanPham.setPhanNhomId(sanPhamData.getPhanNhomId());
		sanPham.setNhomId(sanPhamData.getNhomId());

		List<Long> fileDinhKemIds = sanPhamData.getFileDinhKemIds();
		sanPham = serviceSanPhamService.save(sanPham);
	int	type = Constants.DINH_KEM_1_FILE;
	long	objectId = sanPham.getId();
	String	appCode = SanPham.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					sanPham.setFileDinhKemId(fileDinhKemId);
					serviceSanPhamService.save(sanPham);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		
		
		
		return sanPham;
		
	}
	public SanPham delete(Long id) throws EntityNotFoundException {
		Optional<SanPham> optional = serviceSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(SanPham.class, "id", String.valueOf(id));
		}
		SanPham sanPham = optional.get();
		sanPham.setDaXoa(true);
		sanPham = serviceSanPhamService.save(sanPham);
		return sanPham;
	}
	
	
	public ModelAndView exportExcelChuThe(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,String ten, String chuThe, Long nganhHangId, Long phanNhomId, Long phanHangId,
			Integer trangThai, String quyetDinh, Long nhomId) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		Map<String, Object> model = new HashMap<String, Object>();

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<SanPham> pageSanPham = serviceSanPhamService.findAll(ten, chuThe, nganhHangId, phanNhomId, phanHangId, trangThai, quyetDinh, nhomId,  PageRequest.of(page, size, direction, sortBy));
		Page<SanPhamData> pageSanPhamData = pageSanPham
				.map(this::convertToSanPhamData);

		List<SanPhamData> SanPhamDatas = new ArrayList<>(
				pageSanPhamData.getContent());

	System.out.println(SanPhamDatas+"----------//"+ response);

		// All the remaining employees
		while (pageSanPham.hasNext()) {
			Page<SanPham> nextPageOfEmployees = serviceSanPhamService.findAll(ten, chuThe, nganhHangId, phanNhomId, phanHangId, trangThai, quyetDinh, nhomId, 
					pageSanPham.nextPageable());
			Page<SanPhamData> nextPageOfSanPhamData = nextPageOfEmployees
					.map(this::convertToSanPhamData);
			if (Objects.nonNull(nextPageOfSanPhamData.getContent())) {
				SanPhamDatas.addAll(nextPageOfSanPhamData.getContent());
			}
			// update the page reference to the current page
			pageSanPham = nextPageOfEmployees;
			System.out.println(pageSanPham+"++++++++++++");
		}

		model.put("SanPhamDatas", SanPhamDatas);
		
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKeSanPhamOCOP "+formattedString+".xls");
		return new ModelAndView(new MyExcelViewSanPham(), model);
		
	}
}
