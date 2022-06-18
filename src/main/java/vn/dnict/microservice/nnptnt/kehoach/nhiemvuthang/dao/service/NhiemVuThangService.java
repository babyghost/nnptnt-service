package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

public interface NhiemVuThangService {
	public NhiemVuThang save(NhiemVuThang entity);
	
	public void deleteById(Long id);
	
	public Optional<NhiemVuThang> findById(Long id);
	
	public Optional<NhiemVuThang> findByKeHoachThangId(Long keHoachThangId);
	
	public Page<NhiemVuThang> findAll(Long donViChuTriId, List<LocalDate > thangs, String tenNhiemVu, Long canBoThucHienId,
			LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang, Pageable pageable);
	
	public List<NhiemVuThang> findByKeHoachThangIdAndDaXoa(Long keHoachThangId, Boolean daXoa);
	
	public int setFixedDaXoaForKeHoachThangId(Boolean daXoa, Long keHoachThangId);
	
	public Page<NhiemVuThang> tongHopKeHoachThang(Long donViChuTriId, List<LocalDate > thangs, String tenNhiemVu, Integer tinhTrang,
			Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Pageable pageable);
}
