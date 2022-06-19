package vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.model.DmLoaiGiayTo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.DmLoaiGiayToService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.DmLoaiGiayToSpecifications;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.repo.DmLoaiGiayToRepo;

@Service
public class DmLoaiGiayToServiceImpl implements DmLoaiGiayToService {

	@Autowired
	DmLoaiGiayToRepo repo;
	
	@Override
	public DmLoaiGiayTo save(DmLoaiGiayTo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmLoaiGiayTo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiGiayTo> findAll(String search, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmLoaiGiayToSpecifications.quickSearch(search, trangThai), pageable);
	}

}
