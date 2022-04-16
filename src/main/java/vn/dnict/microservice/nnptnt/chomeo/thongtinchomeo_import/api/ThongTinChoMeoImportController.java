package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.api;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoImportData;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.business.ThongTinChoMeoImportBusiness;
@CrossOrigin
@RestController
@RequestMapping(value = "/import/chomeo")
public class ThongTinChoMeoImportController {
	
	@Autowired
	private ThongTinChoMeoImportBusiness businessThongTinChoMeoImportBusiness;
	
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThongTinChoMeoImportData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "thongTinChoMeoId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "thongTinChoMeoId", required = false) Long thongTinChoMeoId,
			@RequestParam(name = "chuHo", required = false) String chuHo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@RequestParam(name = "loaiDongvat", required = false) String loaiDongVat,
			@RequestParam(name = "giong", required = false) String giong,
			@RequestParam(name = "trangThai", required = false) String trangThai)	 {
		Page<ThongTinChoMeoImportData> objectPage = businessThongTinChoMeoImportBusiness
				.findAll(thongTinChoMeoId,trangThai, chuHo, dienThoai, loaiDongVat, giong, page, size, sortBy, sortDir);
		return ResponseEntity.ok(objectPage);
	}
	
	@GetMapping(value = { "/getdata/" })
	public ResponseEntity<Boolean> getDataImport(
			@RequestParam(name = "fileImportId", required = false) Long fileImportId)
			throws EntityNotFoundException, IOException {
		Boolean objectFlat = businessThongTinChoMeoImportBusiness.getDataImport( fileImportId);
		return ResponseEntity.ok(objectFlat);
	}
	
//	@PostMapping(value = { "/save" })
//	public ResponseEntity<HashMap<String, String>> create(@Valid @RequestBody ImportChoMeo input)
//			throws EntityNotFoundException, IOException {
//		HashMap<String, String> result = businessThongTinChoMeoImportBusiness.saveImport(input);
//		return ResponseEntity.status(HttpStatus.CREATED).body(result);
//	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ThongTinChoMeoImportData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		ThongTinChoMeoImportData data = businessThongTinChoMeoImportBusiness.findById(id);
		return ResponseEntity.ok(data);
	}

	@DeleteMapping(value = { "/delete/" })
	public ResponseEntity<Integer> delete(@Valid @RequestBody List<Long> ids) throws EntityNotFoundException {
		Integer check = businessThongTinChoMeoImportBusiness.delete(ids);
		return ResponseEntity.ok(check);
	}
	@PostMapping(value = { "/save" })
	public ResponseEntity<Integer> create(@Valid @RequestBody List<Long> ids)
			throws EntityNotFoundException, IOException {
		Integer count = businessThongTinChoMeoImportBusiness.save(ids);
		return ResponseEntity.status(HttpStatus.CREATED).body(count);
	}	
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinChoMeoImportData> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThongTinChoMeoImportData bodyData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		bodyData = businessThongTinChoMeoImportBusiness.update(id, bodyData, result);
		return ResponseEntity.ok(bodyData);
	}
}

