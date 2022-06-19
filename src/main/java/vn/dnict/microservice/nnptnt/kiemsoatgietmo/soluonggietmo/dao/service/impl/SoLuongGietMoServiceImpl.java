package vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.model.SoLuongGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.SoLuongGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.repo.SoLuongGietMoRepo;

@Service
@Transactional
public class SoLuongGietMoServiceImpl implements SoLuongGietMoService {

	@Autowired
	SoLuongGietMoRepo repo;
	
	@Override
	public SoLuongGietMo save(SoLuongGietMo soLuongGietMo) {
		// TODO Auto-generated method stub
		return repo.save(soLuongGietMo);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<SoLuongGietMo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<SoLuongGietMo> findByThongTinGietMoIdAndDaXoa(Long thongTinGietMoId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByThongTinGietMoIdAndDaXoa(thongTinGietMoId, daXoa);
	}

	@Override
	public int setFixedDaXoaAndThongTinGietMoId(Boolean daXoa, Long thongTinGietMoId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaAndThongTinGietMoId(daXoa, thongTinGietMoId);
	}

	@Override
	public Optional<SoLuongGietMo> findByThongTinGietMoId(Long thongTinGietMoId) {
		// TODO Auto-generated method stub
		return repo.findByThongTinGietMoId(thongTinGietMoId);
	}

}
