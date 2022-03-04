package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThongTinChoMeo;

@Service
@Transactional
public class ThongTinChoMeoServiceImpl implements ThongTinChoMeoService{

	@Autowired
	ThongTinChoMeoRepo repo;
	@Override
	public ThongTinChoMeo save(ThongTinChoMeo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<ThongTinChoMeo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ThongTinChoMeo> findAll(String search, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThongTinChoMeoSpecifications.quickSearch(search, trangThai), pageable);
	}

}
