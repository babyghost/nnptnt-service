package vn.dnict.microservice.nnptnt.ocop.danhgia.api;

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
import vn.dnict.microservice.nnptnt.ocop.danhgia.bussiness.DanhGiaSanPhamBussiness;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;
import vn.dnict.microservice.nnptnt.ocop.data.DanhGiaSanPhamData;

@CrossOrigin
@RestController
@RequestMapping(value = "/ocop/danhgia")
public class DanhGiaSanPhamController {

	@Autowired
	DanhGiaSanPhamBussiness bussinessDanhGiaSanPhamBussiness;
	

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DanhGiaSanPhamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "phanHangId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "sanPhamId", required = false) Long sanPhamId,
			@RequestParam(name = "phanHangId", required = false) Long phanHangId
			) {
		Page<DanhGiaSanPhamData> pageDanhGiaSanPham = bussinessDanhGiaSanPhamBussiness.findAll(page, size, sortBy, sortDir, sanPhamId, phanHangId);
		return ResponseEntity.ok(pageDanhGiaSanPham);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DanhGiaSanPhamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DanhGiaSanPhamData DanhGiaSanPhamData = bussinessDanhGiaSanPhamBussiness.findById(id);
		return ResponseEntity.ok(DanhGiaSanPhamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DanhGiaSanPham> create(
			@Valid @RequestBody DanhGiaSanPhamData DanhGiaSanPhamData) {
		DanhGiaSanPham danhGiaSanPham = bussinessDanhGiaSanPhamBussiness.create(DanhGiaSanPhamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(danhGiaSanPham);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DanhGiaSanPham> update(@PathVariable("id") Long id,
			@Valid @RequestBody DanhGiaSanPhamData DanhGiaSanPhamData) throws EntityNotFoundException {
		DanhGiaSanPham danhGiaSanPham = bussinessDanhGiaSanPhamBussiness.update(id, DanhGiaSanPhamData);
		return ResponseEntity.ok(danhGiaSanPham);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DanhGiaSanPham> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DanhGiaSanPham danhGiaSanPham = bussinessDanhGiaSanPhamBussiness.delete(id);
		return ResponseEntity.ok(danhGiaSanPham);
	}
	
}
