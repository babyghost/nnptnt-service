package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public interface HoatDongChanNuoiService {
	public HoatDongChanNuoi save(HoatDongChanNuoi entity);

	
//	public Optional<HoatDongChanNuoi> findByLoaiVatNuoiId(Long loaiVatNuoiId);
	

	public void deleteById(Long id);

	public Optional<HoatDongChanNuoi> findById(Long id);

	public Page<HoatDongChanNuoi> findAll(String search, Integer donViTinh, Integer soLuongNuoi,
			String mucDichNuoi, LocalDate thoiGianBatDauNuoi, LocalDate thoiGianXuat, Integer slVatNuoiXuat,
			Float sanLuongXuat, String ghiChu, Long loaiVatNuoiId,Long coSoChanNuoiId, String nam, Integer quy, Pageable pageable);
//	public List<HoatDongChanNuoi> findHoatDongChanNuoiByLoaiVatNuoiIdAndDaXoa(Long loaiVatNuoiId, Integer daXoa);

	
}
