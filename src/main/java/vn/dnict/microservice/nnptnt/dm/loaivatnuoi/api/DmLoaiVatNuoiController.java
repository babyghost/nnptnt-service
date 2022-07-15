package vn.dnict.microservice.nnptnt.dm.loaivatnuoi.api;

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
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.business.DmLoaiVatNuoiBusiness;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.data.DmLoaiVatNuoiInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/dm/dmloaivatnuoi")
public class DmLoaiVatNuoiController {
	@Autowired
	DmLoaiVatNuoiBusiness businessDmLoaiVatNuoiBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiVatNuoi>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmLoaiVatNuoi> pageLoaiVatNuoi = businessDmLoaiVatNuoiBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageLoaiVatNuoi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiVatNuoi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiVatNuoiBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiVatNuoi> create(
			@Valid @RequestBody DmLoaiVatNuoiInput DmLoaiVatNuoiInput) {
		DmLoaiVatNuoi LoaiVatNuoi = businessDmLoaiVatNuoiBusiness.create(DmLoaiVatNuoiInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(LoaiVatNuoi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiVatNuoi> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiVatNuoiInput DmLoaiVatNuoiInput) throws EntityNotFoundException {
		DmLoaiVatNuoi LoaiVatNuoi = businessDmLoaiVatNuoiBusiness.update(id, DmLoaiVatNuoiInput);
		return ResponseEntity.ok(LoaiVatNuoi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiVatNuoi> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiVatNuoi LoaiVatNuoi = businessDmLoaiVatNuoiBusiness.delete(id);
		return ResponseEntity.ok(LoaiVatNuoi);
	}
}
