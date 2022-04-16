
package vn.dnict.microservice.nnptnt.dm.giong.dao.service.impl;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongSpecifications;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.repo.DmGiongRepo;
@Service
@Transactional

public class DmGiongServiceImpl implements DmGiongService{

	

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
	public Page<DmGiong> findAll(String search,Long loaiDongVatId, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmGiongSpecifications.quickSearch(search,loaiDongVatId, trangThai), pageable);
	}

	@Override
	public List<DmGiong> findByTen(String ten) {
		// TODO Auto-generated method stub
		return repo.findByTen(ten);
	}


}