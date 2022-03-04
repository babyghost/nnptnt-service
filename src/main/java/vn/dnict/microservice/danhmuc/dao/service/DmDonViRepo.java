package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
@Repository
public interface DmDonViRepo extends JpaRepository<DmDonVi, Long>,JpaSpecificationExecutor<DmDonVi> {
	public List<DmDonVi> findByIdIn(List<Long> idList);
	
	@Query("SELECT q FROM DmDonVi q WHERE q.daXoa=?1")
	public List<DmDonVi> findByDaXoa(Boolean daXoa);
	
	@Query("SELECT q FROM DmDonVi q WHERE q.donViChaId = ?1 and q.daXoa=?2")
	public List<DmDonVi> findByDonViChaIdAndDaXoa(Long donViChaId, Boolean daXoa);
}
