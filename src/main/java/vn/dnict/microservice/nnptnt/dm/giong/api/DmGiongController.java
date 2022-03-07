package vn.dnict.microservice.nnptnt.dm.giong.api;

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
import vn.dnict.microservice.nnptnt.chomeo.data.DmGiongInput;
import vn.dnict.microservice.nnptnt.dm.giong.business.DmGiongBusiness;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/dmgiong")
public class DmGiongController {
	@Autowired
	DmGiongBusiness businessDmGiongBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmGiong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "loaiVatNuoiId", required = false) Long loaiVatNuoiId,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmGiong> pageGiong = businessDmGiongBusiness.findAll(page, size, sortBy, sortDir, search,loaiVatNuoiId, trangThai);
		return ResponseEntity.ok(pageGiong);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmGiong> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmGiongBusiness.findById(id));
	}


	@GetMapping(value = "/{id}/dmloaidongvat")
	public ResponseEntity<DmLoaiDongVat> findDmLoaiDongVatByDmGiongId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiDongVat loaiDongVat = businessDmGiongBusiness.findDmLoaiDongVatByDmGiongId(id);
		return ResponseEntity.ok(loaiDongVat);
	}


	@PostMapping(value = { "" })
	public ResponseEntity<DmGiong> create(
			@Valid @RequestBody DmGiongInput DmGiongInput) {
		DmGiong Giong = businessDmGiongBusiness.create(DmGiongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(Giong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmGiong> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmGiongInput DmGiongInput) throws EntityNotFoundException {
		DmGiong Giong = businessDmGiongBusiness.update(id, DmGiongInput);
		return ResponseEntity.ok(Giong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmGiong> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmGiong Giong = businessDmGiongBusiness.delete(id);
		return ResponseEntity.ok(Giong);
	}
}