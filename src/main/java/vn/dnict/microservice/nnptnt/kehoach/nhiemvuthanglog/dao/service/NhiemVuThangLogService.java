package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;

public interface NhiemVuThangLogService {
	public NhiemVuThangLog save(NhiemVuThangLog nhiemVuThangLog);
	
	public void deleteById(Long id);
	
	public Optional<NhiemVuThangLog> findById(Long id);
	
	public List<NhiemVuThangLog> findByNhiemVuThangIdAndDaXoa(Long nhiemVuThangId, Boolean daXoa);
	
	public int setFixedDaXoaForNhiemVuThangId(Boolean daXoa, Long nhiemVuThangId);
	
	public Optional<NhiemVuThangLog> findByNhiemVuThangId(Long nhiemVuThangId);
	
	public List<NhiemVuThangLog> findByNhiemVuThangIdAndCanBoThucHienIdAndDaXoa(Long nhiemVuThangId, Long canBoThucHienId,
			Boolean daXoa);
}
