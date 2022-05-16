package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.api;

import java.time.LocalDate;

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

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.data.YeuCauBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.bussiness.YeuCauBaoCaoBussiness;

@CrossOrigin
@RestController
@RequestMapping(value = "/baocao/yeucaubaocao")
public class YeuCauBaoCaoController {
	@Autowired
	YeuCauBaoCaoBussiness bussinessYeuCauBaoCaoBussiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<YeuCauBaoCaoData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "tieuDe", required = false) String tieuDe,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "ngayYeuCauTuNgay", required = false) LocalDate ngayYeuCauTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "ngayYeuCauDenNgay", required = false) LocalDate ngayYeuCauDenNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiHanTuNgay", required = false) LocalDate thoiHanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiHanDenNgay", required = false) LocalDate thoiHanDenNgay,
			@RequestParam(name = "linhVucId", required = false) Long linhVucIdId,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {

		Page<YeuCauBaoCaoData> pageYeuCauBaoCaoData = bussinessYeuCauBaoCaoBussiness.findAll(page, size, sortBy,
				sortDir, tieuDe, ngayYeuCauTuNgay, ngayYeuCauDenNgay, thoiHanTuNgay, thoiHanDenNgay, linhVucIdId,
				trangThai);
		return ResponseEntity.ok(pageYeuCauBaoCaoData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<YeuCauBaoCaoData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		YeuCauBaoCaoData YeuCauBaoCao = bussinessYeuCauBaoCaoBussiness.findById(id);
		return ResponseEntity.ok(YeuCauBaoCao);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<YeuCauBaoCaoData> create(@Valid @RequestBody YeuCauBaoCaoData yeuCauBaoCaoData) {
		yeuCauBaoCaoData = bussinessYeuCauBaoCaoBussiness.create(yeuCauBaoCaoData);
		return ResponseEntity.status(HttpStatus.CREATED).body(yeuCauBaoCaoData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<YeuCauBaoCaoData> update(@PathVariable("id") Long id,
			@Valid @RequestBody YeuCauBaoCaoData yeuCauBaoCaoData) throws EntityNotFoundException {
		yeuCauBaoCaoData = bussinessYeuCauBaoCaoBussiness.update(id, yeuCauBaoCaoData);
		return ResponseEntity.ok(yeuCauBaoCaoData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<YeuCauBaoCaoData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		YeuCauBaoCaoData yeuCauBaoCaoData = bussinessYeuCauBaoCaoBussiness.delete(id);
		return ResponseEntity.ok(yeuCauBaoCaoData);
	}
}
