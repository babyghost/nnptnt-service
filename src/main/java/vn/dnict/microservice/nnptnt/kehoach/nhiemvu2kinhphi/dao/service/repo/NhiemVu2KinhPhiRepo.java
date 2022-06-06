package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model.NhiemVu2KinhPhi;

@Repository
public interface NhiemVu2KinhPhiRepo extends JpaRepository<NhiemVu2KinhPhi, Long>, JpaSpecificationExecutor<NhiemVu2KinhPhi> {

	@Modifying(clearAutomatically = true)
	@Query("update NhiemVu2KinhPhi u set u.daXoa = ?1 where u.keHoach2NhiemVuId = ?2")
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2KinhPhi> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);
}