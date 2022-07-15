package vn.dnict.microservice.nnptnt.dm.phanhang.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmPhanHangInput;
import vn.dnict.microservice.nnptnt.dm.phanhang.bussiness.DmPhanHangBussiness;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;

@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/phanhang")
public class DmPhanHangController {
	@Autowired
	DmPhanHangBussiness businessDmPhanHangBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmPhanHang>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<DmPhanHang> pageNganhHang = businessDmPhanHangBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pageNganhHang);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmPhanHang> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmPhanHangBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmPhanHang> create(
			@Valid @RequestBody DmPhanHangInput DmPhanHangInput) {
		DmPhanHang NganhHang = businessDmPhanHangBusiness.create(DmPhanHangInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(NganhHang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmPhanHang> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmPhanHangInput DmPhanHangInput) throws EntityNotFoundException {
		DmPhanHang NganhHang = businessDmPhanHangBusiness.update(id, DmPhanHangInput);
		return ResponseEntity.ok(NganhHang);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmPhanHang> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhanHang NganhHang = businessDmPhanHangBusiness.delete(id);
		return ResponseEntity.ok(NganhHang);
	}
}
