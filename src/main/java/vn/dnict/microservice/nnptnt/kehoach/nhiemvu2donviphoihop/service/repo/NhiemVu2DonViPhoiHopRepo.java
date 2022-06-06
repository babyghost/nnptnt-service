package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.model.NhiemVu2DonViPhoiHop;

@Repository
public interface NhiemVu2DonViPhoiHopRepo extends JpaRepository<NhiemVu2DonViPhoiHop, Long>, JpaSpecificationExecutor<NhiemVu2DonViPhoiHop> {

	@Modifying(clearAutomatically = true)
	@Query("update NhiemVu2DonViPhoiHop u set u.daXoa = ?1 where u.keHoach2NhiemVuId = ?2")
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDonViId(Long keHoach2NhiemVuId, Long donViId);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId, Long phongBanId);
	
	Optional<NhiemVu2DonViPhoiHop> findFirstByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId, Long phongBanId);
}