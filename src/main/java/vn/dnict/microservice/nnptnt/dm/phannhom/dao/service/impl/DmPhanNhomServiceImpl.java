package vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.DmPhanNhomService;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.DmPhanNhomSpecifications;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.repo.DmPhanNhomRepo;
@Service
@Transactional
public class DmPhanNhomServiceImpl implements DmPhanNhomService{
	@Autowired
	DmPhanNhomRepo repo;

	@Override
	public DmPhanNhom save(DmPhanNhom entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmPhanNhom> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmPhanNhom> findAll(String ten, Long dmNhomId, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmPhanNhomSpecifications.quickSearch(ten, dmNhomId, trangThai),pageable);
	}
	
	

}
