package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiHinhKinhTeDn;

@Service
@Transactional
public class DmLoaiHinhKinhTeDnServiceImpl implements DmLoaiHinhKinhTeDnService{
	
	@Autowired
	private DmLoaiHinhKinhTeDnRepo repo;
	
	public DmLoaiHinhKinhTeDn save(DmLoaiHinhKinhTeDn entity) {
		return repo.save(entity);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmLoaiHinhKinhTeDn> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmLoaiHinhKinhTeDn> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(DmLoaiHinhKinhTeDnSpecifications.quickSearch(search, trangThai), pageable);
	}

}
