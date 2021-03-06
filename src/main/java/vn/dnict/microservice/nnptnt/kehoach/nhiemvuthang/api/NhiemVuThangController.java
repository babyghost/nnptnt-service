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
			@RequestParam(name = "thang", required = false) List<LocalDate> thangs,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@RequestParam(name = "tinhTrang", required = false) List<Integer> tinhTrangs,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay) {
		Page<NhiemVuThangData> pageNhiemVuThangData = businessNhiemVuThangBusiness.findAll(page, size, sortBy, sortDir,
				donViChuTriId, thangs, canBoThucHienId, tinhTrangs, tenNhiemVu, thoiHanTuNgay, thoiHanDenNgay);
		return ResponseEntity.ok(pageNhiemVuThangData);
	}
	
	@GetMapping(value = { "/thongkesoluong" })
	public ResponseEntity<String> getThongKeSoLuong(
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@RequestParam(name = "thangs", required = false) List<LocalDate> thangs,
			@RequestParam(name = "keHoachThangId", required = false) Long keHoachThangId,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@RequestParam(name = "tinhTrangs", required = false) List<Integer> tinhTrangs,
			@RequestParam(name = "canBoThucHienId", required = false) Long canBoThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgay", required = false) LocalDate denNgay) {

		String thongKe = businessNhiemVuThangBusiness.getThongKeSoLuong(donViChuTriId, thangs, keHoachThangId, tenNhiemVu,
				tinhTrangs, canBoThucHienId, tuNgay, denNgay);
		return ResponseEntity.ok(thongKe);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<NhiemVuThangData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuThangData nhiemVuThang = businessNhiemVuThangBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuThang);
	}

	@PostMapping(value = { "/{nhiemVuId}" })
	public ResponseEntity<NhiemVuThangData> saveTienDo(@PathVariable("nhiemVuId") Long nhiemVuId,
			@Valid @RequestBody NhiemVuThangData nhiemVuThangData) throws EntityNotFoundException {
		nhiemVuThangData = businessNhiemVuThangBusiness.saveTienDo(nhiemVuId, nhiemVuThangData);
		return ResponseEntity.ok(nhiemVuThangData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuThangData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuThangData nhiemVuThangData = businessNhiemVuThangBusiness.delete(id);
		return ResponseEntity.ok(nhiemVuThangData);
	}
}
