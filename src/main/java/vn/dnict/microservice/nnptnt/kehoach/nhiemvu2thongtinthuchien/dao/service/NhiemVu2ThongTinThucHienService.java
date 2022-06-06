package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.model.NhiemVu2ThongTinThucHien;


public interface NhiemVu2ThongTinThucHienService {
	NhiemVu2ThongTinThucHien save(NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<NhiemVu2ThongTinThucHien> findById(Long id);

	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2ThongTinThucHien> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);

}
