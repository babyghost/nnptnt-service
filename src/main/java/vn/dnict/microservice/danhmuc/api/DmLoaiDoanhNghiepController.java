package vn.dnict.microservice.danhmuc.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDoanhNghiep;
import vn.dnict.microservice.danhmuc.dao.service.DmLoaiDoanhNghiepService;
import vn.dnict.microservice.danhmuc.data.DmLoaiDoanhNghiepInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/loaidoanhnghiep")
public class DmLoaiDoanhNghiepController {
	@Autowired
	private DmLoaiDoanhNghiepService dmLoaiDoanhNghiepService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiDoanhNghiep>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search, 
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLoaiDoanhNghiep> pageDanhMucLoaiDoanhNghiep = dmLoaiDoanhNghiepService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucLoaiDoanhNghiep);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiDoanhNghiep> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiep> optional = dmLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiep.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiDoanhNghiep> create(
			@Valid @RequestBody DmLoaiDoanhNghiepInput dmLoaiDoanhNghiepInput)
			throws EntityNotFoundException {
		DmLoaiDoanhNghiep dmLoaiDoanhNghiep = new DmLoaiDoanhNghiep();
		dmLoaiDoanhNghiep.setTen(dmLoaiDoanhNghiepInput.getTen());
		dmLoaiDoanhNghiep.setMa(dmLoaiDoanhNghiepInput.getMa());
		dmLoaiDoanhNghiep.setTrangThai(dmLoaiDoanhNghiepInput.getTrangThai());
		dmLoaiDoanhNghiep.setDaXoa(0);
		dmLoaiDoanhNghiep = dmLoaiDoanhNghiepService.save(dmLoaiDoanhNghiep);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmLoaiDoanhNghiep);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDoanhNghiep> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiDoanhNghiepInput dmLoaiDoanhNghiepInput)
			throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiep> optional = dmLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiep.class, "id", String.valueOf(id));
		}
		DmLoaiDoanhNghiep dmLoaiDoanhNghiep = optional.get();
		dmLoaiDoanhNghiep.setTen(dmLoaiDoanhNghiepInput.getTen());
		dmLoaiDoanhNghiep.setMa(dmLoaiDoanhNghiepInput.getMa());
		dmLoaiDoanhNghiep.setTrangThai(dmLoaiDoanhNghiepInput.getTrangThai());
		dmLoaiDoanhNghiep = dmLoaiDoanhNghiepService.save(dmLoaiDoanhNghiep);	
		return ResponseEntity.ok(dmLoaiDoanhNghiep);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDoanhNghiep> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiep> optional = dmLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiep.class, "id", String.valueOf(id));
		}
		DmLoaiDoanhNghiep dmLoaiDoanhNghiep = optional.get();
		dmLoaiDoanhNghiep.setDaXoa(1);
		dmLoaiDoanhNghiep = dmLoaiDoanhNghiepService.save(dmLoaiDoanhNghiep);	
		return ResponseEntity.ok(dmLoaiDoanhNghiep);
	}
}
