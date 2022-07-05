package vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.TieuChiNamService;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.TieuChiNamSpecifications;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.repo.TieuChiNamRepo;
@Service
@Transactional
public class TieuChiNamServiceImpl implements TieuChiNamService {

	@Autowired
	TieuChiNamRepo repo;
	
	@Override
	public TieuChiNam save(TieuChiNam entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<TieuChiNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Optional<TieuChiNam> findByNamAndPhanNhomAndNhomAndNganhHang(Integer nam, Long phanNhomId, Long nganhHangId,
			Long nhomId) {
		// TODO Auto-generated method stub
		return repo.findByNamAndPhanNhomAndNhomAndNganhHang(nam, phanNhomId, nganhHangId, nhomId);
	}

	@Override
	public Page<TieuChiNam> findAll(Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId,Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(TieuChiNamSpecifications.quickSearch(nam, phanNhomId, nganhHangId, nhomId, trangThai),pageable);
	}

	@Override
	public Page<TieuChiNam> findChiTiet(Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(TieuChiNamSpecifications.searchChiTiet(nam, phanNhomId, nganhHangId, nhomId),pageable);
	}

}
