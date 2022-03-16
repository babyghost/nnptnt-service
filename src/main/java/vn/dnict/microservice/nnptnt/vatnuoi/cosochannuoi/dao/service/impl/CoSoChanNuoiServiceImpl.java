package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiSpecifications;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.repo.CoSoChanNuoiRepo; 

@Service
@Transactional
public class CoSoChanNuoiServiceImpl implements CoSoChanNuoiService{

	@Autowired
	CoSoChanNuoiRepo repo;
	@Override
	public CoSoChanNuoi save(CoSoChanNuoi entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
	}

	@Override
	public Optional<CoSoChanNuoi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	@Override
	public Optional<CoSoChanNuoi> findByTenCoSo(String tenCoSo) {
		// TODO Auto-generated method stub
		return repo.findByTenCoSo(tenCoSo);
	}

	@Override
	public Page<CoSoChanNuoi> findAll(String search, String tenChuCoSo, String dienThoai, String email, Long phuongXaId, 
			Long quanHuyenId, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(CoSoChanNuoiSpecifications.quickSearch(search, tenChuCoSo, dienThoai, email, phuongXaId, 
				quanHuyenId), pageable);
	}

}
