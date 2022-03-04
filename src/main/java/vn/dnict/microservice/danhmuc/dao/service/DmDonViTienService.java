package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmDonViTien;

public interface DmDonViTienService {
	public DmDonViTien save(DmDonViTien entity);

	public void deleteById(Long id);

	public Optional<DmDonViTien> findById(Long id);

	public Page<DmDonViTien> findAll(String search, Integer trangThai, Pageable pageable);
}
