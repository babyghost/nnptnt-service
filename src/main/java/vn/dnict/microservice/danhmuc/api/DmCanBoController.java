package vn.dnict.microservice.danhmuc.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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

import vn.dnict.microservice.danhmuc.business.DmCanBoBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.data.DmCanBoInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
@CrossOrigin
@Controller
@RequestMapping(value = "/danhmuc/canbo")
public class DmCanBoController {
	@Autowired
	private DmCanBoBusiness canBoBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmCanBo>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "hoTen", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "hoTen", required = false) String hoTen,
			@RequestParam(name = "diaChi", required = false) String diaChi,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "cmndSo", required = false) String cmndSo,
			@RequestParam(name = "donViId", defaultValue = "-1", required = false) Long donViId,
			@RequestParam(name = "donViChaId", defaultValue = "-1", required = false) Long donViChaId,
			@RequestParam(name = "chucVuId", defaultValue = "-1", required = false) Long chucVuId,
			@RequestParam(name = "phongBanId", defaultValue = "-1", required = false) Long phongBanId,
			@RequestParam(name = "gioiTinhId", defaultValue = "-1", required = false) Long gioiTinhId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgaySinh", required = false) LocalDate tuNgaySinh,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgaySinh", required = false) LocalDate denNgaySinh) {

		Page<DmCanBo> pageCanBo = canBoBusiness.findAll(page, size, sortBy, sortDir, search, hoTen, phongBanId, donViId,
				chucVuId, email, diaChi, cmndSo, gioiTinhId, tuNgaySinh, denNgaySinh, donViChaId);
		return ResponseEntity.ok(pageCanBo);
	}

	@GetMapping(value = { "/phongbanid" })
	public ResponseEntity<List<DmCanBo>> findAll(
			@RequestParam(name="phongBanId", defaultValue = "", required = true) Long phongBanId,
			@RequestParam(name="daXoa", defaultValue = "0", required = true) Integer daXoa
			) throws EntityNotFoundException {
		List<DmCanBo> list = canBoBusiness.findByPhongBan(phongBanId, daXoa);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DmCanBo> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmCanBo canBo = canBoBusiness.findById(id);
		return ResponseEntity.ok(canBo);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<DmCanBo> findByEmail(@PathVariable("email") String email) throws EntityNotFoundException {
		DmCanBo canBo = canBoBusiness.findByEmail(email);
		return ResponseEntity.ok(canBo);
	}
	

	@GetMapping(value = "/email/{email}/donvi")
	public ResponseEntity<DmDonVi> findDonViByEmail(@PathVariable("email") String email)
			throws EntityNotFoundException {
		DmDonVi donVi = canBoBusiness.findDonViByEmail(email);
		return ResponseEntity.ok(donVi);
	}

	@GetMapping(value = "/email/{email}/phongban")
	public ResponseEntity<DmPhongBan> findPhongBanByEmail(@PathVariable("email") String email)
			throws EntityNotFoundException {
		DmPhongBan phongBan = canBoBusiness.findPhongBanByEmail(email);
		return ResponseEntity.ok(phongBan);
	}

	@GetMapping(value = "/{id}/donvi")
	public ResponseEntity<DmDonVi> findDonViByCanBoId(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmDonVi donVi = canBoBusiness.findDonViByCanBoId(id);
		return ResponseEntity.ok(donVi);
	}

	@GetMapping(value = "/{id}/phongban")
	public ResponseEntity<DmPhongBan> findPhongBanByCanBoId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DmPhongBan phongBan = canBoBusiness.findPhongBanByCanBoId(id);
		return ResponseEntity.ok(phongBan);
	}

	@GetMapping(value = "ids/{ids}")
	public ResponseEntity<List<DmCanBo>> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return ResponseEntity.ok(canBoBusiness.findByIdIn(ids));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmCanBo> create(@Valid @RequestBody DmCanBoInput canBoInput) throws EntityNotFoundException {
		DmCanBo canBo = canBoBusiness.create(canBoInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(canBo);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmCanBo> update(@PathVariable("id") Long id, @Valid @RequestBody DmCanBoInput canBoInput)
			throws EntityNotFoundException {
		DmCanBo canBo = canBoBusiness.update(id, canBoInput);
		return ResponseEntity.ok().body(canBo);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmCanBo> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmCanBo canBo = canBoBusiness.delete(id);
		return ResponseEntity.ok().body(canBo);
	}

}
