package vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.model.ThucHien2VanBanLienQuan;


@Repository
public interface ThucHien2VanBanLienQuanRepo
		extends
			JpaRepository<ThucHien2VanBanLienQuan, Long>,
			JpaSpecificationExecutor<ThucHien2VanBanLienQuan> {

	@Modifying(clearAutomatically = true)
	@Query("update ThucHien2VanBanLienQuan u set u.daXoa = ?1 where u.nhiemVu2ThucHienId = ?2")
	int setFixedDaXoaForNhiemVu2ThucHienId(boolean daXoa, Long nvKhac2ThucHienId);

	List<ThucHien2VanBanLienQuan> findByNhiemVu2ThucHienIdAndDaXoa(Long nhiemVu2ThucHienId, boolean daXoa);

	Optional<ThucHien2VanBanLienQuan> findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(Long nhiemVu2ThucHienId, Long vanBanDinhKemId);
}