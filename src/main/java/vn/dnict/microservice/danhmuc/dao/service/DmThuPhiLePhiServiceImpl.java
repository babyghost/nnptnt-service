package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmThuPhiLePhi;

@Service
@Transactional
public class DmThuPhiLePhiServiceImpl implements DmThuPhiLePhiService{
	
	@Autowired
	private DmThuPhiLePhiRepo repo;
	
	public DmThuPhiLePhi save(DmThuPhiLePhi entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmThuPhiLePhi> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmThuPhiLePhi> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmThuPhiLePhiSpecifications.quickSearch(search, trangThai), pageable);
	}

}
