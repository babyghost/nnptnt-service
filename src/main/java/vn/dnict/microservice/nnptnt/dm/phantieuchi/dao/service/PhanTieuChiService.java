package vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.model.PhanTieuChi;

public interface PhanTieuChiService {
	public PhanTieuChi save(PhanTieuChi entity);

	public void deleteById(Long id);

	public Optional<PhanTieuChi> findById(Long id);
	

	public Page<PhanTieuChi> findAll(String ten, Boolean trangThai, Pageable pageable);
}
