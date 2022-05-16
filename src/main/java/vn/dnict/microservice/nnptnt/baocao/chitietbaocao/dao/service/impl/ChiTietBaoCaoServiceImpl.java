package vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.model.ChiTietBaoCao;
import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service.ChiTietBaoCaoService;
import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service.repo.ChiTietBaoCaoRepo;
@Service
@Transactional
public class ChiTietBaoCaoServiceImpl implements ChiTietBaoCaoService {
	
	@Autowired
	ChiTietBaoCaoRepo repo;
	
	@Override
	public ChiTietBaoCao save(ChiTietBaoCao chiTietBaoCao) {
		// TODO Auto-generated method stub
		return repo.save(chiTietBaoCao);
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
	public Optional<ChiTietBaoCao> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public int setFixedDaXoaForYeuCauBaoCaoId(boolean daXoa, Long yeuCauBaoCaoId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForYeuCauBaoCaoId(daXoa, yeuCauBaoCaoId);
	}

	@Override
	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(Long yeuCauBaoCaoId, Long donViId,
			boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(yeuCauBaoCaoId, donViId, daXoa);
	}

	@Override
	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDaXoa(Long yeuCauBaoCaoId, boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByYeuCauBaoCaoIdAndDaXoa(yeuCauBaoCaoId, daXoa);
	}



}
