package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model.NhiemVu2KinhPhi;

public interface NhiemVu2KinhPhiService {
	NhiemVu2KinhPhi save(NhiemVu2KinhPhi nhiemVu2KinhPhi);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<NhiemVu2KinhPhi> findById(Long id);

	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2KinhPhi> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);

}
