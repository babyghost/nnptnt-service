package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.api;

import java.time.LocalDate;

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
			@RequestParam(name = "sortBy", defaultValue = "tuNgay", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "keHoachNamId",required = false) Long keHoachNamId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "tinhTrang",required = false) Integer tinhTrang,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay) {
		Page<NhiemVuNamData> pageNhiemVuNamData = businessNhiemVuNamBusiness.findAll(page, size, sortBy, sortDir, donViChuTriId,
				keHoachNamId, nam, tinhTrang, tenNhiemVu, tuNgay, denNgay);
		System.out.println("11111111111"+ donViChuTriId);
		return ResponseEntity.ok(pageNhiemVuNamData);
	}
	
	@GetMapping(value = { "/thongKeKeHoachNam" })
	public ResponseEntity<Page<ThongKeKeHoachNamData>> thongKeKeHoachNam(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tuNgay", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "tinhTrang",required = false) Integer tinhTrang,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay) {
		Page<ThongKeKeHoachNamData> pageThongKeKeHoachNam = businessNhiemVuNamBusiness.thongKeKeHoachNam(page, size, sortBy, sortDir,
				donViChuTriId, keHoachId, nam, tinhTrang, tenNhiemVu, tuNgay, denNgay);
		return ResponseEntity.ok(pageThongKeKeHoachNam);
	}
	
	@GetMapping(value = { "/thongKeKeHoachNam/export" })
	public ModelAndView thongKeKeHoachNam(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tuNgay", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViChuTriId",required = false) Long donViChuTriId,
			@RequestParam(name = "keHoachId",required = false) Long keHoachId,
			@RequestParam(name = "nam",required = false) Integer nam,
			@RequestParam(name = "tinhTrang",required = false) Integer tinhTrang,
			@RequestParam(name = "tenNhiemVu", required = false) String tenNhiemVu,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgay", required = false) LocalDate tuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgay", required = false) LocalDate denNgay) {
		return businessNhiemVuNamBusiness.exportExcelThongKeKeHoachNam(request, response, page, size, sortBy, sortDir, donViChuTriId,
				keHoachId, nam, tinhTrang, tenNhiemVu, tuNgay, denNgay);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.findById(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}
	
	@GetMapping(value = { "/{id}/kehoachnam" })
	public ResponseEntity<KeHoachNamData> findByKeHoachNamId(@PathVariable("id") long id) throws EntityNotFoundException {
		KeHoachNamData keHoachNamData = businessKeHoachNamBusiness.findById(id);
		return ResponseEntity.ok(keHoachNamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<NhiemVuNam> create(@Valid @RequestBody NhiemVuNamData nhiemVuNamData) throws MethodArgumentNotValidException {
		NhiemVuNam nhiemVuNam = businessNhiemVuNamBusiness.create(nhiemVuNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(nhiemVuNam);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNam> update(@PathVariable("id") Long id, @Valid @RequestBody NhiemVuNamData nhiemVuNamData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		NhiemVuNam nhiemVuNam = businessNhiemVuNamBusiness.update(id, nhiemVuNamData);
		return ResponseEntity.ok(nhiemVuNam);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<NhiemVuNamData> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		NhiemVuNamData nhiemVuNamData = businessNhiemVuNamBusiness.delete(id);
		return ResponseEntity.ok(nhiemVuNamData);
	}
}