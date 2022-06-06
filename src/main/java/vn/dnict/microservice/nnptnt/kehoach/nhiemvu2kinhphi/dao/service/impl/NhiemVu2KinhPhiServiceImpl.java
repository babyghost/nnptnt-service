package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model.NhiemVu2KinhPhi;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service.NhiemVu2KinhPhiService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service.repo.NhiemVu2KinhPhiRepo;

@Service
@Transactional
public class NhiemVu2KinhPhiServiceImpl implements NhiemVu2KinhPhiService {
	@Autowired
	private NhiemVu2KinhPhiRepo repo;

	@Override
	public NhiemVu2KinhPhi save(NhiemVu2KinhPhi nhiemVu2KinhPhi) {
		return repo.save(nhiemVu2KinhPhi);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVu2KinhPhi> findById(Long id) {
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
	public List<NhiemVu2KinhPhi> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa) {
		return repo.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVuId, daXoa);
	}

}
