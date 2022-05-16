package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;

public interface ThongTinHopDongService {
	public ThongTinHopDong save(ThongTinHopDong ThongTinHopDong);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<ThongTinHopDong> findById(Long id);

	public Page<ThongTinHopDong> findByDaXoaAndSearch(String search, Pageable pageable);

	public Page<ThongTinHopDong> findAll(String search, String ten, String soHieu, Long loaiHopDongId,
			Integer trangThai, LocalDate tuNgayKy, LocalDate denNgayKy, LocalDate thoiGianThTuNgay,
			LocalDate thoiGianThDenNgay, Pageable pageable);

	public List<ThongTinHopDong> findByDaXoa(boolean daXoa);
	
	public Long countByTrangThaiAndThoiGianThDenNgayBetween(Integer trangThai, LocalDate before5day, LocalDate nowDay);
}
