package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;

public interface FileDinhKemNhiemVuThangService {
	public FileDinhKemNhiemVuThang save(FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang);

	public void deleteById(Long id);

	public Optional<FileDinhKemNhiemVuThang> findById(Long id);
	
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangId(Long tienDoNhiemVuThangId);
	
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndDaXoa(Long tienDoNhiemVuThangId, Boolean daXoa);

	public List<FileDinhKemNhiemVuThang> findListByTienDoNhiemVuThangIdAndDaXoa(Long tienDoNhiemVuThangId, Boolean daXoa);

	public int setFixedDaXoaForTienDoNhiemVuThangId(Boolean daXoa, Long tienDoNhiemVuThangId);

	public List<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndFileDinhKemId(Long tienDoNhiemVuThangId,
			Long fileDinhKemId);
}
