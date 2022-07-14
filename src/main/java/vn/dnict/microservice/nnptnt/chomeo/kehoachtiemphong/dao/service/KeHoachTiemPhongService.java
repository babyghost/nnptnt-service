package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;

public interface KeHoachTiemPhongService {
	public KeHoachTiemPhong save(KeHoachTiemPhong entity);

	public void deleteById(Long id);

	public Optional<KeHoachTiemPhong> findById(Long id);

	public Page<KeHoachTiemPhong> findAll(String soKeHoach, String tenKeHoach,
			 LocalDate ngayDuKienTuNgay, LocalDate ngayDuKienDenNgay,LocalDate ngayBanHanhTuNgay,LocalDate ngayBanHanhDenNgay,
			Pageable pageable);
}