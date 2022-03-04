package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;

public interface DmLoaiDonViService {
	public DmLoaiDonVi save(DmLoaiDonVi loaiDonVi);

	public Optional<DmLoaiDonVi> findById(Long id);

	public void delete(Long id);

	public Page<DmLoaiDonVi> findAll(String search, Boolean trangThai, Pageable pageable);

	public Page<DmLoaiDonVi> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable);

	public List<DmLoaiDonVi> findByIdIn(List<Long> idList);
}
