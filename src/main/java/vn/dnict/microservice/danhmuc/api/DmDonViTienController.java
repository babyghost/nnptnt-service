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

import vn.dnict.microservice.danhmuc.dao.model.DmDonViTien;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViTienService;
import vn.dnict.microservice.danhmuc.data.DmDonViTienInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/donvitien")
public class DmDonViTienController {
	
	@Autowired
	private DmDonViTienService dmDonViTienService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmDonViTien>> findAll(
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
		Page<DmDonViTien> pageDanhMucDonViTien = dmDonViTienService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucDonViTien);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmDonViTien> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmDonViTien> optional = dmDonViTienService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonViTien.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmDonViTien> create(
			@Valid @RequestBody DmDonViTienInput dmDonViTienInput)
			throws EntityNotFoundException {
		DmDonViTien dmDonViTien = new DmDonViTien();
		dmDonViTien.setTen(dmDonViTienInput.getTen());
		dmDonViTien.setMa(dmDonViTienInput.getMa());
		dmDonViTien.setTrangThai(dmDonViTienInput.getTrangThai());
		dmDonViTien.setDaXoa(0);
		dmDonViTien = dmDonViTienService.save(dmDonViTien);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmDonViTien);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmDonViTien> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmDonViTienInput dmDonViTienInput)
			throws EntityNotFoundException {
		Optional<DmDonViTien> optional = dmDonViTienService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonViTien.class, "id", String.valueOf(id));
		}
		DmDonViTien dmDonViTien = optional.get();
		dmDonViTien.setTen(dmDonViTienInput.getTen());
		dmDonViTien.setMa(dmDonViTienInput.getMa());
		dmDonViTien.setTrangThai(dmDonViTienInput.getTrangThai());
		dmDonViTien = dmDonViTienService.save(dmDonViTien);	
		return ResponseEntity.ok(dmDonViTien);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmDonViTien> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmDonViTien> optional = dmDonViTienService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonViTien.class, "id", String.valueOf(id));
		}
		DmDonViTien dmDonViTien = optional.get();
		dmDonViTien.setDaXoa(1);
		dmDonViTien = dmDonViTienService.save(dmDonViTien);	
		return ResponseEntity.ok(dmDonViTien);
	}
}
