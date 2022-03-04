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

import vn.dnict.microservice.danhmuc.business.DmDonViBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;
import vn.dnict.microservice.danhmuc.data.DmDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@Controller
@RequestMapping(value = "/danhmuc/donvi")
public class DmDonViController {
	@Autowired
	private DmDonViBusiness donViBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmDonVi>> findAll(//@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenDonVi", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tenDonVi", required = false) String tenDonVi,
			@RequestParam(name = "diaChi", required = false) String diaChi,
			@RequestParam(name = "soDienThoai", required = false) String soDienThoai,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "donViChaId", defaultValue = "-1", required = false) Long donViChaId,
			@RequestParam(name = "capId", defaultValue = "-1", required = false) Long capId,
			@RequestParam(name = "loaiDonViId", defaultValue = "-1", required = false) Long loaiDonViId) {
		Page<DmDonVi> pageDonVi = donViBusiness.findAll(page, size, sortBy, sortDir, search, tenDonVi, diaChi,
				soDienThoai, email, donViChaId, capId, loaiDonViId);
		return ResponseEntity.ok(pageDonVi);
	}

	@GetMapping(value = "/{id}/donvicha")
	public ResponseEntity<DmDonVi> findDonViChaByDonViId(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmDonVi donVi = donViBusiness.findDonViChaByDonViId(id);
		return ResponseEntity.ok(donVi);
	}

	@GetMapping(value = "/{id}/capdonvi")
	public ResponseEntity<DmCapDonVi> findCapDonViByDonViId(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmCapDonVi capDonVi = donViBusiness.findCapDonViByDonViId(id);
		return ResponseEntity.ok(capDonVi);
	}

	@GetMapping(value = "/{id}/loaidonvi")
	public ResponseEntity<DmLoaiDonVi> findLoaiDonViByDonViId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmLoaiDonVi loaiDonVi = donViBusiness.findLoaiDonViByDonViId(id);
		return ResponseEntity.ok(loaiDonVi);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmDonVi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmDonVi donVi = donViBusiness.findById(id);
		return ResponseEntity.ok(donVi);
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmDonVi>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(donViBusiness.findByIdIn(ids));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmDonVi> create(
			@Valid @RequestBody DmDonViInput donViInput) {
		DmDonVi donVi = donViBusiness.create(donViInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(donVi);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmDonVi> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmDonViInput donViInput) throws EntityNotFoundException {
		DmDonVi donVi = donViBusiness.update(id, donViInput);
		return ResponseEntity.ok().body(donVi);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmDonVi> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmDonVi donVi = donViBusiness.delete(id);
		return ResponseEntity.ok().body(donVi);
	}

	@GetMapping(value = { "/select" })
	public ResponseEntity<List<DmDonVi>> select() {
		List<DmDonVi> listCha = donViBusiness.select();
		return ResponseEntity.ok().body(listCha);
	}

	/*@GetMapping(value = "/inuse")
	public ResponseEntity<List<DmDonVi>> getListDonViInUseLimit20(
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tenDonVi", required = false) String tenDonVi,
			@RequestParam(name = "diaChi", required = false) String diaChi,
			@RequestParam(name = "soDienThoai", required = false) String soDienThoai,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "donViChaId", defaultValue = "-1", required = false) Long donViChaId,
			@RequestParam(name = "capId", defaultValue = "-1", required = false) Long capId,
			@RequestParam(name = "loaiDonViId", defaultValue = "-1", required = false) Long loaiDonViId) {
		List<DmDonVi> listDonVi = donViBusiness.getListDonViInUseLimit20(search, tenDonVi, diaChi, soDienThoai, email,
				donViChaId, capId, loaiDonViId);
		return ResponseEntity.ok(listDonVi);
	}

	@GetMapping(value = "/inuse/appcode")
	public ResponseEntity<List<DmDonVi>> getListDonViInUseLimit20AndAppCode(
			@RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tenDonVi", required = false) String tenDonVi,
			@RequestParam(name = "diaChi", required = false) String diaChi,
			@RequestParam(name = "soDienThoai", required = false) String soDienThoai,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "donViChaId", defaultValue = "-1", required = false) Long donViChaId,
			@RequestParam(name = "capId", defaultValue = "-1", required = false) Long capId,
			@RequestParam(name = "loaiDonViId", defaultValue = "-1", required = false) Long loaiDonViId) {
		List<DmDonVi> listDonVi = donViBusiness.getListDonViInUseLimit20AndAppCode(appCode, search, tenDonVi, diaChi,
				soDienThoai, email, donViChaId, capId, loaiDonViId);
		return ResponseEntity.ok(listDonVi);
	}*/
}
