
package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.DmGiong;
@Service
@Transactional

public class DmGiongServiceImpl implements DmGiongService{

	
//	@Autowired
//	private DmGiongRepo repo;
//	
//	public DmGiong save(DmGiong entity) {
//		return repo.save(entity);
//	}
//
//	public void deleteById(Long id) {
//		repo.deleteById(id);
//	}
//
//	public Optional<DmGiong> findById(Long id) {
//		return repo.findById(id);
//	}
//
//
//
//	@Override
//	public Page<DmGiong> findAll(String search, Integer trangThai, PageRequest pageable) {
//		return repo.findAll(DmGiongSpecifications.quickSearch(search, trangThai),pageable);
//		// TODO Auto-generated method stub
//		
//		
//	}
//
//	@Override
//	public List<DmGiong> findByIdIn(List<Long> ids) {
//		// TODO Auto-generated method stub
//		return null;
	
//	}
	@Autowired
	DmGiongRepo repo;
	@Override
	public DmGiong save(DmGiong entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<DmGiong> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmGiong> findAll(String search, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmGiongSpecifications.quickSearch(search, trangThai), pageable);
	}


}