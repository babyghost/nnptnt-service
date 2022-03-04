package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmThuPhiLePhi;

public interface DmThuPhiLePhiService {
	public DmThuPhiLePhi save(DmThuPhiLePhi entity);

	public void deleteById(Long id);

	public Optional<DmThuPhiLePhi> findById(Long id);

	public Page<DmThuPhiLePhi> findAll(String search, Integer trangThai, Pageable pageable);
}
