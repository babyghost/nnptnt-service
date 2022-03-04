package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.DmLoaiDongVat;



public interface DmLoaiDongVatService {
	public DmLoaiDongVat save(DmLoaiDongVat entity);

	public void deleteById(Long id);

	public Optional<DmLoaiDongVat> findById(Long id);

	public Page<DmLoaiDongVat> findAll(String search, Integer trangThai, Pageable pageable);
}
