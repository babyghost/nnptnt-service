package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.model.KinhPhi2ThanhToanKhac;


@Repository
public interface KinhPhi2ThanhToanKhacRepo extends JpaRepository<KinhPhi2ThanhToanKhac, Long>, JpaSpecificationExecutor<KinhPhi2ThanhToanKhac> {

	@Modifying(clearAutomatically = true)
	@Query("update KinhPhi2ThanhToanKhac u set u.daXoa = ?1 where u.nhiemVu2KinhPhiId = ?2")
	int setFixedDaXoaForNhiemVu2KinhPhiId(boolean daXoa, Long nhiemVu2KinhPhiId);

	List<KinhPhi2ThanhToanKhac> findByNhiemVu2KinhPhiIdAndDaXoa(Long nhiemVu2KinhPhiId, boolean daXoa);
}