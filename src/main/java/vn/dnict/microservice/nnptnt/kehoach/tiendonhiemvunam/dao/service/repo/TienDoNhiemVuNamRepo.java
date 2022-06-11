package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

@Repository
public interface TienDoNhiemVuNamRepo extends JpaRepository<TienDoNhiemVuNam, Long>,JpaSpecificationExecutor<TienDoNhiemVuNam> {
	
	public List<TienDoNhiemVuNam> findByNhiemVuNamIdAndDaXoa(Long nhiemVuNamId, Boolean daXoa);
	
	public Optional<TienDoNhiemVuNam> findByNhiemVuNamId(Long nhiemVuNamId);
	
	@Modifying(clearAutomatically = true)
	@Query("update TienDoNhiemVuNam u set u.daXoa = ?1 where u.nhiemVuNamId = ?2")
	public int setFixedDaXoaForNhiemVuNamId(Boolean daXoa, Long nhiemVuNamId);
}
