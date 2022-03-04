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

import vn.dnict.microservice.danhmuc.dao.model.DmDanToc;
import vn.dnict.microservice.danhmuc.dao.service.DmDanTocService;
import vn.dnict.microservice.danhmuc.data.DmDanTocInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/dantoc")
public class DmDanTocController {
	@Autowired
	private DmDanTocService dmDanTocService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmDanToc>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "sapXep", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search, 
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmDanToc> pageDanhMucDanToc = dmDanTocService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucDanToc);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmDanToc> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmDanToc> optional = dmDanTocService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDanToc.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmDanToc> create(
			@Valid @RequestBody DmDanTocInput dmDanTocInput)
			throws EntityNotFoundException {
		DmDanToc dmDanToc = new DmDanToc();
		dmDanToc.setTen(dmDanTocInput.getTen());
		dmDanToc.setMa(dmDanTocInput.getMa());
		dmDanToc.setSapXep(dmDanToc.getSapXep());
		dmDanToc.setTrangThai(dmDanTocInput.getTrangThai());
		dmDanToc.setDaXoa(0);
		dmDanToc = dmDanTocService.save(dmDanToc);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmDanToc);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmDanToc> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmDanTocInput dmDanTocInput)
			throws EntityNotFoundException {
		Optional<DmDanToc> optional = dmDanTocService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDanToc.class, "id", String.valueOf(id));
		}
		DmDanToc dmDanToc = optional.get();
		dmDanToc.setTen(dmDanTocInput.getTen());
		dmDanToc.setMa(dmDanTocInput.getMa());
		dmDanToc.setSapXep(dmDanToc.getSapXep());
		dmDanToc.setTrangThai(dmDanTocInput.getTrangThai());
		dmDanToc = dmDanTocService.save(dmDanToc);
		return ResponseEntity.ok(dmDanToc);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmDanToc> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmDanToc> optional = dmDanTocService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDanToc.class, "id", String.valueOf(id));
		}
		DmDanToc dmDanToc = optional.get();
		dmDanToc.setDaXoa(0);
		dmDanToc = dmDanTocService.save(dmDanToc);
		return ResponseEntity.ok(dmDanToc);
	}
}
