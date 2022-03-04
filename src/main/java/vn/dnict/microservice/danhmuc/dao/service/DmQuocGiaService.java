package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmQuocGia;

public interface DmQuocGiaService {
	public DmQuocGia save(DmQuocGia entity);

	public void deleteById(Long id);

	public Optional<DmQuocGia> findById(Long id);

	public Page<DmQuocGia> findAll(String search, Boolean trangThai, Pageable pageable);
}
