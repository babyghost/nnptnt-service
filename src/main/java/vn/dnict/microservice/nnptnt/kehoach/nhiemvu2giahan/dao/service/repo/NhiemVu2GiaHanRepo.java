package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.model.NhiemVu2GiaHan;


@Repository
public interface NhiemVu2GiaHanRepo extends JpaRepository<NhiemVu2GiaHan, Long>, JpaSpecificationExecutor<NhiemVu2GiaHan> {

	@Modifying(clearAutomatically = true)
	@Query("update NhiemVu2GiaHan u set u.daXoa = ?1 where u.keHoach2NhiemVuId = ?2")
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2GiaHan> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);
}