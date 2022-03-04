package vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.DmLoaiVatNuoi;

@Service
@Transactional
public class DmLoaiVatNuoiServiceImpl implements DmLoaiVatNuoiService{

	@Autowired
	DmLoaiVatNuoiRepo repo;
	@Override
	public DmLoaiVatNuoi save(DmLoaiVatNuoi entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<DmLoaiVatNuoi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiVatNuoi> findAll(String search, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLoaiVatNuoiSpecifications.quickSearch(search, trangThai), pageable);
	}

}