package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model.YeuCauBaoCao;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.YeuCauBaoCaoService;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.YeuCauBaoCaoSpecifications;
import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.repo.YeuCauBaoCaoRepo;
@Service
@Transactional
public class YeuCauBaoCaoServiceImpl implements YeuCauBaoCaoService{
	
	@Autowired
	YeuCauBaoCaoRepo repo;
	
	@Override
	public YeuCauBaoCao save(YeuCauBaoCao entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Optional<YeuCauBaoCao> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<YeuCauBaoCao> findAll(String tieuDe, LocalDate ngayYeuCauTuNgay, LocalDate ngayYeuCauDenNgay,
			LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Long linhVucId, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(YeuCauBaoCaoSpecifications.quickSearch(tieuDe, ngayYeuCauTuNgay, ngayYeuCauDenNgay, thoiHanTuNgay, thoiHanDenNgay, linhVucId, trangThai),pageable);
	}

}
