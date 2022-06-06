package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.model.NhiemVu2GiaHan;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service.NhiemVu2GiaHanService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service.repo.NhiemVu2GiaHanRepo;


@Service
@Transactional
public class NhiemVu2GiaHanServiceImpl implements NhiemVu2GiaHanService {
	@Autowired
	private NhiemVu2GiaHanRepo repo;

	@Override
	public NhiemVu2GiaHan save(NhiemVu2GiaHan nhiemVu2GiaHan) {
		return repo.save(nhiemVu2GiaHan);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVu2GiaHan> findById(Long id) {
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
	public List<NhiemVu2GiaHan> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa) {
		return repo.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVuId, daXoa);
	}

}
