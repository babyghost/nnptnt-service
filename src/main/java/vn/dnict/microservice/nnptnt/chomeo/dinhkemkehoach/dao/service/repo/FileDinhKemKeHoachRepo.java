package vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.model.FileDinhKemKeHoach;
@Transactional
@Repository
public interface FileDinhKemKeHoachRepo
		extends JpaRepository<FileDinhKemKeHoach, Long>, JpaSpecificationExecutor<FileDinhKemKeHoach> {
//	@Query("SELECT u FROM FileDinhKemKeHoach u WHERE u.dinhKemFileId = ?1 AND u.daXoa = ?2")
//	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndDaXoa(Long dinhKemFileId, Boolean daXoa);

	@Modifying
	@Query("update FileDinhKemKeHoach u set u.daXoa = ?1 where u.keHoachTiemPhongId = ?2")
	public int setFixedDaXoaForKeHoachTiemPhongId(Boolean daXoa, Long keHoachTiemPhongId);
	
	@Query("SELECT u FROM FileDinhKemKeHoach u WHERE u.fileDinhKemId = ?1 AND u.keHoachTiemPhongId = ?2")
	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndKeHoachTiemPhongId(Long dinhKemFileId, Long keHoachTiemPhongId);
	@Query("SELECT u FROM FileDinhKemKeHoach u WHERE u.keHoachTiemPhongId = ?1 AND u.daXoa = false")
	public Optional<FileDinhKemKeHoach> findBykeHoachTiemPhongId(Long keHoachTiemPhongId);
	@Query("SELECT u FROM FileDinhKemKeHoach u WHERE u.keHoachTiemPhongId = ?1 AND u.daXoa = ?2")
	public List<FileDinhKemKeHoach> findByKeHoachIdAndDaXoa(Long keHoachTiemPhongId, Boolean daXoa);
}
