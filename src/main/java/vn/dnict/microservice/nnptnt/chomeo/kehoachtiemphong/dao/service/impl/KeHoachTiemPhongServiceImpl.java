package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.impl;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongSpecifications;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.repo.KeHoachTiemPhongRepo;

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
	public Page<KeHoachTiemPhong> findAll(String noiDung, String soKeHoach, String tenKeHoach,
			LocalDate ngayDuKienTuNgay, LocalDate ngayDuKienDenNgay, LocalDate ngayBanHanhTuNgay, LocalDate ngayBanHanhDenNgay,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoachTiemPhongSpecifications.quickSearch(soKeHoach, ngayDuKienTuNgay, ngayDuKienDenNgay, ngayBanHanhTuNgay, ngayBanHanhDenNgay, tenKeHoach),pageable);
	}



}	

