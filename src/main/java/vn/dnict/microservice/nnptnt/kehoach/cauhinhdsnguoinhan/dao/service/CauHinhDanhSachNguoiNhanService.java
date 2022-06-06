package vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.model.CauHinhDanhSachNguoiNhan;

public interface CauHinhDanhSachNguoiNhanService {

	CauHinhDanhSachNguoiNhan save(CauHinhDanhSachNguoiNhan cauHinhDanhSachNguoiNhan);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<CauHinhDanhSachNguoiNhan> findById(Long id);

	Page<CauHinhDanhSachNguoiNhan> findAll(String search, Boolean trangThai, Pageable pageable);

	List<Long> getDanhSachCanBoIdsByTrangThaiKeHoachIdAndPhongBanId(Long trangThaiKeHoachId, Long phongBanId);

	List<CauHinhDanhSachNguoiNhan> findAllTrangThai(Long donViId, Long phongBanId, Long trangThaiKeHoachId);

}
