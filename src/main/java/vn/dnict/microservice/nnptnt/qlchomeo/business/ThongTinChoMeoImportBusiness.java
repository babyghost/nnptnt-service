package vn.dnict.microservice.nnptnt.qlchomeo.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThongTinChoMeoImport;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.ThongTinChoMeoImportService;
import vn.dnict.microservice.nnptnt.qlchomeo.data.ThongTinChoMeoImportInput;
import vn.dnict.microservice.nnptnt.qlchomeo.data.ThongTinChoMeoImportOutput;
import vn.dnict.microservice.utils.Constants;

@Service
@Slf4j
public class ThongTinChoMeoImportBusiness {
	@Autowired
	ThongTinChoMeoImportService serviceThongTinChoMeoImportService;
	
	public Page<ThongTinChoMeoImportOutput> findBySearchOption(String search, Long thongTinChoMeoId, String trangThai, int page,
			int size, String sortBy, String sortDir) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<ThongTinChoMeoImport> result = serviceThongTinChoMeoImportService.findAll(search, thongTinChoMeoId, trangThai, PageRequest.of(page, size, direction, sortBy));
		Page<ThongTinChoMeoImportOutput> dataConvert = result.map(this::thongTinChoMeoImportConvert2OutPut);
		return dataConvert;
	}
	
	public Boolean getDataImport(Long thongTinChoMeoId, Long fileImportId) throws IOException {
		if (fileImportId != null && fileImportId > 0) {
			int type = Constants.DINH_KEM_1_FILE;
			long objectId = thongTinChoMeoId;
			String appCode = ThongTinChoMeoImport.class.getSimpleName();
			CoreAttachment coreAttachment = CoreAttachmentBusiness.dinhKemFile(fileImportId, objectId, type, appCode);
			String[] keyArray = { "stt", "tenchuho", "diachi", "quanhuyen", "phuongxa", "dienthoai", "loaidongvat", "giong", 
					"tenconvat", "namsinh", "maulong", "tinhbiet", "trangthai" };
			List<HashMap<String, String>> listConvert = excelReturnObject.excel2Object(coreAttachment, keyArray);
			List<ThongTinChoMeoImport> listObjectTemp = new ArrayList<ThongTinChoMeoImport>();

			if (listConvert.size() > 0) {
				// insert database import log
				listConvert.parallelStream().forEach(item -> {
					ThongTinChoMeoImport object = new ThongTinChoMeoImport();
					boolean checkFlat = false;
					if (item.get("tenchuho") != null) {
						object.setChuHo(item.get("tenchuho"));
					} else {
						checkFlat = true;
					}

					if (item.get("diachi") != null) {
						object.setDiaChi(item.get("diachi"));
					} else {
						checkFlat = true;
					}

					if (item.get("quanhuyen") != null) {
						object.setQuanHuyen(item.get("quanhuyen"));
					} else {
						checkFlat = true;
					}
					if (item.get("phuongxa") != null) {
						object.setPhuongXa(item.get("phuongxa"));
					} else {
						checkFlat = true;
					}
					object = serviceThongTinChoMeoImportService.save(object);
					listObjectTemp.add(object);
				});
			}
			if (listObjectTemp.size() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public Page<ThongTinChoMeoImport> findAll(int page, int size, String sortBy, String sortDir, String search, 
			Long thongTinChoMeoId, String trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThongTinChoMeoImport> pageThongTinChoMeoImport = serviceThongTinChoMeoImportService.findAll(search, thongTinChoMeoId, 
				trangThai, PageRequest.of(page, size, direction, sortBy));
		return pageThongTinChoMeoImport;
	}
	public ThongTinChoMeoImport findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeoImport.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public ThongTinChoMeoImport create(ThongTinChoMeoImportInput ThongTinChoMeoImportInput) {
		ThongTinChoMeoImport thongTinChoMeoImport = new ThongTinChoMeoImport();
		thongTinChoMeoImport.setChuHo(ThongTinChoMeoImportInput.getChuHo());
		thongTinChoMeoImport.setTrangThai(ThongTinChoMeoImportInput.getTrangThai());
		thongTinChoMeoImport = serviceThongTinChoMeoImportService.save(thongTinChoMeoImport);
		return thongTinChoMeoImport;
	}

	public ThongTinChoMeoImport update(Long id, ThongTinChoMeoImportInput ThongTinChoMeoImportInput) throws EntityNotFoundException {
		Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeoImport.class, "id", String.valueOf(id));
		}
		ThongTinChoMeoImport thongTinChoMeoImport = optional.get();
		thongTinChoMeoImport.setChuHo(ThongTinChoMeoImportInput.getChuHo());
		thongTinChoMeoImport.setTrangThai(ThongTinChoMeoImportInput.getTrangThai());
		thongTinChoMeoImport = serviceThongTinChoMeoImportService.save(thongTinChoMeoImport);
		return thongTinChoMeoImport;
	}

	@DeleteMapping(value = { "/{id}" })
	public ThongTinChoMeoImport delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeoImport.class, "id", String.valueOf(id));
		}
		ThongTinChoMeoImport thongTinChoMeoImport = optional.get();
		thongTinChoMeoImport = serviceThongTinChoMeoImportService.save(thongTinChoMeoImport);
		return thongTinChoMeoImport;
	}
}
