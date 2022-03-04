package vn.dnict.microservice.danhmuc.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import vn.dnict.microservice.danhmuc.business.DmPhuongXaBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;
import vn.dnict.microservice.danhmuc.data.DmPhuongXaData;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/danhmuc/phuongxa")
public class DmPhuongXaController {

	@Autowired
	private DmPhuongXaBusiness dmPhuongXaBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmPhuongXaData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "quanHuyenCode", required = false) String quanHuyenCode,
			@RequestParam(name = "tinhThanhCode", required = false) String tinhThanhCode,
			@RequestParam(name = "tinhThanhIds", required = false) List<Long> tinhThanhIds,
			@RequestParam(name = "quanHuyenIds", required = false) List<Long> quanHuyenIds,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {

		Page<DmPhuongXaData> pageDmPhuongXaData = dmPhuongXaBusiness.findAll(page, size, sortBy, sortDir, search,
				quanHuyenCode, tinhThanhCode, quanHuyenIds, tinhThanhIds, trangThai);
		return ResponseEntity.ok(pageDmPhuongXaData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmPhuongXaData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmPhuongXaData> create(@Valid @RequestBody DmPhuongXaData dmPhuongXaData,
			BindingResult result) throws MethodArgumentNotValidException {
		return ResponseEntity.status(HttpStatus.CREATED).body(dmPhuongXaBusiness.create(dmPhuongXaData, result));
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmPhuongXaData> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmPhuongXaData dmPhuongXaData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		return ResponseEntity.ok(dmPhuongXaBusiness.update(id, dmPhuongXaData, result));
	}

	@GetMapping(value = "/{id}/tinhthanh")
	public ResponseEntity<DmTinhThanh> findByTinhThanhById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.findByTinhThanhById(id));
	}

	@GetMapping(value = "/{id}/quanhuyen")
	public ResponseEntity<DmQuanHuyen> findByQuanHuyenById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.findByQuanHuyenById(id));
	}

	@GetMapping(value = "/quanhuyen/{ma}")
	public ResponseEntity<DmQuanHuyen> findByQuanHuyenByMa(@PathVariable("ma") String ma)
			throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.findByQuanHuyenByMa(ma));
	}

	@GetMapping(value = "/tinhthanh/{ma}")
	public ResponseEntity<DmTinhThanh> findByTinhThanhByMa(@PathVariable("ma") String ma)
			throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.findByTinhThanhByMa(ma));
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmPhuongXa> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(dmPhuongXaBusiness.delete(id));
	}
}
