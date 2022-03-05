package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.api;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.FileDinhKemKeHoachService;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.business.KeHoach2ChoMeoBusiness;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business.KeHoachTiemPhongBusiness;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.business.ThoiGianTiemPhongBusiness;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/kehoachtiemphong")
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
	public ResponseEntity<Page<KeHoachTiemPhong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "tenKeHoach", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "soKeHoach",required = false) String soKeHoach,
			@RequestParam(name = "ngayBanHanh", required = false) LocalDateTime ngayBanHanh,
			@RequestParam(name = "ngayDuKienTuNgay", required = false) LocalDateTime ngayDuKienTuNgay,
			@RequestParam(name = "ngayDuKienDenNgay", required = false) LocalDateTime ngayDuKienDenNgay,
			@RequestParam(name = "tenKeHoach", required = false) String tenKeHoach,
			@RequestParam(name = "noiDung",required=false) String noiDung) {
		Page<KeHoachTiemPhong> pageKeHoachTiemPhong = businessKeHoachTiemPhongBusiness.findAll(page, size, sortBy, sortDir, search, noiDung, soKeHoach, tenKeHoach,ngayDuKienDenNgay,ngayDuKienTuNgay, ngayBanHanh);
		return ResponseEntity.ok(pageKeHoachTiemPhong);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<KeHoachTiemPhong> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessKeHoachTiemPhongBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<KeHoachTiemPhong> create(
			@Valid @RequestBody KeHoachTiemPhongInput keHoachTiemPhongInput) {
		KeHoachTiemPhong keHoachTiemPhong = businessKeHoachTiemPhongBusiness.create(keHoachTiemPhongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(keHoachTiemPhong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachTiemPhong> update(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoachTiemPhongInput KeHoachTiemPhongInput) throws EntityNotFoundException {
		KeHoachTiemPhong keHoachTiemPhong = businessKeHoachTiemPhongBusiness.update(id, KeHoachTiemPhongInput);
		return ResponseEntity.ok(keHoachTiemPhong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<KeHoachTiemPhong> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		KeHoachTiemPhong keHoachTiemPhong = businessKeHoachTiemPhongBusiness.delete(id);
		return ResponseEntity.ok(keHoachTiemPhong);
	}
}