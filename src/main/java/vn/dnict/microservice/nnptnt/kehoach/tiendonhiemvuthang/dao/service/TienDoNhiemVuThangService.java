package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service;

import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;

public interface TienDoNhiemVuThangService {
	public TienDoNhiemVuThang save(TienDoNhiemVuThang tienDoNhiemVuThang);

	public void deleteById(Long id);

	public Optional<TienDoNhiemVuThang> findById(Long id);
}
