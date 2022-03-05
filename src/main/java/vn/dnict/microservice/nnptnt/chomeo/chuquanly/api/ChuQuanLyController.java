package vn.dnict.microservice.nnptnt.chomeo.chuquanly.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.business.ChuQuanLyBusiness;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyInput;
@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/chuquanly")

public class ChuQuanLyController {
	@Autowired
	ChuQuanLyBusiness businessChuQuanLyBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ChuQuanLy>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "chuHo", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "chuHo", required = false) String chuHo,
			@RequestParam(name="diaChi",required  =  false) String diaChi, 
			@RequestParam(name = "dienThoai",required=false) Integer dienThoai) {
		Page<ChuQuanLy> pageChuQuanLy = businessChuQuanLyBusiness.findAll(page, size, sortBy, sortDir, chuHo, diaChi, dienThoai);
		return ResponseEntity.ok(pageChuQuanLy);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChuQuanLy> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessChuQuanLyBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ChuQuanLy> create(
			@Valid @RequestBody ChuQuanLyInput chuQuanLyInput) {
		ChuQuanLy chuQuanLy = businessChuQuanLyBusiness.create(chuQuanLyInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(chuQuanLy);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ChuQuanLy> update(@PathVariable("id") Long id,
			@Valid @RequestBody ChuQuanLyInput chuQuanLyInput) throws EntityNotFoundException {
		ChuQuanLy chuQuanLy = businessChuQuanLyBusiness.update(id, chuQuanLyInput);
		return ResponseEntity.ok(chuQuanLy);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ChuQuanLy> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ChuQuanLy chuQuanLy = businessChuQuanLyBusiness.delete(id);
		return ResponseEntity.ok(chuQuanLy);
	}
}
