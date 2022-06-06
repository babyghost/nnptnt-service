package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.model.NhiemVu2ThongTinThucHien;


@Repository
public interface NhiemVu2ThongTinThucHienRepo
		extends
			JpaRepository<NhiemVu2ThongTinThucHien, Long>,
			JpaSpecificationExecutor<NhiemVu2ThongTinThucHien> {

	@Modifying(clearAutomatically = true)
	@Query("update NhiemVu2ThongTinThucHien u set u.daXoa = ?1 where u.keHoach2NhiemVuId = ?2")
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2ThongTinThucHien> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);
}