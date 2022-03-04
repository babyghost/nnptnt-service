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

import vn.dnict.microservice.danhmuc.business.DmCapDonViBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;
import vn.dnict.microservice.danhmuc.data.DmCapDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@Controller
@RequestMapping(value="/danhmuc/capdonvi")
public class DmCapDonViController {
	@Autowired
	private DmCapDonViBusiness capDonViBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmCapDonVi>> findAll(//@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search) {
		Page<DmCapDonVi> pageCapDonVi = capDonViBusiness.findAll(page, size, sortBy, sortDir, search);
		return ResponseEntity.ok(pageCapDonVi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmCapDonVi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(capDonViBusiness.findById(id));
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmCapDonVi>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(capDonViBusiness.findByIdIn(ids));
	}

	/*@GetMapping(value = "/inuse")
	public ResponseEntity<List<DmCapDonVi>> getListCapDonViInUseLimit20(
			@RequestParam(name = "ten", required = false) String ten) {
		List<DmCapDonVi> listCapDonVi = capDonViBusiness.getListCapDonViInUseLimit20(ten);
		return ResponseEntity.ok(listCapDonVi);
	}

	@GetMapping(value = "/inuse/appcode")
	public ResponseEntity<List<DmCapDonVi>> getListCapDonViInUseLimit20AndAppCode(
			@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "ten", required = false) String ten) {
		List<DmCapDonVi> listCapDonVi = capDonViBusiness.getListCapDonViInUseLimit20AndAppCode(appCode, ten);
		return ResponseEntity.ok(listCapDonVi);
	}*/

	@PostMapping(value = { "" })
	public ResponseEntity<DmCapDonVi> create(
			@Valid @RequestBody DmCapDonViInput capDonViInput) {
		DmCapDonVi capDonVi = capDonViBusiness.create(capDonViInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(capDonVi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmCapDonVi> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmCapDonViInput capDonViInput) throws EntityNotFoundException {
		DmCapDonVi capDonVi = capDonViBusiness.update(id, capDonViInput);
		return ResponseEntity.ok(capDonVi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmCapDonVi> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmCapDonVi capDonVi = capDonViBusiness.delete(id);
		return ResponseEntity.ok(capDonVi);
	}
}
