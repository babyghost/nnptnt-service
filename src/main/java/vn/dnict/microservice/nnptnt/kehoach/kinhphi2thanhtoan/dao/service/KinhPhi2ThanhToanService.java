package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.model.KinhPhi2ThanhToan;

public interface KinhPhi2ThanhToanService {

	KinhPhi2ThanhToan save(KinhPhi2ThanhToan kinhPhi2ThanhToan);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<KinhPhi2ThanhToan> findById(Long id);

	int setFixedDaXoaForKeHoach2KinhPhiId(boolean daXoa, Long keHoach2KinhPhiId);

	List<KinhPhi2ThanhToan> findByKeHoach2KinhPhiIdAndDaXoa(Long keHoach2KinhPhiId, boolean daXoa);

}
