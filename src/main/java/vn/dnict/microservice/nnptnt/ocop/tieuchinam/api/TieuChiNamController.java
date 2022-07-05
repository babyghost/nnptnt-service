package vn.dnict.microservice.nnptnt.ocop.tieuchinam.api;

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
import vn.dnict.microservice.nnptnt.ocop.data.TieuChiNamData;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.bussiness.TieuChiNamBussiness;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;
@CrossOrigin
@RestController
@RequestMapping(value = "/ocop/tieuchinam")
public class TieuChiNamController {
	
	
	@Autowired
	TieuChiNamBussiness bussinessTieuChiNamBussiness;
	
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<TieuChiNamData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "nganhHangId", required = false) Long nganhHangId,
			@RequestParam(name = "phanNhomId", required = false) Long phanNhomId,
			@RequestParam(name = "nhomId", required = false) Long nhomId,
			@RequestParam(name = "trangThai",required=false) Integer trangThai,
			@RequestParam(name = "nam",required=false) Integer nam) {
		Page<TieuChiNamData> pageTieuChiNamData= bussinessTieuChiNamBussiness.findAll(page, size, sortBy, sortDir, nam, phanNhomId, nganhHangId, nhomId, trangThai);
		return ResponseEntity.ok(pageTieuChiNamData);
	}
	@GetMapping(value = { "/chitiet" })
	public ResponseEntity<Page<TieuChiNamData>> findChiTiet(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "nganhHangId", required = false) Long nganhHangId,
			@RequestParam(name = "phanNhomId", required = false) Long phanNhomId,
			@RequestParam(name = "nhomId", required = false) Long nhomId,
			@RequestParam(name = "nam",required=false) Integer nam) {
		Page<TieuChiNamData> pageTieuChiNamData= bussinessTieuChiNamBussiness.findChiTiet(page, size, sortBy, sortDir, nam, phanNhomId, nganhHangId, nhomId);
		return ResponseEntity.ok(pageTieuChiNamData);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<TieuChiNamData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		TieuChiNamData tieuChiNamData = bussinessTieuChiNamBussiness.findById(id);
		return ResponseEntity.ok(tieuChiNamData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<TieuChiNamData> create(
			@Valid @RequestBody TieuChiNamData tieuChiNamData) {
		tieuChiNamData = bussinessTieuChiNamBussiness.create(tieuChiNamData);
		return ResponseEntity.status(HttpStatus.CREATED).body(tieuChiNamData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<TieuChiNamData> update(@PathVariable("id") Long id,
			@Valid @RequestBody TieuChiNamData tieuChiNamData) throws EntityNotFoundException {
		tieuChiNamData = bussinessTieuChiNamBussiness.update(id, tieuChiNamData);
		return ResponseEntity.ok(tieuChiNamData);
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<TieuChiNam> delete(@PathVariable("id") Long id)
			throws EntityNotFoundException {
		TieuChiNam tieuChiNam = bussinessTieuChiNamBussiness.delete(id);
		return ResponseEntity.ok(tieuChiNam);
	}
	@GetMapping(value = { "/tieuchi" })
	public ResponseEntity<TieuChiNamData> findByTieuChi(

			@RequestParam(name = "nam", required = false) Integer nam,
			@RequestParam(name = "phanNhomId", required = false) long phanNhomId,
			@RequestParam(name = "nganhHangId", required = false) long nganhHangId,
			@RequestParam(name = "nhomId", required = false) long nhomId
			) throws EntityNotFoundException {
		TieuChiNamData tieuChiNamData = bussinessTieuChiNamBussiness.findTieuChi(nam, phanNhomId, nganhHangId, nhomId);
		return ResponseEntity.ok(tieuChiNamData);
	}
	
	@GetMapping(value = { "/export" })
	public ModelAndView  export(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "nam", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "nam",required=false) Integer nam,

			@RequestParam(name = "phanNhomId", required = false) Long phanNhomId,
			@RequestParam(name = "nganhHangId", required = false) Long nganhHangId,
			@RequestParam(name = "nhomId", required = false) Long nhomId,
			@RequestParam(name = "trangThai",required=false) Integer trangThai) {
		return bussinessTieuChiNamBussiness.exportExcelTieuChiNam(request, response, page, size, sortBy, sortDir, nam, phanNhomId, nganhHangId, nhomId, trangThai);
	}
  
}
