package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;

@Repository
public interface DmPhuongXaRepo extends JpaRepository<DmPhuongXa, Long>, JpaSpecificationExecutor<DmPhuongXa> {

	public List<DmPhuongXa> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmPhuongXa> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmPhuongXa> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmPhuongXa> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa);

	public List<DmPhuongXa> findByQuanHuyenCodeIgnoreCaseAndDaXoa(String quanHuyenCode, Boolean daXoa);
}
