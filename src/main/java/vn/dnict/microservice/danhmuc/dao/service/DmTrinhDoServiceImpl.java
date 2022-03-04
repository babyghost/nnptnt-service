package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmTrinhDo;

@Service
@Transactional
public class DmTrinhDoServiceImpl implements DmTrinhDoService{
	
	@Autowired
	private DmTrinhDoRepo repo;
	
	public DmTrinhDo save(DmTrinhDo entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmTrinhDo> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmTrinhDo> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmTrinhDoSpecifications.quickSearch(search, trangThai), pageable);
	}

}
