package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;

@Repository
public interface CoreCauHinhDanhSachRepo
		extends JpaRepository<CoreCauHinhDanhSach, Long>, JpaSpecificationExecutor<CoreCauHinhDanhSach> {
	public List<CoreCauHinhDanhSach> findByIdIn(List<Long> idList);

	public List<CoreCauHinhDanhSach> findByMaDanhSachAndAppCodeAndTrangThaiAndDaXoaOrderBySapXepAsc(String maDanhSach, String appCode,
			Boolean trangThai, Boolean daXoa);
}