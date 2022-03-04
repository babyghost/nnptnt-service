package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;

public interface DmPhuongXaService {
	public DmPhuongXa save(DmPhuongXa dmPhuongXa);

	public void deleteById(Long id);

	public Optional<DmPhuongXa> findById(Long id);

	public Page<DmPhuongXa> findAll(String search, Integer trangThai, String quanHuyenCode, String tinhThanhCode,
			List<String> quanHuyenCodes, List<String> tinhThanhCodes, Pageable pageable);

	public List<DmPhuongXa> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmPhuongXa> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmPhuongXa> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmPhuongXa> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa);

	public List<DmPhuongXa> findByQuanHuyenCodeIgnoreCaseAndDaXoa(String quanHuyenCode, Boolean daXoa);
}
