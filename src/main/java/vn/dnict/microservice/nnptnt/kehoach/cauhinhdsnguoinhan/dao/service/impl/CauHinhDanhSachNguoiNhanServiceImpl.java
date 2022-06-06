package vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.model.CauHinhDanhSachNguoiNhan;
import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service.CauHinhDanhSachNguoiNhanService;
import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service.CauHinhDanhSachNguoiNhanSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service.repo.CauHinhDanhSachNguoiNhanRepo;

@Service
public class CauHinhDanhSachNguoiNhanServiceImpl implements CauHinhDanhSachNguoiNhanService {
	@Autowired
	private CauHinhDanhSachNguoiNhanRepo repo;

	@Override
	public CauHinhDanhSachNguoiNhan save(CauHinhDanhSachNguoiNhan cauHinhDanhSachNguoiNhan) {
		return repo.save(cauHinhDanhSachNguoiNhan);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<CauHinhDanhSachNguoiNhan> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Page<CauHinhDanhSachNguoiNhan> findAll(String search, Boolean trangThai, Pageable pageable) {
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<Long> getDanhSachCanBoIdsByTrangThaiKeHoachIdAndPhongBanId(Long trangThaiKeHoachId, Long phongBanId) {
		return repo.getDanhSachCanBoIdsByTrangThaiKeHoachIdAndPhongBanId(trangThaiKeHoachId, phongBanId);
	}

	@Override
	public List<CauHinhDanhSachNguoiNhan> findAllTrangThai(Long donViId, Long phongBanId, Long trangThaiKeHoachId) {
		return repo.findAll(CauHinhDanhSachNguoiNhanSpecifications.quichSearch(donViId, phongBanId, trangThaiKeHoachId));
	}

}
