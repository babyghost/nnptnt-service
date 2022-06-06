package vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.model.KeHoach2QuyetDinh;

@Repository
public interface KeHoach2QuyetDinhRepo extends JpaRepository<KeHoach2QuyetDinh, Long>, JpaSpecificationExecutor<KeHoach2QuyetDinh> {

	@Modifying(clearAutomatically = true)
	@Query("update KeHoach2QuyetDinh u set u.daXoa = ?1 where u.keHoachKhacId = ?2")
	int setFixedDaXoaForKeHoachKhacId(boolean daXoa, Long keHoachKhacId);

	@Modifying(clearAutomatically = true)
	@Query("update KeHoach2QuyetDinh u set u.isHienTai = ?1 where u.keHoachKhacId = ?2")
	int setFixedIsHienTaiForKeHoachKhacId(boolean isHienTai, Long keHoachKhacId);

	List<KeHoach2QuyetDinh> findByKeHoachKhacIdAndDaXoa(Long keHoachKhacId, boolean daXoa);

	List<KeHoach2QuyetDinh> findByIsHienTaiAndKeHoachKhacIdAndDaXoa(boolean isHienTai, Long keHoachKhacId, boolean daXoa);

}