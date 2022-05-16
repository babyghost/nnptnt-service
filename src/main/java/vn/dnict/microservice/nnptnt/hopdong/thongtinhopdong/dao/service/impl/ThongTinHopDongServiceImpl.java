package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.ThongTinHopDongService;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.ThongTinHopDongSpecifications;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.repo.ThongTinHopDongRepo;


@Service
public class ThongTinHopDongServiceImpl implements ThongTinHopDongService {
	@Autowired
	private ThongTinHopDongRepo repo;

	public ThongTinHopDong save(ThongTinHopDong ThongTinHopDong) {
		return repo.save(ThongTinHopDong);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<ThongTinHopDong> findById(Long id) {
		return repo.findById(id);
	}

	public Page<ThongTinHopDong> findByDaXoaAndSearch(String search, Pageable pageable) {
		return repo.findAll(ThongTinHopDongSpecifications.findByDaXoaAndSearch(search), pageable);
	}

	public Page<ThongTinHopDong> findAll(String search, String ten, String soHieu, Long loaiHopDongId,
			Integer trangThai, LocalDate tuNgayKy, LocalDate denNgayKy, LocalDate thoiGianThTuNgay,
			LocalDate thoiGianThDenNgay, Pageable pageable) {
		return repo.findAll(ThongTinHopDongSpecifications.quickSearch(search, ten, soHieu, loaiHopDongId,
				trangThai, tuNgayKy, denNgayKy, thoiGianThTuNgay, thoiGianThDenNgay), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<ThongTinHopDong> findByDaXoa(boolean daXoa) {
		return repo.findByDaXoa(daXoa);
	}

	public Long countByTrangThaiAndThoiGianThDenNgayBetween(Integer trangThai, LocalDate before5day, LocalDate nowDay) {
		return repo.countByTrangThaiAndThoiGianThDenNgayBetween(trangThai, before5day, nowDay);
	}
}
