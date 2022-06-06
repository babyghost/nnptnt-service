package vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.model.KeHoach2QuyetDinh;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service.KeHoach2QuyetDinhService;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service.repo.KeHoach2QuyetDinhRepo;

@Service
@Transactional
public class KeHoach2QuyetDinhServiceImpl implements KeHoach2QuyetDinhService {
	@Autowired
	private KeHoach2QuyetDinhRepo repo;

	@Override
	public KeHoach2QuyetDinh save(KeHoach2QuyetDinh keHoach2QuyetDinh) {
		return repo.save(keHoach2QuyetDinh);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<KeHoach2QuyetDinh> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForKeHoachKhacId(boolean daXoa, Long keHoachKhacId) {
		return repo.setFixedDaXoaForKeHoachKhacId(daXoa, keHoachKhacId);
	}

	@Override
	public List<KeHoach2QuyetDinh> findByKeHoachKhacIdAndDaXoa(Long keHoachKhacId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndDaXoa(keHoachKhacId, daXoa);
	}

	@Override
	public int setFixedIsHienTaiForKeHoachKhacId(boolean isHienTai, Long keHoachKhacId) {
		return repo.setFixedIsHienTaiForKeHoachKhacId(isHienTai, keHoachKhacId);
	}

	@Override
	public List<KeHoach2QuyetDinh> findByIsHienTaiAndKeHoachKhacIdAndDaXoa(boolean isHienTai, Long keHoachKhacId, boolean daXoa) {
		return repo.findByIsHienTaiAndKeHoachKhacIdAndDaXoa(isHienTai, keHoachKhacId, daXoa);
	}

}
