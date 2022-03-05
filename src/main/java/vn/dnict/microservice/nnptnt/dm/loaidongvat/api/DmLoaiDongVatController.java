package vn.dnict.microservice.nnptnt.dm.loaidongvat.api;

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
import vn.dnict.microservice.nnptnt.chomeo.data.DmLoaiDongVatInput;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.business.DmLoaiDongVatBusiness;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/dmloaidongvat")
public class DmLoaiDongVatController {
	@Autowired
	DmLoaiDongVatBusiness businessDmLoaiDongVatBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiDongVat>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmLoaiDongVat> pageLoaiDongVat = businessDmLoaiDongVatBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageLoaiDongVat);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiDongVat> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiDongVatBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiDongVat> create(
			@Valid @RequestBody DmLoaiDongVatInput DmLoaiDongVatInput) {
		DmLoaiDongVat LoaiDongVat = businessDmLoaiDongVatBusiness.create(DmLoaiDongVatInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(LoaiDongVat);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDongVat> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiDongVatInput DmLoaiDongVatInput) throws EntityNotFoundException {
		DmLoaiDongVat LoaiDongVat = businessDmLoaiDongVatBusiness.update(id, DmLoaiDongVatInput);
		return ResponseEntity.ok(LoaiDongVat);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDongVat> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiDongVat LoaiDongVat = businessDmLoaiDongVatBusiness.delete(id);
		return ResponseEntity.ok(LoaiDongVat);
	}
}
