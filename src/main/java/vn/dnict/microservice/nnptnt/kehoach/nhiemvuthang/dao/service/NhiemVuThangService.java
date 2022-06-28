package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

public interface NhiemVuThangService {
	public NhiemVuThang save(NhiemVuThang nhiemVuThang);

	public void deleteById(Long id);

	public Optional<NhiemVuThang> findById(Long id);

	public List<NhiemVuThang> findByKeHoachThangIdAndDaXoa(Long keHoachThangId, Boolean daXoa);

	public int setFixedDaXoaForKeHoachThangId(Boolean daXoa, Long keHoachThangId);
	
	public Page<NhiemVuThang> findAll(Long donViChuTriId, List<LocalDate> thangs, List<Integer> tinhTrangs, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay, Pageable pageable);

	public List<NhiemVuThang> getThongKeSoLuong(Long donViChuTriId, List<LocalDate> thangs, Long keHoachThangId, String tenNhiemVu,
			List<Integer> tinhTrangs, Long canBoThucHienId, LocalDate tuNgay, LocalDate denNgay);
}
