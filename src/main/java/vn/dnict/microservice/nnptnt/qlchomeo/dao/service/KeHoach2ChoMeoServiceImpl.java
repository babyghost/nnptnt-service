package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoach2ChoMeo;

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
	public Page<KeHoach2ChoMeo> findAll(String search, Long thongTinChoMeoId, Long keHoachTiemPhongId, boolean trangThaiTiem, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoach2ChoMeoSpecifications.quickSearch(search, trangThaiTiem), pageable);
	}


}
