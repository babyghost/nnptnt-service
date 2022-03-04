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

import vn.dnict.microservice.danhmuc.dao.model.DmGioiTinh;
import vn.dnict.microservice.danhmuc.dao.service.DmGioiTinhService;
import vn.dnict.microservice.danhmuc.data.DmGioiTinhInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/gioitinh")
public class DmGioiTinhController {
	
	@Autowired
	private DmGioiTinhService dmGioiTinhService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmGioiTinh>> findAll(
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
		Page<DmGioiTinh> pageDanhMucGioiTinh = dmGioiTinhService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucGioiTinh);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmGioiTinh> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmGioiTinh> optional = dmGioiTinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGioiTinh.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmGioiTinh> create(
			@Valid @RequestBody DmGioiTinhInput dmGioiTinhInput)
			throws EntityNotFoundException {
		DmGioiTinh dmGioiTinh = new DmGioiTinh();
		dmGioiTinh.setTen(dmGioiTinhInput.getTen());
		dmGioiTinh.setMa(dmGioiTinhInput.getMa());
		dmGioiTinh.setSapXep(dmGioiTinhInput.getSapXep());
		dmGioiTinh.setTrangThai(dmGioiTinhInput.getTrangThai());
		dmGioiTinh.setDaXoa(0);
		dmGioiTinh = dmGioiTinhService.save(dmGioiTinh);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmGioiTinh);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmGioiTinh> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmGioiTinhInput dmGioiTinhInput)
			throws EntityNotFoundException {
		Optional<DmGioiTinh> optional = dmGioiTinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGioiTinh.class, "id", String.valueOf(id));
		}
		DmGioiTinh dmGioiTinh = optional.get();
		dmGioiTinh.setTen(dmGioiTinhInput.getTen());
		dmGioiTinh.setMa(dmGioiTinhInput.getMa());
		dmGioiTinh.setSapXep(dmGioiTinhInput.getSapXep());
		dmGioiTinh.setTrangThai(dmGioiTinhInput.getTrangThai());
		dmGioiTinh = dmGioiTinhService.save(dmGioiTinh);	
		return ResponseEntity.ok(dmGioiTinh);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmGioiTinh> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmGioiTinh> optional = dmGioiTinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGioiTinh.class, "id", String.valueOf(id));
		}
		DmGioiTinh dmGioiTinh = optional.get();
		dmGioiTinh.setDaXoa(1);
		dmGioiTinh = dmGioiTinhService.save(dmGioiTinh);
		return ResponseEntity.ok(dmGioiTinh);
	}
}
