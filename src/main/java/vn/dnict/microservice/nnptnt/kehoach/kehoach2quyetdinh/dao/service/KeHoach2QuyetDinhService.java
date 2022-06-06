package vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.model.KeHoach2QuyetDinh;

public interface KeHoach2QuyetDinhService {

	KeHoach2QuyetDinh save(KeHoach2QuyetDinh keHoach2QuyetDinh);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<KeHoach2QuyetDinh> findById(Long id);

	int setFixedDaXoaForKeHoachKhacId(boolean daXoa, Long keHoachKhacId);

	List<KeHoach2QuyetDinh> findByKeHoachKhacIdAndDaXoa(Long keHoachKhacId, boolean daXoa);

	int setFixedIsHienTaiForKeHoachKhacId(boolean isHienTai, Long keHoachKhacId);

	List<KeHoach2QuyetDinh> findByIsHienTaiAndKeHoachKhacIdAndDaXoa(boolean isHienTai, Long keHoachKhacId, boolean daXoa);

}
