package vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.model.ThucHien2VanBanLienQuan;
import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service.ThucHien2VanBanLienQuanService;
import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service.repo.ThucHien2VanBanLienQuanRepo;


@Service
@Transactional
public class ThucHien2VanBanLienQuanServiceImpl implements ThucHien2VanBanLienQuanService {
	@Autowired
	private ThucHien2VanBanLienQuanRepo repo;

	@Override
	public ThucHien2VanBanLienQuan save(ThucHien2VanBanLienQuan thucHien2VanBanLienQuan) {
		return repo.save(thucHien2VanBanLienQuan);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<ThucHien2VanBanLienQuan> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForNhiemVu2ThucHienId(boolean daXoa, Long nhiemVu2ThucHienId) {
		return repo.setFixedDaXoaForNhiemVu2ThucHienId(daXoa, nhiemVu2ThucHienId);
	}

	@Override
	public List<ThucHien2VanBanLienQuan> findByNhiemVu2ThucHienIdAndDaXoa(Long nhiemVu2ThucHienId, boolean daXoa) {
		return repo.findByNhiemVu2ThucHienIdAndDaXoa(nhiemVu2ThucHienId, daXoa);
	}

	@Override
	public Optional<ThucHien2VanBanLienQuan> findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(Long nhiemVu2ThucHienId, Long vanBanDinhKemId) {
		return repo.findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(nhiemVu2ThucHienId, vanBanDinhKemId);
	}

}
