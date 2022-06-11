package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;

@Repository
public interface FileDinhKemNhiemVuNamRepo extends JpaRepository<FileDinhKemNhiemVuNam, Long>, JpaSpecificationExecutor<FileDinhKemNhiemVuNam> {
	@Query("SELECT u FROM FileDinhKemNhiemVuNam u WHERE u.fileDinhKemId = ?1 AND u.daXoa = ?2")
	public List<FileDinhKemNhiemVuNam> findByFileDinhKemIdAndDaXoa(Long fileDinhKemId, Boolean daXoa);
	
	@Modifying
	@Query("update FileDinhKemNhiemVuNam u set u.daXoa = ?1 where u.tienDoNhiemVuNamId = ?2")
	public int setFixedDaXoaForTienDoNhiemVuNamId(Boolean daXoa, Long tienDoNhiemVuNamId);
	
	public List<FileDinhKemNhiemVuNam> findByFileDinhKemIdAndTienDoNhiemVuNamId(Long fileDinhKemId, Long tienDoNhiemVuNamId);
	
	public Optional<FileDinhKemNhiemVuNam> findByTienDoNhiemVuNamId(Long tienDoNhiemVuNamId);
}
