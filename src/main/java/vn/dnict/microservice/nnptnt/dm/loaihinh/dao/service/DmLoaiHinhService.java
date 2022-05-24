package vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;

public interface DmLoaiHinhService {
	public DmLoaiHinh save(DmLoaiHinh entity);

	public void deleteById(Long id);

	public Optional<DmLoaiHinh> findById(Long id);
	

	public Page<DmLoaiHinh> findAll(String ten, Integer trangThai, Pageable pageable);
}
