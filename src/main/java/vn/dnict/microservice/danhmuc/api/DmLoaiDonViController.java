package vn.dnict.microservice.danhmuc.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.dnict.microservice.danhmuc.business.DmLoaiDonViBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;
import vn.dnict.microservice.danhmuc.data.DmLoaiDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@Controller
@RequestMapping(value = "/danhmuc/loaidonvi")
public class DmLoaiDonViController {
	@Autowired
	private DmLoaiDonViBusiness loaiDonViBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmLoaiDonVi>> findAll(//@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Page<DmLoaiDonVi> pageLoaiDonVi = loaiDonViBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageLoaiDonVi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmLoaiDonVi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmLoaiDonVi loaiDonVi = loaiDonViBusiness.findById(id);
		return ResponseEntity.ok(loaiDonVi);
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmLoaiDonVi>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(loaiDonViBusiness.findByIdIn(ids));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmLoaiDonVi> create(
			@Valid @RequestBody DmLoaiDonViInput loaiDonViInput) {
		DmLoaiDonVi loaiDonVi = loaiDonViBusiness.create(loaiDonViInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(loaiDonVi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDonVi> update(
			@PathVariable("id") Long id, @Valid @RequestBody DmLoaiDonViInput loaiDonViInput)
			throws EntityNotFoundException {
		DmLoaiDonVi loaiDonVi = loaiDonViBusiness.update(id, loaiDonViInput);
		return ResponseEntity.ok(loaiDonVi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmLoaiDonVi> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmLoaiDonVi loaiDonVi = loaiDonViBusiness.delete(id);
		return ResponseEntity.ok(loaiDonVi);
	}

	/*@GetMapping(value = "/inuse")
	public ResponseEntity<List<DmLoaiDonVi>> getListLoaiDonViInUseLimit20(
			@RequestParam(name = "ten", required = false) String ten) {
		List<DmLoaiDonVi> listLoaiDonVi = loaiDonViBusiness.getListLoaiDonViInUseLimit20(ten);
		return ResponseEntity.ok(listLoaiDonVi);
	}

	@GetMapping(value = "/inuse/appcode")
	public ResponseEntity<List<DmLoaiDonVi>> getListLoaiDonViInUseLimit20AndAppCode(
			@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "ten", required = false) String ten) {
		List<DmLoaiDonVi> listLoaiDonVi = loaiDonViBusiness.getListLoaiDonViInUseLimit20AndAppCode(appCode, ten);
		return ResponseEntity.ok(listLoaiDonVi);
	}*/
}
