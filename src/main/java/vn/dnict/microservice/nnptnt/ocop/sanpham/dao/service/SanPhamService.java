package vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;

public interface SanPhamService {
	public SanPham save(SanPham entity);

	public void deleteById(Long id);

	public Optional<SanPham> findById(Long id);
	

	public Page<SanPham> findAll(String ten, String chuThe, Long nganhHangId,
			Long phanNhomId, Long phanHangId , Integer trangThai, String quyetDinh, Long nhomId, Pageable pageable);
}
