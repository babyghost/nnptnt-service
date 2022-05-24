package vn.dnict.microservice.nnptnt.dm.phannhom.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmPhanNhomInput;
import vn.dnict.microservice.nnptnt.dm.phannhom.bussiness.DmPhanNhomBussiness;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;

@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/phannhom")
public class DmPhanNhomController {
	@Autowired
	DmPhanNhomBussiness businessDmPhanNhomBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmPhanNhomInput>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "dmNganhHangId", required = false) Long dmNganhHangId,
			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<DmPhanNhomInput> pageNganhHang = businessDmPhanNhomBusiness.findAll(page, size, sortBy, sortDir, ten, dmNganhHangId,trangThai);
		return ResponseEntity.ok(pageNganhHang);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmPhanNhom> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmPhanNhomBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmPhanNhom> create(
			@Valid @RequestBody DmPhanNhomInput DmPhanNhomInput) {
		DmPhanNhom NganhHang = businessDmPhanNhomBusiness.create(DmPhanNhomInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(NganhHang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmPhanNhom> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmPhanNhomInput DmPhanNhomInput) throws EntityNotFoundException {
		DmPhanNhom NganhHang = businessDmPhanNhomBusiness.update(id, DmPhanNhomInput);
		return ResponseEntity.ok(NganhHang);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmPhanNhom> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhanNhom NganhHang = businessDmPhanNhomBusiness.delete(id);
		return ResponseEntity.ok(NganhHang);
	}
}
