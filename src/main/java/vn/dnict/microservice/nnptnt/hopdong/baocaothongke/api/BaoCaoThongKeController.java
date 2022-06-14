package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.api;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.business.BaoCaoThongKeBusiness;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;

@CrossOrigin
@RestController
@RequestMapping(value = "/hopdong/baocaothongke")
public class BaoCaoThongKeController {
	@Autowired
	private BaoCaoThongKeBusiness businessBaoCaoThongKeBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<BaoCaoThongKe>> baoCaoThongKe(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenHopDong", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenHopDong", required = false) String tenHopDong,
			@RequestParam(name = "loaiHopDongId", defaultValue = "-1", required = false) Long loaiHopDongId,
			@RequestParam(name = "dvthTen", required = false) String dvthTen,
			@RequestParam(name = "cnthTen", required = false) String cnthTen,
			@RequestParam(name = "trangThai", required = false) Integer trangThai,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuThanhToanNgay", required = false) LocalDate tuThanhToanNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denThanhToanNgay", required = false) LocalDate denThanhToanNgay) {
		Page<BaoCaoThongKe> pageBaoCaoThongKe = businessBaoCaoThongKeBusiness.baoCaoThongKe(page, size,
				sortBy, sortDir, tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay,
				trangThai);
		return ResponseEntity.ok(pageBaoCaoThongKe);
	}
	
	@GetMapping(value = { "/export" })
	public ModelAndView  export(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenHopDong", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenHopDong", required = false) String tenHopDong,
			@RequestParam(name = "loaiHopDongId", defaultValue = "-1", required = false) Long loaiHopDongId,
			@RequestParam(name = "dvthTen", required = false) String dvthTen,
			@RequestParam(name = "cnthTen", required = false) String cnthTen,
			@RequestParam(name = "trangThai", required = false) Integer trangThai,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuThanhToanNgay", required = false) LocalDate tuThanhToanNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denThanhToanNgay", required = false) LocalDate denThanhToanNgay) {
		return businessBaoCaoThongKeBusiness.exportExcelBaoCaoHopDong(request, response, page, size, sortBy, sortDir, tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay, trangThai);
	}
}
