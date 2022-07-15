package vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.model.DmLoaiHopDong;

public interface DmLoaiHopDongService {
	public DmLoaiHopDong save(DmLoaiHopDong loaiHopDong);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<DmLoaiHopDong> findById(Long id);

	public Page<DmLoaiHopDong> findAll(String ten, String ma, Boolean trangThai, Pageable pageable);
}
