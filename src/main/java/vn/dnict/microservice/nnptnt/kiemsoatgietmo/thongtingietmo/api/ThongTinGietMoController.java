package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongTinGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.business.ThongTinGietMoBusiness;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;

@CrossOrigin
@RestController
@RequestMapping(value = "/kiemsoatgietmo/thongtingietmo")
public class ThongTinGietMoController {
	@Autowired
	ThongTinGietMoBusiness businessThongTinGietMoBusiness;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<ThongTinGietMoData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenCoSo", required = false) List<String> tenCoSos,
			@RequestParam(name = "tenChuCoSo", required = false) String tenChuCoSo,
			@RequestParam(name = "dienThoai", required = false) String dienThoai,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "gietMoTuNgay", required = false) LocalDate gietMoTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy")	@RequestParam(name = "gietMoDenNgay", required = false) LocalDate gietMoDenNgay,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId) {
		Page<ThongTinGietMoData> pageThongTinData = businessThongTinGietMoBusiness.findAll(page, size, sortBy, sortDir, tenCoSos,
				tenChuCoSo, dienThoai, gietMoTuNgay, gietMoDenNgay, quanHuyenId, phuongXaId);
		return ResponseEntity.ok(pageThongTinData);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ThongTinGietMoData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(businessThongTinGietMoBusiness.findById(id));
	}
	
	@PostMapping(value = { "" })
	public ResponseEntity<ThongTinGietMo> create(@Valid @RequestBody ThongTinGietMoData thongTinGietMoData)
			throws MethodArgumentNotValidException {
		ThongTinGietMo thongTinGietMo = businessThongTinGietMoBusiness.create(thongTinGietMoData);
		return ResponseEntity.status(HttpStatus.CREATED).body(thongTinGietMo);
	}
	
	@PutMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinGietMo> update(@PathVariable("id") Long id, @Valid @RequestBody ThongTinGietMoData thongTinGietMoData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		ThongTinGietMo thongTinGietMo = businessThongTinGietMoBusiness.update(id, thongTinGietMoData);
		return ResponseEntity.ok(thongTinGietMo);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<ThongTinGietMoData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		ThongTinGietMoData thongTinGietMoData = businessThongTinGietMoBusiness.delete(id);
		return ResponseEntity.ok(thongTinGietMoData);
	}
}
