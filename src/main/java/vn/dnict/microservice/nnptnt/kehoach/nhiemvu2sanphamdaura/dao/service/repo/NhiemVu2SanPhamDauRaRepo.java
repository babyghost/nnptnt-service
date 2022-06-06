package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.model.NhiemVu2SanPhamDauRa;


@Repository
public interface NhiemVu2SanPhamDauRaRepo extends JpaRepository<NhiemVu2SanPhamDauRa, Long>, JpaSpecificationExecutor<NhiemVu2SanPhamDauRa> {
	
	@Modifying(clearAutomatically = true)
	@Query("update NhiemVu2SanPhamDauRa u set u.daXoa = ?1 where u.keHoach2NhiemVuId = ?2")
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);
	
	List<NhiemVu2SanPhamDauRa> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);
	
	Optional<NhiemVu2SanPhamDauRa> findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(Long keHoach2NhiemVuId, Long sanPhamDauRaId);
}