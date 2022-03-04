package vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.DmLoaiVatNuoi;

@Service
public interface DmLoaiVatNuoiService {
	public DmLoaiVatNuoi save(DmLoaiVatNuoi entity);

	public void deleteById(Long id);

	public Optional<DmLoaiVatNuoi> findById(Long id);

	public Page<DmLoaiVatNuoi> findAll(String search, Integer trangThai, Pageable pageable);
}
