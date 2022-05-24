package vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.DoanhNghiepService;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.DoanhNghiepSpecifications;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.repo.DoanhNghiepRepo;
@Service
@Transactional
public class DoanhNghiepServiceImpl implements DoanhNghiepService{
	
	@Autowired
	DoanhNghiepRepo repo;

	@Override
	public DoanhNghiep save(DoanhNghiep entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DoanhNghiep> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DoanhNghiep> findAll(String ten, String chuSoHuu, Long loaiDoanhNghiepId, Long loaiHinhId,
			Long nganhNgheId, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DoanhNghiepSpecifications.quickSearch(ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai),pageable);
	}

}
