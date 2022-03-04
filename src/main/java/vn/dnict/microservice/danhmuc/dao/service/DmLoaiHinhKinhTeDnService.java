package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiHinhKinhTeDn;

public interface DmLoaiHinhKinhTeDnService {
	public DmLoaiHinhKinhTeDn save(DmLoaiHinhKinhTeDn entity);

	public void deleteById(Long id);

	public Optional<DmLoaiHinhKinhTeDn> findById(Long id);

	public Page<DmLoaiHinhKinhTeDn> findAll(String search, Integer trangThai, Pageable pageable);
}
