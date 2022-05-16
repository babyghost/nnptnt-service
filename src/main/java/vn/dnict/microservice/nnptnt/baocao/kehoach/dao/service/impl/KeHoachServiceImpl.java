package vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.KeHoachService;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.KeHoachSpecifications;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.repo.KeHoachRepo;

@Service
@Transactional
public class KeHoachServiceImpl implements KeHoachService {
	
	@Autowired
	KeHoachRepo repo;
		
	@Override
	public KeHoach save(KeHoach entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Optional<KeHoach> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<KeHoach> findAll(Long linhVucId, Integer nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoachSpecifications.quickSearch(linhVucId, nam),pageable);
	}

	@Override
	public Optional<KeHoach> findByNamAndLinhVucIdAndChiTieuId(Integer nam, Long linhVucId,Long chiTieuId) {
		// TODO Auto-generated method stub
		return repo.findByNamAndLinhVucIdAndChiTieuId(nam, linhVucId,chiTieuId);
	}

}
