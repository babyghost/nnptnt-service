package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDoanhNghiep;

public interface DmLoaiDoanhNghiepService {
	public DmLoaiDoanhNghiep save(DmLoaiDoanhNghiep entity);

	public void deleteById(Long id);

	public Optional<DmLoaiDoanhNghiep> findById(Long id);

	public Page<DmLoaiDoanhNghiep> findAll(String search, Integer trangThai, Pageable pageable);
}
