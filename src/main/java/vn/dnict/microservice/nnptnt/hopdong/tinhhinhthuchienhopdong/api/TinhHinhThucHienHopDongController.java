package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.hopdong.data.TinhHinhThucHienHopDongInput;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.business.TinhHinhThucHienHopDongBusiness;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;

@CrossOrigin
@RestController
@RequestMapping(value = "/hopdong/tinhhinhthuchienhopdong")

public class TinhHinhThucHienHopDongController {
	@Autowired
	private TinhHinhThucHienHopDongBusiness businessTinhHinhThucHienHopDongBusiness;

	@GetMapping(value = { "/{hopDongId}" })
	public ResponseEntity<List<TinhHinhThucHienHopDongInput>> getListByHopDongId(
			@PathVariable("hopDongId") Long hopDongId) {
		List<TinhHinhThucHienHopDongInput> listTinhHinhThucHienHopDongInput = businessTinhHinhThucHienHopDongBusiness
				.getListByHopDongId(hopDongId);
		return ResponseEntity.ok(listTinhHinhThucHienHopDongInput);
	}

	@GetMapping(value = "/{hopDongId}/tinhhinhthuchien/{tinhHinhThucHienId}")
	public ResponseEntity<TinhHinhThucHienHopDongInput> findById(@PathVariable("hopDongId") Long hopDongId,
			@PathVariable("tinhHinhThucHienId") Long tinhHinhThucHienId) throws EntityNotFoundException {
		TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput = businessTinhHinhThucHienHopDongBusiness
				.findById(hopDongId, tinhHinhThucHienId);
		return ResponseEntity.ok(tinhHinhThucHienHopDongInput);
	}

//	@GetMapping(value = "/{hopDongId}/tinhhinhthuchien/{tinhHinhThucHienId}/filedinhkem/hoadon")
//	public ResponseEntity<FileDinhKem> getFileDinhKemHoaDon(@PathVariable("hopDongId") Long hopDongId,
//			@PathVariable("tinhHinhThucHienId") Long tinhHinhThucHienId) throws EntityNotFoundException {
//		return ResponseEntity
//				.ok(businessTinhHinhThucHienHopDongBusiness.getFileDinhKemHoaDon(hopDongId, tinhHinhThucHienId));
//	}
//
//	@GetMapping(value = "/{hopDongId}/tinhhinhthuchien/{tinhHinhThucHienId}/filedinhkem/thanhtoan")
//	public ResponseEntity<FileDinhKem> getFileDinhKemThanhToan(@PathVariable("hopDongId") Long hopDongId,
//			@PathVariable("tinhHinhThucHienId") Long tinhHinhThucHienId) throws EntityNotFoundException {
//		return ResponseEntity
//				.ok(businessTinhHinhThucHienHopDongBusiness.getFileDinhKemThanhToan(hopDongId, tinhHinhThucHienId));
//	}

	@PostMapping(value = { "/{hopDongId}/tinhhinhthuchien" })
	public ResponseEntity<TinhHinhThucHienHopDong> create(@PathVariable("hopDongId") Long hopDongId,
			@Valid @RequestBody TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput)
			throws EntityNotFoundException {
		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = businessTinhHinhThucHienHopDongBusiness
				.create(hopDongId, tinhHinhThucHienHopDongInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(tinhHinhThucHienHopDong);
	}

	@PutMapping(value = { "/{hopDongId}/tinhhinhthuchien/{tinhHinhThucHienId}" })
	public ResponseEntity<TinhHinhThucHienHopDong> update(@PathVariable("hopDongId") Long hopDongId,
			@PathVariable("tinhHinhThucHienId") Long tinhHinhThucHienId,
			@Valid @RequestBody TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput)
			throws EntityNotFoundException {

		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = businessTinhHinhThucHienHopDongBusiness
				.update(hopDongId, tinhHinhThucHienId, tinhHinhThucHienHopDongInput);
		return ResponseEntity.ok(tinhHinhThucHienHopDong);
	}

	@DeleteMapping(value = { "/{hopDongId}/tinhhinhthuchien/{tinhHinhThucHienId}" })
	public ResponseEntity<TinhHinhThucHienHopDong> delete(@PathVariable("hopDongId") Long hopDongId,
			@PathVariable("tinhHinhThucHienId") Long tinhHinhThucHienId) throws EntityNotFoundException {
		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = businessTinhHinhThucHienHopDongBusiness
				.delete(hopDongId, tinhHinhThucHienId);
		return ResponseEntity.ok(tinhHinhThucHienHopDong);
	}

}
