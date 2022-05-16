package vn.dnict.microservice.nnptnt.baocao.chitieunam.api;


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
import vn.dnict.microservice.nnptnt.baocao.chitieunam.bussiness.ChiTieuNamBussiness;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;
import vn.dnict.microservice.nnptnt.baocao.data.ChiTieuNamData;
@CrossOrigin
@RestController
@RequestMapping(value = "/baocao/chitieunam")
public class ChiTieuNamController {

	@Autowired
	ChiTieuNamBussiness bussinessChiTieuNamBussiness;
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ChiTieuNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,

			@RequestParam(name = "nam",required=false) Integer nam) {
		Page<ChiTieuNamData> pageChiTieuNamData= bussinessChiTieuNamBussiness.findAll(page, size, sortBy, sortDir, linhVucId,nam);
		return ResponseEntity.ok(pageChiTieuNamData);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChiTieuNamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		ChiTieuNamData chiTieuNamData = bussinessChiTieuNamBussiness.findById(id);
		return ResponseEntity.ok(chiTieuNamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ChiTieuNamData> create(
			@Valid @RequestBody ChiTieuNamData chiTieuNamData) {
		chiTieuNamData = bussinessChiTieuNamBussiness.create(chiTieuNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(chiTieuNamData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ChiTieuNamData> update(@PathVariable("id") Long id,
			@Valid @RequestBody ChiTieuNamData chiTieuNamData) throws EntityNotFoundException {
		chiTieuNamData = bussinessChiTieuNamBussiness.update(id, chiTieuNamData);
		return ResponseEntity.ok(chiTieuNamData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ChiTieuNam> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ChiTieuNam chiTieuNam = bussinessChiTieuNamBussiness.delete(id);
		return ResponseEntity.ok(chiTieuNam);
	}
	@GetMapping(value = { "/chitieu" })
	public ResponseEntity<ChiTieuNamData> findByChiTieu(
			@RequestParam(name = "linhVucId", required = false) long linhVucId,
			@RequestParam(name = "nam", required = false) Integer nam
		
			) throws EntityNotFoundException {
		ChiTieuNamData chiTieuNamData = bussinessChiTieuNamBussiness.findChiTieu(linhVucId, nam);
		return ResponseEntity.ok(chiTieuNamData);
	}
}
