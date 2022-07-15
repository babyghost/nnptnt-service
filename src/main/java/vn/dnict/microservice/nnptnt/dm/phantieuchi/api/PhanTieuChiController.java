package vn.dnict.microservice.nnptnt.dm.phantieuchi.api;

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
import vn.dnict.microservice.nnptnt.dm.data.DmNganhHangInput;
import vn.dnict.microservice.nnptnt.dm.data.PhanTieuChiInput;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.bussiness.PhanTieuChiBussiness;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.model.PhanTieuChi;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/phantieuchi")
public class PhanTieuChiController {
	@Autowired
	PhanTieuChiBussiness businessPhanTieuChiBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<PhanTieuChi>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<PhanTieuChi> pagephanTieuChi = businessPhanTieuChiBusiness.findAll(page, size, sortBy, sortDir, ten, trangThai);
		return ResponseEntity.ok(pagephanTieuChi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PhanTieuChi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessPhanTieuChiBusiness.findById(id));
	}



	@PostMapping(value = { "" })
	public ResponseEntity<PhanTieuChi> create(
			@Valid @RequestBody PhanTieuChiInput phanTieuChiInput) {
		PhanTieuChi phanTieuChi = businessPhanTieuChiBusiness.create(phanTieuChiInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(phanTieuChi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<PhanTieuChi> update(@PathVariable("id") Long id,
			@Valid @RequestBody PhanTieuChiInput PhanTieuChiInput) throws EntityNotFoundException {
		PhanTieuChi phanTieuChi = businessPhanTieuChiBusiness.update(id, PhanTieuChiInput);
		return ResponseEntity.ok(phanTieuChi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<PhanTieuChi> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		PhanTieuChi phanTieuChi = businessPhanTieuChiBusiness.delete(id);
		return ResponseEntity.ok(phanTieuChi);
	}
}
