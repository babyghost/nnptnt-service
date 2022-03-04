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

import vn.dnict.microservice.danhmuc.dao.model.DmThuPhiLePhi;
import vn.dnict.microservice.danhmuc.dao.service.DmThuPhiLePhiService;
import vn.dnict.microservice.danhmuc.data.DmThuPhiLePhiInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;


@RestController
@RequestMapping(value = "/danhmuc/thuphilephi")
public class DmThuPhiLePhiController {
	
	@Autowired
	private DmThuPhiLePhiService dmThuPhiLePhiService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmThuPhiLePhi>> findAll(
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
		Page<DmThuPhiLePhi> pageDanhMucThuPhiLePhi = dmThuPhiLePhiService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucThuPhiLePhi);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmThuPhiLePhi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmThuPhiLePhi> optional = dmThuPhiLePhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuPhiLePhi.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmThuPhiLePhi> create(
			@Valid @RequestBody DmThuPhiLePhiInput dmThuPhiLePhiInput)
			throws EntityNotFoundException {
		DmThuPhiLePhi dmThuPhiLePhi = new DmThuPhiLePhi();
		dmThuPhiLePhi.setTen(dmThuPhiLePhiInput.getTen());
		dmThuPhiLePhi.setMa(dmThuPhiLePhiInput.getMa());
		dmThuPhiLePhi.setTrangThai(dmThuPhiLePhiInput.getTrangThai());
		dmThuPhiLePhi.setDaXoa(0);
		dmThuPhiLePhi = dmThuPhiLePhiService.save(dmThuPhiLePhi);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmThuPhiLePhi);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmThuPhiLePhi> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmThuPhiLePhiInput dmThuPhiLePhiInput)
			throws EntityNotFoundException {
		Optional<DmThuPhiLePhi> optional = dmThuPhiLePhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuPhiLePhi.class, "id", String.valueOf(id));
		}
		DmThuPhiLePhi dmThuPhiLePhi = optional.get();
		dmThuPhiLePhi.setTen(dmThuPhiLePhiInput.getTen());
		dmThuPhiLePhi.setMa(dmThuPhiLePhiInput.getMa());
		dmThuPhiLePhi.setTrangThai(dmThuPhiLePhiInput.getTrangThai());
		dmThuPhiLePhi = dmThuPhiLePhiService.save(dmThuPhiLePhi);	
		return ResponseEntity.ok(dmThuPhiLePhi);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmThuPhiLePhi> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmThuPhiLePhi> optional = dmThuPhiLePhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuPhiLePhi.class, "id", String.valueOf(id));
		}
		DmThuPhiLePhi dmThuPhiLePhi = optional.get();
		dmThuPhiLePhi.setDaXoa(1);
		dmThuPhiLePhi = dmThuPhiLePhiService.save(dmThuPhiLePhi);	
		return ResponseEntity.ok(dmThuPhiLePhi);
	}
}
