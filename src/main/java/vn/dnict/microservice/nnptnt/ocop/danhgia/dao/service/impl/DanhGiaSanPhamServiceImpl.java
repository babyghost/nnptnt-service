package vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.DanhGiaSanPhamService;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.DanhGiaSanPhamSpecifications;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.repo.DanhGiaSanPhamRepo;
@Service
@Transactional
public class DanhGiaSanPhamServiceImpl implements DanhGiaSanPhamService{

	@Autowired
	DanhGiaSanPhamRepo repo;
	
	@Override
	public DanhGiaSanPham save(DanhGiaSanPham entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DanhGiaSanPham> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DanhGiaSanPham> findAll(Long sanPhamId, Long tieuChiId, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DanhGiaSanPhamSpecifications.quickSearch(sanPhamId, tieuChiId),pageable);
	}

	@Override
	public Long getPhanHangBySanPhamId(Long sanPhamId) {
		// TODO Auto-generated method stub
		return repo.getPhanHangBySanPhamId(sanPhamId);
	}

}
