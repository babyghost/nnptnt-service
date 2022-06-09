package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.api;

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
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.business.CoSoGietMoBusiness;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.CoSoGietMoData;

@CrossOrigin
@RestController
@RequestMapping(value = "/kiemsoatgietmo/cosogietmo")
public class CoSoGietMoController {
	@Autowired
	CoSoGietMoBusiness businessCoSoGietMoBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoSoGietMoData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "tenCoSo", required = false) String tenCoSo,
			@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "phuongXaId",required=false) Long phuongXaId,
			@RequestParam(name = "quanHuyenId",required=false) Long quanHuyenId) {
		Page<CoSoGietMoData> pageCoSoGietMoData = businessCoSoGietMoBusiness.findAll(page, size, sortBy, sortDir, tenCoSo, tenChuCoSo,
				dienThoai, email, phuongXaId, quanHuyenId);
		return ResponseEntity.ok(pageCoSoGietMoData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CoSoGietMoData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoSoGietMoData coSoGietMo = businessCoSoGietMoBusiness.findById(id);
		return ResponseEntity.ok(coSoGietMo);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoSoGietMoData> create(@Valid @RequestBody CoSoGietMoData coSoGietMoData,
			BindingResult result) throws MethodArgumentNotValidException {
		coSoGietMoData = businessCoSoGietMoBusiness.create(coSoGietMoData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coSoGietMoData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoSoGietMoData> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoSoGietMoData coSoGietMoData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coSoGietMoData = businessCoSoGietMoBusiness.update(id, coSoGietMoData, result);
		return ResponseEntity.ok(coSoGietMoData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoSoGietMoData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoSoGietMoData coSoGietMoData = businessCoSoGietMoBusiness.delete(id);
		return ResponseEntity.ok(coSoGietMoData);
	}
}
