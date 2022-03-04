package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmGioiTinh;

public interface DmGioiTinhService {
	public DmGioiTinh save(DmGioiTinh entity);

	public void deleteById(Long id);

	public Optional<DmGioiTinh> findById(Long id);

	public Page<DmGioiTinh> findAll(String search, Integer trangThai, Pageable pageable);
}
