package vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;

public interface DanhGiaSanPhamService {
	public DanhGiaSanPham save(DanhGiaSanPham entity);

	public void deleteById(Long id);

	public Optional<DanhGiaSanPham> findById(Long id);
	
	public Long getPhanHangBySanPhamId(Long sanPhamId);
	public Page<DanhGiaSanPham> findAll(Long sanPhamId, Long tieuChiId , Pageable pageable);
}
