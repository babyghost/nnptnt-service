package vn.dnict.microservice.nnptnt.dm.loaihinh.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmLoaiHinhInput;
import vn.dnict.microservice.nnptnt.dm.loaihinh.bussiness.DmLoaiHinhBussiness;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/loaihinh")
public class DmLoaiHinhController {
	@Autowired
	DmLoaiHinhBussiness businessDmLoaiHinhBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiHinh>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DmLoaiHinh> pageloaiHinh = businessDmLoaiHinhBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pageloaiHinh);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiHinh> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLoaiHinhBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiHinh> create(
			@Valid @RequestBody DmLoaiHinhInput DmLoaiHinhInput) {
		DmLoaiHinh loaiHinh = businessDmLoaiHinhBusiness.create(DmLoaiHinhInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(loaiHinh);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHinh> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiHinhInput DmLoaiHinhInput) throws EntityNotFoundException {
		DmLoaiHinh loaiHinh = businessDmLoaiHinhBusiness.update(id, DmLoaiHinhInput);
		return ResponseEntity.ok(loaiHinh);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHinh> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiHinh loaiHinh = businessDmLoaiHinhBusiness.delete(id);
		return ResponseEntity.ok(loaiHinh);
	}
}
