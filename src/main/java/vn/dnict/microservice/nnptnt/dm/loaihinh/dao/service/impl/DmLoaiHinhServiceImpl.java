package vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.DmLoaiHinhService;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.DmLoaiHinhSpecifications;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.repo.DmLoaiHinhRepo;
@Service
@Transactional
public class DmLoaiHinhServiceImpl implements DmLoaiHinhService {
	
	@Autowired
	DmLoaiHinhRepo repo;

	@Override
	public DmLoaiHinh save(DmLoaiHinh entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmLoaiHinh> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiHinh> findAll(String ten, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLoaiHinhSpecifications.quickSearch(ten, trangThai),pageable);
	}
	
}
