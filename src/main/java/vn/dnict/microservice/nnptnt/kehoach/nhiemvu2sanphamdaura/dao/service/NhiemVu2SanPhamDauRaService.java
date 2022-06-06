package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.model.NhiemVu2SanPhamDauRa;


public interface NhiemVu2SanPhamDauRaService {
	
	NhiemVu2SanPhamDauRa save(NhiemVu2SanPhamDauRa nhiemVu2SanPhamDauRa);
	
	void deleteById(Long id);
	
	boolean existsById(Long id);
	
	Optional<NhiemVu2SanPhamDauRa> findById(Long id);
	
	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);
	
	List<NhiemVu2SanPhamDauRa> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);
	
	Optional<NhiemVu2SanPhamDauRa> findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(Long keHoach2NhiemVuId, Long sanPhamDauRaId);
	
}
