package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmGioiTinh;

@Service
@Transactional
public class DmGioiTinhServiceImpl implements DmGioiTinhService{
	
	@Autowired
	private DmGioiTinhRepo repo;
	
	public DmGioiTinh save(DmGioiTinh entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	public Optional<DmGioiTinh> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmGioiTinh> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmGioiTinhSpecifications.quickSearch(search, trangThai), pageable);
	}

}
