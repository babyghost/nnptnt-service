package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.model.KinhPhi2ThanhToanKhac;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service.KinhPhi2ThanhToanKhacService;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service.repo.KinhPhi2ThanhToanKhacRepo;

@Service
@Transactional
public class KinhPhi2ThanhToanKhacServiceImpl implements KinhPhi2ThanhToanKhacService {
	@Autowired
	private KinhPhi2ThanhToanKhacRepo repo;

	@Override
	public KinhPhi2ThanhToanKhac save(KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac) {
		return repo.save(kinhPhi2ThanhToanKhac);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<KinhPhi2ThanhToanKhac> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForNhiemVu2KinhPhiId(boolean daXoa, Long nhiemVu2KinhPhiId) {
		return repo.setFixedDaXoaForNhiemVu2KinhPhiId(daXoa, nhiemVu2KinhPhiId);
	}

	@Override
	public List<KinhPhi2ThanhToanKhac> findByNhiemVu2KinhPhiIdAndDaXoa(Long nhiemVu2KinhPhiId, boolean daXoa) {
		return repo.findByNhiemVu2KinhPhiIdAndDaXoa(nhiemVu2KinhPhiId, daXoa);
	}

}
