package vn.dnict.microservice.nnptnt.dm.phanhang.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;

public interface DmPhanHangService {
	public DmPhanHang save(DmPhanHang entity);

	public void deleteById(Long id);

	public Optional<DmPhanHang> findById(Long id);
	

	public Page<DmPhanHang> findAll(String ten, Boolean trangThai, Pageable pageable);
}
