package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.api;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business.NhiemVuThangBusiness;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/nhiemvuthang")
public class NhiemVuThangController {
	@Autowired
	NhiemVuThangBusiness businessNhiemVuThangBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<NhiemVuThangData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thang", required = false) List<LocalDate> thangs,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay,
			@RequestParam(name = "tinhTrang", required = false) Integer tinhTrang) {
		Page<NhiemVuThangData> pageNhiemVuThangData = businessNhiemVuThangBusiness.findAll(page, size, sortBy, sortDir,
				donViChuTriId, thangs, tenNhiemVu, canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang);
		return ResponseEntity.ok(pageNhiemVuThangData);
	}
	
	@GetMapping(value = { "/thongKeKeHoachNam" })
	public ResponseEntity<Page<ThongKeKeHoachThangData>> thongKeKeHoachThang(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thang", required = false) List<LocalDate> thangs,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@RequestParam(name = "tinhTrang", required = false) Integer tinhTrang,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay) {
		Page<ThongKeKeHoachThangData> pageThongKeKeHoachThang = businessNhiemVuThangBusiness.thongKeKeHoachThang(page, size, sortBy, sortDir,
				donViChuTriId, thangs, tenNhiemVu, tinhTrang, canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay);
		return ResponseEntity.ok(pageThongKeKeHoachThang);
	}
	
	@GetMapping(value = { "/thongKeKeHoachNam/export" })
	public ModelAndView thongKeKeHoachThang(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thang", required = false) List<LocalDate> thangs,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@RequestParam(name = "tinhTrang", required = false) Integer tinhTrang,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay) {
		return businessNhiemVuThangBusiness.exportExcelThongKeKeHoachThang(request, response, page, size, sortBy, sortDir,
				donViChuTriId, thangs, tenNhiemVu, tinhTrang, canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuThangData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuThangData nhiemVuThangData = businessNhiemVuThangBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuThangData);
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<NhiemVuThangData> create(@Valid @RequestBody NhiemVuThangData nhiemVuThangData)
			throws MethodArgumentNotValidException {
		nhiemVuThangData = businessNhiemVuThangBusiness.create(nhiemVuThangData);
		return ResponseEntity.ok(nhiemVuThangData);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuThangData> update(@PathVariable("id") Long id, @Valid @RequestBody NhiemVuThangData
			nhiemVuThangData) throws EntityNotFoundException, MethodArgumentNotValidException {
		nhiemVuThangData = businessNhiemVuThangBusiness.update(id, nhiemVuThangData);
		return ResponseEntity.ok(nhiemVuThangData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuThangData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuThangData nhiemVuThangData = businessNhiemVuThangBusiness.delete(id);
		return ResponseEntity.ok(nhiemVuThangData);
	}
}
