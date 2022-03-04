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

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiHinhKinhTeDn;
import vn.dnict.microservice.danhmuc.dao.service.DmLoaiHinhKinhTeDnService;
import vn.dnict.microservice.danhmuc.data.DmLoaiHinhKinhTeDnInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/loaihinhkinhtedn")
public class DmLoaiHinhKinhTeDnController {
	@Autowired
	private DmLoaiHinhKinhTeDnService dmLoaiHinhKinhTeDnService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiHinhKinhTeDn>> findAll(
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
		Page<DmLoaiHinhKinhTeDn> pageDanhMucLoaiHinhKinhTeDn = dmLoaiHinhKinhTeDnService.findAll(search, trangThai, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucLoaiHinhKinhTeDn);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiHinhKinhTeDn> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmLoaiHinhKinhTeDn> optional = dmLoaiHinhKinhTeDnService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinhKinhTeDn.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiHinhKinhTeDn> create(
			@Valid @RequestBody DmLoaiHinhKinhTeDnInput dmLoaiHinhKinhTeDnInput)
			throws EntityNotFoundException {
		DmLoaiHinhKinhTeDn dmLoaiHinhKinhTeDn = new DmLoaiHinhKinhTeDn();
		dmLoaiHinhKinhTeDn.setLoaiDoanhNghiepId(dmLoaiHinhKinhTeDnInput.getLoaiDoanhNghiepId());
		dmLoaiHinhKinhTeDn.setTen(dmLoaiHinhKinhTeDnInput.getTen());
		dmLoaiHinhKinhTeDn.setMa(dmLoaiHinhKinhTeDnInput.getMa());
		dmLoaiHinhKinhTeDn.setTrangThai(dmLoaiHinhKinhTeDnInput.getTrangThai());
		dmLoaiHinhKinhTeDn.setDaXoa(0);
		dmLoaiHinhKinhTeDn = dmLoaiHinhKinhTeDnService.save(dmLoaiHinhKinhTeDn);				
		return ResponseEntity.status(HttpStatus.CREATED).body(dmLoaiHinhKinhTeDn);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHinhKinhTeDn> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmLoaiHinhKinhTeDnInput dmLoaiHinhKinhTeDnInput)
			throws EntityNotFoundException {
		Optional<DmLoaiHinhKinhTeDn> optional = dmLoaiHinhKinhTeDnService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinhKinhTeDn.class, "id", String.valueOf(id));
		}
		DmLoaiHinhKinhTeDn dmLoaiHinhKinhTeDn = optional.get();
		dmLoaiHinhKinhTeDn.setLoaiDoanhNghiepId(dmLoaiHinhKinhTeDnInput.getLoaiDoanhNghiepId());
		dmLoaiHinhKinhTeDn.setTen(dmLoaiHinhKinhTeDnInput.getTen());
		dmLoaiHinhKinhTeDn.setMa(dmLoaiHinhKinhTeDnInput.getMa());
		dmLoaiHinhKinhTeDn.setTrangThai(dmLoaiHinhKinhTeDnInput.getTrangThai());
		dmLoaiHinhKinhTeDn = dmLoaiHinhKinhTeDnService.save(dmLoaiHinhKinhTeDn);	
		return ResponseEntity.ok(dmLoaiHinhKinhTeDn);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiHinhKinhTeDn> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmLoaiHinhKinhTeDn> optional = dmLoaiHinhKinhTeDnService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinhKinhTeDn.class, "id", String.valueOf(id));
		}
		DmLoaiHinhKinhTeDn dmLoaiHinhKinhTeDn = optional.get();
		dmLoaiHinhKinhTeDn.setDaXoa(1);
		dmLoaiHinhKinhTeDn = dmLoaiHinhKinhTeDnService.save(dmLoaiHinhKinhTeDn);	
		return ResponseEntity.ok(dmLoaiHinhKinhTeDn);
	}
}
