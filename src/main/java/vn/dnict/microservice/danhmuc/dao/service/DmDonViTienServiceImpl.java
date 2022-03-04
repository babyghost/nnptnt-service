package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmDonViTien;

@Service
@Transactional
public class DmDonViTienServiceImpl implements DmDonViTienService {
	@Autowired
	private DmDonViTienRepo repo;

	public DmDonViTien save(DmDonViTien entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmDonViTien> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmDonViTien> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmDonViTienSpecifications.quickSearch(search, trangThai), pageable);
	}
}
