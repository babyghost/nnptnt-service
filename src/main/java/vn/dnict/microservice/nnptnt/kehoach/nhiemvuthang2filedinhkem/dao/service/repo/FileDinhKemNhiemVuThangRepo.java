package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;

@Repository
public interface FileDinhKemNhiemVuThangRepo extends JpaRepository<FileDinhKemNhiemVuThang, Long>,
		JpaSpecificationExecutor<FileDinhKemNhiemVuThang> {
	@Query("SELECT u FROM FileDinhKemNhiemVuThang u WHERE u.tienDoNhiemVuThangId = ?1 AND u.daXoa = ?2")
	public List<FileDinhKemNhiemVuThang> findListByTienDoNhiemVuThangIdAndDaXoa(Long tienDoNhiemVuThangId, Boolean daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update FileDinhKemNhiemVuThang u set u.daXoa = ?1 where u.tienDoNhiemVuThangId = ?2")
	public int setFixedDaXoaForTienDoNhiemVuThangId(Boolean daXoa, Long tienDoNhiemVuThangId);

	@Query("SELECT u FROM FileDinhKemNhiemVuThang u WHERE u.tienDoNhiemVuThangId = ?1 AND u.fileDinhKemId = ?2")
	public List<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndFileDinhKemId(Long tienDoNhiemVuThangId, Long fileDinhKemId);
	
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangId(Long tienDoNhiemVuThangId);
	
	public Optional<FileDinhKemNhiemVuThang> findByTienDoNhiemVuThangIdAndDaXoa(Long tienDoNhiemVuThangId, Boolean daXoa);
}
