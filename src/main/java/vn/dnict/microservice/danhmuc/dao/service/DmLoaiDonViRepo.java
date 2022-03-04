package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;
@Repository
public interface DmLoaiDonViRepo extends JpaRepository<DmLoaiDonVi, Long>,JpaSpecificationExecutor<DmLoaiDonVi> {
	public List<DmLoaiDonVi> findByIdIn(List<Long> idList);
	
	@Query("SELECT q FROM DmLoaiDonVi q WHERE q.daXoa=?1")
	public List<DmLoaiDonVi> findByDaXoa(Integer daXoa);
	
}
