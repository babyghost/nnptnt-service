package vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model.KeHoach2NhiemVu;

@Repository
public interface KeHoach2NhiemVuRepo extends JpaRepository<KeHoach2NhiemVu, Long>, JpaSpecificationExecutor<KeHoach2NhiemVu> {

	@Modifying(clearAutomatically = true)
	@Query("update KeHoach2NhiemVu u set u.daXoa = ?1 where u.keHoachKhacId = ?2")
	int setFixedDaXoaForKeHoachKhacId(boolean daXoa, Long keHoachKhacId);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoa(Long keHoachKhacId, boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndNhiemVuChaIdIsNull(Long keHoachKhacId, boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId, Long nhiemVuChaId, boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndDonViThucHienIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId, Long nhiemVuChaId,
			Long donViThucHienId, boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndPhongBanThucHienIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId, Long nhiemVuChaId,
			Long phongBanThucHienId, boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndDonViThucHienIdAndNhiemVuChaIdIsNull(Long keHoachKhacId, Long donViThucHienId,
			boolean daXoa);

	List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndPhongBanThucHienIdAndNhiemVuChaIdIsNull(Long keHoachKhacId, Long phongBanThucHienId,
			boolean daXoa);

}