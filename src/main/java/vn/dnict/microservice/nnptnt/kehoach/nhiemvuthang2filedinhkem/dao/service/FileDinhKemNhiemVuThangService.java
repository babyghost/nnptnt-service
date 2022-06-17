package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;

public interface FileDinhKemNhiemVuThangService {
	public FileDinhKemNhiemVuThang save(FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang);
	
	public void deleteById(Long id);
	
	public Optional<FileDinhKemNhiemVuThang> findById(Long id);
	
	public List<FileDinhKemNhiemVuThang> findByFileDinhKemIdAndDaXoa(Long fileDinhKemId, Boolean daXoa);
	
	public int setFixedDaXoaForTienDoNhiemVuThangId(Boolean daXoa, Long tienDoNhiemVuThangId);
	
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangId(Long tienDoNhiemVuThangId);
	
	public List<FileDinhKemNhiemVuThang> findByFileDinhKemIdAndTienDoNhiemVuThangId(Long fileDinhKemId, Long tienDoNhiemVuThangId);
}
