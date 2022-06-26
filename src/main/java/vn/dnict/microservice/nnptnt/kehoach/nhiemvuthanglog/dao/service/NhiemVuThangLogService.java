package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;

public interface NhiemVuThangLogService {
	public NhiemVuThangLog save(NhiemVuThangLog nhiemVuThangLog);

	public void deleteById(Long id);

	public Optional<NhiemVuThangLog> findById(Long id);

	public List<NhiemVuThangLog> findByNhiemVuThangId(Long nhiemVuThangId);
}
