package vn.dnict.microservice.nnptnt.dm.loainhiemvu.api;

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
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.business.DmLoaiNhiemVuBusiness;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;

@CrossOrigin
@RestController
@RequestMapping(value = "/dm/dmloainhiemvu")
public class DmLoaiNhiemVuController {
	@Autowired
	DmLoaiNhiemVuBusiness businessDmLoaiNhiemVuBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiNhiemVu>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "sapXep", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<DmLoaiNhiemVu> pageDmLoaiNhiemVu = businessDmLoaiNhiemVuBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageDmLoaiNhiemVu);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiNhiemVu> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiNhiemVuBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiNhiemVu> create(
			@Valid @RequestBody DmLoaiNhiemVuData dmLoaiNhiemVuData) {
		DmLoaiNhiemVu dmLoaiNhiemVu = businessDmLoaiNhiemVuBusiness.create(dmLoaiNhiemVuData);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmLoaiNhiemVu);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiNhiemVu> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiNhiemVuData dmLoaiNhiemVuData) throws EntityNotFoundException {
		DmLoaiNhiemVu dmLoaiNhiemVu = businessDmLoaiNhiemVuBusiness.update(id, dmLoaiNhiemVuData);
		return ResponseEntity.ok(dmLoaiNhiemVu);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiNhiemVu> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiNhiemVu dmLoaiNhiemVu = businessDmLoaiNhiemVuBusiness.delete(id);
		return ResponseEntity.ok(dmLoaiNhiemVu);
	}
}
