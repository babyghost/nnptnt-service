package vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.model.PhanTieuChi;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.PhanTieuChiService;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.PhanTieuChiSpecifications;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.repo.PhanTieuChiRepo;
@Service
@Transactional
public class PhanTieuChiServiceImpl implements PhanTieuChiService {
	
	@Autowired
	PhanTieuChiRepo repo;
	
	@Override
	public PhanTieuChi save(PhanTieuChi entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<PhanTieuChi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<PhanTieuChi> findAll(String ten, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(PhanTieuChiSpecifications.quickSearch(ten, trangThai),pageable);
	}

}
