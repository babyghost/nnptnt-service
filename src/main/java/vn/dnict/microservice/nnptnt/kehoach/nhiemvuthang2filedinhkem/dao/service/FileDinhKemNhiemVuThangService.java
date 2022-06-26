package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;

public interface FileDinhKemNhiemVuThangService {
	public FileDinhKemNhiemVuThang save(FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang);

	public void deleteById(Long id);

	public Optional<FileDinhKemNhiemVuThang> findById(Long id);

	public List<FileDinhKemNhiemVuThang> findByTienDoNvThangIdAndDaXoa(Long tienDoNvThangId, Boolean daXoa);

	public int setFixedDaXoaForTienDoNvThangId(Boolean daXoa, Long tienDoNvThangId);

	public List<FileDinhKemNhiemVuThang> findByTienDoNvThangIdAndFileDinhKemId(Long tienDoNvThangId,
			Long fileDinhKemId);
}
