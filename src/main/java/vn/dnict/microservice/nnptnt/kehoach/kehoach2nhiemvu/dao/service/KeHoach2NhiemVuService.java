package vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model.KeHoach2NhiemVu;

public interface KeHoach2NhiemVuService {
	KeHoach2NhiemVu save(KeHoach2NhiemVu keHoach2NhiemVu);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<KeHoach2NhiemVu> findById(Long id);

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

	List<KeHoach2NhiemVu> findAll(Long keHoachKhacId, Long nhiemVuChaId, Boolean isBanHanh, Boolean isThucHien, List<Long> nguonKinhPhiIds,
			List<Integer> tinhTrangs, LocalDate thoiGianThucHienTuNgay, LocalDate thoiGianThucHienDenNgay, LocalDate thoiGianThanhToanTuNgay,
			LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds, List<Long> phongBanPhoiHopIds, Long donViThucHienId,
			Long phongBanThucHienId);

}
