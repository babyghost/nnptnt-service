package vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.model.TieuChi;

public interface TieuChiService {
	public TieuChi save(TieuChi tieuChi);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<TieuChi> findById(Long id);
	
	public Page<TieuChi> findAll(Long tieuChiNamId, Integer nam, Long phanNhomId, Long nganhHangId,Long nhomId,Pageable pageable);
	
	public int setFixedDaXoaForTieuChiNamId(boolean daXoa, Long tieuChiNamId);

	
	public List<TieuChi> getByTieuChiNamIdAndChaIdIsNullAndDaXoa(Long tieuChiNamId, boolean daXoa);

	
	public List<TieuChi> getByTieuChiNamIdAndChaIdAndDaXoa(Long tieuChiNamId, Long chaId,
			boolean daXoa);
	
	public List<TieuChi> getByChaIdAndDaXoa( Long chaId,
			boolean daXoa);
}
