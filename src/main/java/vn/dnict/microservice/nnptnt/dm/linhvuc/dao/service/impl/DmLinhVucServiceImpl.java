package vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucSpecifications;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.repo.DmLinhVucRepo;
@Service
@Transactional
public class DmLinhVucServiceImpl implements DmLinhVucService {
	
	@Autowired
	DmLinhVucRepo repo;
	@Override
	public DmLinhVuc save(DmLinhVuc entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmLinhVuc> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLinhVuc> findAll(String search, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLinhVucSpecifications.quickSearch(search, trangThai),pageable);
	}



}
