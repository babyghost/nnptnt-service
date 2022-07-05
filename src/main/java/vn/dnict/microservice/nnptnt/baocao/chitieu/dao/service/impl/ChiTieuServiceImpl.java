package vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.ChiTieuService;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.ChiTieuSpecifications;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.repo.ChiTieuRepo;
@Transactional
@Service
public class ChiTieuServiceImpl implements ChiTieuService{
	@Autowired
	ChiTieuRepo repo;

	@Override
	public ChiTieu save(ChiTieu chiTieu) {
		// TODO Auto-generated method stub
		return repo.save(chiTieu);
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
	public Optional<ChiTieu> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ChiTieu> findAll(String ten, Long chiTieuNamId, Integer nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ChiTieuSpecifications.quickSearch(ten, chiTieuNamId, nam),pageable);
	}

	@Override
	public int setFixedDaXoaForChiTieuNamId(boolean daXoa, Long chiTieuNamId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForChiTieuNamId(daXoa, chiTieuNamId);
	}

	@Override
	public List<ChiTieu> getByChiTieuNamIdAndChaIdIsNullAndDaXoa(Long chiTieuNamId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByChiTieuNamIdAndChaIdIsNullAndDaXoa(chiTieuNamId, daXoa);
	}

	@Override
	public List<ChiTieu> getByChiTieuNamIdAndChaIdAndDaXoa(Long chiTieuNamId, Long chaId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByChiTieuNamIdAndChaIdAndDaXoa(chiTieuNamId, chaId, daXoa);
	}

	@Override
	public List<ChiTieu> getByChaIdAndDaXoa(Long chaId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByChaIdAndDaXoa(chaId, daXoa);
	}
	
	
	

}
