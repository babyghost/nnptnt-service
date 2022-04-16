package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

public interface ThongTinChoMeoService {
	public ThongTinChoMeo save(ThongTinChoMeo entity);

	public void deleteById(Long id);

	public Optional<ThongTinChoMeo> findById(Long id);

	public Page<ThongTinChoMeo> findAll(
			Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,
			LocalDate tuNgayTiemPhong, LocalDate denNgayTiemPhong,Long quanHuyenId, Long phuongXaId, Long keHoachTiemPhongId, Integer trangThai, Pageable pageable);
	
	public List<ThongTinChoMeo> findByChuQuanLyIdAndDaXoa(Long chuQuanLyId, Boolean daXoa);
	
	public int setFixedDaXoaForChuQuanLyId(Boolean daXoa, Long chuQuanLyId);

	
	public Optional<ThongTinChoMeo> findByChuQuanLyId(Long chuQuanLyId);
	
	public Page<ThongTinChoMeo> thongKe(
			Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,
			LocalDate tuNgayTiemPhong, LocalDate denNgayTiemPhong,Long quanHuyenId, Long phuongXaId, Long keHoachTiemPhongId, Integer trangThai, Pageable pageable);
}
