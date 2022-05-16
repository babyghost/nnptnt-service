package vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.api;

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
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.business.DmPhuongThucBaoLanhHdBusiness;
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;
import vn.dnict.microservice.nnptnt.hopdong.data.DmPhuongThucBaoLanhHdInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/phuongthucbaolanhhd")
public class DmPhuongThucBaoLanhHdController {
	@Autowired
	private DmPhuongThucBaoLanhHdBusiness businessPhuongThucBaoLanhHdBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmPhuongThucBaoLanhHd>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "type", defaultValue = "-1", required = false) Integer type,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai,
			@RequestParam(name = "search", required = false) String search) {
		Page<DmPhuongThucBaoLanhHd> pageDmPhuongThucBaoLanhHd = businessPhuongThucBaoLanhHdBusiness
				.findAll(page, size, sortBy, sortDir, search, type, trangThai);
		return ResponseEntity.ok(pageDmPhuongThucBaoLanhHd);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmPhuongThucBaoLanhHd> findById(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = businessPhuongThucBaoLanhHdBusiness
				.findById(id);
		return ResponseEntity.ok(dmPhuongThucBaoLanhHd);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmPhuongThucBaoLanhHd> create(
			@Valid @RequestBody DmPhuongThucBaoLanhHdInput dmPhuongThucBaoLanhHdInput) {
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = businessPhuongThucBaoLanhHdBusiness
				.create(dmPhuongThucBaoLanhHdInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmPhuongThucBaoLanhHd);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmPhuongThucBaoLanhHd> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmPhuongThucBaoLanhHdInput dmPhuongThucBaoLanhHdInput)
			throws EntityNotFoundException {

		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = businessPhuongThucBaoLanhHdBusiness.update(id,
				dmPhuongThucBaoLanhHdInput);
		return ResponseEntity.ok(dmPhuongThucBaoLanhHd);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmPhuongThucBaoLanhHd> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = businessPhuongThucBaoLanhHdBusiness.delete(id);
		return ResponseEntity.ok(dmPhuongThucBaoLanhHd);
	}

}
