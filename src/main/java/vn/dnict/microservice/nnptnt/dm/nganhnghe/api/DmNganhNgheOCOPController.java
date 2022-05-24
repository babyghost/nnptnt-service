package vn.dnict.microservice.nnptnt.dm.nganhnghe.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmNganhNgheInput;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.bussiness.DmNganhNgheOCOPBussiness;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/ocopnganhnghe")
public class DmNganhNgheOCOPController {
	@Autowired
	DmNganhNgheOCOPBussiness businessDmNganhNgheBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmNganhNgheOCOP>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmNganhNgheOCOP> pagenganhNghe = businessDmNganhNgheBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pagenganhNghe);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmNganhNgheOCOP> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmNganhNgheBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmNganhNgheOCOP> create(
			@Valid @RequestBody DmNganhNgheInput DmNganhNgheInput) {
		DmNganhNgheOCOP nganhNghe = businessDmNganhNgheBusiness.create(DmNganhNgheInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(nganhNghe);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhNgheOCOP> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmNganhNgheInput DmNganhNgheInput) throws EntityNotFoundException {
		DmNganhNgheOCOP nganhNghe = businessDmNganhNgheBusiness.update(id, DmNganhNgheInput);
		return ResponseEntity.ok(nganhNghe);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhNgheOCOP> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmNganhNgheOCOP nganhNghe = businessDmNganhNgheBusiness.delete(id);
		return ResponseEntity.ok(nganhNghe);
	}
}
