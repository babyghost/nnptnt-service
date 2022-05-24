package vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.SanPhamService;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.SanPhamSpecifications;
import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.repo.SanPhamRepo;
@Service
@Transactional
public class SanPhamServiceImpl implements SanPhamService{

	@Autowired
	SanPhamRepo repo;
	
	@Override
	public SanPham save(SanPham entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Optional<SanPham> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<SanPham> findAll(String ten, String chuThe, Long nganhHangId, Long phanNhomId, Long phanHangId,
			Integer trangThai, String quyetDinh, Long nhomId, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(SanPhamSpecifications.quickSearch(ten, chuThe, nganhHangId, phanNhomId, phanHangId, trangThai, quyetDinh, nhomId),pageable);
	}

}
