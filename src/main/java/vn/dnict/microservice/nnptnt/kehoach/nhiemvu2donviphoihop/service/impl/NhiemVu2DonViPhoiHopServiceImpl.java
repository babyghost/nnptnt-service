package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.model.NhiemVu2DonViPhoiHop;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service.NhiemVu2DonViPhoiHopService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service.repo.NhiemVu2DonViPhoiHopRepo;

@Service
@Transactional
public class NhiemVu2DonViPhoiHopServiceImpl implements NhiemVu2DonViPhoiHopService {
	@Autowired
	private NhiemVu2DonViPhoiHopRepo repo;

	@Override
	public NhiemVu2DonViPhoiHop save(NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop) {
		return repo.save(nhiemVu2DonViPhoiHop);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVu2DonViPhoiHop> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId) {
		return repo.setFixedDaXoaForKeHoach2NhiemVuId(daXoa, keHoach2NhiemVuId);
	}

	@Override
	public List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa) {
		return repo.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVuId, daXoa);
	}

	@Override
	public List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndDonViId(Long keHoach2NhiemVuId, Long donViId) {
		return repo.findByKeHoach2NhiemVuIdAndDonViId(keHoach2NhiemVuId, donViId);
	}

	@Override
	public List<NhiemVu2DonViPhoiHop> findByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId, Long phongBanId) {
		return repo.findByKeHoach2NhiemVuIdAndPhongBanId(keHoach2NhiemVuId, phongBanId);
	}

	@Override
	public Optional<NhiemVu2DonViPhoiHop> findFirstByKeHoach2NhiemVuIdAndPhongBanId(Long keHoach2NhiemVuId,
			Long phongBanId) {
		return repo.findFirstByKeHoach2NhiemVuIdAndPhongBanId(keHoach2NhiemVuId, phongBanId);
	}

}
