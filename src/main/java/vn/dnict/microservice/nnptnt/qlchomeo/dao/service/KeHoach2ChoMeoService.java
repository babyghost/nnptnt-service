package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoach2ChoMeo;

public interface KeHoach2ChoMeoService {
	public KeHoach2ChoMeo save(KeHoach2ChoMeo entity);

	public void deleteById(Long id);

	public Optional<KeHoach2ChoMeo> findById(Long id);

	public Page<KeHoach2ChoMeo> findAll(String search, Long thongTinChoMeoId, Long keHoachTiemPhongId, boolean trangThaiTiem, Pageable pageable);	

}