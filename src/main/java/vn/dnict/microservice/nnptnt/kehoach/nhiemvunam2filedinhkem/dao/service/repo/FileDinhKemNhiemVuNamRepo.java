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
	@Query("SELECT u FROM FileDinhKemNhiemVuNam u WHERE u.tienDoNvNamId = ?1 AND u.daXoa = ?2")
	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndDaXoa(Long tienDoNvNamId, Boolean daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update FileDinhKemNhiemVuNam u set u.daXoa = ?1 where u.tienDoNvNamId = ?2")
	public int setFixedDaXoaForTienDoNvNamId(Boolean daXoa, Long tienDoNvNamId);

	@Query("SELECT u FROM FileDinhKemNhiemVuNam u WHERE u.tienDoNvNamId = ?1 AND u.fileDinhKemId = ?2")
	public List<FileDinhKemNhiemVuNam> findByTienDoNvNamIdAndFileDinhKemId(Long tienDoNvNamId, Long fileDinhKemId);
	
	public Optional<FileDinhKemNhiemVuNam> findByTienDoNvNamId(Long tienDoNvNamId);
}
