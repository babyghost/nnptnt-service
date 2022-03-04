package vn.dnict.microservice.danhmuc.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

import vn.dnict.microservice.danhmuc.dao.model.DmChucVu;
import vn.dnict.microservice.danhmuc.dao.service.DmChucVuService;
import vn.dnict.microservice.danhmuc.data.DmChucVuInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/chucvu")
public class DmChucVuController {
	
	@Autowired
	private DmChucVuService dmChucVuService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmChucVu>> findAll(
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
		Page<DmChucVu> pageDanhMucChucVu = dmChucVuService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucChucVu);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<DmChucVu> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmChucVu> optional = dmChucVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmChucVu.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmChucVu> create(
			@Valid @RequestBody DmChucVuInput dmChucVuInput)
			throws EntityNotFoundException {
		DmChucVu dmChucVu = new DmChucVu();
		dmChucVu.setTen(dmChucVuInput.getTen());
		dmChucVu.setMa(dmChucVuInput.getMa());
		dmChucVu.setTrangThai(dmChucVuInput.getTrangThai());
		dmChucVu.setDaXoa(0);
		dmChucVu = dmChucVuService.save(dmChucVu);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmChucVu);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmChucVu> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmChucVuInput dmChucVuInput)
			throws EntityNotFoundException {
		Optional<DmChucVu> optional = dmChucVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmChucVu.class, "id", String.valueOf(id));
		}
		DmChucVu dmChucVu = optional.get();
		dmChucVu.setTen(dmChucVuInput.getTen());
		dmChucVu.setMa(dmChucVuInput.getMa());
		dmChucVu.setTrangThai(dmChucVuInput.getTrangThai());
		dmChucVu = dmChucVuService.save(dmChucVu);	
		return ResponseEntity.ok(dmChucVu);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmChucVu> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmChucVu> optional = dmChucVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmChucVu.class, "id", String.valueOf(id));
		}
		DmChucVu dmChucVu = optional.get();
		dmChucVu.setDaXoa(1);
		dmChucVu = dmChucVuService.save(dmChucVu);	
		return ResponseEntity.ok(dmChucVu);
	}
}
