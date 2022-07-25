package vn.dnict.microservice.nnptnt.baocao.kehoach.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import vn.dnict.microservice.nnptnt.baocao.data.ChiTieuKeHoachData;
import vn.dnict.microservice.nnptnt.baocao.data.KeHoachData;
import vn.dnict.microservice.nnptnt.baocao.data.ThucHienBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.kehoach.bussiness.KeHoachBussiness;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
@CrossOrigin
@RestController
@RequestMapping(value = "/baocao/kehoach")
public class KeHoachController {
	
	@Autowired
	KeHoachBussiness bussinessKeHoachBussiness;
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ChiTieuKeHoachData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,

			@RequestParam(name = "nam",required=false) Integer nam) {
		Page<ChiTieuKeHoachData> pageKeHoachData= bussinessKeHoachBussiness.findAll(page, size, sortBy, sortDir, linhVucId,nam);
		return ResponseEntity.ok(pageKeHoachData);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KeHoachData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		KeHoachData KeHoachData = bussinessKeHoachBussiness.findById(id);
		return ResponseEntity.ok(KeHoachData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<KeHoachData> create(
			@Valid @RequestBody KeHoachData KeHoachData) {
		KeHoachData = bussinessKeHoachBussiness.create(KeHoachData);
		return ResponseEntity.status(HttpStatus.CREATED).body(KeHoachData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachData> update(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoachData KeHoachData) throws EntityNotFoundException {
		KeHoachData = bussinessKeHoachBussiness.update(id, KeHoachData);
		return ResponseEntity.ok(KeHoachData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoach> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		KeHoach KeHoach = bussinessKeHoachBussiness.delete(id);
		return ResponseEntity.ok(KeHoach);
	}
	
	@DeleteMapping(value = { "/delete/" })
	public ResponseEntity<Integer> deletes(@Valid @RequestBody List<Long> ids) throws EntityNotFoundException {
		Integer check = bussinessKeHoachBussiness.deletes(ids);
		return ResponseEntity.ok(check);
	}
	
	@GetMapping(value = { "/kehoach" })
	public ResponseEntity<Page<KeHoachData>> findByKeHoach(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,

			@RequestParam(name = "nam",required=false) Integer nam) {
		Page<KeHoachData> pageKeHoachData= bussinessKeHoachBussiness.findByKeHoach(page, size, sortBy, sortDir, linhVucId,nam);
		return ResponseEntity.ok(pageKeHoachData);
	}
}
