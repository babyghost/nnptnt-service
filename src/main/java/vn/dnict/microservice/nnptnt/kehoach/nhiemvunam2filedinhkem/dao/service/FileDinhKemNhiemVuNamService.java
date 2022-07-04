package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;

public interface FileDinhKemNhiemVuNamService {
	public FileDinhKemNhiemVuNam save(FileDinhKemNhiemVuNam fileDinhKemNhiemVuNam);

	public void deleteById(Long id);

	public Optional<FileDinhKemNhiemVuNam> findById(Long id);
	
	public Optional<FileDinhKemNhiemVuNam> findByTienDoNhiemVuNamId(Long tienDoNhiemVuNamId);

	public List<FileDinhKemNhiemVuNam> findByTienDoNhiemVuNamIdAndDaXoa(Long tienDoNhiemVuNamId, Boolean daXoa);

	public int setFixedDaXoaForTienDoNhiemVuNamId(Boolean daXoa, Long tienDoNhiemVuNamId);

	public List<FileDinhKemNhiemVuNam> findByFileDinhKemIdAndTienDoNhiemVuNamId(Long fileDinhKemId, Long tienDoNhiemVuNamId);
}
