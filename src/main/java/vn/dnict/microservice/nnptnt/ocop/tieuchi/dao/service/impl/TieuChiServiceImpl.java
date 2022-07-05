package vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.model.TieuChi;
import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.TieuChiService;
import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.TieuChiSpecifications;
import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.repo.TieuChiRepo;
@Service
@Transactional
public class TieuChiServiceImpl implements TieuChiService{
	
	@Autowired
	TieuChiRepo repo;

	@Override
	public TieuChi save(TieuChi tieuChi) {
		// TODO Auto-generated method stub
		return repo.save(tieuChi);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<TieuChi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<TieuChi> findAll(Long tieuChiNamId, Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(TieuChiSpecifications.quickSearch(tieuChiNamId, nam, phanNhomId, nganhHangId, nhomId),pageable);
	}

	@Override
	public int setFixedDaXoaForTieuChiNamId(boolean daXoa, Long tieuChiNamId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForTieuChiNamId(daXoa, tieuChiNamId);
	}

	@Override
	public List<TieuChi> getByTieuChiNamIdAndChaIdIsNullAndDaXoa(Long tieuChiNamId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByTieuChiNamIdAndChaIdIsNullAndDaXoa(tieuChiNamId, daXoa);
	}

	@Override
	public List<TieuChi> getByTieuChiNamIdAndChaIdAndDaXoa(Long tieuChiNamId, Long chaId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByTieuChiNamIdAndChaIdAndDaXoa(tieuChiNamId, chaId, daXoa);
	}

	@Override
	public List<TieuChi> getByChaIdAndDaXoa(Long chaId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByChaIdAndDaXoa(chaId, daXoa);
	}

}
