package vn.dnict.microservice.nnptnt.ocop.chuthe.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.exceptions.EntityNotFoundException;

import vn.dnict.microservice.nnptnt.ocop.chuthe.bussiness.DoanhNghiepBussiness;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
import vn.dnict.microservice.nnptnt.ocop.data.DoanhNghiepData;

@CrossOrigin
@RestController
@RequestMapping(value = "/ocop/doanhnghiep")
public class DoanhNghiepController {
	@Autowired
	DoanhNghiepBussiness bussinessDoanhNghiepBussiness;
	
	
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DoanhNghiepData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "chuSoHuu", required = false) String chuSoHuu,
			@RequestParam(name = "loaiDoanhNghiepId", required = false) Long loaiDoanhNghiepId,
			@RequestParam(name = "loaiHinhId", required = false) Long loaiHinhId,
			@RequestParam(name = "nganhNgheId", required = false) Long nganhNgheId,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		Page<DoanhNghiepData> pageNganhHang = bussinessDoanhNghiepBussiness.findAll(page, size, sortBy, sortDir, ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai);
		return ResponseEntity.ok(pageNganhHang);
	}
	
	@GetMapping(value = { "/export" })
	public ModelAndView  export(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "chuSoHuu", required = false) String chuSoHuu,
			@RequestParam(name = "loaiDoanhNghiepId", required = false) Long loaiDoanhNghiepId,
			@RequestParam(name = "loaiHinhId", required = false) Long loaiHinhId,
			@RequestParam(name = "nganhNgheId", required = false) Long nganhNgheId,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		return bussinessDoanhNghiepBussiness.exportExcelChuThe(request, response, page, size, sortBy, sortDir, ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DoanhNghiepData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DoanhNghiepData doanhNghiepData = bussinessDoanhNghiepBussiness.findById(id);
		return ResponseEntity.ok(doanhNghiepData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DoanhNghiep> create(
			@Valid @RequestBody DoanhNghiepData DoanhNghiepData) {
		DoanhNghiep NganhHang = bussinessDoanhNghiepBussiness.create(DoanhNghiepData);
		return ResponseEntity.status(HttpStatus.CREATED).body(NganhHang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DoanhNghiep> update(@PathVariable("id") Long id,
			@Valid @RequestBody DoanhNghiepData DoanhNghiepData) throws EntityNotFoundException {
		DoanhNghiep NganhHang = bussinessDoanhNghiepBussiness.update(id, DoanhNghiepData);
		return ResponseEntity.ok(NganhHang);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DoanhNghiep> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		DoanhNghiep NganhHang = bussinessDoanhNghiepBussiness.delete(id);
		return ResponseEntity.ok(NganhHang);
	}
}
