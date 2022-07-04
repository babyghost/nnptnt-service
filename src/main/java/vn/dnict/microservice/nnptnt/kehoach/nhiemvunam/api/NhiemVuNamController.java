package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.api;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.business.KeHoachNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.NhiemVuNamBusiness;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

@CrossOrigin
@RestController
@RequestMapping(value = "/kehoach/nhiemvunam")
public class NhiemVuNamController {
	@Autowired
	NhiemVuNamBusiness businessNhiemVuNamBusiness;
	
	@Autowired
	KeHoachNamBusiness businessKeHoachNamBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<NhiemVuNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
			@RequestParam(name = "tinhTrangs", required = false) List<Integer> tinhTrangs,
			@RequestParam(name = "nam", required = false) Integer nam,
			@RequestParam(name = "keHoachId", required = false) Long keHoachId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgay", required = false) LocalDate denNgay,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu) {

		Page<NhiemVuNamData> pageNhiemVuNamData = businessNhiemVuNamBusiness.findAll(page, size, sortBy, sortDir,
				donViChuTriId, tinhTrangs, nam, keHoachId, tuNgay, denNgay, tenNhiemVu);
		return ResponseEntity.ok(pageNhiemVuNamData);
	}

//	@GetMapping(value = { "/thongkesoluong" })
//	public ResponseEntity<String> getThongKeSoLuong(
//			@RequestParam(name = "donViChuTriId", required = false) Long donViChuTriId,
//			@RequestParam(name = "nam", required = false) Integer nam,
//			@RequestParam(name = "keHoachId", required = false) Long keHoachId,
//			@RequestParam(name = "tinhTrangs", required = false) List<Integer> tinhTrangs,
//			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
//			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgay", required = false) LocalDate denNgay,
//			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu) {
//
//		String thongKe = businessNhiemVuNamBusiness.getThongKeSoLuong(donViChuTriId, nam, keHoachId, tinhTrangs, tuNgay, denNgay,
//				tenNhiemVu);
//		return ResponseEntity.ok(thongKe);
//	}
	
	@GetMapping(value = { "/thongKe" })
	public ResponseEntity<Page<ThongKeKeHoachNamData>> thongKe(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tuNgay", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "tinhTrang",required = false) List<Integer> tinhTrangs,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu) {
		Page<ThongKeKeHoachNamData> pageThongKeKeHoachNam = businessNhiemVuNamBusiness.thongKe(page, size, sortBy, sortDir,
				donViChuTriId, nam, keHoachId, tinhTrangs, tuNgay, denNgay, tenNhiemVu);
		return ResponseEntity.ok(pageThongKeKeHoachNam);
	}
	
	@GetMapping(value = { "/thongKe/export" })
	public ModelAndView thongKeKeHoachNam(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tuNgay", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "tinhTrang",required = false) List<Integer> tinhTrangs,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu) {
		return businessNhiemVuNamBusiness.exportExcelThongKeKeHoachNam(request, response, page, size, sortBy, sortDir, donViChuTriId,
				 nam, keHoachId, tinhTrangs, tuNgay, denNgay, tenNhiemVu);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<NhiemVuNamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNam = businessNhiemVuNamBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuNam);
	}

	@PostMapping(value = { "/{nhiemVuNamId}" })
	public ResponseEntity<NhiemVuNamData> saveTienDo(@PathVariable("nhiemVuNamId") Long nhiemVuNamId,
			@Valid @RequestBody NhiemVuNamData nhiemVuNamData) throws EntityNotFoundException {
		nhiemVuNamData = businessNhiemVuNamBusiness.saveTienDo(nhiemVuNamId, nhiemVuNamData);
		return ResponseEntity.ok(nhiemVuNamData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.delete(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}

	@DeleteMapping(value = { "/{nhiemVuNamId}/delete/tiendo/{tienDoId}" })
	public ResponseEntity<Void> deleteTienDo(@PathVariable("nhiemVuNamId") Long nhiemVuNamId,
			@PathVariable("tienDoId") Long tienDoId) throws EntityNotFoundException {
		businessNhiemVuNamBusiness.deleteTienDo(tienDoId);
		return ResponseEntity.ok().build();
	}
}
