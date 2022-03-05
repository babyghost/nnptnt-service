package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLySpecifications;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.repo.ChuQuanLyRepo;
@Service
// @Transactional

class ChuQuanLyServiceImpl implements ChuQuanLyService {
	
	@Autowired
	private ChuQuanLyRepo repo;

	@Override
	public ChuQuanLy save(ChuQuanLy entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Optional<ChuQuanLy> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ChuQuanLy> findAll(String chuHo, String diaChi, Integer dienThoai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ChuQuanLySpecifications.quickSearch(chuHo, diaChi, dienThoai),pageable);
	}

}
