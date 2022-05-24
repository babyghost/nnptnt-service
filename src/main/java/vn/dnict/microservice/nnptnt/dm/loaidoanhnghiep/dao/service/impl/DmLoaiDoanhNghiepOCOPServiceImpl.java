package vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.DmLoaiDoanhNghiepOCOPService;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.DmLoaiDoanhNghiepOCOPSpecifications;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.repo.DmLoaiDoanhNghiepOCOPRepo;
@Service
@Transactional
public class DmLoaiDoanhNghiepOCOPServiceImpl implements DmLoaiDoanhNghiepOCOPService{
	
	@Autowired
	DmLoaiDoanhNghiepOCOPRepo repo;

	@Override
	public DmLoaiDoanhNghiepOCOP save(DmLoaiDoanhNghiepOCOP entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmLoaiDoanhNghiepOCOP> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiDoanhNghiepOCOP> findAll(String ten, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLoaiDoanhNghiepOCOPSpecifications.quickSearch(ten, trangThai),pageable);
	}
	
}
