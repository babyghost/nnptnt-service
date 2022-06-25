package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.FileDinhKemNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.repo.FileDinhKemNhiemVuNamRepo;

@Service
@Transactional
public class FileDinhKemNhiemVuNamServiceImpl implements FileDinhKemNhiemVuNamService {
	@Autowired
	FileDinhKemNhiemVuNamRepo repo;

	@Override
	public FileDinhKemNhiemVuNam save(FileDinhKemNhiemVuNam fileDinhKemNhiemVuNam) {
		// TODO Auto-generated method stub
		return repo.save(fileDinhKemNhiemVuNam);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<FileDinhKemNhiemVuNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndDaXoa(Long tienDoNvNamId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNvNamIdAndDaXoa(tienDoNvNamId, daXoa);
	}

	@Override
	public int setFixedDaXoaForTienDoNvNamId(Boolean daXoa, Long tienDoNvNamId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForTienDoNvNamId(daXoa, tienDoNvNamId);
	}

	@Override
	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndFileDinhKemId(Long tienDoNvNamId, Long fileDinhKemId) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNvNamIdAndFileDinhKemId(tienDoNvNamId, fileDinhKemId);
	}

	@Override
	public Optional<FileDinhKemNhiemVuNam> findByTienDoNvNamId(Long tienDoNvNamId) {
		// TODO Auto-generated method stub
		return repo.findByTienDoNvNamId(tienDoNvNamId);
	}
}
