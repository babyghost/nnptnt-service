package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.api;

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

import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.ThoiGianTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business.KeHoachTiemPhongBusiness;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.business.ThoiGianTiemPhongBusiness;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlchomeo/thoigiantiemphong")
public class ThoiGianTiemPhongController {
	@Autowired
	ThoiGianTiemPhongBusiness businessThoiGianTiemPhongBusiness;
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	@Autowired
	KeHoachTiemPhongBusiness businessKeHoachTiemPhongBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThoiGianTiemPhong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "keHoachTiemPhongId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "phuongXaId",required = false) Long phuongXaId,
			@RequestParam(name = "quanHuyenId",required = false) Long quanHuyenId,
			@RequestParam(name = "thoiGianTiemTuNgay", required= false) LocalDateTime thoiGianTiemTuNgay,
			@RequestParam(name = "thoiGianTiemDenNgay", required= false) LocalDateTime thoiGianTiemDenNgay,
			@RequestParam(name = "keHoachTiemPhongId",required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "diaDiem",required=false) String diaDiem) {
		Page<ThoiGianTiemPhong> pageThoiGianTiemPhong = businessThoiGianTiemPhongBusiness.findAll(page, size, sortBy, sortDir, phuongXaId, keHoachTiemPhongId,thoiGianTiemTuNgay,thoiGianTiemDenNgay, keHoachTiemPhongId, diaDiem);
		return ResponseEntity.ok(pageThoiGianTiemPhong);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ThoiGianTiemPhong> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessThoiGianTiemPhongBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ThoiGianTiemPhong> create(
			@Valid @RequestBody ThoiGianTiemPhongInput ThoiGianTiemPhongInput) {
		ThoiGianTiemPhong ThoiGianTiemPhong = businessThoiGianTiemPhongBusiness.create(ThoiGianTiemPhongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(ThoiGianTiemPhong);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThoiGianTiemPhong> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThoiGianTiemPhongInput ThoiGianTiemPhongInput) throws EntityNotFoundException {
		ThoiGianTiemPhong ThoiGianTiemPhong = businessThoiGianTiemPhongBusiness.update(id, ThoiGianTiemPhongInput);
		return ResponseEntity.ok(ThoiGianTiemPhong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThoiGianTiemPhong> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ThoiGianTiemPhong ThoiGianTiemPhong = businessThoiGianTiemPhongBusiness.delete(id);
		return ResponseEntity.ok(ThoiGianTiemPhong);
	}
}
