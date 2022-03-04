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

import vn.dnict.microservice.danhmuc.dao.model.DmQuocGia;
import vn.dnict.microservice.danhmuc.dao.service.DmQuocGiaService;
import vn.dnict.microservice.danhmuc.data.DmQuocGiaInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;


@RestController
@RequestMapping(value = "/danhmuc/quocgia")
public class DmQuocGiaController {
	
	@Autowired
	private DmQuocGiaService dmQuocGiaService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmQuocGia>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search, 
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmQuocGia> pageDanhMucQuocGia = dmQuocGiaService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucQuocGia);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmQuocGia> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmQuocGia> optional = dmQuocGiaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuocGia.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmQuocGia> create(
			@Valid @RequestBody DmQuocGiaInput dmQuocGiaInput)
			throws EntityNotFoundException {
		DmQuocGia dmQuocGia = new DmQuocGia();
		dmQuocGia.setTen(dmQuocGiaInput.getTen());
		dmQuocGia.setMa(dmQuocGiaInput.getMa());
		dmQuocGia.setTenVietNam(dmQuocGiaInput.getTenVietNam());
		dmQuocGia.setTrangThai(dmQuocGiaInput.getTrangThai());
		dmQuocGia.setDaXoa(false);
		dmQuocGia = dmQuocGiaService.save(dmQuocGia);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmQuocGia);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmQuocGia> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmQuocGiaInput dmQuocGiaInput)
			throws EntityNotFoundException {
		Optional<DmQuocGia> optional = dmQuocGiaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuocGia.class, "id", String.valueOf(id));
		}
		DmQuocGia dmQuocGia = optional.get();
		dmQuocGia.setTen(dmQuocGiaInput.getTen());
		dmQuocGia.setMa(dmQuocGiaInput.getMa());
		dmQuocGia.setTenVietNam(dmQuocGiaInput.getTenVietNam());
		dmQuocGia.setTrangThai(dmQuocGiaInput.getTrangThai());
		dmQuocGia = dmQuocGiaService.save(dmQuocGia);	
		return ResponseEntity.ok(dmQuocGia);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmQuocGia> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmQuocGia> optional = dmQuocGiaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuocGia.class, "id", String.valueOf(id));
		}
		DmQuocGia dmQuocGia = optional.get();
		dmQuocGia.setDaXoa(true);
		dmQuocGia = dmQuocGiaService.save(dmQuocGia);	
		return ResponseEntity.ok(dmQuocGia);
	}
}
