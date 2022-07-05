package vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;

public interface ChiTieuService {
	public ChiTieu save(ChiTieu chiTieu);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<ChiTieu> findById(Long id);
	
	public Page<ChiTieu> findAll(String ten, Long chiTieuNamId, Integer nam,Pageable pageable);
	
	public int setFixedDaXoaForChiTieuNamId(boolean daXoa, Long chiTieuNamId);

	
	public List<ChiTieu> getByChiTieuNamIdAndChaIdIsNullAndDaXoa(Long chiTieuNamId, boolean daXoa);

	
	public List<ChiTieu> getByChiTieuNamIdAndChaIdAndDaXoa(Long chiTieuNamId, Long chaId,
			boolean daXoa);
	
	public List<ChiTieu> getByChaIdAndDaXoa( Long chaId,
			boolean daXoa);
}
