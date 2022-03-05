package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;

public interface ChuQuanLyService {
	public ChuQuanLy save(ChuQuanLy entity);

	public void deleteById(Long id);

	public Optional<ChuQuanLy> findById(Long id);

	public Page<ChuQuanLy> findAll(String chuHo, String diaChi, Integer dienThoai, Pageable pageable);

}
