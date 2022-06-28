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
	
	@Query("SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.daXoa = ?2")
	public List<NhiemVuNam> findByKeHoachNamIdAndDaXoa(Long keHoachNamId, Boolean daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update NhiemVuNam u set u.daXoa = ?1 where u.keHoachNamId = ?2")
	public int setFixedDaXoaForKeHoachNamId(Boolean daXoa, Long keHoachNamId);

	@Query("SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.nhiemVuChaId IS NULL AND u.daXoa = ?2 ORDER BY u.sapXep ASC")
	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa);

	@Query("SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.nhiemVuChaId = ?2 AND u.daXoa = ?3 ORDER BY u.sapXep ASC")
	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuChaId, Boolean daXoa);

	@Query("SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.loaiNhiemVuId = ?2 AND  u.nhiemVuChaId IS NULL AND u.daXoa = ?3 ORDER BY u.sapXep ASC")
	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Boolean daXoa);

	@Query("SELECT u FROM NhiemVuNam u WHERE u.keHoachNamId = ?1 AND u.loaiNhiemVuId = ?2 AND u.nhiemVuChaId = ?3 AND u.daXoa = ?4 ORDER BY u.sapXep ASC")
	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Long nhiemVuChaId, Boolean daXoa);
}
