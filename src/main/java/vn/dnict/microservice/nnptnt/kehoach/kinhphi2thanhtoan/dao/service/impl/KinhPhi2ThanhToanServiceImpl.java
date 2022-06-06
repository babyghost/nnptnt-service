package vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.model.KinhPhi2ThanhToan;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.service.KinhPhi2ThanhToanService;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoan.dao.service.repo.KinhPhi2ThanhToanRepo;

@Service
@Transactional
public class KinhPhi2ThanhToanServiceImpl implements KinhPhi2ThanhToanService {
	@Autowired
	private KinhPhi2ThanhToanRepo repo;

	@Override
	public KinhPhi2ThanhToan save(KinhPhi2ThanhToan kinhPhi2ThanhToan) {
		return repo.save(kinhPhi2ThanhToan);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<KinhPhi2ThanhToan> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForKeHoach2KinhPhiId(boolean daXoa, Long keHoach2KinhPhiId) {
		return repo.setFixedDaXoaForKeHoach2KinhPhiId(daXoa, keHoach2KinhPhiId);
	}

	@Override
	public List<KinhPhi2ThanhToan> findByKeHoach2KinhPhiIdAndDaXoa(Long keHoach2KinhPhiId, boolean daXoa) {
		return repo.findByKeHoach2KinhPhiIdAndDaXoa(keHoach2KinhPhiId, daXoa);
	}

}
