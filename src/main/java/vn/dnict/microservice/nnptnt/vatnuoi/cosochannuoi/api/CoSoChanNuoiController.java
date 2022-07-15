package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.business.CoSoChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiOutput;

@CrossOrigin
@RestController
@RequestMapping(value = "/vatnuoi/cosochannuoi")
public class CoSoChanNuoiController {
	@Autowired
	CoSoChanNuoiBusiness businessCoSoChanNuoiBusiness; 
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoSoChanNuoiOutput>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phuongXaId",required=false) Long phuongXaId,
			@RequestParam(name = "quanHuyenId",required=false) Long quanHuyenId) {
		Page<CoSoChanNuoiOutput> pageCoSoChanNuoiOutput = businessCoSoChanNuoiBusiness.findAll(page, size, sortBy, sortDir, search, 
				tenChuCoSo, dienThoai, email, phuongXaId, quanHuyenId);
		return ResponseEntity.ok(pageCoSoChanNuoiOutput);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CoSoChanNuoiOutput> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoSoChanNuoiOutput coSoChanNuoi = businessCoSoChanNuoiBusiness.findById(id);
		return ResponseEntity.ok(coSoChanNuoi);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoSoChanNuoiOutput> create(@Valid @RequestBody CoSoChanNuoiOutput coSoChanNuoiOutput,
			BindingResult result) throws MethodArgumentNotValidException {
		coSoChanNuoiOutput = businessCoSoChanNuoiBusiness.create(coSoChanNuoiOutput, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coSoChanNuoiOutput);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoSoChanNuoiOutput> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoSoChanNuoiOutput coSoChanNuoiOutput, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coSoChanNuoiOutput = businessCoSoChanNuoiBusiness.update(id, coSoChanNuoiOutput, result);
		return ResponseEntity.ok(coSoChanNuoiOutput);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoSoChanNuoiOutput> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoSoChanNuoiOutput coSoChanNuoiOutput = businessCoSoChanNuoiBusiness.delete(id);
		return ResponseEntity.ok(coSoChanNuoiOutput);
	}
}
