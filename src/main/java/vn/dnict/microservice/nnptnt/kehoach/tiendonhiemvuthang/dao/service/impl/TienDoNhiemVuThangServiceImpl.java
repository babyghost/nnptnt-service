package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.repo.TienDoNhiemVuThangRepo;

@Service
@Transactional
public class TienDoNhiemVuThangServiceImpl implements TienDoNhiemVuThangService{
	
	@Autowired
	TienDoNhiemVuThangRepo repo;
	
	@Override
	public TienDoNhiemVuThang save(TienDoNhiemVuThang entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<TienDoNhiemVuThang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<TienDoNhiemVuThang> findAll(String tenNguoiCapNhat, Integer mucDoHoanThanh, String ketQua,Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(TienDoNhiemVuThangSpecifications.quickSearch(tenNguoiCapNhat, mucDoHoanThanh, ketQua), pageable);
	}

}
