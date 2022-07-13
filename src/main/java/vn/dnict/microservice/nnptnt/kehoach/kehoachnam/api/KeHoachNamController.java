package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.api;

import java.time.LocalDate;
import java.util.List;

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
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.business.KeHoachNamBusiness;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/kehoachnam")
public class KeHoachNamController {
	@Autowired
	KeHoachNamBusiness businessKeHoachNamBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<KeHoachNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai,
			@RequestParam(name = "nam", required = false) Integer nam,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@RequestParam(name = "tenKeHoach", required = false) String tenKeHoach,
			@RequestParam(name = "soKyHieu", required = false) String soKyHieu,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgayBanHanh", required = false) LocalDate tuNgayBanHanh,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgayBanHanh", required = false) LocalDate denNgayBanHanh) {

		Page<KeHoachNamData> pageKeHoachNamData = businessKeHoachNamBusiness.findAll(page, size, sortBy, sortDir,
				donViChuTriId, nam, tenKeHoach, soKyHieu, trangThai, tuNgayBanHanh, denNgayBanHanh);
		return ResponseEntity.ok(pageKeHoachNamData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<KeHoachNamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		KeHoachNamData keHoachNam = businessKeHoachNamBusiness.findById(id);
		return ResponseEntity.ok(keHoachNam);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<KeHoachNamData> create(@Valid @RequestBody KeHoachNamData keHoachNamData)
			throws MethodArgumentNotValidException {
		keHoachNamData = businessKeHoachNamBusiness.create(keHoachNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(keHoachNamData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachNamData> update(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoachNamData keHoachNamData) throws EntityNotFoundException, MethodArgumentNotValidException {
		keHoachNamData = businessKeHoachNamBusiness.update(id, keHoachNamData);
		return ResponseEntity.ok(keHoachNamData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachNamData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		KeHoachNamData keHoachNamData = businessKeHoachNamBusiness.delete(id);
		return ResponseEntity.ok(keHoachNamData);
	}
	
	//-------------Thong ke ke hoach-----------------------------
	@GetMapping(value = { "/thongKe" })
	public ResponseEntity<Page<KeHoachNamData>> thongKe(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "tinhTrangs",required = false) List<Integer> tinhTrangs,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu) {
		Page<KeHoachNamData> pageThongKe = businessKeHoachNamBusiness.thongKe(page, size, sortBy, sortDir,
				donViChuTriId, nam, keHoachId, tinhTrangs, tuNgay, denNgay, tenNhiemVu);
		return ResponseEntity.ok(pageThongKe);
	}
}
