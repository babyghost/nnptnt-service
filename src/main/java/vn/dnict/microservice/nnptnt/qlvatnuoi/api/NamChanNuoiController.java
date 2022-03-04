package vn.dnict.microservice.nnptnt.qlvatnuoi.api;

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
import vn.dnict.microservice.nnptnt.qlvatnuoi.business.NamChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.NamChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.NamChanNuoiInput;


@CrossOrigin
@RestController
@RequestMapping(value = "/nnptnt/qlvatnuoi/namchannuoi")
public class NamChanNuoiController {
	@Autowired
	NamChanNuoiBusiness BusinessNamChanNuoiBusiness;
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<NamChanNuoi>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "quy",required=false) Integer quy
			)
			{
		Page<NamChanNuoi> pageNamChanNuoi = BusinessNamChanNuoiBusiness.findAll(page, size, sortBy, sortDir, search, quy);
		return ResponseEntity.ok(pageNamChanNuoi);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<NamChanNuoi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(BusinessNamChanNuoiBusiness.findById(id));
	}


	@PostMapping(value = { "" })
	public ResponseEntity<NamChanNuoi> create(
			@Valid @RequestBody NamChanNuoiInput NamChanNuoiInput) {
		NamChanNuoi NamChanNuoi = BusinessNamChanNuoiBusiness.create(NamChanNuoiInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(NamChanNuoi);
	}


	@PutMapping(value = { "/{id}" })
	public ResponseEntity<NamChanNuoi> update(@PathVariable("id") Long id,
			@Valid @RequestBody NamChanNuoiInput NamChanNuoiInput) throws EntityNotFoundException {
		NamChanNuoi NamChanNuoi = BusinessNamChanNuoiBusiness.update(id, NamChanNuoiInput);
		return ResponseEntity.ok(NamChanNuoi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NamChanNuoi> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		NamChanNuoi NamChanNuoi = BusinessNamChanNuoiBusiness.delete(id);
		return ResponseEntity.ok(NamChanNuoi);
	}
}
