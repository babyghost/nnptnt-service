package vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.ChiTieuNamService;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.ChiTieuNamSpecifications;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.repo.ChiTieuNamRepo;
@Transactional
@Service
public class ChiTieuNamServiceImpl implements ChiTieuNamService {
	@Autowired
	ChiTieuNamRepo repo;
	
	@Override
	public ChiTieuNam save(ChiTieuNam entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<ChiTieuNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ChiTieuNam> findAll(Long linhVucId, Integer nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ChiTieuNamSpecifications.quickSearch(linhVucId, nam),pageable);
	}

	@Override
	public Optional<ChiTieuNam> findByLinhVucIdAndNamAndDaXoa(Long linhVucId, Integer nam) {
		// TODO Auto-generated method stub
		return repo.findByLinhVucIdAndNamAndDaXoa(linhVucId, nam);
	}

}
