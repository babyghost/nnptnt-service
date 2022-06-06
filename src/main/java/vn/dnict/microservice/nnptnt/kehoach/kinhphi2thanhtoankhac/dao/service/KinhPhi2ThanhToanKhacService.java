package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.model.KinhPhi2ThanhToanKhac;

public interface KinhPhi2ThanhToanKhacService {
	KinhPhi2ThanhToanKhac save(KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<KinhPhi2ThanhToanKhac> findById(Long id);

	int setFixedDaXoaForNhiemVu2KinhPhiId(boolean daXoa, Long nhiemVu2KinhPhiId);

	List<KinhPhi2ThanhToanKhac> findByNhiemVu2KinhPhiIdAndDaXoa(Long nhiemVu2KinhPhiId, boolean daXoa);

}
