package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.api;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.business.KeHoachThangBusiness;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/kehoachthang")
public class KeHoachThangController {
	@Autowired
	KeHoachThangBusiness businessKeHoachThangBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<KeHoachThangData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thang", required = false) LocalDate thang,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay,
			@RequestParam(name = "tinhTrang", required = false) Integer tinhTrang) {
		Page<KeHoachThangData> pageKeHoachThangData = businessKeHoachThangBusiness.findAll(page, size, sortBy, sortDir, donViChuTriId,
				thang, tenNhiemVu, canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang);
		return ResponseEntity.ok(pageKeHoachThangData);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachThangData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		KeHoachThangData keHoachThangData = businessKeHoachThangBusiness.findById(id);
		return ResponseEntity.ok(keHoachThangData);
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<KeHoachThang> create(@Valid @RequestBody KeHoachThangData keHoachThangData)
			throws MethodArgumentNotValidException {
		KeHoachThang keHoachThang = businessKeHoachThangBusiness.create(keHoachThangData);
		return ResponseEntity.status(HttpStatus.CREATED).body(keHoachThang);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachThang> update(@PathVariable("id") Long id, @Valid @RequestBody KeHoachThangData keHoachThangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		KeHoachThang keHoachThang = businessKeHoachThangBusiness.update(id, keHoachThangData);
		return ResponseEntity.ok(keHoachThang);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachThangData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		KeHoachThangData keHoachThangData = businessKeHoachThangBusiness.delete(id);
		return ResponseEntity.ok(keHoachThangData);
	}
}
