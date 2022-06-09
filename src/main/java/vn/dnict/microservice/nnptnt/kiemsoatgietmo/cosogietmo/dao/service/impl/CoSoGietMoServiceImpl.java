package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.CoSoGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.CoSoGietMoSpecifications;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.repo.CoSoGietMoRepo;

@Service
public class CoSoGietMoServiceImpl implements CoSoGietMoService {
	@Autowired
	CoSoGietMoRepo repo;
	@Override
	public CoSoGietMo save(CoSoGietMo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
	}

	@Override
	public Optional<CoSoGietMo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	@Override
	public Optional<CoSoGietMo> findByTenCoSo(String tenCoSo) {
		// TODO Auto-generated method stub
		return repo.findByTenCoSo(tenCoSo);
	}

	@Override
	public Page<CoSoGietMo> findAll(String tenCoSo, String tenChuCoSo, String dienThoai, String email, Long phuongXaId, 
			Long quanHuyenId, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(CoSoGietMoSpecifications.quickSearch(tenCoSo, tenChuCoSo, dienThoai, email, phuongXaId, 
				quanHuyenId), pageable);
	}
}
