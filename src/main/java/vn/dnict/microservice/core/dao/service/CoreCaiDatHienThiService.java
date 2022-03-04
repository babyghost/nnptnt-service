package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreCaiDatHienThi;

public interface CoreCaiDatHienThiService {
	public Page<CoreCaiDatHienThi> findAll(String ma, Pageable pageable);

	public Optional<CoreCaiDatHienThi> findById(Long id);

	public CoreCaiDatHienThi save(CoreCaiDatHienThi CoreCaiDatHienThi);

	public void delete(Long id);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSachAndDaXoa(String appCode, String nguoiSuDung,
			String maDanhSach, Integer daXoa);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSach(String appCode, String nguoiSuDung,
			String maDanhSach);
}