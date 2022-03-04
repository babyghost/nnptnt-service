package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmNganhNghe;

public interface DmNganhNgheService {
	public DmNganhNghe save(DmNganhNghe entity);

	public void deleteById(Long id);

	public Optional<DmNganhNghe> findById(Long id);

	public Page<DmNganhNghe> findAll(String search, Integer trangThai, Pageable pageable);
}
