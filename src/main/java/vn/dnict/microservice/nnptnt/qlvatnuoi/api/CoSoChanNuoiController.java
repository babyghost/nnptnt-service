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

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.qlvatnuoi.business.CoSoChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.CoSoChanNuoiInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlvatnuoi/cosochannuoi")
public class CoSoChanNuoiController {
	@Autowired
	CoSoChanNuoiBusiness businessCoSoChanNuoiBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoSoChanNuoi>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenCoSo", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "phuongXaId",required=false) Long phuongXaId,
			@RequestParam(name = "quanHuyenId",required=false) Long quanHuyenId) {
		Page<CoSoChanNuoi> pageCoSoChanNuoi = businessCoSoChanNuoiBusiness.findAll(page, size, sortBy, sortDir, search, 
				phuongXaId, quanHuyenId);
		return ResponseEntity.ok(pageCoSoChanNuoi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CoSoChanNuoi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessCoSoChanNuoiBusiness.findById(id));
	}
	
	@GetMapping(value = "/{id}/DmPhuongXa")
	public ResponseEntity<DmPhuongXa> findDmPhuongXaByCoSoChanNuoiId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhuongXa dmPhuongXa = businessCoSoChanNuoiBusiness.findDmPhuongXaByCoSoChanNuoiId(id);
		return ResponseEntity.ok(dmPhuongXa);
	}
	
	@GetMapping(value = "/{id}/DmQuanHuyen")
	public ResponseEntity<DmQuanHuyen> findDmQuanHuyenByCoSoChanNuoiId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmQuanHuyen dmQuanHuyen = businessCoSoChanNuoiBusiness.findDmQuanHuyenByCoSoChanNuoiId(id);
		return ResponseEntity.ok(dmQuanHuyen);
	}
	
	@GetMapping(value = "/id/{tenCoSo}")
	public ResponseEntity<CoSoChanNuoi> findByTenCoSo(@PathVariable("tenCoSo") String tenCoSo) throws EntityNotFoundException {
		return ResponseEntity.ok(businessCoSoChanNuoiBusiness.findByTenCoSo(tenCoSo));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoSoChanNuoi> create(
			@Valid @RequestBody CoSoChanNuoiInput CoSoChanNuoiInput) {
		CoSoChanNuoi CoSoChanNuoi = businessCoSoChanNuoiBusiness.create(CoSoChanNuoiInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(CoSoChanNuoi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoSoChanNuoi> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoSoChanNuoiInput CoSoChanNuoiInput) throws EntityNotFoundException {
		CoSoChanNuoi CoSoChanNuoi = businessCoSoChanNuoiBusiness.update(id, CoSoChanNuoiInput);
		return ResponseEntity.ok(CoSoChanNuoi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoSoChanNuoi> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		CoSoChanNuoi CoSoChanNuoi = businessCoSoChanNuoiBusiness.delete(id);
		return ResponseEntity.ok(CoSoChanNuoi);
	}
}
