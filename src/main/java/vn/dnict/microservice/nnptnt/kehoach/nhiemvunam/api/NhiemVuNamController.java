package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.business.KeHoachNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.NhiemVuNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/nhiemvunam")
public class NhiemVuNamController {
	@Autowired
	NhiemVuNamBusiness businessNhiemVuNamBusiness;
	
	@Autowired
	KeHoachNamBusiness businessKeHoachNamBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<NhiemVuNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenNhiemVu", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay) {
		Page<NhiemVuNamData> pageNhiemVuNamData = businessNhiemVuNamBusiness.findAll(page, size, sortBy, sortDir, keHoachId,
				tenNhiemVu, tuNgay, denNgay);
		return ResponseEntity.ok(pageNhiemVuNamData);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}
	
	@GetMapping(value = { "/{id}/kehoachnam" })
	public ResponseEntity<KeHoachNamData> findByKeHoachNamId(@PathVariable("id") long id) throws EntityNotFoundException {
		KeHoachNamData keHoachNamData = businessKeHoachNamBusiness.findById(id);
		return ResponseEntity.ok(keHoachNamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<NhiemVuNamData> create(@Valid @RequestBody NhiemVuNamData nhiemVuNamData) throws MethodArgumentNotValidException {
		nhiemVuNamData = businessNhiemVuNamBusiness.create(nhiemVuNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(nhiemVuNamData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> update(@PathVariable("id") Long id, @Valid @RequestBody NhiemVuNamData nhiemVuNamData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		nhiemVuNamData = businessNhiemVuNamBusiness.update(id, nhiemVuNamData);
		return ResponseEntity.ok(nhiemVuNamData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.delete(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}
}
