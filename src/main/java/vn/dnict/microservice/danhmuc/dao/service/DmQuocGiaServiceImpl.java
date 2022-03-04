package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmQuocGia;

@Service
@Transactional
public class DmQuocGiaServiceImpl implements DmQuocGiaService{
	
	@Autowired
	private DmQuocGiaRepo repo;
	
	public DmQuocGia save(DmQuocGia entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmQuocGia> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmQuocGia> findAll(String search, Boolean trangThai, Pageable pageable) {
		return repo.findAll(DmQuocGiaSpecifications.quickSearch(search, trangThai), pageable);
	}

}
