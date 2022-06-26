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
	public List<FileDinhKemNhiemVuThang> findByTienDoNvThangIdAndDaXoa(Long tienDoNvThangId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNvThangIdAndDaXoa(tienDoNvThangId, daXoa);
	}

	@Override
	public int setFixedDaXoaForTienDoNvThangId(Boolean daXoa, Long tienDoNvThangId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForTienDoNvThangId(daXoa, tienDoNvThangId);
	}

	@Override
	public List<FileDinhKemNhiemVuThang> findByTienDoNvThangIdAndFileDinhKemId(Long tienDoNvThangId,
			Long fileDinhKemId) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNvThangIdAndFileDinhKemId(tienDoNvThangId, fileDinhKemId);
	}
}
