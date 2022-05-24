package vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.DmPhanHangService;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.DmPhanHangSpecifications;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.repo.DmPhanHangRepo;
@Service
@Transactional
public class DmPhanHangServiceImpl implements DmPhanHangService {
	
	@Autowired
	DmPhanHangRepo repo;
	
	@Override
	public DmPhanHang save(DmPhanHang entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmPhanHang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmPhanHang> findAll(String ten, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmPhanHangSpecifications.quickSearch(ten, trangThai),pageable);
	}

}
