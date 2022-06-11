package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.api;

import java.time.LocalDate;

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
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.NhiemVuNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.FileDinhKemNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.business.TienDoNhiemVuNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/tiendonhiemvunam")
public class TienDoNhiemVuNamController {
	@Autowired
	TienDoNhiemVuNamBusiness businessTienDoNhiemVuNamBusiness;
	
	@Autowired
	NhiemVuNamBusiness businessNhiemVuNamBusiness;
	
	@Autowired
	FileDinhKemNhiemVuNamService serviceFileDinhKemNhiemVuNamService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<TienDoNhiemVuNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tinhTrang", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "ngayBaoCao",required = false) LocalDate ngayBaoCao,
			@RequestParam(name = "tinhTrang", required = false) Boolean tinhTrang,
			@RequestParam(name = "mucDoHoanThanh",required=false) Integer mucDoHoanThanh)
			{
		Page<TienDoNhiemVuNamData> pageTienDoNhiemVuNamData = businessTienDoNhiemVuNamBusiness.findAll(page, size, sortBy, sortDir, 
				ngayBaoCao, tinhTrang, mucDoHoanThanh);
		return ResponseEntity.ok(pageTienDoNhiemVuNamData);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuNamData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		TienDoNhiemVuNamData tienDoNhiemVuNamData = businessTienDoNhiemVuNamBusiness.findById(id);
		return ResponseEntity.ok(tienDoNhiemVuNamData);
	}
	
	@GetMapping(value = { "/{id}/nhiemvunam"})
	public ResponseEntity<NhiemVuNamData> findByNhiemVuNamId(@PathVariable("id") long id) throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<TienDoNhiemVuNam> create(@Valid @RequestBody TienDoNhiemVuNamData tienDoNhiemVuNamData) throws MethodArgumentNotValidException {
		TienDoNhiemVuNam tienDoNhiemVuNam = businessTienDoNhiemVuNamBusiness.create(tienDoNhiemVuNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(tienDoNhiemVuNam);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuNam> update(@PathVariable("id") Long id,
			@Valid @RequestBody TienDoNhiemVuNamData tienDoNhiemVuNamData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		TienDoNhiemVuNam tienDoNhiemVuNam = businessTienDoNhiemVuNamBusiness.update(id, tienDoNhiemVuNamData);
		return ResponseEntity.ok(tienDoNhiemVuNam);
	}
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuNamData> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		TienDoNhiemVuNamData tienDoNhiemVuNamData = businessTienDoNhiemVuNamBusiness.delete(id);
		return ResponseEntity.ok(tienDoNhiemVuNamData);
	}
}
