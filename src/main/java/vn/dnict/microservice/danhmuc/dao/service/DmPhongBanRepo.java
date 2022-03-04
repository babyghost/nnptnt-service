package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;

@Repository
public interface DmPhongBanRepo extends JpaRepository<DmPhongBan, Long>, JpaSpecificationExecutor<DmPhongBan> {
	public List<DmPhongBan> findByIdIn(List<Long> idList);

	@Query("SELECT q FROM DmPhongBan q WHERE q.daXoa=?1")
	public List<DmPhongBan> findByDaXoa(Integer daXoa);

	public List<DmPhongBan> findByDonViIdAndDaXoa(Long donViId, Integer daXoa);
}
