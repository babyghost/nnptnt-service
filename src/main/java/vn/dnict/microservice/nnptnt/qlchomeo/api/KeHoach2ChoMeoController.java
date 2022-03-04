package vn.dnict.microservice.nnptnt.qlchomeo.api;

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
import vn.dnict.microservice.nnptnt.qlchomeo.business.KeHoach2ChoMeoBusiness;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.qlchomeo.data.KeHoach2ChoMeoInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/kehoach2chomeo")
public class KeHoach2ChoMeoController {
	@Autowired
	KeHoach2ChoMeoBusiness businessKeHoach2ChoMeoBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<KeHoach2ChoMeo>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "thongTinChoMeoId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "thongTinChoMeoId", required = false) Long thongTinChoMeoId,
			@RequestParam(name = "keHoachTiemPhongId", required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "trangThaiTiem",required=false) boolean trangThaiTiem) {
		Page<KeHoach2ChoMeo> pageKeHoach2ChoMeo = businessKeHoach2ChoMeoBusiness.findAll(page, size, sortBy, sortDir, search, 
				thongTinChoMeoId, keHoachTiemPhongId, trangThaiTiem);
		return ResponseEntity.ok(pageKeHoach2ChoMeo);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<KeHoach2ChoMeo> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessKeHoach2ChoMeoBusiness.findById(id));
	}




	@PostMapping(value = { "" })
	public ResponseEntity<KeHoach2ChoMeo> create(
			@Valid @RequestBody KeHoach2ChoMeoInput KeHoach2ChoMeoInput) {
		KeHoach2ChoMeo KeHoach2ChoMeo = businessKeHoach2ChoMeoBusiness.create(KeHoach2ChoMeoInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(KeHoach2ChoMeo);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoach2ChoMeo> update(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoach2ChoMeoInput KeHoach2ChoMeoInput) throws EntityNotFoundException {
		KeHoach2ChoMeo KeHoach2ChoMeo = businessKeHoach2ChoMeoBusiness.update(id, KeHoach2ChoMeoInput);
		return ResponseEntity.ok(KeHoach2ChoMeo);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoach2ChoMeo> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		KeHoach2ChoMeo KeHoach2ChoMeo = businessKeHoach2ChoMeoBusiness.delete(id);
		return ResponseEntity.ok(KeHoach2ChoMeo);
	}
}
