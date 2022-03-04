package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDoanhNghiep;

@Service
@Transactional
public class DmLoaiDoanhNghiepServiceImpl implements DmLoaiDoanhNghiepService {
	@Autowired
	private DmLoaiDoanhNghiepRepo repo;

	public DmLoaiDoanhNghiep save(DmLoaiDoanhNghiep entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmLoaiDoanhNghiep> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmLoaiDoanhNghiep> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmLoaiDoanhNghiepSpecifications.quickSearch(search, trangThai), pageable);
	}
}
