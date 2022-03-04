package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmNganhNghe;

@Service
@Transactional
public class DmNganhNgheServiceImpl implements DmNganhNgheService {
	@Autowired
	private DmNganhNgheRepo repo;

	public DmNganhNghe save(DmNganhNghe entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmNganhNghe> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmNganhNghe> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmNganhNgheSpecifications.quickSearch(search, trangThai), pageable);
	}
}
