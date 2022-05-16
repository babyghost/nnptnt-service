package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.api;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import vn.dnict.microservice.nnptnt.baocao.data.ThongKeData;
import vn.dnict.microservice.nnptnt.baocao.data.ThucHienBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.bussiness.ThucHienBaoCaoBussiness;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;

@CrossOrigin
@RestController
@RequestMapping(value = "/baocao/thuchienbaocao")
public class ThucHienBaoCaoController {
	
	@Autowired
	ThucHienBaoCaoBussiness bussinessThucHienBaoCaoBussiness;
	
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThucHienBaoCaoData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayThucHien", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangNam", required = false) LocalDate thangNam,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayThucHien", required = false) LocalDate ngayThucHien) {
		Page<ThucHienBaoCaoData> pageThucHienBaoCaoData= bussinessThucHienBaoCaoBussiness.findAll(page, size, sortBy, sortDir, linhVucId, thangNam, ngayThucHien);
		return ResponseEntity.ok(pageThucHienBaoCaoData);
	}
	
	
	@GetMapping(value = { "/thongke" })
	public ResponseEntity<Page<ThongKeData>> thongKe(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayThucHien", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangNam", required = false) LocalDate thangNam,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayThucHien", required = false) LocalDate ngayThucHien,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangBatDau", required = false) LocalDate thangBatDau,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangKetThuc", required = false) LocalDate thangKetThuc,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangBatDauTN", required = false) LocalDate thangBatDauTN,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangKetThucTN", required = false) LocalDate thangKetThucTN
) {
		Page<ThongKeData> pageThucHienBaoCaoData= bussinessThucHienBaoCaoBussiness.thongKe(page, size, sortBy, sortDir, linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc,thangBatDauTN,thangKetThucTN);
		return ResponseEntity.ok(pageThucHienBaoCaoData);
	}
	
	@GetMapping(value = { "/thongke/export" })
	public ModelAndView thongKe(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayThucHien", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "linhVucId", required = false) Long linhVucId,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangNam", required = false) LocalDate thangNam,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "ngayThucHien", required = false) LocalDate ngayThucHien,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangBatDau", required = false) LocalDate thangBatDau,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangKetThuc", required = false) LocalDate thangKetThuc,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangBatDauTN", required = false) LocalDate thangBatDauTN,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangKetThucTN", required = false) LocalDate thangKetThucTN
) {
		return bussinessThucHienBaoCaoBussiness.exportExcelThongKeSoLieuChuyenNganh(request, response, page, size, sortBy, sortDir, linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN);
		
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ThucHienBaoCaoData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		ThucHienBaoCaoData thucHienBaoCaoData = bussinessThucHienBaoCaoBussiness.findById(id);
		return ResponseEntity.ok(thucHienBaoCaoData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<ThucHienBaoCaoData> create(
			@Valid @RequestBody ThucHienBaoCaoData thucHienBaoCaoData) {
		thucHienBaoCaoData = bussinessThucHienBaoCaoBussiness.create(thucHienBaoCaoData);
		return ResponseEntity.status(HttpStatus.CREATED).body(thucHienBaoCaoData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThucHienBaoCaoData> update(@PathVariable("id") Long id,
			@Valid @RequestBody ThucHienBaoCaoData thucHienBaoCaoData) throws EntityNotFoundException {
		thucHienBaoCaoData = bussinessThucHienBaoCaoBussiness.update(id, thucHienBaoCaoData);
		return ResponseEntity.ok(thucHienBaoCaoData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThucHienBaoCao> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		ThucHienBaoCao thucHienBaoCao = bussinessThucHienBaoCaoBussiness.delete(id);
		return ResponseEntity.ok(thucHienBaoCao);
	}
	@GetMapping(value = { "/chitieu" })
	public ResponseEntity<ThucHienBaoCaoData> findByChiTieu(
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "thangNam", required = false) LocalDate thangNam,
			@RequestParam(name = "linhVucId", required = false) long linhVucId,
			@RequestParam(name = "nam", required = false) Integer nam
		
			) throws EntityNotFoundException {
		ThucHienBaoCaoData thucHienBaoCaoData = bussinessThucHienBaoCaoBussiness.findChiTieu(thangNam,linhVucId, nam);
		return ResponseEntity.ok(thucHienBaoCaoData);
	}
	

}
