package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;

public interface FileDinhKemNhiemVuNamService {
	public FileDinhKemNhiemVuNam save(FileDinhKemNhiemVuNam fileDinhKemNhiemVuNam);

	public void deleteById(Long id);

	public Optional<FileDinhKemNhiemVuNam> findById(Long id);
	
	public Optional<FileDinhKemNhiemVuNam> findByTienDoNvNamId(Long tienDoNvNamId);

	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndDaXoa(Long tienDoNvNamId, Boolean daXoa);

	public int setFixedDaXoaForTienDoNvNamId(Boolean daXoa, Long tienDoNvNamId);

	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndFileDinhKemId(Long tienDoNvNamId, Long fileDinhKemId);
}
