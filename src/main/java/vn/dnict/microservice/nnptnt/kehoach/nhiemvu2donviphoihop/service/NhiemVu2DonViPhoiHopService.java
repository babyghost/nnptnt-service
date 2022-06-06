package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.model.NhiemVu2DonViPhoiHop;

public interface NhiemVu2DonViPhoiHopService {
	NhiemVu2DonViPhoiHop save(NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<NhiemVu2DonViPhoiHop> findById(Long id);

	int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDonViId(Long keHoach2NhiemVuId, Long donViId);

	List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId, Long phongBanId);
	
	Optional<NhiemVu2DonViPhoiHop> findFirstByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId, Long phongBanId);

}
