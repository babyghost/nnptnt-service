package vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;


public interface DmLoaiDongVatService {
	public DmLoaiDongVat save(DmLoaiDongVat entity);

	public void deleteById(Long id);

	public Optional<DmLoaiDongVat> findById(Long id);
	

	public Page<DmLoaiDongVat> findAll(String search, Integer trangThai, Pageable pageable);
}
