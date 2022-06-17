package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.business.TienDoNhiemVuThangBusiness;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/tiendonhiemvuthang")
public class TienDoNhiemVuThangController {
	@Autowired
	TienDoNhiemVuThangBusiness businessTienDoNhiemVuThangBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<TienDoNhiemVuThangData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenNguoiCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenNguoiCapNhat", required = false) String tenNguoiCapNhat,
			@RequestParam(name = "mucDoHoanThanh", required = false) Integer mucDoHoanThanh,
			@RequestParam(name = "ketQua", required = false) String ketQua) {
		Page<TienDoNhiemVuThangData> pageTienDoThangData = businessTienDoNhiemVuThangBusiness.findAll(page, size, sortBy, sortDir,
				tenNguoiCapNhat, mucDoHoanThanh, ketQua);
		return ResponseEntity.ok(pageTienDoThangData);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuThangData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		TienDoNhiemVuThangData tienDoNhiemVuThangData = businessTienDoNhiemVuThangBusiness.findById(id);
		return ResponseEntity.ok(tienDoNhiemVuThangData);
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<TienDoNhiemVuThang> create(@Valid @RequestBody TienDoNhiemVuThangData tienDoNhiemVuThangData)
			throws MethodArgumentNotValidException {
		TienDoNhiemVuThang tienDoNhiemVuThang = businessTienDoNhiemVuThangBusiness.create(tienDoNhiemVuThangData);
		return ResponseEntity.status(HttpStatus.CREATED).body(tienDoNhiemVuThang);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuThang> update(@PathVariable("id") Long id,@Valid @RequestBody TienDoNhiemVuThangData 
			tienDoNhiemVuThangData) throws EntityNotFoundException, MethodArgumentNotValidException {
		TienDoNhiemVuThang tienDoNhiemVuThang = businessTienDoNhiemVuThangBusiness.update(id, tienDoNhiemVuThangData);
		return ResponseEntity.ok(tienDoNhiemVuThang);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<TienDoNhiemVuThangData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		TienDoNhiemVuThangData tienDoNhiemVuThangData = businessTienDoNhiemVuThangBusiness.delete(id);
		return ResponseEntity.ok(tienDoNhiemVuThangData);
	}
}
