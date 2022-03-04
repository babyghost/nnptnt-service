package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmDanToc;

public interface DmDanTocService {
	public DmDanToc save(DmDanToc entity);

	public void deleteById(Long id);

	public Optional<DmDanToc> findById(Long id);

	public Page<DmDanToc> findAll(String search, Integer trangThai, Pageable pageable);
}
