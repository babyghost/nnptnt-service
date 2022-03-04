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
import vn.dnict.microservice.danhmuc.data.DmQuanHuyenData;
import vn.dnict.microservice.danhmuc.data.validator.DmQuanHuyenValidator;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/quanhuyen")
public class DmQuanHuyenController {

	@Autowired
	private DmQuanHuyenService dmQuanHuyenService;
	@Autowired
	private DmQuanHuyenValidator dmQuanHuyenValidator;
	@Autowired
	private DmTinhThanhService dmTinhThanhService;
	@Autowired
	private DmPhuongXaService dmPhuongXaService;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmQuanHuyen>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tinhThanhCode", required = false) String tinhThanhCode,
			@RequestParam(name = "duAnId", defaultValue = "-1", required = false) Long duAnId,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmQuanHuyen> pageDanhMucQuanHuyen = dmQuanHuyenService.findAll(search, trangThai, tinhThanhCode, duAnId,
				PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageDanhMucQuanHuyen);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmQuanHuyen> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmQuanHuyen> optional = dmQuanHuyenService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuanHuyen.class, "id", String.valueOf(id));
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmQuanHuyen> create(@Valid @RequestBody DmQuanHuyenData dmQuanHuyenData, BindingResult result)
			throws MethodArgumentNotValidException {
		dmQuanHuyenValidator.validate(dmQuanHuyenData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmQuanHuyen dmQuanHuyen = new DmQuanHuyen();
		dmQuanHuyen.setTen(dmQuanHuyenData.getTen());
		dmQuanHuyen.setMa(dmQuanHuyenData.getMa());
		List<DmTinhThanh> tinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(dmQuanHuyenData.getTinhThanhCode(),
				false);
		dmQuanHuyen.setTinhThanhCode(null);
		if (Objects.nonNull(tinhThanhs) && !tinhThanhs.isEmpty()) {
			DmTinhThanh dmTinhThanh = tinhThanhs.get(0);
			dmQuanHuyen.setTinhThanhCode(dmTinhThanh.getMa());
		}
		dmQuanHuyen.setTrangThai(dmQuanHuyenData.getTrangThai());
		dmQuanHuyen.setDaXoa(false);
		dmQuanHuyen = dmQuanHuyenService.save(dmQuanHuyen);
		return ResponseEntity.status(HttpStatus.CREATED).body(dmQuanHuyen);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmQuanHuyen> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmQuanHuyenData dmQuanHuyenData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<DmQuanHuyen> optional = dmQuanHuyenService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuanHuyen.class, "id", String.valueOf(id));
		}
		dmQuanHuyenValidator.validate(dmQuanHuyenData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmQuanHuyen dmQuanHuyen = optional.get();
		String quanHuyenCodeCu = dmQuanHuyen.getMa();
		String quanHuyenCodeMoi = dmQuanHuyenData.getMa();
		if (!quanHuyenCodeCu.equalsIgnoreCase(quanHuyenCodeMoi)) {
			updateMaQuanHuyenPhuongXa(quanHuyenCodeCu, quanHuyenCodeMoi);
		}
		dmQuanHuyen.setTen(dmQuanHuyenData.getTen());
		dmQuanHuyen.setMa(dmQuanHuyenData.getMa());
		List<DmTinhThanh> tinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(dmQuanHuyenData.getTinhThanhCode(),
				false);
		dmQuanHuyen.setTinhThanhCode(null);
		if (Objects.nonNull(tinhThanhs) && !tinhThanhs.isEmpty()) {
			DmTinhThanh dmTinhThanh = tinhThanhs.get(0);
			dmQuanHuyen.setTinhThanhCode(dmTinhThanh.getMa());
		}
		dmQuanHuyen.setTrangThai(dmQuanHuyenData.getTrangThai());
		dmQuanHuyen = dmQuanHuyenService.save(dmQuanHuyen);
		return ResponseEntity.ok(dmQuanHuyen);
	}

	@GetMapping(value = "/{id}/tinhthanh")
	public ResponseEntity<DmTinhThanh> findByTinhThanhById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmQuanHuyen> optional = dmQuanHuyenService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuanHuyen.class, "id", String.valueOf(id));
		}
		DmQuanHuyen quanHuyen = optional.get();
		DmTinhThanh tinhThanh = new DmTinhThanh();
		List<DmTinhThanh> listTinhThanh = dmTinhThanhService.findByMa(quanHuyen.getTinhThanhCode());
		if (listTinhThanh != null && !listTinhThanh.isEmpty()) {
			tinhThanh = listTinhThanh.get(0);
		}
		return ResponseEntity.ok(tinhThanh);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmQuanHuyen> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmQuanHuyen> optional = dmQuanHuyenService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmQuanHuyen.class, "id", String.valueOf(id));
		}
		DmQuanHuyen dmQuanHuyen = optional.get();
		dmQuanHuyen.setDaXoa(true);
		dmQuanHuyen = dmQuanHuyenService.save(dmQuanHuyen);
		return ResponseEntity.ok(dmQuanHuyen);
	}

	@GetMapping(value = "/tinhthanh/{ma}")
	public ResponseEntity<DmTinhThanh> findByTinhThanhByMa(@PathVariable("ma") String ma)
			throws EntityNotFoundException {
		DmTinhThanh tinhThanh = new DmTinhThanh();
		List<DmTinhThanh> dmTinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(ma, false);
		if (dmTinhThanhs != null && !dmTinhThanhs.isEmpty()) {
			tinhThanh = dmTinhThanhs.get(0);
		}
		return ResponseEntity.ok(tinhThanh);
	}

	public void updateMaQuanHuyenPhuongXa(String quanHuyenCodeCu, String quanHuyenCodeMoi) {
		List<DmPhuongXa> dmPhuongXas = dmPhuongXaService.findByTinhThanhCodeIgnoreCaseAndDaXoa(quanHuyenCodeCu, false);
		if (Objects.nonNull(dmPhuongXas) && !dmPhuongXas.isEmpty()) {
			for (DmPhuongXa dmPhuongXa : dmPhuongXas) {
				dmPhuongXa.setQuanHuyenCode(quanHuyenCodeMoi);
				dmPhuongXaService.save(dmPhuongXa);
			}
		}
	}
}
