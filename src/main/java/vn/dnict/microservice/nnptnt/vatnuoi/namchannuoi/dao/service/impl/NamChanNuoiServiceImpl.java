package vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.model.NamChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service.NamChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service.NamChanNuoiSpecifications;
import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service.repo.NamChanNuoiRepo;
@Service
@Transactional
public class NamChanNuoiServiceImpl implements NamChanNuoiService{

	@Autowired
	NamChanNuoiRepo repo;
	public NamChanNuoi save(NamChanNuoi entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}
	
	public Optional<NamChanNuoi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	
	
	

	public Page<NamChanNuoi> findAll(String search, Integer Quy, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NamChanNuoiSpecifications.quickSearch(search, Quy), pageable);
	}


}