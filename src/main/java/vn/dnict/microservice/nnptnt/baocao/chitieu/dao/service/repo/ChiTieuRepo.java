package vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;
@Repository
public interface ChiTieuRepo extends JpaRepository<ChiTieu, Long>,JpaSpecificationExecutor<ChiTieu> {

	@Modifying(clearAutomatically = true)
	@Query("update ChiTieu u set u.daXoa = ?1 where u.chiTieuNamId = ?2")
	public int setFixedDaXoaForChiTieuNamId(boolean daXoa, Long chiTieuNamId);

	@Query(name = "SELECT u FROM ChiTieu u WHERE u.chiTieuNamId = ?1 AND u.chaId IS NULL AND u.daXoa = ?2 ORDER BY u.sapXep ASC", nativeQuery = true)
	public List<ChiTieu> getByChiTieuNamIdAndChaIdIsNullAndDaXoa(Long chiTieuNamId, boolean daXoa);

	@Query(name = "SELECT u FROM ChiTieu u WHERE ct.chiTieuNamId = ?1 AND u.chaId ?2 AND u.daXoa = ?3 ORDER BY u.sapXep ASC", nativeQuery = true)
	public List<ChiTieu> getByChiTieuNamIdAndChaIdAndDaXoa(Long chiTieuNamId, Long chaId,
			boolean daXoa);
}
