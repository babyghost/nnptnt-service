package vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

public interface KeHoach2ChoMeoService {
	public KeHoach2ChoMeo save(KeHoach2ChoMeo entity);

	public void deleteById(Long id);

	public Optional<KeHoach2ChoMeo> findById(Long id);

	public Page<KeHoach2ChoMeo> findAll(Long thongTinChoMeoId, Long keHoachTiemPhongId, LocalDate ngayTiemPhongTuNgay, LocalDate ngayTiemPhongDenNgay, boolean trangThaiTiem, Pageable pageable);	
	public List<KeHoach2ChoMeo> findByThongTinChoMeoIdAndDaXoa(Long thongTinChoMeoId, Boolean daXoa);

}