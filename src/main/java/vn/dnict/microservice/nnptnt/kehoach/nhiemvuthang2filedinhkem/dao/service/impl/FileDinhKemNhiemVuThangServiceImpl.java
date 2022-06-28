package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.repo.FileDinhKemNhiemVuThangRepo;

@Service
@Transactional
public class FileDinhKemNhiemVuThangServiceImpl implements FileDinhKemNhiemVuThangService {
	@Autowired
	FileDinhKemNhiemVuThangRepo repo;

	@Override
	public FileDinhKemNhiemVuThang save(FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang) {
		// TODO Auto-generated method stub
		return repo.save(fileDinhKemNhiemVuThang);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<FileDinhKemNhiemVuThang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndDaXoa(Long tienDoNhiemVuThangId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNhiemVuThangIdAndDaXoa(tienDoNhiemVuThangId, daXoa);
	}

	@Override
	public int setFixedDaXoaForTienDoNhiemVuThangId(Boolean daXoa, Long tienDoNhiemVuThangId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForTienDoNhiemVuThangId(daXoa, tienDoNhiemVuThangId);
	}

	@Override
	public List<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndFileDinhKemId(Long tienDoNhiemVuThangId,
			Long fileDinhKemId) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNhiemVuThangIdAndFileDinhKemId(tienDoNhiemVuThangId, fileDinhKemId);
	}

	@Override
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangId(Long tienDoNhiemVuThangId) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNhiemVuThangId(tienDoNhiemVuThangId);
	}
}
