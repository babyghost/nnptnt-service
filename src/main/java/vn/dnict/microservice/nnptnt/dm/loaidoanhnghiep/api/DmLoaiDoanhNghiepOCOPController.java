package vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmLoaiDoanhNghiepInput;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.bussiness.DmLoaiDoanhNghiepBussiness;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/ocoploaidoanhnghiep")
public class DmLoaiDoanhNghiepOCOPController {
	@Autowired
	DmLoaiDoanhNghiepBussiness businessDmLoaiDoanhNghiepBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiDoanhNghiepOCOP>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmLoaiDoanhNghiepOCOP> pageloaiDoanhNghiep = businessDmLoaiDoanhNghiepBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pageloaiDoanhNghiep);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiDoanhNghiepOCOP> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiDoanhNghiepBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiDoanhNghiepOCOP> create(
			@Valid @RequestBody DmLoaiDoanhNghiepInput DmLoaiDoanhNghiepInput) {
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = businessDmLoaiDoanhNghiepBusiness.create(DmLoaiDoanhNghiepInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(loaiDoanhNghiep);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDoanhNghiepOCOP> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiDoanhNghiepInput DmLoaiDoanhNghiepInput) throws EntityNotFoundException {
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = businessDmLoaiDoanhNghiepBusiness.update(id, DmLoaiDoanhNghiepInput);
		return ResponseEntity.ok(loaiDoanhNghiep);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDoanhNghiepOCOP> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = businessDmLoaiDoanhNghiepBusiness.delete(id);
		return ResponseEntity.ok(loaiDoanhNghiep);
	}
}
