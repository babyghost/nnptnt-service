package vn.dnict.microservice.nnptnt.dm.nganhhang.api;

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

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNganhHangInput;
import vn.dnict.microservice.nnptnt.dm.nganhhang.bussiness.DmNganhHangBussiness;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/nganhhang")
public class DmNganhHangController {
	@Autowired
	DmNganhHangBussiness businessDmNganhHangBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmNganhHang>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<DmNganhHang> pageNganhHang = businessDmNganhHangBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pageNganhHang);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmNganhHang> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmNganhHangBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmNganhHang> create(
			@Valid @RequestBody DmNganhHangInput DmNganhHangInput) {
		DmNganhHang NganhHang = businessDmNganhHangBusiness.create(DmNganhHangInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(NganhHang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhHang> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmNganhHangInput DmNganhHangInput) throws EntityNotFoundException {
		DmNganhHang NganhHang = businessDmNganhHangBusiness.update(id, DmNganhHangInput);
		return ResponseEntity.ok(NganhHang);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhHang> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmNganhHang NganhHang = businessDmNganhHangBusiness.delete(id);
		return ResponseEntity.ok(NganhHang);
	}
}
