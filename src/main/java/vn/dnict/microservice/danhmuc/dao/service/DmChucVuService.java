package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmChucVu;

public interface DmChucVuService {
	public DmChucVu save(DmChucVu entity);

	public void deleteById(Long id);

	public Optional<DmChucVu> findById(Long id);

	public Page<DmChucVu> findAll(String search, Integer trangThai, Pageable pageable);
}
