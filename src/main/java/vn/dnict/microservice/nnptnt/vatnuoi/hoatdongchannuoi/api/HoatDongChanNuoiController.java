package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.api;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiInput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business.HoatDongChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@CrossOrigin
@RestController
@RequestMapping(value = "/vatnuoi/hoatdongchannuoi")
public class HoatDongChanNuoiController {
	@Autowired
	HoatDongChanNuoiBusiness businessHoatDongChanNuoiBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<HoatDongChanNuoiOutput>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "donViTinh", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "donViTinh",required=false) Integer donViTinh,
			@RequestParam(name= "soLuongNuoi",required = false) Integer soLuongNuoi,
			@RequestParam(name= "mucDichNuoi",required = false) String mucDichNuoi,
			@RequestParam(name= "thoiGianBatDauNuoi",required = false) LocalDate thoiGianBatDauNuoi,
			@RequestParam(name= "thoiGianXuat",required = false) LocalDate thoiGianXuat,
			@RequestParam(name= "slVatNuoiXuat",required = false) Integer slVatNuoiXuat,
			@RequestParam(name= "sanLuongXuat",required = false) Float sanLuongXuat,
			@RequestParam(name= "loaiVatNuoiId",required = false) Long loaiVatNuoiId,
			@RequestParam(name= "coSoChanNuoId",required = false) Long coSoChanNuoiId,
			@RequestParam(name= "ghiChu",required = false) String ghiChu,
			@RequestParam(name= "nam",required = false) String nam,
			@RequestParam(name= "quy",required = false) Integer quy) {
		Page<HoatDongChanNuoiOutput> pageHoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.findAll(page, size, sortBy, sortDir, 
				search,donViTinh,soLuongNuoi, mucDichNuoi, thoiGianBatDauNuoi, thoiGianXuat, slVatNuoiXuat, sanLuongXuat,
				ghiChu, loaiVatNuoiId, coSoChanNuoiId, nam, quy);
			return ResponseEntity.ok(pageHoatDongChanNuoiOutput);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<HoatDongChanNuoiOutput> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		HoatDongChanNuoiOutput hoatDongChanNuoi = businessHoatDongChanNuoiBusiness.findById(id);
		return ResponseEntity.ok(hoatDongChanNuoi);
	}	
	
	@PostMapping(value = { "" })
	public ResponseEntity<HoatDongChanNuoiOutput> create(@Valid @RequestBody HoatDongChanNuoiOutput hoatDongChanNuoiOutput, 
			BindingResult result) throws MethodArgumentNotValidException {
		hoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.create(hoatDongChanNuoiOutput,  result);
		return ResponseEntity.status(HttpStatus.CREATED).body(hoatDongChanNuoiOutput);
	}

	public ResponseEntity<HoatDongChanNuoiOutput> update(@PathVariable("id") Long id,
			@Valid @RequestBody HoatDongChanNuoiOutput hoatDongChanNuoiOutput, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		hoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.update(id, hoatDongChanNuoiOutput, result);
		return ResponseEntity.ok(hoatDongChanNuoiOutput);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<HoatDongChanNuoiOutput> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		HoatDongChanNuoiOutput hoatDongChanNuoiOutput = businessHoatDongChanNuoiBusiness.delete(id);
		return ResponseEntity.ok(hoatDongChanNuoiOutput);
	}

//	@GetMapping(value = "/{id}/cosochannuoi")
//	public ResponseEntity<CoSoChanNuoi> findCoSoChanNuoiByHoatDongChanNuoiId(@PathVariable("id") Long id)
//			throws EntityNotFoundException {
//		CoSoChanNuoi coSoChanNuoi = businessHoatDongChanNuoiBusiness.findCoSoChanNuoiByHoatDongChanNuoiId(id);
//		return ResponseEntity.ok(coSoChanNuoi);
//	}
//
//	@GetMapping(value = "/{id}/loaivatnuoi")
//	public ResponseEntity<DmLoaiVatNuoi> findLoaiVatNuoiByHoatDongChanNuoiId(@PathVariable("id") Long id)
//			throws EntityNotFoundException {
//		DmLoaiVatNuoi LoaiVatNuoi = businessHoatDongChanNuoiBusiness.findLoaiVatNuoiByHoatDongChanNuoiId(id);
//		return ResponseEntity.ok(LoaiVatNuoi);
//	}
		
}
