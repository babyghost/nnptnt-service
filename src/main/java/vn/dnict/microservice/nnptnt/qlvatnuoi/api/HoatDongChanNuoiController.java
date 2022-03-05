package vn.dnict.microservice.nnptnt.qlvatnuoi.api;

import java.time.LocalDate;

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
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.business.HoatDongChanNuoiBusiness;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.NamChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.HoatDongChanNuoiInput;

@CrossOrigin
@RestController
@RequestMapping(value = "/qlvatnuoi/hoatdongchannuoi")
public class HoatDongChanNuoiController {
		@Autowired
		HoatDongChanNuoiBusiness businessHoatDongChanNuoiBusiness;
		
		
		
		@GetMapping(value = { "/", "" })
		public ResponseEntity<Page<HoatDongChanNuoi>> findAll(
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
				@RequestParam(name= "namChanNuoiId",required = false) Long namChanNuoiId,
				@RequestParam(name= "coSoChanNuoId",required = false) Long coSoChanNuoiId,
				@RequestParam(name= "ghiChu",required = false) String ghiChu
				)
				{
			Page<HoatDongChanNuoi> pageHoatDongChanNuoi = (Page<HoatDongChanNuoi>) businessHoatDongChanNuoiBusiness.findAll(page, size, sortBy, sortDir, search,donViTinh,soLuongNuoi,
					mucDichNuoi,thoiGianBatDauNuoi,thoiGianXuat,slVatNuoiXuat,sanLuongXuat,ghiChu,loaiVatNuoiId,namChanNuoiId,coSoChanNuoiId);
			
			return ResponseEntity.ok(pageHoatDongChanNuoi);
		}
		
		@PostMapping(value = { "" })
		public ResponseEntity<HoatDongChanNuoi> create(
				@Valid @RequestBody HoatDongChanNuoiInput HoatDongChanNuoiInput) {
			HoatDongChanNuoi HoatDongChanNuoi = businessHoatDongChanNuoiBusiness.create(HoatDongChanNuoiInput);
			return ResponseEntity.status(HttpStatus.CREATED).body(HoatDongChanNuoi);
		}
		
		
		@GetMapping(value = "/{id}/cosochannuoi")
		public ResponseEntity<CoSoChanNuoi> findCoSoChanNuoiByHoatDongChanNuoiId(@PathVariable("id") Long id)
				throws EntityNotFoundException {
			CoSoChanNuoi coSoChanNuoi = businessHoatDongChanNuoiBusiness.findCoSoChanNuoiByHoatDongChanNuoiId(id);
			return ResponseEntity.ok(coSoChanNuoi);
		}
		
		@GetMapping(value = "/{id}/namchannuoi")
		public ResponseEntity<NamChanNuoi> findNamChanNuoiByHoatDongChanNuoiId(@PathVariable("id") Long id)
				throws EntityNotFoundException {
			NamChanNuoi NamChanNuoi = businessHoatDongChanNuoiBusiness.findNamChanNuoiByHoatDongChanNuoiId(id);
			return ResponseEntity.ok(NamChanNuoi);
		}
		
		@GetMapping(value = "/{id}/loaivatnuoi")
		public ResponseEntity<DmLoaiVatNuoi> findLoaiVatNuoiByHoatDongChanNuoiId(@PathVariable("id") Long id)
				throws EntityNotFoundException {
			DmLoaiVatNuoi LoaiVatNuoi = businessHoatDongChanNuoiBusiness.findLoaiVatNuoiByHoatDongChanNuoiId(id);
			return ResponseEntity.ok(LoaiVatNuoi);
		}
		

		@PutMapping(value = { "/{id}" })
		public ResponseEntity<HoatDongChanNuoi> update(@PathVariable("id") Long id,
				@Valid @RequestBody HoatDongChanNuoiInput HoatDongChanNuoiInput) throws EntityNotFoundException {
			HoatDongChanNuoi HoatDongChanNuoi = businessHoatDongChanNuoiBusiness.update(id, HoatDongChanNuoiInput);
			return ResponseEntity.ok(HoatDongChanNuoi);
		}

		@DeleteMapping(value = { "/{id}" })
		public ResponseEntity<HoatDongChanNuoi> delete(@PathVariable("id") Long id)
				throws EntityNotFoundException {
			HoatDongChanNuoi HoatDongChanNuoi = businessHoatDongChanNuoiBusiness.delete(id);
			return ResponseEntity.ok(HoatDongChanNuoi);
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<HoatDongChanNuoi> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
			return ResponseEntity.ok(businessHoatDongChanNuoiBusiness.findById(id));
		}

		
		
		
		
		
		
		
		
		
		
		
		
}
