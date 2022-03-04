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

import vn.dnict.microservice.danhmuc.dao.model.DmTrinhDo;
import vn.dnict.microservice.danhmuc.dao.service.DmTrinhDoService;
import vn.dnict.microservice.danhmuc.data.DmTrinhDoInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;


@RestController
@RequestMapping(value = "/danhmuc/trinhdo")
public class DmTrinhDoController {
	
	@Autowired
	private DmTrinhDoService dmTrinhDoService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmTrinhDo>> findAll(
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
		Page<DmTrinhDo> pageDanhMucTinhTrinhDo = dmTrinhDoService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucTinhTrinhDo);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmTrinhDo> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmTrinhDo> optional = dmTrinhDoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTrinhDo.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmTrinhDo> create(
			@Valid @RequestBody DmTrinhDoInput dmTrinhDoInput)
			throws EntityNotFoundException {
		DmTrinhDo dmTrinhDo = new DmTrinhDo();
		dmTrinhDo.setTen(dmTrinhDoInput.getTen());
		dmTrinhDo.setMa(dmTrinhDoInput.getMa());
		dmTrinhDo.setTrangThai(dmTrinhDoInput.getTrangThai());
		dmTrinhDo.setDaXoa(0);
		dmTrinhDo = dmTrinhDoService.save(dmTrinhDo);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmTrinhDo);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmTrinhDo> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmTrinhDoInput dmTrinhDoInput)
			throws EntityNotFoundException {
		Optional<DmTrinhDo> optional = dmTrinhDoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTrinhDo.class, "id", String.valueOf(id));
		}
		DmTrinhDo dmTrinhDo = optional.get();
		dmTrinhDo.setTen(dmTrinhDoInput.getTen());
		dmTrinhDo.setMa(dmTrinhDoInput.getMa());
		dmTrinhDo.setTrangThai(dmTrinhDoInput.getTrangThai());
		dmTrinhDo = dmTrinhDoService.save(dmTrinhDo);	
		return ResponseEntity.ok(dmTrinhDo);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmTrinhDo> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmTrinhDo> optional = dmTrinhDoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTrinhDo.class, "id", String.valueOf(id));
		}
		DmTrinhDo dmTrinhDo = optional.get();
		dmTrinhDo.setDaXoa(1);
		dmTrinhDo = dmTrinhDoService.save(dmTrinhDo);	
		return ResponseEntity.ok(dmTrinhDo);
	}
}
