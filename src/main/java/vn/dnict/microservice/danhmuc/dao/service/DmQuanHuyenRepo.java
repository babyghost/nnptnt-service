package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;

@Repository
public interface DmQuanHuyenRepo extends JpaRepository<DmQuanHuyen, Long>, JpaSpecificationExecutor<DmQuanHuyen> {
	public List<DmQuanHuyen> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmQuanHuyen> findByMa(String ma);

	public List<DmQuanHuyen> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmQuanHuyen> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmQuanHuyen> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa);

	public List<DmQuanHuyen> findByIdIn(List<Long> ids);
	public Optional<DmQuanHuyen> findQuanHuyenByMa(String ma);
}
