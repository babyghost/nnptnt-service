package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;

public interface NhiemVuThangLogRepo extends JpaRepository<NhiemVuThangLog, Long>, JpaSpecificationExecutor<NhiemVuThangLog> {

	public List<NhiemVuThangLog> findByNhiemVuThangIdAndDaXoa(Long nhiemVuThangId, Boolean daXoa);
	
	@Modifying
	@Query("update NhiemVuThangLog u set u.daXoa = ?1 where u.nhiemVuThangId = ?2")
	public int setFixedDaXoaForNhiemVuThangId(Boolean daXoa, Long nhiemVuThangId);
	
	public Optional<NhiemVuThangLog> findByNhiemVuThangId(Long nhiemVuThangId);
	
	public List<NhiemVuThangLog> findByNhiemVuThangIdAndCanBoThucHienIdAndDaXoa(Long nhiemVuThangId, Long canBoThucHienId,
			Boolean daXoa);
}
