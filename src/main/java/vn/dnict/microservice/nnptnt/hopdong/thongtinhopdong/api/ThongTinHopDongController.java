package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.api;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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

import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.hopdong.data.ThongTinHopDongInput;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.business.ThongTinHopDongBusiness;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;

@CrossOrigin
@RestController
@RequestMapping(value = "/hopdong/thongtinhopdong")

public class ThongTinHopDongController {
	@Autowired
	private ThongTinHopDongBusiness businessThongTinHopDongBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThongTinHopDong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "soHieu", required = false) String soHieu,
			@RequestParam(name = "loaiHopDongId", defaultValue = "-1", required = false) Long loaiHopDongId,
			@RequestParam(name = "trangThai", defaultValue = "-1", required = false) Integer trangThai,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgayKy", required = false) LocalDate tuNgayKy,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgayKy", required = false) LocalDate denNgayKy,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThTuNgay", required = false) LocalDate thoiGianThTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThDenNgay", required = false) LocalDate thoiGianThDenNgay) {
		Page<ThongTinHopDong> pageThongTinHopDong = businessThongTinHopDongBusiness.findAll(page, size,
				sortBy, sortDir, search, ten, soHieu, loaiHopDongId, trangThai, tuNgayKy, denNgayKy, thoiGianThTuNgay,
				thoiGianThDenNgay);
		return ResponseEntity.ok(pageThongTinHopDong);
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Long> countHopDongSapHetHan() throws EntityNotFoundException {
		Long count = businessThongTinHopDongBusiness.countHopDongSapHetHan();
		return ResponseEntity.ok(count);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ThongTinHopDongInput> findById(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ThongTinHopDongInput thongTinHopDongInput = businessThongTinHopDongBusiness.findById(id);
		return ResponseEntity.ok(thongTinHopDongInput);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ThongTinHopDong> create(
			@Valid @RequestBody ThongTinHopDongInput thongTinHopDongInput) {
		ThongTinHopDong thongTinHopDong = businessThongTinHopDongBusiness
				.create(thongTinHopDongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(thongTinHopDong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinHopDong> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThongTinHopDongInput thongTinHopDongInput)
			throws EntityNotFoundException {
		ThongTinHopDong thongTinHopDong = businessThongTinHopDongBusiness.update(id, thongTinHopDongInput);
		return ResponseEntity.ok(thongTinHopDong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinHopDong> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		ThongTinHopDong thongTinHopDong = businessThongTinHopDongBusiness.delete(id);
		return ResponseEntity.ok(thongTinHopDong);
	}

}
