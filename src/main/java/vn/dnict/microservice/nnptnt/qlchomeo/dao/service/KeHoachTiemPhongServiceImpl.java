package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoachTiemPhong;

@Service
@Transactional
public class KeHoachTiemPhongServiceImpl implements KeHoachTiemPhongService{

	@Autowired
	KeHoachTiemPhongRepo repo;
	@Override
	public KeHoachTiemPhong save(KeHoachTiemPhong entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<KeHoachTiemPhong> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, String tenKeHoach,
			LocalDateTime ngayBanHanh,LocalDateTime ngayDuKienTuNgay,LocalDateTime ngayDuKienDenNgay, Pageable pageable) {
		return repo.findAll(KeHoachTiemPhongSpecifications.quickSearch(search, noiDung, soKeHoach, ngayBanHanh, ngayDuKienTuNgay, ngayDuKienDenNgay, tenKeHoach), pageable);

	
	}

	public Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, LocalDateTime ngayBanHanh,LocalDateTime ngayDuKienTuNgay,LocalDateTime ngayDuKienDenNgay,
			String tenKeHoach, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, String tenKeHoach,
			LocalDateTime ngayBanHanh, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<KeHoachTiemPhong> findAll(String search, String noiDung, String soKeHoach, LocalDateTime ngayBanHanh,
			String tenKeHoach, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
