package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.business.CoSoChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.vatnuoi.data.BaoCaoHoatDongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business.HoatDongChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@CrossOrigin
@RestController
@RequestMapping(value = "/vatnuoi/hoatdongchannuoi")
public class HoatDongChanNuoiController {
	@Autowired
	CoSoChanNuoiBusiness businessCoSoChanNuoiBusiness; 
	
	@Autowired
	HoatDongChanNuoiBusiness businessHoatDongChanNuoiBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<HoatDongChanNuoiOutput>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "coSoChanNuoiId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenCoSo", required = false) String tenCoSo,
			@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
			@RequestParam(name= "nam",required = false) String nam,
			@RequestParam(name= "quy",required = false) Integer quy) {
		Page<HoatDongChanNuoiOutput> pageHoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.findAll(page, size, 
				sortBy, sortDir, tenCoSo, tenChuCoSo, dienThoai, quanHuyenId, phuongXaId, nam, quy);
			return ResponseEntity.ok(pageHoatDongChanNuoiOutput);
	} 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<HoatDongChanNuoiOutput> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessHoatDongChanNuoiBusiness.findById(id));
	}
	
	@GetMapping(value = "/{coSoChanNuoiId}/{nam}/{quy}")
	public ResponseEntity<List<HoatDongChanNuoi>> getHoatDongChanNuoiByCoSoAndNamAndQuy(@PathVariable("coSoChanNuoiId") 
			Long coSoChanNuoiId, @PathVariable("nam") String nam, @PathVariable("quy") Integer quy) throws EntityNotFoundException {
		List<HoatDongChanNuoi> list = businessHoatDongChanNuoiBusiness.getHoatDongChanNuoiByCoSoAndNamAndQuy(coSoChanNuoiId, nam, quy);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping(value = { "/save" })
	public ResponseEntity<List<HoatDongChanNuoi>> save(@Valid @RequestBody HoatDongChanNuoiOutput hoatDongChanNuoiOutput, 
			BindingResult result) throws MethodArgumentNotValidException {
		List<HoatDongChanNuoi> list = businessHoatDongChanNuoiBusiness.create(hoatDongChanNuoiOutput,  result);
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<HoatDongChanNuoiOutput> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		HoatDongChanNuoiOutput hoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.delete(id);
		return ResponseEntity.ok(hoatDongChanNuoiOutput);
	}	
	
	
//	----------------------------------------baocaothongkevatnuoi

@GetMapping(value = { "/thongkesoluong" })
public ResponseEntity<Page<ThongKeSoLuongChanNuoiData>> thongKeSoLuong(
		@RequestParam(name = "page", defaultValue = "0", required = false) int page,
		@RequestParam(name = "size", defaultValue = "20", required = false) int size,
		@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
		@RequestParam(name= "nam",required = false) String nam,
		@RequestParam(name= "quy",required = false) List< Integer> quy,
		@RequestParam(name= "loaiVatNuoiId",required = false) List< Long> loaiVatNuoiIds
		) {

	Page<ThongKeSoLuongChanNuoiData> pageThongKeSoLuongChanNuoiData = businessHoatDongChanNuoiBusiness.thongKeSoLuong(page, size, sortBy, sortDir, nam , quy, loaiVatNuoiIds, null, null, null
			);
	return ResponseEntity.ok(pageThongKeSoLuongChanNuoiData);
}



@GetMapping(value = { "/thongkesoluongdemo" })
public ResponseEntity<Page<Object>> thongKeSoLuongDemo(
		@RequestParam(name = "page", defaultValue = "0", required = false) int page,
		@RequestParam(name = "size", defaultValue = "20", required = false) int size,
		@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
		@RequestParam(name= "nam",required = false) String nam,
		@RequestParam(name= "quy",required = false) List< Integer> quy,
		@RequestParam(name= "loaiVatNuoiId",required = false) List< Long> loaiVatNuoiIds
		) {

	Page<Object> pageThongKeSoLuongChanNuoiData = businessHoatDongChanNuoiBusiness.thongKeSoLuongDemo(page, size, sortBy, sortDir, nam, quy, loaiVatNuoiIds, null, null, null
			);
	return ResponseEntity.ok(pageThongKeSoLuongChanNuoiData);
}

@GetMapping(value = { "/thongke/export" })
public ModelAndView exportExcelThongKeSoLuong (HttpServletRequest request, HttpServletResponse response,
		@RequestParam(name = "page", defaultValue = "0", required = false) int page,
		@RequestParam(name = "size", defaultValue = "20", required = false) int size,
		@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
		@RequestParam(name= "nam",required = false) String nam,
		@RequestParam(name= "quy",required = false) List< Integer> quy,
		@RequestParam(name= "loaiVatNuoiId",required = false) List< Long> loaiVatNuoiIds
) {
	
	return businessHoatDongChanNuoiBusiness.exportExcelThongKeSoLuong(request, response, page, size, sortBy, sortDir, nam , quy, loaiVatNuoiIds
			);
}


//------------------------- Thống Kê Hoạt Động -------------------------------

@GetMapping(value = { "/thongkehoatdong" })
public ResponseEntity<Page<BaoCaoHoatDongChanNuoiData>> ThongKeHoatDong(
		@RequestParam(name = "page", defaultValue = "0", required = false) int page,
		@RequestParam(name = "size", defaultValue = "20", required = false) int size,
		@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
		@RequestParam(name = "tenCoSo", required = false) String tenCoSo,
		@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
		@RequestParam(name = "loaiVatNuoiId", required = false) List<Long> loaiVatNuoiIds,
		@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
		@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
		@RequestParam(name= "nam",required = false) String nam,
		@RequestParam(name= "quy",required = false) List<Integer> quys) {
	Page<BaoCaoHoatDongChanNuoiData> pageHoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.thongKeHoatDong(page, size, 
			sortBy, sortDir, tenCoSo, tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys);
		return ResponseEntity.ok(pageHoatDongChanNuoiOutput);
} 

@GetMapping(value = { "/thongkehoatdong/export" })
public ModelAndView exportExcelThongKeHoatDong(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(name = "page", defaultValue = "0", required = false) int page,
		@RequestParam(name = "size", defaultValue = "20", required = false) int size,
		@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
		@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
		@RequestParam(name = "tenCoSo", required = false) String tenCoSo,
		@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
		@RequestParam(name = "loaiVatNuoiId", required = false) List<Long> loaiVatNuoiIds,
		@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
		@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
		@RequestParam(name= "nam",required = false) String nam,
		@RequestParam(name= "quy",required = false) List<Integer> quys) {
	return businessHoatDongChanNuoiBusiness.exportExcelThongKeHoatDong(request, response, page, size, sortBy, sortDir, tenCoSo, tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys);
}

}
