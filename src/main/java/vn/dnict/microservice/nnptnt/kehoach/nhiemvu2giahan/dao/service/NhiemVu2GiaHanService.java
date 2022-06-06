package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.model.NhiemVu2GiaHan;


public interface NhiemVu2GiaHanService {
	NhiemVu2GiaHan save(NhiemVu2GiaHan nhiemVu2GiaHan);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<NhiemVu2GiaHan> findById(Long id);

	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2GiaHan> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);

}
