package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

@Repository
public interface NhiemVuNamRepo extends JpaRepository<NhiemVuNam, Long>, JpaSpecificationExecutor<NhiemVuNam> {
	
	public List<NhiemVuNam> findByKeHoachNamIdAndDaXoa(Long keHoachNamId, Boolean daXoa);
	
	@Query(name = "SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.nhiemVuChaId IS NULL AND u.daXoa = ?2 ORDER BY u.sapXep DESC", nativeQuery = true)
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa);
	
	@Query(name = "SELECT u FROM NhiemVuNam u WHERE ct.keHoachNamId = ?1 AND u.nhiemVuChaId ?2 AND u.daXoa = ?3 ORDER BY u.sapXep ASC", nativeQuery = true)
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuCha, Boolean daXoa);
	
	@Modifying(clearAutomatically = true)
	@Query("update NhiemVuNam u set u.daXoa = ?1 where u.keHoachNamId = ?2")
	public int setFixedDaXoaForKeHoachNamId(Boolean daXoa, Long keHoachNamId);
		
	public Optional<NhiemVuNam> findByKeHoachNamId(Long keHoachNamId);
}
