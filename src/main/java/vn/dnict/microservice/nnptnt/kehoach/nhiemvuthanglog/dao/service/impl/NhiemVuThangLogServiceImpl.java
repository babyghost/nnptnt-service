package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.NhiemVuThangLogService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.repo.NhiemVuThangLogRepo;

@Service
@Transactional
public class NhiemVuThangLogServiceImpl implements NhiemVuThangLogService {

	@Autowired
	NhiemVuThangLogRepo repo;

	@Override
	public NhiemVuThangLog save(NhiemVuThangLog nhiemVuThangLog) {
		// TODO Auto-generated method stub
		return repo.save(nhiemVuThangLog);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVuThangLog> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<NhiemVuThangLog> findByNhiemVuThangId(Long nhiemVuThangId) {
		// TODO Auto-generated method stub
		return repo.findByNhiemVuThangId(nhiemVuThangId);
	}

}
