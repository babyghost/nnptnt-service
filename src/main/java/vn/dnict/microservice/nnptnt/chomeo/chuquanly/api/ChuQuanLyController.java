package vn.dnict.microservice.nnptnt.chomeo.chuquanly.api;

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

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;
import vn.dnict.microservice.core.data.CoreNhomChucNangData;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.business.ChuQuanLyBusiness;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/chuquanly")

public class ChuQuanLyController {
	@Autowired
	ChuQuanLyBusiness businessChuQuanLyBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ChuQuanLyData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "chuHo", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "chuHo", required = false) String chuHo,
			@RequestParam(name="diaChi",required  =  false) String diaChi, 
			@RequestParam(name = "dienThoai",required=false) String dienThoai) {
		Page<ChuQuanLyData> pageChuQuanLyData = businessChuQuanLyBusiness.findAll(page, size, sortBy, sortDir, chuHo, diaChi, dienThoai);
		return ResponseEntity.ok(pageChuQuanLyData);
	}

//	@GetMapping(value = "/{id}")
//	public ResponseEntity<ChuQuanLyData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
//		return ResponseEntity.ok(businessChuQuanLyBusiness.findById(id));
//	}
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<ChuQuanLyData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		ChuQuanLyData chuQuanLyData = businessChuQuanLyBusiness.findById(id);
		return ResponseEntity.ok(chuQuanLyData);
	}

//	@PostMapping(value = { "" })
//	public ResponseEntity<ChuQuanLyData> create(
//			@Valid @RequestBody ChuQuanLyData chuQuanLyData) {
//		chuQuanLyData = businessChuQuanLyBusiness.create(chuQuanLyData);
//		return ResponseEntity.status(HttpStatus.CREATED).body(chuQuanLyData);
//	}
	@PostMapping(value = { "" })
	public ResponseEntity<ChuQuanLy> create(@Valid @RequestBody ChuQuanLyData chuQuanLyData) throws MethodArgumentNotValidException {
		ChuQuanLy chuQuanLy = businessChuQuanLyBusiness.create(chuQuanLyData);
		return ResponseEntity.status(HttpStatus.CREATED).body(chuQuanLy);
	}

//	@PutMapping(value = { "/{id}" })
//	public ResponseEntity<ChuQuanLyData> update(@PathVariable("id") Long id,
//			@Valid @RequestBody ChuQuanLyData chuQuanLyData) throws EntityNotFoundException {
//		chuQuanLyData = businessChuQuanLyBusiness.update(id, chuQuanLyData);
//		return ResponseEntity.ok(chuQuanLyData);
//	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ChuQuanLy> update(@PathVariable("id") Long id,
			@Valid @RequestBody ChuQuanLyData chuQuanLyData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		ChuQuanLy chuQuanLy = businessChuQuanLyBusiness.update(id, chuQuanLyData);
		return ResponseEntity.ok(chuQuanLy);
	}
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ChuQuanLyData> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ChuQuanLyData chuQuanLyData = businessChuQuanLyBusiness.delete(id);
		return ResponseEntity.ok(chuQuanLyData);
	}
}
