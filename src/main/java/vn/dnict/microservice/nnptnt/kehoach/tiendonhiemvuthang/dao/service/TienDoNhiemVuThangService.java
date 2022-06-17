package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;

public interface TienDoNhiemVuThangService {
	public TienDoNhiemVuThang save(TienDoNhiemVuThang entity);
	
	public void deleteById(Long id);
	
	public Optional<TienDoNhiemVuThang> findById(Long id);
	
	public Page<TienDoNhiemVuThang> findAll(String tenNguoiCapNhat, Integer mucDoHoanThanh, String ketQua, Pageable pageable);
}
