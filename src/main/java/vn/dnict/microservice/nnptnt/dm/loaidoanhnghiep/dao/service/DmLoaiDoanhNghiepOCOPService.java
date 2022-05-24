package vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;

public interface DmLoaiDoanhNghiepOCOPService {
	public DmLoaiDoanhNghiepOCOP save(DmLoaiDoanhNghiepOCOP entity);

	public void deleteById(Long id);

	public Optional<DmLoaiDoanhNghiepOCOP> findById(Long id);
	

	public Page<DmLoaiDoanhNghiepOCOP> findAll(String ten, Integer trangThai, Pageable pageable);
}
