package vn.dnict.microservice.nnptnt.dm.nhom.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomService;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomSpecifications;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.repo.DmNhomRepo;
@Service
@Transactional
public class DmNhomServiceImpl implements DmNhomService{
	
	@Autowired
	DmNhomRepo repo;
	
	
	@Override
	public DmNhom save(DmNhom entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmNhom> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmNhom> findAll(String ten, Long dmNganhHangId, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmNhomSpecifications.quickSearch(ten, dmNganhHangId, trangThai),pageable);
	}

}
