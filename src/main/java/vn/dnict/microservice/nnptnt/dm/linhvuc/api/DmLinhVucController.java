package vn.dnict.microservice.nnptnt.dm.linhvuc.api;

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
import vn.dnict.microservice.nnptnt.dm.linhvuc.business.DmLinhVucBusiness;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.data.DmLinhVucInput;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/linhvuc")
public class DmLinhVucController {
	@Autowired
	DmLinhVucBusiness businessDmLinhVucBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLinhVuc>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,

			@RequestParam(name = "trangThai",required=false) Boolean trangThai) {
		Page<DmLinhVuc> pageLinhVuc = businessDmLinhVucBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageLinhVuc);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLinhVuc> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessDmLinhVucBusiness.findById(id));
	}





	@PostMapping(value = { "" })
	public ResponseEntity<DmLinhVuc> create(
			@Valid @RequestBody DmLinhVucInput DmLinhVucInput) {
		DmLinhVuc Giong = businessDmLinhVucBusiness.create(DmLinhVucInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(Giong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLinhVuc> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLinhVucInput DmLinhVucInput) throws EntityNotFoundException {
		DmLinhVuc linhVuc = businessDmLinhVucBusiness.update(id, DmLinhVucInput);
		return ResponseEntity.ok(linhVuc);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLinhVuc> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLinhVuc linhVuc = businessDmLinhVucBusiness.delete(id);
		return ResponseEntity.ok(linhVuc);
	}
}