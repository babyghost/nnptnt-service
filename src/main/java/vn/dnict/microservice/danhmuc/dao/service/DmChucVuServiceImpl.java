package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmChucVu;

@Service
@Transactional
public class DmChucVuServiceImpl implements DmChucVuService {
	
	@Autowired
	private DmChucVuRepo repo;

	public DmChucVu save(DmChucVu entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmChucVu> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmChucVu> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmChucVuSpecifications.quickSearch(search, trangThai), pageable);
	}
}
