package vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatSpecifications;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.repo.DmLoaiDongVatRepo;
@Service
@Transactional
public class DmLoaiDongVatServiceImpl implements DmLoaiDongVatService{

	@Autowired
	DmLoaiDongVatRepo repo;
	@Override
	public DmLoaiDongVat save(DmLoaiDongVat entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<DmLoaiDongVat> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiDongVat> findAll(String search, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLoaiDongVatSpecifications.quickSearch(search, trangThai), pageable);
	}

}
