package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

public interface KeHoachThangService {
	public KeHoachThang save(KeHoachThang entity);
	
	public void deleteById(Long id);
	
	public Optional<KeHoachThang> findById(Long id);
	
	public Page<KeHoachThang> findAll(Long donViChuTriId, LocalDate thang, String tenNhiemVu, Long canBoThucHienId,
			LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang, Pageable pageable);
}
