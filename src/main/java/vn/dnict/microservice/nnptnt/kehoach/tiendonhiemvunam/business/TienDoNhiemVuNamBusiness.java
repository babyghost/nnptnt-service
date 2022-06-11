package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.business;

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
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
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

@Slf4j
@Service
public class TienDoNhiemVuNamBusiness {
	@Autowired
	TienDoNhiemVuNamService serviceTienDoNhiemVuNamService;
	
	@Autowired
	NhiemVuNamService serviceNhiemVuNamService;
	
	@Autowired
	KeHoachNamService serviceKeHoachNamService;
	
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	FileDinhKemNhiemVuNamService serviceFileDinhKemNhiemVuNamService;
	
	public Page<TienDoNhiemVuNamData> findAll(int page, int size, String sortBy, String sortDir, LocalDate ngayBaoCao, Boolean tinhTrang,
			Integer mucDonHoanThanh) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<TienDoNhiemVuNam> pageTienDoNhiemVuNam = serviceTienDoNhiemVuNamService.findAll(ngayBaoCao, tinhTrang, mucDonHoanThanh,
				PageRequest.of(page, size, direction, sortBy));
		final Page<TienDoNhiemVuNamData> pageTienDoNhiemVuNamData = pageTienDoNhiemVuNam.map(this::convertToTienDoNhiemVuNamData);
		return pageTienDoNhiemVuNamData;
	}
	
	private TienDoNhiemVuNamData convertToTienDoNhiemVuNamData(TienDoNhiemVuNam tienDoNhiemVuNam) {
		TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
		tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
		tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
		if(tienDoNhiemVuNam.getNhiemVuNamId() != null && tienDoNhiemVuNam.getNhiemVuNamId() > 0) {
			Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService.findById(tienDoNhiemVuNam.getNhiemVuNamId());
			if(optionalNhiemVuNam.isPresent()) {
				tienDoNhiemVuNamData.setNhiemVuNamTen(optionalNhiemVuNam.get().getTenNhiemVu());
				tienDoNhiemVuNamData.setNvNamNhiemVuChaId(optionalNhiemVuNam.get().getNhiemVuChaId());
				tienDoNhiemVuNamData.setNvNamDonViPhoiHop(optionalNhiemVuNam.get().getDonViPhoiHop());
				tienDoNhiemVuNamData.setNvNamTuNgay(optionalNhiemVuNam.get().getTuNgay());
				tienDoNhiemVuNamData.setNvNamDenNgay(optionalNhiemVuNam.get().getDenNgay());
				tienDoNhiemVuNamData.setNvNamGhiChu(optionalNhiemVuNam.get().getGhiChu());
			}
		}
		tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
		tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
		tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
		tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());
		return tienDoNhiemVuNamData;
	}
	
	public TienDoNhiemVuNamData findById(Long id) throws EntityNotFoundException {
		Optional<TienDoNhiemVuNam> optional = serviceTienDoNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuNam.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuNam tienDoNhiemVuNam = optional.get();
		TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
		tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
		tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
		if(tienDoNhiemVuNam.getNhiemVuNamId() != null && tienDoNhiemVuNam.getNhiemVuNamId() > 0) {
			Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService.findById(tienDoNhiemVuNam.getNhiemVuNamId());
			if(optionalNhiemVuNam.isPresent()) {
				tienDoNhiemVuNamData.setNhiemVuNamTen(optionalNhiemVuNam.get().getTenNhiemVu());
				tienDoNhiemVuNamData.setNvNamNhiemVuChaId(optionalNhiemVuNam.get().getNhiemVuChaId());
				tienDoNhiemVuNamData.setNvNamDonViPhoiHop(optionalNhiemVuNam.get().getDonViPhoiHop());
				tienDoNhiemVuNamData.setNvNamTuNgay(optionalNhiemVuNam.get().getTuNgay());
				tienDoNhiemVuNamData.setNvNamDenNgay(optionalNhiemVuNam.get().getDenNgay());
				tienDoNhiemVuNamData.setNvNamGhiChu(optionalNhiemVuNam.get().getGhiChu());
			}
		}
		tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
		tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
		tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
		tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());

		if (Objects.nonNull(tienDoNhiemVuNam)) {
			int type = Constants.DINH_KEM_1_FILE;
			Optional<FileDinhKemNhiemVuNam> fileDinhKemNhiemVuNam = serviceFileDinhKemNhiemVuNamService.findByTienDoNhiemVuNamId(tienDoNhiemVuNam.getId());
			Long fileDinhKemId = null;
			Long objectId = tienDoNhiemVuNam.getId();
			String appCode = TienDoNhiemVuNam.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemNhiemVuNam.get().getFileDinhKemId(), appCode, objectId, type);
			tienDoNhiemVuNamData.setFileDinhKem(fileDinhKem);
			tienDoNhiemVuNamData.setFileDinhKemIds(fileDinhKem.getIds());
			System.out.println(fileDinhKemNhiemVuNam.get().getFileDinhKemId() + appCode + objectId + type + "++++++++++");
		}
	
		return tienDoNhiemVuNamData;
	}
	
	public NhiemVuNamData findByNhiemVuNamId(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService.findById(id);
		if(!optionalNhiemVuNam.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNamData.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optionalNhiemVuNam.get();
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(!optionalKeHoachNam.isPresent()) {
				nhiemVuNamData.setKeHoachNamTen(optionalKeHoachNam.get().getTenKeHoach());
			}
		}
		nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		
		return nhiemVuNamData;
	}
	
	public TienDoNhiemVuNam create(TienDoNhiemVuNamData tienDoNhiemVuNamData) {
		TienDoNhiemVuNam tienDoNhiemVuNam = new TienDoNhiemVuNam();
		tienDoNhiemVuNam.setDaXoa(false);
		tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
		tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
		tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
		tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
		tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
		tienDoNhiemVuNam = serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
		
		serviceFileDinhKemNhiemVuNamService.setFixedDaXoaForTienDoNhiemVuNamId(false, tienDoNhiemVuNam.getId());
		List<Long> fileDinhKemIds = tienDoNhiemVuNamData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tienDoNhiemVuNam.getId();
		String appCode = TienDoNhiemVuNam.class.getSimpleName();

		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemNhiemVuNam   fileDinhNhiemVuNam = new FileDinhKemNhiemVuNam();
					List<FileDinhKemNhiemVuNam> fileDinhKemNhiemVuNams = serviceFileDinhKemNhiemVuNamService
							.findByFileDinhKemIdAndTienDoNhiemVuNamId(fileDinhKemId, tienDoNhiemVuNam.getId());
					if (Objects.nonNull(fileDinhKemNhiemVuNams) && !fileDinhKemNhiemVuNams.isEmpty()) {
						fileDinhNhiemVuNam = fileDinhKemNhiemVuNams.get(0);
					}
					fileDinhNhiemVuNam.setDaXoa(false);
					fileDinhNhiemVuNam.setTienDoNhiemVuNamId(tienDoNhiemVuNam.getId());
					fileDinhNhiemVuNam.setFileDinhKemId(coreAttachment.getId());
					fileDinhNhiemVuNam = serviceFileDinhKemNhiemVuNamService.save(fileDinhNhiemVuNam);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}

		return tienDoNhiemVuNam;
	}
	
	public TienDoNhiemVuNam update(Long id, TienDoNhiemVuNamData tienDoNhiemVuNamData) throws EntityNotFoundException {
		Optional<TienDoNhiemVuNam> optionalTienDoNam = serviceTienDoNhiemVuNamService.findById(id);
		if (!optionalTienDoNam.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuNam.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuNam tienDoNhiemVuNam = optionalTienDoNam.get();
		tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
		tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
		tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
		tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
		tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
		tienDoNhiemVuNam = serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
		
		serviceFileDinhKemNhiemVuNamService.setFixedDaXoaForTienDoNhiemVuNamId(false, tienDoNhiemVuNam.getId());
		List<Long> fileDinhKemIds = tienDoNhiemVuNamData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tienDoNhiemVuNam.getId();
		String appCode = TienDoNhiemVuNam.class.getSimpleName();

		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemNhiemVuNam   fileDinhKemNhiemVuNam = new FileDinhKemNhiemVuNam();
					List<FileDinhKemNhiemVuNam> fileDinhKemNhiemVuNams = serviceFileDinhKemNhiemVuNamService
							.findByFileDinhKemIdAndTienDoNhiemVuNamId(fileDinhKemId, tienDoNhiemVuNam.getId());
					if (Objects.nonNull(fileDinhKemNhiemVuNams) && !fileDinhKemNhiemVuNams.isEmpty()) {
						fileDinhKemNhiemVuNam = fileDinhKemNhiemVuNams.get(0);
					}
					fileDinhKemNhiemVuNam.setDaXoa(false);
					fileDinhKemNhiemVuNam.setTienDoNhiemVuNamId(tienDoNhiemVuNam.getId());
					fileDinhKemNhiemVuNam.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemNhiemVuNam = serviceFileDinhKemNhiemVuNamService.save(fileDinhKemNhiemVuNam);
					
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					System.out.println("fss");
					break;
				
				}
			}
		}
		
		return tienDoNhiemVuNam;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public TienDoNhiemVuNamData delete(Long id) throws EntityNotFoundException {
		Optional<TienDoNhiemVuNam> optionalTienDoNhiemVuNam = serviceTienDoNhiemVuNamService.findById(id);
		if (!optionalTienDoNhiemVuNam.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuNam.class, "id", String.valueOf(id));
		}
		TienDoNhiemVuNam tienDoNhiemVuNam = optionalTienDoNhiemVuNam.get();
		tienDoNhiemVuNam.setDaXoa(true);
		tienDoNhiemVuNam = serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
		return this.convertToTienDoNhiemVuNamData(tienDoNhiemVuNam);
	}
}
