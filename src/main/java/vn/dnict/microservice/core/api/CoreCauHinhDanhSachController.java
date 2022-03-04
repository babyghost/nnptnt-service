package vn.dnict.microservice.core.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import vn.dnict.microservice.core.business.CoreCauHinhDanhSachBusiness;
import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;
import vn.dnict.microservice.core.dao.model.DanhSachHienThi;
import vn.dnict.microservice.core.data.CoreCauHinhDanhSachInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/core/cauhinhdanhsach")
//@Slf4j
public class CoreCauHinhDanhSachController {
	@Autowired
	private CoreCauHinhDanhSachBusiness coreCauHinhDanhSachBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreCauHinhDanhSach>> findAll(// @RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "sapXep", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "maDanhSach", defaultValue = "maDanhSach", required = false) String maDanhSach,
			@RequestParam(name = "tenCot", defaultValue = "tenCot", required = false) String tenCot,
			@RequestParam(name = "trangThai", defaultValue = "true", required = false) Boolean trangThai) {
		Page<CoreCauHinhDanhSach> pageList = coreCauHinhDanhSachBusiness.findAll(page, size, sortBy, sortDir,
				maDanhSach, tenCot, trangThai);
		return ResponseEntity.ok(pageList);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreCauHinhDanhSach> findOne(@PathVariable("id") long id) throws EntityNotFoundException {
		return ResponseEntity.ok(coreCauHinhDanhSachBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreCauHinhDanhSach> create(@Valid @RequestBody CoreCauHinhDanhSachInput modelInput) {
		CoreCauHinhDanhSach model = coreCauHinhDanhSachBusiness.create("", modelInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(model);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreCauHinhDanhSach> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoreCauHinhDanhSachInput modelInput) throws EntityNotFoundException {
		CoreCauHinhDanhSach model = coreCauHinhDanhSachBusiness.update("", id, modelInput);
		return ResponseEntity.ok(model);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreCauHinhDanhSach> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreCauHinhDanhSach model = coreCauHinhDanhSachBusiness.delete("", id);
		return ResponseEntity.ok(model);
	}

	@GetMapping(value = "/inuse")
	public ResponseEntity<List<DanhSachHienThi>> findByMaDanhSachAndNguoiSuDung(
			@RequestParam(name = "maDanhSach", defaultValue = "", required = false) String maDanhSach,
			@RequestParam(name = "nguoiSuDung", defaultValue = "", required = false) String nguoiSuDung) {
		return ResponseEntity
				.ok(coreCauHinhDanhSachBusiness.findByMaDanhSachAndNguoiSuDung(maDanhSach, nguoiSuDung));
	}

}