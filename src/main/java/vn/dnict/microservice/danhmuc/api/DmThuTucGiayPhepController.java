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

import vn.dnict.microservice.danhmuc.dao.model.DmThuTucGiayPhep;
import vn.dnict.microservice.danhmuc.dao.service.DmThuTucGiayPhepService;
import vn.dnict.microservice.danhmuc.data.DmThuTucGiayPhepInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/thutucgiayphep")
public class DmThuTucGiayPhepController {

	@Autowired
	private DmThuTucGiayPhepService dmThuTucGiayPhepService;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmThuTucGiayPhep>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmThuTucGiayPhep> pageDanhMucTinhTrinhDo = dmThuTucGiayPhepService.findAll(search,
				PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucTinhTrinhDo);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmThuTucGiayPhep> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmThuTucGiayPhep> optional = dmThuTucGiayPhepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuTucGiayPhep.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmThuTucGiayPhep> create(@Valid @RequestBody DmThuTucGiayPhepInput dmThuTucGiayPhepInput)
			throws EntityNotFoundException {
		DmThuTucGiayPhep dmThuTucGiayPhep = new DmThuTucGiayPhep();
		dmThuTucGiayPhep.setTen(dmThuTucGiayPhepInput.getTen());
		dmThuTucGiayPhep.setMa(dmThuTucGiayPhepInput.getMa());
		dmThuTucGiayPhep.setDaXoa(0);
		dmThuTucGiayPhep = dmThuTucGiayPhepService.save(dmThuTucGiayPhep);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmThuTucGiayPhep);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmThuTucGiayPhep> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmThuTucGiayPhepInput dmThuTucGiayPhepInput) throws EntityNotFoundException {
		Optional<DmThuTucGiayPhep> optional = dmThuTucGiayPhepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuTucGiayPhep.class, "id", String.valueOf(id));
		}
		DmThuTucGiayPhep dmThuTucGiayPhep = optional.get();
		dmThuTucGiayPhep.setTen(dmThuTucGiayPhepInput.getTen());
		dmThuTucGiayPhep.setMa(dmThuTucGiayPhepInput.getMa());
		dmThuTucGiayPhep = dmThuTucGiayPhepService.save(dmThuTucGiayPhep);
		return ResponseEntity.ok(dmThuTucGiayPhep);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmThuTucGiayPhep> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmThuTucGiayPhep> optional = dmThuTucGiayPhepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmThuTucGiayPhep.class, "id", String.valueOf(id));
		}
		DmThuTucGiayPhep dmThuTucGiayPhep = optional.get();
		dmThuTucGiayPhep.setDaXoa(1);
		dmThuTucGiayPhep = dmThuTucGiayPhepService.save(dmThuTucGiayPhep);
		return ResponseEntity.ok(dmThuTucGiayPhep);
	}
}
