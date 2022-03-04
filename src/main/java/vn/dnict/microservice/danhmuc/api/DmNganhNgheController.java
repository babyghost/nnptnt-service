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

import vn.dnict.microservice.danhmuc.dao.model.DmNganhNghe;
import vn.dnict.microservice.danhmuc.dao.service.DmNganhNgheService;
import vn.dnict.microservice.danhmuc.data.DmNganhNgheInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;


@RestController
@RequestMapping(value = "/danhmuc/nganhnghe")
public class DmNganhNgheController {
	
	@Autowired
	private DmNganhNgheService dmNganhNgheService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmNganhNghe>> findAll(
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
		Page<DmNganhNghe> pageDanhMucNganhNghe = dmNganhNgheService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucNganhNghe);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmNganhNghe> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmNganhNghe> optional = dmNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNghe.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmNganhNghe> create(
			@Valid @RequestBody DmNganhNgheInput dmNganhNgheInput)
			throws EntityNotFoundException {
		DmNganhNghe dmNganhNghe = new DmNganhNghe();
		dmNganhNghe.setTen(dmNganhNgheInput.getTen());
		dmNganhNghe.setMa(dmNganhNgheInput.getMa());
		dmNganhNghe.setTrangThai(dmNganhNgheInput.getTrangThai());
		dmNganhNghe.setDaXoa(0);
		dmNganhNghe = dmNganhNgheService.save(dmNganhNghe);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmNganhNghe);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhNghe> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmNganhNgheInput dmNganhNgheInput)
			throws EntityNotFoundException {
		Optional<DmNganhNghe> optional = dmNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNghe.class, "id", String.valueOf(id));
		}
		DmNganhNghe dmNganhNghe = optional.get();
		dmNganhNghe.setTen(dmNganhNgheInput.getTen());
		dmNganhNghe.setMa(dmNganhNgheInput.getMa());
		dmNganhNghe.setTrangThai(dmNganhNgheInput.getTrangThai());
		dmNganhNghe = dmNganhNgheService.save(dmNganhNghe);
		return ResponseEntity.ok(dmNganhNghe);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmNganhNghe> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmNganhNghe> optional = dmNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNghe.class, "id", String.valueOf(id));
		}
		DmNganhNghe dmNganhNghe = optional.get();
		dmNganhNghe.setDaXoa(1);
		dmNganhNghe = dmNganhNgheService.save(dmNganhNghe);	
		return ResponseEntity.ok(dmNganhNghe);
	}
}
