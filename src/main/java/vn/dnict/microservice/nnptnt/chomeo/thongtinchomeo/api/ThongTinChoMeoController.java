package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.api;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.business.ChuQuanLyBusiness;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoData;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.ThongTinChoMeoBusiness;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.view.MauExcelThongTinChoMeoExporter;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/thongtinchomeo")

public class ThongTinChoMeoController {
	@Autowired
	ThongTinChoMeoBusiness businessThongTinChoMeoBusiness;
	@Autowired
	ChuQuanLyBusiness businessChuQuanLyBusiness;
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThongTinChoMeoData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "chuQuanLyId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "loaiDongVatId", required = false) Long loaiDongVatId,
			@RequestParam(name = "giongId", required = false) Long giongId,
			@RequestParam(name = "tenChuHo",required = false) String tenChuHo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgayTiemPhong", required = false) LocalDate tuNgayTiemPhong,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgayTiemPhong", required = false) LocalDate denNgayTiemPhong,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
			@RequestParam(name = "keHoachTiemPhongId", required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Page<ThongTinChoMeoData> pageThongTinChoMeoData = businessThongTinChoMeoBusiness.findAll(page, size, sortBy, sortDir, loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai
				);
		return ResponseEntity.ok(pageThongTinChoMeoData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ThongTinChoMeoData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessThongTinChoMeoBusiness.findById(id));
	}
	@GetMapping(value = { "/{id}/chuho" })
	public ResponseEntity<ChuQuanLyData> findByChuQuanLyId(@PathVariable("id") long id) throws EntityNotFoundException {
		ChuQuanLyData chuQuanLyData = businessChuQuanLyBusiness.findById(id);
		return ResponseEntity.ok(chuQuanLyData);
	}
	
	@GetMapping(value = "/{id}/chuquanly")
	public ResponseEntity<ChuQuanLyData> findChuQuanLyByThongTinChoMeoId(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ChuQuanLyData chuQuanLyData = businessThongTinChoMeoBusiness.findChuQuanLyByThongTinChoMeoId(id);
		return ResponseEntity.ok(chuQuanLyData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ThongTinChoMeoData> create(@Valid @RequestBody ThongTinChoMeoData thongTinChoMeoData) {
		thongTinChoMeoData = businessThongTinChoMeoBusiness.create(thongTinChoMeoData);
		return ResponseEntity.status(HttpStatus.CREATED).body(thongTinChoMeoData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinChoMeoData> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThongTinChoMeoData thongTinChoMeoData) throws EntityNotFoundException {
		thongTinChoMeoData = businessThongTinChoMeoBusiness.update(id, thongTinChoMeoData);
		return ResponseEntity.ok(thongTinChoMeoData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinChoMeoData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		ThongTinChoMeoData ThongTinChoMeoData = businessThongTinChoMeoBusiness.delete(id);
		return ResponseEntity.ok(ThongTinChoMeoData);
	}
	
	@GetMapping(value = { "/thongke" })
	public ResponseEntity<Page<ThongTinChoMeoData>> thongKe(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "chuQuanLyId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "loaiDongVatId", required = false) Long loaiDongVatId,
			@RequestParam(name = "giongId", required = false) Long giongId,
			@RequestParam(name = "tenChuHo",required = false) String tenChuHo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgayTiemPhong", required = false) LocalDate tuNgayTiemPhong,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgayTiemPhong", required = false) LocalDate denNgayTiemPhong,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
			@RequestParam(name = "keHoachTiemPhongId", required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Page<ThongTinChoMeoData> pageThongTinChoMeoData = businessThongTinChoMeoBusiness.thongKe(page, size, sortBy, sortDir, loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai
				);
		return ResponseEntity.ok(pageThongTinChoMeoData);
	}
	
	@GetMapping(value = { "/thongke/export" })
	public ModelAndView exportExcelThongKeChoMeo (HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "chuQuanLyId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "loaiDongVatId", required = false) Long loaiDongVatId,
			@RequestParam(name = "giongId", required = false) Long giongId,
			@RequestParam(name = "tenChuHo",required = false) String tenChuHo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "tuNgayTiemPhong", required = false) LocalDate tuNgayTiemPhong,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "denNgayTiemPhong", required = false) LocalDate denNgayTiemPhong,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
			@RequestParam(name = "keHoachTiemPhongId", required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		
		return businessThongTinChoMeoBusiness.exportExcelThongKeChoMeo(request, response, page, size, sortBy, sortDir, loaiDongVatId, giongId, tenChuHo, dienThoai, tuNgayTiemPhong, denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai);
	
	}
	
	@GetMapping("/mau/export/")
	public void exportExcelMau(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=mau.xlsx";
		response.setHeader(headerKey, headerValue);
		MauExcelThongTinChoMeoExporter exporterMauExcelExporter;
		exporterMauExcelExporter = new MauExcelThongTinChoMeoExporter();
		exporterMauExcelExporter.export(response);
	}
}
