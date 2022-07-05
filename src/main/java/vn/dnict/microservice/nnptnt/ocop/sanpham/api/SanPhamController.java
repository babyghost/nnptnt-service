package vn.dnict.microservice.nnptnt.ocop.sanpham.api;

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
import vn.dnict.microservice.nnptnt.ocop.data.SanPhamData;
import vn.dnict.microservice.nnptnt.ocop.sanpham.bussiness.SanPhamBussiness;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;

@CrossOrigin
@RestController
@RequestMapping(value = "/ocop/sanpham")
public class SanPhamController {
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<SanPhamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "chuThe", required = false) String chuThe,
			@RequestParam(name = "nganhHangId", required = false) Long nganhHangId,
			@RequestParam(name = "phanNhomId", required = false) Long phanNhomId,
			@RequestParam(name = "phanHangId", required = false) Long phanHangId,
			@RequestParam(name = "quyetDinh", required = false) String quyetDinh,
			@RequestParam(name = "nhomId", required = false) Long nhomId,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Page<SanPhamData> pageSanPham = bussinessSanPhamBussiness.findAll(page, size, sortBy, sortDir, ten, chuThe,
				nganhHangId, phanNhomId, phanHangId, trangThai, quyetDinh, nhomId);
		return ResponseEntity.ok(pageSanPham);
	}

	@GetMapping(value = { "/export" })
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "chuThe", required = false) String chuThe,
			@RequestParam(name = "nganhHangId", required = false) Long nganhHangId,
			@RequestParam(name = "phanNhomId", required = false) Long phanNhomId,
			@RequestParam(name = "phanHangId", required = false) Long phanHangId,
			@RequestParam(name = "quyetDinh", required = false) String quyetDinh,
			@RequestParam(name = "nhomId", required = false) Long nhomId,
			@RequestParam(name = "trangThai", required = false) Integer trangTha) {
		return bussinessSanPhamBussiness.exportExcelChuThe(request, response, page, size, sortBy, sortDir, ten, chuThe, nganhHangId, phanNhomId, phanHangId, trangTha, quyetDinh, nhomId);
	}

	@Autowired
	SanPhamBussiness bussinessSanPhamBussiness;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SanPhamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		SanPhamData SanPhamData = bussinessSanPhamBussiness.findById(id);
		return ResponseEntity.ok(SanPhamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<SanPham> create(@Valid @RequestBody SanPhamData SanPhamData) {
		SanPham NganhHang = bussinessSanPhamBussiness.create(SanPhamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(NganhHang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<SanPham> update(@PathVariable("id") Long id, @Valid @RequestBody SanPhamData SanPhamData)
			throws EntityNotFoundException {
		SanPham NganhHang = bussinessSanPhamBussiness.update(id, SanPhamData);
		return ResponseEntity.ok(NganhHang);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<SanPham> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		SanPham NganhHang = bussinessSanPhamBussiness.delete(id);
		return ResponseEntity.ok(NganhHang);
	}
}
