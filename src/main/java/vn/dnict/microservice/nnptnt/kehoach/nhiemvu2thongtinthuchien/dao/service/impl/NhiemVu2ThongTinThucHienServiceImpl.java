package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.model.NhiemVu2ThongTinThucHien;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service.NhiemVu2ThongTinThucHienService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service.repo.NhiemVu2ThongTinThucHienRepo;


@Service
@Transactional
public class NhiemVu2ThongTinThucHienServiceImpl implements NhiemVu2ThongTinThucHienService {
	@Autowired
	private NhiemVu2ThongTinThucHienRepo repo;

	@Override
	public NhiemVu2ThongTinThucHien save(NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien) {
		return repo.save(nhiemVu2ThongTinThucHien);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVu2ThongTinThucHien> findById(Long id) {
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
	public List<NhiemVu2ThongTinThucHien> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa) {
		return repo.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVuId, daXoa);
	}

}
