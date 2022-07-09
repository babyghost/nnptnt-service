package vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.api;

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
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.business.DmLoaiHopDongBusiness;
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.dao.model.DmLoaiHopDong;
import vn.dnict.microservice.nnptnt.hopdong.data.DmLoaiHopDongInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/loaihopdong")
public class DmLoaiHopDongController {
	@Autowired
	private DmLoaiHopDongBusiness businessDmLoaiHopDongBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiHopDong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "ma", required = false) String ma,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Page<DmLoaiHopDong> pageDmLoaiHopDong = businessDmLoaiHopDongBusiness.findAll(page, size,
				sortBy, sortDir, ten, ma, trangThai);
		return ResponseEntity.ok(pageDmLoaiHopDong);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiHopDong> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmLoaiHopDong dmLoaiHopDong = businessDmLoaiHopDongBusiness.findById(id);
		return ResponseEntity.ok(dmLoaiHopDong);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiHopDong> create(
			@Valid @RequestBody DmLoaiHopDongInput dmLoaiHopDongInput) {
		DmLoaiHopDong dmLoaiHopDong = businessDmLoaiHopDongBusiness.create(dmLoaiHopDongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmLoaiHopDong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHopDong> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiHopDongInput dmLoaiHopDongInput) throws EntityNotFoundException {

		DmLoaiHopDong dmLoaiHopDong = businessDmLoaiHopDongBusiness.update(id, dmLoaiHopDongInput);
		return ResponseEntity.ok(dmLoaiHopDong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHopDong> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmLoaiHopDong dmLoaiHopDong = businessDmLoaiHopDongBusiness.delete(id);
		return ResponseEntity.ok(dmLoaiHopDong);
	}

}
