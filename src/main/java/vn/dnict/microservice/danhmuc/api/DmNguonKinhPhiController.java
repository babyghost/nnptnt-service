package vn.dnict.microservice.danhmuc.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.dnict.microservice.danhmuc.business.DmNguonKinhPhiBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;
import vn.dnict.microservice.danhmuc.data.DmNguonKinhPhiInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Controller
@RequestMapping(value = "/danhmuc/nguonkinhphi")
public class DmNguonKinhPhiController {
	@Autowired
	private DmNguonKinhPhiBusiness dmNguonKinhPhiBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmNguonKinhPhi>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "id", defaultValue = "-1", required = false) Long id,
			@RequestParam(name = "chaId", defaultValue = "-1", required = false) Long chaId,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Page<DmNguonKinhPhi> pageDmNguonKinhPhi = dmNguonKinhPhiBusiness.findAll(page, size, sortBy, sortDir, search,
				trangThai, id, chaId);
		return ResponseEntity.ok(pageDmNguonKinhPhi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmNguonKinhPhi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmNguonKinhPhi dmNguonKinhPhi = dmNguonKinhPhiBusiness.findById(id);
		return ResponseEntity.ok(dmNguonKinhPhi);
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmNguonKinhPhi>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(dmNguonKinhPhiBusiness.findByIdIn(ids));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmNguonKinhPhi> create(@Valid @RequestBody DmNguonKinhPhiInput dmNguonKinhPhiInput) {
		DmNguonKinhPhi dmNguonKinhPhi = dmNguonKinhPhiBusiness.create(dmNguonKinhPhiInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmNguonKinhPhi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmNguonKinhPhi> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmNguonKinhPhiInput dmNguonKinhPhiInput) throws EntityNotFoundException {
		DmNguonKinhPhi dmNguonKinhPhi = dmNguonKinhPhiBusiness.update(id, dmNguonKinhPhiInput);
		return ResponseEntity.ok(dmNguonKinhPhi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmNguonKinhPhi> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmNguonKinhPhi dmNguonKinhPhi = dmNguonKinhPhiBusiness.delete(id);
		return ResponseEntity.ok(dmNguonKinhPhi);
	}

}
