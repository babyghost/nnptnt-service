package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

@Repository
public interface NhiemVuThangRepo extends JpaRepository<NhiemVuThang, Long>, JpaSpecificationExecutor<NhiemVuThang> {
	
	//@Query(name = "SELECT u FROM NhiemVuThang u WHERE u.keHoachThangId = ?1 AND u.daXoa = ?2 ", nativeQuery = true)
	public List<NhiemVuThang> findByKeHoachThangIdAndDaXoa(Long keHoachThangId, Boolean daXoa);
	
	@Modifying(clearAutomatically = true)
	@Query("update NhiemVuThang u set u.daXoa = ?1 where u.keHoachThangId = ?2")
	public int setFixedDaXoaForKeHoachThangId(Boolean daXoa, Long keHoachThangId);
}
