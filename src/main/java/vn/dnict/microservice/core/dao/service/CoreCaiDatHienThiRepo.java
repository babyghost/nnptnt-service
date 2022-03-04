package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreCaiDatHienThi;

@Repository
public interface CoreCaiDatHienThiRepo
		extends JpaRepository<CoreCaiDatHienThi, Long>, JpaSpecificationExecutor<CoreCaiDatHienThi> {
	public List<CoreCaiDatHienThi> findByIdIn(List<Long> idList);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSachAndDaXoa(String appCode, String nguoiSuDung,
			String maDanhSach, Integer daXoa);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSach(String appCode, String nguoiSuDung,
			String maDanhSach);
}