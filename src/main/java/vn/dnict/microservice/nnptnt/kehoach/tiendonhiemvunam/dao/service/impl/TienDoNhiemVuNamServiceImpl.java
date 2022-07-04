package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.TienDoNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.repo.TienDoNhiemVuNamRepo;

@Service
@Transactional
public class TienDoNhiemVuNamServiceImpl implements TienDoNhiemVuNamService {
	
	@Autowired
	TienDoNhiemVuNamRepo repo;

	@Override
	public TienDoNhiemVuNam save(TienDoNhiemVuNam entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<TienDoNhiemVuNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<TienDoNhiemVuNam> findByNhiemVuNamIdAndDaXoa(Long nhiemVuNamId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByNhiemVuNamIdAndDaXoa(nhiemVuNamId, daXoa);
	}

	@Override
	public int setFixedDaXoaForNhiemVuNamId(Boolean daXoa, Long nhiemVuNamId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForNhiemVuNamId(daXoa, nhiemVuNamId);
	}

}
