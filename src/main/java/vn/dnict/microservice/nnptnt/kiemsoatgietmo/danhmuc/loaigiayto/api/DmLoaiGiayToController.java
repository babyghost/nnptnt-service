package vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.business.DmLoaiGiayToBusiness;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.model.DmLoaiGiayTo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.DmLoaiGiayToData;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/loaigiayto")
public class DmLoaiGiayToController {
	@Autowired
	DmLoaiGiayToBusiness businessDmLoaiGiayToBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiGiayTo>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmLoaiGiayTo> pageLoaiGiayTo = businessDmLoaiGiayToBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageLoaiGiayTo);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiGiayTo> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiGiayToBusiness.findById(id));
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiGiayTo> create(@Valid @RequestBody DmLoaiGiayToData dmLoaiGiayToData) {
		DmLoaiGiayTo loaiGiayTo = businessDmLoaiGiayToBusiness.create(dmLoaiGiayToData);
		return ResponseEntity.status(HttpStatus.CREATED).body(loaiGiayTo);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiGiayTo> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmLoaiGiayTo loaiGiayTo = businessDmLoaiGiayToBusiness.delete(id);
		return ResponseEntity.ok(loaiGiayTo);
	}
}
