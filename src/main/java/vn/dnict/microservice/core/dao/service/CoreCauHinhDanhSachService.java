package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;

public interface CoreCauHinhDanhSachService {
	public Page<CoreCauHinhDanhSach> findAll(String maDanhSach, String tenCot, Boolean trangThai, Pageable pageable);

	public Optional<CoreCauHinhDanhSach> findById(Long id);

	public CoreCauHinhDanhSach save(CoreCauHinhDanhSach coreCauHinhDanhSach);

	public void delete(Long id);

	public List<CoreCauHinhDanhSach> findByMaDanhSachAndAppCodeAndTrangThaiAndDaXoaOrderBySapXepAsc(String maDanhSach, String appCode,
			Boolean trangThai, Boolean daXoa);
}
