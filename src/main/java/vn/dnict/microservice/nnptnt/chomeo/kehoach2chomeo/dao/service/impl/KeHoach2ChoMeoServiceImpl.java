package vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoSpecifications;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.repo.KeHoach2ChoMeoRepo;

@Service
@Transactional

public class KeHoach2ChoMeoServiceImpl implements KeHoach2ChoMeoService{

	@Autowired
	KeHoach2ChoMeoRepo repo;
	@Override
	public KeHoach2ChoMeo save(KeHoach2ChoMeo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<KeHoach2ChoMeo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<KeHoach2ChoMeo> findAll(Long thongTinChoMeoId, Long keHoachTiemPhongId, LocalDate ngayTiemPhongTuNgay,
			LocalDate ngayTiemPhongDenNgay, boolean trangThaiTiem, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoach2ChoMeoSpecifications.quickSearch(thongTinChoMeoId, keHoachTiemPhongId, ngayTiemPhongTuNgay, ngayTiemPhongDenNgay, trangThaiTiem),pageable);
	}

	@Override
	public List<KeHoach2ChoMeo> findByThongTinChoMeoIdAndDaXoa(Long thongTinChoMeoId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByThongTinChoMeoIdAndDaXoa(thongTinChoMeoId, daXoa);
	}






}
