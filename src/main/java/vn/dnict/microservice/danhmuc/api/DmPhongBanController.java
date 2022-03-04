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

import vn.dnict.microservice.danhmuc.business.DmPhongBanBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.data.DmPhongBanInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@Controller
@RequestMapping(value = "/danhmuc/phongban")
public class DmPhongBanController {
	@Autowired
	private DmPhongBanBusiness phongBanBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmPhongBan>> findAll(//@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai,
			@RequestParam(name = "donViId", defaultValue = "-1", required = false) Long donViId) {
		Page<DmPhongBan> pagePhongBan = phongBanBusiness.findAll(page, size, sortBy, sortDir, search, donViId,
				trangThai);
		return ResponseEntity.ok(pagePhongBan);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmPhongBan> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmPhongBan phongBan = phongBanBusiness.findById(id);
		return ResponseEntity.ok(phongBan);
	}

	@GetMapping(value = "/{id}/donvi")
	public ResponseEntity<DmDonVi> findDonViByPhongBanId(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmDonVi donVi = phongBanBusiness.findDonViByPhongBanId(id);
		return ResponseEntity.ok(donVi);
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmPhongBan>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(phongBanBusiness.findByIdIn(ids));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmPhongBan> create(
			@Valid @RequestBody DmPhongBanInput phongBanInput) {
		DmPhongBan phongBan = phongBanBusiness.create(phongBanInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(phongBan);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmPhongBan> update(
			@PathVariable("id") Long id, @Valid @RequestBody DmPhongBanInput phongBanInput)
			throws EntityNotFoundException {
		DmPhongBan phongBan = phongBanBusiness.update(id, phongBanInput);
		return ResponseEntity.ok(phongBan);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmPhongBan> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmPhongBan phongBan = phongBanBusiness.delete(id);
		return ResponseEntity.ok(phongBan);
	}

	/*@GetMapping(value = "/inuse")
	public ResponseEntity<List<DmPhongBan>> getListPhongBanInUseLimit20(
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "donViId", defaultValue = "-1", required = false) Long donViId) {
		List<DmPhongBan> listPhongBan = phongBanBusiness.getListPhongBanInUseLimit20(ten, donViId);
		return ResponseEntity.ok(listPhongBan);
	}

	@GetMapping(value = "/inuse/appcode")
	public ResponseEntity<List<DmPhongBan>> getListPhongBanInUseLimit20AndAppCode(
			@RequestHeader(name = "APP_CODE") String appCode, @RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "donViId", defaultValue = "-1", required = false) Long donViId) {
		List<DmPhongBan> listPhongBan = phongBanBusiness.getListPhongBanInUseLimit20AndAppCode(appCode, ten, donViId);
		return ResponseEntity.ok(listPhongBan);
	}*/
}
