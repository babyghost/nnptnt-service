package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;

@Repository
public interface DmTinhThanhRepo extends JpaRepository<DmTinhThanh, Long>, JpaSpecificationExecutor<DmTinhThanh> {
	public List<DmTinhThanh> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmTinhThanh> findByMa(String ma);

	public List<DmTinhThanh> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmTinhThanh> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmTinhThanh> findByIdIn(List<Long> ids);
	public Optional<DmTinhThanh> findTinhThanhByMa(String ma);
}
