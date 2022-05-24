package vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangService;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangSpecifications;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.repo.DmNganhHangRepo;

@Service
@Transactional
public class DmNganhHangServiceImpl implements DmNganhHangService {
	
	@Autowired
	DmNganhHangRepo repo;

	@Override
	public DmNganhHang save(DmNganhHang entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<DmNganhHang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<DmNganhHang> findAll(String ten, Boolean trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(DmNganhHangSpecifications.quickSearch(ten, trangThai),pageable);
	}


}
