package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoachTiemPhong;

public interface KeHoachTiemPhongService {
	public KeHoachTiemPhong save(KeHoachTiemPhong entity);

	public void deleteById(Long id);

	public Optional<KeHoachTiemPhong> findById(Long id);

	public Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, String tenKeHoach, LocalDateTime ngayBanHanh, Pageable pageable);

	Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, LocalDateTime ngayBanHanh,
			String tenKeHoach, Pageable pageable);

	Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, String tenKeHoach,
			LocalDateTime ngayBanHanh, LocalDateTime ngayDuKienTuNgay, LocalDateTime ngayDuKienDenNgay,
			Pageable pageable);
}