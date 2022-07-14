package vn.dnict.microservice.nnptnt.dm.loaigiayto.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loaigiayto.dao.model.DmLoaiGiayTo;

public interface DmLoaiGiayToService {
public DmLoaiGiayTo save(DmLoaiGiayTo entity);
	
	public void deleteById(Long id);
	
	public Optional<DmLoaiGiayTo> findById(Long id);
	
	public Page<DmLoaiGiayTo> findAll(String search, Integer trangThai, Pageable pageable);
}
