package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.business.view.MyExcelViewThongKeHopDong;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.service.BaoCaoThongKeService;
import vn.dnict.microservice.nnptnt.ocop.chuthe.bussiness.view.MyExcelViewChuThe;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
import vn.dnict.microservice.nnptnt.ocop.data.DoanhNghiepData;

@Service
public class BaoCaoThongKeBusiness {
	@Autowired
	private BaoCaoThongKeService serviceBaoCaoThongKeService;

	public Page<BaoCaoThongKe> baoCaoThongKe(int page, int size, String sortBy, String sortDir,
			String tenHopDong, Long loaiHopDongId, String dvthTen, String cnthTen, LocalDate tuThanhToanNgay,
			LocalDate denThanhToanNgay, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;  
		}
		Page<BaoCaoThongKe> pageBaoCaoThongKe = serviceBaoCaoThongKeService.getBaoCaoThongKe(
				tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay, trangThai,
				PageRequest.of(page, size, direction, sortBy));

		return pageBaoCaoThongKe;
	}
	public ModelAndView exportExcelBaoCaoHopDong(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,String tenHopDong, Long loaiHopDongId, String dvthTen, String cnthTen, LocalDate tuThanhToanNgay,
			LocalDate denThanhToanNgay, Integer trangThai) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		Map<String, Object> model = new HashMap<String, Object>();

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<BaoCaoThongKe> pageBaoCaoThongKe = serviceBaoCaoThongKeService.getBaoCaoThongKe(
				tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay, trangThai,
				PageRequest.of(page, size, direction, sortBy));



		List<BaoCaoThongKe> BaoCaoThongKeDatas = new ArrayList<>(
				pageBaoCaoThongKe.getContent());

		// All the remaining employees
		while (pageBaoCaoThongKe.hasNext()) {
			Page<BaoCaoThongKe> nextPageOfEmployees = serviceBaoCaoThongKeService.getBaoCaoThongKe(
					tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay, trangThai,
					pageBaoCaoThongKe.nextPageable());
		
			
			if (Objects.nonNull(nextPageOfEmployees.getContent())) {
				BaoCaoThongKeDatas.addAll(nextPageOfEmployees.getContent());
			}
			// update the page reference to the current page
			pageBaoCaoThongKe = nextPageOfEmployees;
		
		}

		model.put("BaoCaoThongKeDatas", BaoCaoThongKeDatas);
		
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKe "+formattedString+".xls");
		return new ModelAndView(new MyExcelViewThongKeHopDong(), model);
		
	}

}
