package vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;

public interface DmNganhHangService {
	public DmNganhHang save(DmNganhHang entity);

	public void deleteById(Long id);

	public Optional<DmNganhHang> findById(Long id);
	

	public Page<DmNganhHang> findAll(String ten, Boolean trangThai, Pageable pageable);
}
