package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business;

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
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.dao.model.FileList;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThoiGianTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.model.FileDinhKemKeHoach;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.FileDinhKemKeHoachService;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;

import vn.dnict.microservice.utils.Constants;
@Slf4j
@Service
public class KeHoachTiemPhongBusiness {
	@Autowired
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	@Autowired
	ThoiGianTiemPhongService serviceThoiGianTiemPhongService;
	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;

	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	@Autowired
	FileDinhKemKeHoachService serviceFileDinhKemKeHoachService;
	
	
	public Page<KeHoachTiemPhongData> findAll(int page, int size, String sortBy, String sortDir,String noiDung, String soKeHoach, String tenKeHoach, LocalDate ngayBanHanhTuNgay, LocalDate ngayBanHanhDenNgay,LocalDate ngayDuKienTuNgay,LocalDate ngayDuKienDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
	    final	Page<KeHoachTiemPhong> pageKeHoachTiemPhong = serviceKeHoachTiemPhongService.findAll( noiDung, soKeHoach, tenKeHoach, ngayBanHanhTuNgay, ngayBanHanhDenNgay, ngayDuKienTuNgay, ngayDuKienDenNgay,
				PageRequest.of(page, size, direction, sortBy));
	    final   Page<KeHoachTiemPhongData> pageKeHoachTiemPhongData = pageKeHoachTiemPhong.map(this::convertToKeHoachTiemPhongData);
		return pageKeHoachTiemPhongData;
	}
	public KeHoachTiemPhongData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong keHoachTiemPhong = optional.get();
		KeHoachTiemPhongData keHoachTiemPhongData = new KeHoachTiemPhongData();
		keHoachTiemPhongData.setId(keHoachTiemPhong.getId());
		keHoachTiemPhongData.setTenKeHoach(keHoachTiemPhong.getTenKeHoach());
		keHoachTiemPhongData.setSoKeHoach(keHoachTiemPhong.getSoKeHoach());
		keHoachTiemPhongData.setNoiDung(keHoachTiemPhong.getNoiDung());
		keHoachTiemPhongData.setNgayBanHanh(keHoachTiemPhong.getNgayBanHanh());
		keHoachTiemPhongData.setNgayDuKienTuNgay(keHoachTiemPhong.getNgayDuKienTuNgay());
		keHoachTiemPhongData.setNgayDuKienDenNgay(keHoachTiemPhong.getNgayDuKienDenNgay());
		List<ThoiGianTiemPhongData> thoiGianTiemPhongDatas = new ArrayList<>();
		List<ThoiGianTiemPhong> thoiGianTiemPhongs = serviceThoiGianTiemPhongService.findByKeHoachTiemPhongIdAndDaXoa(keHoachTiemPhong.getId(), false);
		if (Objects.nonNull(thoiGianTiemPhongs) && !thoiGianTiemPhongs.isEmpty()) {
			for (ThoiGianTiemPhong thoiGianTiemPhong : thoiGianTiemPhongs) {
				ThoiGianTiemPhongData thoiGianTiemPhongData = new ThoiGianTiemPhongData();
				thoiGianTiemPhongData.setId(thoiGianTiemPhong.getId());
				thoiGianTiemPhongData.setDiaDiem(thoiGianTiemPhong.getDiaDiem());
				thoiGianTiemPhongData.setThoiGianTiemDenNgay(thoiGianTiemPhong.getThoiGianTiemDenNgay());
				thoiGianTiemPhongData.setThoiGianTiemTuNgay(thoiGianTiemPhong.getThoiGianTiemTuNgay());
				thoiGianTiemPhongData.setThoiGianTiemTuNgay(thoiGianTiemPhong.getThoiGianTiemTuNgay());
				thoiGianTiemPhongData.setThoiGianTiemDenNgay(thoiGianTiemPhong.getThoiGianTiemDenNgay());
				thoiGianTiemPhongData.setPhuongXaId(thoiGianTiemPhong.getPhuongXaId());
				thoiGianTiemPhongData.setQuanHuyenId(thoiGianTiemPhong.getQuanHuyenId());
				if(thoiGianTiemPhong.getQuanHuyenId() != null && thoiGianTiemPhong.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQH = serviceDmQuanHuyenService.findById(thoiGianTiemPhong.getQuanHuyenId());
					if (optionalQH.isPresent()) {
						thoiGianTiemPhongData.setQuanHuyenTen(optionalQH.get().getTen());
					}
				}
				if (thoiGianTiemPhong.getPhuongXaId() != null && thoiGianTiemPhong.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPX = serviceDmPhuongXaService.findById(thoiGianTiemPhong.getPhuongXaId());
					if (optionalPX.isPresent()) {
						thoiGianTiemPhongData.setPhuongXaTen(optionalPX.get().getTen());
					}
				}
				thoiGianTiemPhongDatas.add(thoiGianTiemPhongData);
				System.out.println(thoiGianTiemPhongs);
			}
		}
		if (Objects.nonNull(keHoachTiemPhong)) {
			int type = Constants.DINH_KEM_1_FILE;
			Optional<FileDinhKemKeHoach> fileDinhKemKeHoach = serviceFileDinhKemKeHoachService.findBykeHoachTiemPhongId(keHoachTiemPhong.getId());
			Long fileDinhKemId = null;
			Long objectId = keHoachTiemPhong.getId();
			String appCode = KeHoachTiemPhong.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemKeHoach.get().getFileDinhKemId(), appCode, objectId, type);
			keHoachTiemPhongData.setFileDinhKem(fileDinhKem);
			keHoachTiemPhongData.setFileDinhKemIds(fileDinhKem.getIds());
			System.out.println(objectId + "---+---"+fileDinhKem + appCode + type + fileDinhKemId);
		}
		keHoachTiemPhongData.setThoiGianTiemPhongDatas(thoiGianTiemPhongDatas);	
		return keHoachTiemPhongData;
	}



	
	public KeHoachTiemPhong create(KeHoachTiemPhongData KeHoachTiemPhongData) {
		KeHoachTiemPhong KeHoachTiemPhong = new KeHoachTiemPhong();
		KeHoachTiemPhong.setDaXoa(false);
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongData.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongData.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongData.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongData.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongData.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongData.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		
		
		serviceThoiGianTiemPhongService.setFixedDaXoaForKeHoachTiemPhongId(false, KeHoachTiemPhong.getId());
		List<ThoiGianTiemPhongData> thoiGianTiemPhongDatas = KeHoachTiemPhongData.getThoiGianTiemPhongDatas();
		if (Objects.nonNull(thoiGianTiemPhongDatas) && !thoiGianTiemPhongDatas.isEmpty()) {
			for (ThoiGianTiemPhongData thoiGianTiemPhongData : thoiGianTiemPhongDatas) {
				ThoiGianTiemPhong thoiGianTiemPhong = new ThoiGianTiemPhong();
				if (Objects.nonNull(thoiGianTiemPhong.getId())) {
					Optional<ThoiGianTiemPhong> optionalThoiGianTiemPhong = serviceThoiGianTiemPhongService
							.findById(thoiGianTiemPhongData.getId());
					if (optionalThoiGianTiemPhong.isPresent()) {
						thoiGianTiemPhong = optionalThoiGianTiemPhong.get();
					}
				}
				thoiGianTiemPhong.setId(thoiGianTiemPhongData.getId());
				thoiGianTiemPhong.setKeHoachTiemPhongId(KeHoachTiemPhong.getId());
				thoiGianTiemPhong.setDiaDiem(thoiGianTiemPhongData.getDiaDiem());
				thoiGianTiemPhong.setThoiGianTiemTuNgay(thoiGianTiemPhongData.getThoiGianTiemTuNgay());
				thoiGianTiemPhong.setThoiGianTiemDenNgay(thoiGianTiemPhongData.getThoiGianTiemDenNgay());
				thoiGianTiemPhong.setQuanHuyenId(thoiGianTiemPhongData.getQuanHuyenId());	
				thoiGianTiemPhong.setPhuongXaId(thoiGianTiemPhongData.getPhuongXaId());
				serviceThoiGianTiemPhongService.save(thoiGianTiemPhong);
			}
		}
		
		serviceFileDinhKemKeHoachService.setFixedDaXoaForKeHoachTiemPhongId(false, KeHoachTiemPhong.getId());
		List<Long> fileDinhKemIds = KeHoachTiemPhongData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = KeHoachTiemPhong.getId();
		String appCode = KeHoachTiemPhong.class.getSimpleName();

		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemKeHoach   fileDinhKemKeHoach = new FileDinhKemKeHoach();
					List<FileDinhKemKeHoach> fileDinhKemKeHoachs = serviceFileDinhKemKeHoachService
							.findByDinhKemFileIdAndKeHoachTiemPhongId(fileDinhKemId, KeHoachTiemPhong.getId());
					if (Objects.nonNull(fileDinhKemKeHoachs) && !fileDinhKemKeHoachs.isEmpty()) {
						fileDinhKemKeHoach = fileDinhKemKeHoachs.get(0);
					}
					fileDinhKemKeHoach.setDaXoa(false);
					fileDinhKemKeHoach.setKeHoachTiemPhongId(KeHoachTiemPhong.getId());
					fileDinhKemKeHoach.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemKeHoach = serviceFileDinhKemKeHoachService.save(fileDinhKemKeHoach);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		
		return KeHoachTiemPhong;
	}

	public KeHoachTiemPhong update(Long id, KeHoachTiemPhongData KeHoachTiemPhongData) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongData.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongData.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongData.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongData.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongData.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongData.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		
		serviceThoiGianTiemPhongService.setFixedDaXoaForKeHoachTiemPhongId(false, KeHoachTiemPhong.getId());
		List<ThoiGianTiemPhongData> thoiGianTiemPhongDatas = KeHoachTiemPhongData.getThoiGianTiemPhongDatas();
		if (Objects.nonNull(thoiGianTiemPhongDatas) && !thoiGianTiemPhongDatas.isEmpty()) {
			for (ThoiGianTiemPhongData thoiGianTiemPhongData : thoiGianTiemPhongDatas) {
				ThoiGianTiemPhong thoiGianTiemPhong = new ThoiGianTiemPhong();
				if (Objects.nonNull(thoiGianTiemPhong.getId())) {
					Optional<ThoiGianTiemPhong> optionalThoiGianTiemPhong = serviceThoiGianTiemPhongService
							.findById(thoiGianTiemPhongData.getId());
					if (optionalThoiGianTiemPhong.isPresent()) {
						thoiGianTiemPhong = optionalThoiGianTiemPhong.get();
					}
				}
				thoiGianTiemPhong.setId(thoiGianTiemPhongData.getId());
				thoiGianTiemPhong.setKeHoachTiemPhongId(KeHoachTiemPhong.getId());
				thoiGianTiemPhong.setDiaDiem(thoiGianTiemPhongData.getDiaDiem());
				thoiGianTiemPhong.setThoiGianTiemTuNgay(thoiGianTiemPhongData.getThoiGianTiemTuNgay());
				thoiGianTiemPhong.setThoiGianTiemDenNgay(thoiGianTiemPhongData.getThoiGianTiemDenNgay());
				thoiGianTiemPhong.setQuanHuyenId(thoiGianTiemPhongData.getQuanHuyenId());	
				thoiGianTiemPhong.setPhuongXaId(thoiGianTiemPhongData.getPhuongXaId());
				serviceThoiGianTiemPhongService.save(thoiGianTiemPhong);
			}
		}
		serviceFileDinhKemKeHoachService.setFixedDaXoaForKeHoachTiemPhongId(false, KeHoachTiemPhong.getId());
		List<Long> fileDinhKemIds = KeHoachTiemPhongData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = KeHoachTiemPhong.getId();
		String appCode = KeHoachTiemPhong.class.getSimpleName();

		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemKeHoach   fileDinhKemKeHoach = new FileDinhKemKeHoach();
					List<FileDinhKemKeHoach> fileDinhKemKeHoachs = serviceFileDinhKemKeHoachService
							.findByDinhKemFileIdAndKeHoachTiemPhongId(fileDinhKemId, KeHoachTiemPhong.getId());
					if (Objects.nonNull(fileDinhKemKeHoachs) && !fileDinhKemKeHoachs.isEmpty()) {
						fileDinhKemKeHoach = fileDinhKemKeHoachs.get(0);
					}
					fileDinhKemKeHoach.setDaXoa(false);
					fileDinhKemKeHoach.setKeHoachTiemPhongId(KeHoachTiemPhong.getId());
					fileDinhKemKeHoach.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemKeHoach = serviceFileDinhKemKeHoachService.save(fileDinhKemKeHoach);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
					
					System.out.println("++++++++++++");
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					System.out.println("fss");
					break;
				
				}
			}
		}
		System.out.println(fileDinhKemIds);
		return KeHoachTiemPhong;
	}

	@DeleteMapping(value = { "/{id}" })
	public KeHoachTiemPhongData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setDaXoa(true);
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return this.convertToKeHoachTiemPhongData(KeHoachTiemPhong);
	}
	
	private KeHoachTiemPhongData convertToKeHoachTiemPhongData(KeHoachTiemPhong keHoachTiemPhong) {
		KeHoachTiemPhongData keHoachTiemPhongData = new KeHoachTiemPhongData();
		keHoachTiemPhongData.setId(keHoachTiemPhong.getId());
		keHoachTiemPhongData.setTenKeHoach(keHoachTiemPhong.getTenKeHoach());
		keHoachTiemPhongData.setSoKeHoach(keHoachTiemPhong.getSoKeHoach());
		keHoachTiemPhongData.setNoiDung(keHoachTiemPhong.getNoiDung());
		keHoachTiemPhongData.setNgayBanHanh(keHoachTiemPhong.getNgayBanHanh());
		keHoachTiemPhongData.setNgayDuKienTuNgay(keHoachTiemPhong.getNgayDuKienTuNgay());
		keHoachTiemPhongData.setNgayDuKienDenNgay(keHoachTiemPhong.getNgayDuKienDenNgay());
		List<ThoiGianTiemPhongData> thoiGianTiemPhongDatas = new ArrayList<>();
		List<ThoiGianTiemPhong> thoiGianTiemPhongs = serviceThoiGianTiemPhongService.findByKeHoachTiemPhongIdAndDaXoa(keHoachTiemPhong.getId(), false);
		if (Objects.nonNull(thoiGianTiemPhongs) && !thoiGianTiemPhongs.isEmpty()) {
			for (ThoiGianTiemPhong thoiGianTiemPhong : thoiGianTiemPhongs) {
				ThoiGianTiemPhongData thoiGianTiemPhongData = new ThoiGianTiemPhongData();
				thoiGianTiemPhongData.setId(thoiGianTiemPhong.getId());
				thoiGianTiemPhongData.setDiaDiem(thoiGianTiemPhong.getDiaDiem());
				thoiGianTiemPhongData.setThoiGianTiemDenNgay(thoiGianTiemPhong.getThoiGianTiemDenNgay());
				thoiGianTiemPhongData.setThoiGianTiemTuNgay(thoiGianTiemPhong.getThoiGianTiemTuNgay());
				thoiGianTiemPhongData.setThoiGianTiemTuNgay(thoiGianTiemPhong.getThoiGianTiemTuNgay());
				thoiGianTiemPhongData.setThoiGianTiemDenNgay(thoiGianTiemPhong.getThoiGianTiemDenNgay());
				thoiGianTiemPhongData.setQuanHuyenId(thoiGianTiemPhong.getQuanHuyenId());
				thoiGianTiemPhongData.setPhuongXaId(thoiGianTiemPhong.getPhuongXaId());
				if (thoiGianTiemPhong.getQuanHuyenId() != null && thoiGianTiemPhong.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQH = serviceDmQuanHuyenService.findById(thoiGianTiemPhong.getQuanHuyenId());
					if (optionalQH.isPresent()) {
						thoiGianTiemPhongData.setQuanHuyenTen(optionalQH.get().getTen());
					}
				}
				if (thoiGianTiemPhong.getPhuongXaId() != null && thoiGianTiemPhong.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPX = serviceDmPhuongXaService.findById(thoiGianTiemPhong.getPhuongXaId());
					if (optionalPX.isPresent()) {
						thoiGianTiemPhongData.setPhuongXaTen(optionalPX.get().getTen());
					}
				}
				thoiGianTiemPhongDatas.add(thoiGianTiemPhongData);
				
			}
		}

		keHoachTiemPhongData.setThoiGianTiemPhongDatas(thoiGianTiemPhongDatas);	
		return keHoachTiemPhongData;
	}
}