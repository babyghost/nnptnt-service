package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;

public interface NhiemVuThangLogRepo extends JpaRepository<NhiemVuThangLog, Long>, JpaSpecificationExecutor<NhiemVuThangLog> {

	@Query("SELECT u FROM NhiemVuThangLog u WHERE u.nhiemVuThangId = ?1")
	public List<NhiemVuThangLog> findByNhiemVuThangId(Long nhiemVuThangId);
}
