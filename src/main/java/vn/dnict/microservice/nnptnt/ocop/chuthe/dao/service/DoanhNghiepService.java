package vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;

public interface DoanhNghiepService {
	public DoanhNghiep save(DoanhNghiep entity);

	public void deleteById(Long id);

	public Optional<DoanhNghiep> findById(Long id);
	

	public Page<DoanhNghiep> findAll(String ten, String chuSoHuu, Long loaiDoanhNghiepId,
			Long loaiHinhId, Long nganhNgheId , Integer trangThai, Pageable pageable);
}















