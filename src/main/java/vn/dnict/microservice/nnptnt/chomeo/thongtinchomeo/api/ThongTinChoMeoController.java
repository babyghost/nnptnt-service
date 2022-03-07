package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.ThongTinChoMeoBusiness;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo; 

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/thongtinchomeo")

public class ThongTinChoMeoController {
	@Autowired
	ThongTinChoMeoBusiness businessThongTinChoMeoBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThongTinChoMeo>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenConVat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenConVat", required = false) String tenConVat,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<ThongTinChoMeo> pageThongTinChoMeo = businessThongTinChoMeoBusiness.findAll(page, size, sortBy, sortDir, 
				tenConVat, trangThai);
		return ResponseEntity.ok(pageThongTinChoMeo);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ThongTinChoMeo> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessThongTinChoMeoBusiness.findById(id));
	}
	
	@GetMapping(value = "/{id}/chuquanly")
	public ResponseEntity<ChuQuanLy> findChuQuanLyByThongTinChoMeoId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ChuQuanLy chuQuanLy = businessThongTinChoMeoBusiness.findChuQuanLyByThongTinChoMeoId(id);
		return ResponseEntity.ok(chuQuanLy);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ThongTinChoMeo> create(
			@Valid @RequestBody ThongTinChoMeoInput ThongTinChoMeoInput) {
		ThongTinChoMeo ThongTinChoMeo = businessThongTinChoMeoBusiness.create(ThongTinChoMeoInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(ThongTinChoMeo);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinChoMeo> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThongTinChoMeoInput ThongTinChoMeoInput) throws EntityNotFoundException {
		ThongTinChoMeo ThongTinChoMeo = businessThongTinChoMeoBusiness.update(id, ThongTinChoMeoInput);
		return ResponseEntity.ok(ThongTinChoMeo);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinChoMeo> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ThongTinChoMeo ThongTinChoMeo = businessThongTinChoMeoBusiness.delete(id);
		return ResponseEntity.ok(ThongTinChoMeo);
	}
}
