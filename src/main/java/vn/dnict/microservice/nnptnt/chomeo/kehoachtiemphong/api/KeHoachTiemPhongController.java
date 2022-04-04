package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.api;

import java.time.LocalDate;

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
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.FileDinhKemKeHoachService;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.business.KeHoach2ChoMeoBusiness;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business.KeHoachTiemPhongBusiness;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.business.ThoiGianTiemPhongBusiness;

@CrossOrigin
@RestController
@RequestMapping(value = "/chomeo/kehoachtiemphong")
public class KeHoachTiemPhongController {
	@Autowired
	KeHoachTiemPhongBusiness businessKeHoachTiemPhongBusiness;
	@Autowired
	KeHoach2ChoMeoBusiness businessKeHoach2ChoMeoBusiness;
	@Autowired
	ThoiGianTiemPhongBusiness businessThoiGianTiemPhongBusiness;
	@Autowired
	FileDinhKemKeHoachService serviceFileDinhKemKeHoachService;
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<KeHoachTiemPhongData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenKeHoach", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "soKeHoach",required = false) String soKeHoach,
			@RequestParam(name = "tenKeHoach", required = false) String tenKeHoach,
			@RequestParam(name = "noiDung",required=false) String noiDung,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayBanHanhTuNgay", required = false) LocalDate ngayBanHanhTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayBanHanhDenNgay", required = false) LocalDate ngayBanHanhDenNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayDuKienTuNgay", required = false) LocalDate ngayDuKienTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayDuKienDenNgay", required = false) LocalDate ngayDuKienDenNgay)
			{
		Page<KeHoachTiemPhongData> pageKeHoachTiemPhongData = businessKeHoachTiemPhongBusiness.findAll(page, size, sortBy, sortDir,  noiDung, soKeHoach, tenKeHoach,ngayDuKienDenNgay,ngayDuKienTuNgay, ngayBanHanhTuNgay, ngayBanHanhDenNgay);
		return ResponseEntity.ok(pageKeHoachTiemPhongData);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachTiemPhongData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		KeHoachTiemPhongData keHoachTiemPhongData = businessKeHoachTiemPhongBusiness.findById(id);
		return ResponseEntity.ok(keHoachTiemPhongData);
	}


	@PostMapping(value = { "" })
	public ResponseEntity<KeHoachTiemPhong> create(@Valid @RequestBody KeHoachTiemPhongData keHoachTiemPhongData) throws MethodArgumentNotValidException {
		KeHoachTiemPhong keHoachTiemPhong = businessKeHoachTiemPhongBusiness.create(keHoachTiemPhongData);
		return ResponseEntity.status(HttpStatus.CREATED).body(keHoachTiemPhong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachTiemPhong> update(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoachTiemPhongData keHoachTiemPhongData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		KeHoachTiemPhong keHoachTiemPhong = businessKeHoachTiemPhongBusiness.update(id, keHoachTiemPhongData);
		return ResponseEntity.ok(keHoachTiemPhong);
	}
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachTiemPhongData> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		KeHoachTiemPhongData keHoachTiemPhongData = businessKeHoachTiemPhongBusiness.delete(id);
		return ResponseEntity.ok(keHoachTiemPhongData);
	}
}