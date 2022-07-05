package vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.model.TieuChi;
@Repository
public interface TieuChiRepo extends JpaRepository<TieuChi, Long>,JpaSpecificationExecutor<TieuChi> {

	@Modifying(clearAutomatically = true)
	@Query("update TieuChi u set u.daXoa = ?1 where u.tieuChiNamId = ?2")
	public int setFixedDaXoaForTieuChiNamId(boolean daXoa, Long tieuChiNamId);

	@Query(name = "SELECT u FROM TieuChi u WHERE u.tieuChiNamId = ?1 AND u.chaId IS NULL AND u.daXoa = ?2 ", nativeQuery = true)
	public List<TieuChi> getByTieuChiNamIdAndChaIdIsNullAndDaXoa(Long tieuChiNamId, boolean daXoa);

	@Query(name = "SELECT u FROM TieuChi u WHERE ct.tieuChiNamId = ?1 AND u.chaId ?2 AND u.daXoa = ?3 ", nativeQuery = true)
	public List<TieuChi> getByTieuChiNamIdAndChaIdAndDaXoa(Long TieuChiNamId, Long chaId,
			boolean daXoa);
	
	@Query(name = "SELECT u FROM TieuChi u WHERE  AND u.chaId ?2 AND u.daXoa = ?3 ", nativeQuery = true)
	public List<TieuChi> getByChaIdAndDaXoa( Long chaId,
			boolean daXoa);
}
