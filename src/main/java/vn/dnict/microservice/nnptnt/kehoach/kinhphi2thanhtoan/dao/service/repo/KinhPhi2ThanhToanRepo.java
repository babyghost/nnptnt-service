package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.model.KinhPhi2ThanhToan;

@Repository
public interface KinhPhi2ThanhToanRepo extends JpaRepository<KinhPhi2ThanhToan, Long>, JpaSpecificationExecutor<KinhPhi2ThanhToan> {

	@Modifying(clearAutomatically = true)
	@Query("update KinhPhi2ThanhToan u set u.daXoa = ?1 where u.keHoach2KinhPhiId = ?2")
	int setFixedDaXoaForKeHoach2KinhPhiId(boolean daXoa, Long keHoach2KinhPhiId);

	List<KinhPhi2ThanhToan> findByKeHoach2KinhPhiIdAndDaXoa(Long keHoach2KinhPhiId, boolean daXoa);
}