package vn.dnict.microservice.danhmuc.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.danhmuc.dao.service.DmTinhThanhService;
import vn.dnict.microservice.danhmuc.data.DmTinhThanhData;
import vn.dnict.microservice.danhmuc.data.validator.DmTinhThanhValidator;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/tinhthanh")
public class DmTinhThanhController {

	@Autowired
	private DmTinhThanhService dmTinhThanhService;
	@Autowired
	private DmTinhThanhValidator dmTinhThanhValidator;
	@Autowired
	private DmQuanHuyenService dmQuanHuyenService;
	@Autowired
	private DmPhuongXaService dmPhuongXaService;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmTinhThanh>> findAll(
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
		Page<DmTinhThanh> pageDanhMucTinhThanh = dmTinhThanhService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucTinhThanh);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmTinhThanh> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmTinhThanh> optional = dmTinhThanhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTinhThanh.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmTinhThanh> create(@Valid @RequestBody DmTinhThanhData dmTinhThanhData, BindingResult result)
			throws MethodArgumentNotValidException {

		dmTinhThanhValidator.validate(dmTinhThanhData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmTinhThanh dmTinhThanh = new DmTinhThanh();
		dmTinhThanh.setTen(dmTinhThanhData.getTen());
		dmTinhThanh.setMa(dmTinhThanhData.getMa());
		dmTinhThanh.setTrangThai(dmTinhThanhData.getTrangThai());
		dmTinhThanh.setDaXoa(false);
		dmTinhThanh = dmTinhThanhService.save(dmTinhThanh);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmTinhThanh);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmTinhThanh> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmTinhThanhData dmTinhThanhData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<DmTinhThanh> optional = dmTinhThanhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTinhThanh.class, "id", String.valueOf(id));
		}
		dmTinhThanhValidator.validate(dmTinhThanhData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmTinhThanh dmTinhThanh = optional.get();
		String tinhThanhMaCu = dmTinhThanh.getMa();
		String tinhThanhMaMoi = dmTinhThanhData.getMa();
		if (!tinhThanhMaCu.equalsIgnoreCase(tinhThanhMaMoi)) {
			updateMaTinhThanhQuanHuyen(tinhThanhMaCu, tinhThanhMaMoi);
			updateMaTinhThanhPhuongXa(tinhThanhMaCu, tinhThanhMaMoi);
		}
		dmTinhThanh.setTen(dmTinhThanhData.getTen());
		dmTinhThanh.setMa(dmTinhThanhData.getMa());
		dmTinhThanh.setTrangThai(dmTinhThanhData.getTrangThai());
		dmTinhThanh = dmTinhThanhService.save(dmTinhThanh);
		return ResponseEntity.ok(dmTinhThanh);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmTinhThanh> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmTinhThanh> optional = dmTinhThanhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTinhThanh.class, "id", String.valueOf(id));
		}
		DmTinhThanh dmTinhThanh = optional.get();
		dmTinhThanh.setDaXoa(true);
		dmTinhThanh = dmTinhThanhService.save(dmTinhThanh);
		return ResponseEntity.ok(dmTinhThanh);
	}

	public void updateMaTinhThanhQuanHuyen(String tinhThanhCodeCu, String tinhThanhCodeMoi) {
		List<DmQuanHuyen> dmQuanHuyens = dmQuanHuyenService.findByTinhThanhCodeIgnoreCaseAndDaXoa(tinhThanhCodeCu,
				false);
		if (Objects.nonNull(dmQuanHuyens) && !dmQuanHuyens.isEmpty()) {
			for (DmQuanHuyen dmQuanHuyen : dmQuanHuyens) {
				dmQuanHuyen.setTinhThanhCode(tinhThanhCodeMoi);
				dmQuanHuyenService.save(dmQuanHuyen);
			}
		}
	}

	public void updateMaTinhThanhPhuongXa(String tinhThanhCodeCu, String tinhThanhCodeMoi) {
		List<DmPhuongXa> dmPhuongXas = dmPhuongXaService.findByTinhThanhCodeIgnoreCaseAndDaXoa(tinhThanhCodeCu, false);
		if (Objects.nonNull(dmPhuongXas) && !dmPhuongXas.isEmpty()) {
			for (DmPhuongXa dmPhuongXa : dmPhuongXas) {
				dmPhuongXa.setTinhThanhCode(tinhThanhCodeMoi);
				dmPhuongXaService.save(dmPhuongXa);
			}
		}
	}
}
