package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmDanToc;

@Service
@Transactional
public class DmDanTocServiceImpl implements DmDanTocService{
	
	@Autowired
	private DmDanTocRepo repo;
	
	public DmDanToc save(DmDanToc entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	public Optional<DmDanToc> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmDanToc> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmDanTocSpecifications.quickSearch(search, trangThai), pageable);
	}

}
