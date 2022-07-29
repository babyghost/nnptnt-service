package vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;

@Repository
public interface KeHoachRepo extends JpaRepository<KeHoach, Long>, JpaSpecificationExecutor<KeHoach> {
	
	@Query(value = "SELECT * FROM bc_kehoach u WHERE u.nam=?1 AND u.dmlinhvuc_id=?2  AND u.chitieu_id =?3 AND u.daxoa = FALSE", nativeQuery = true)
	public Optional<KeHoach> findByNamAndLinhVucIdAndChiTieuId(Integer nam, Long linhVucId, Long chiTieuId);

	@Query(value = "SELECT * FROM bc_kehoach u WHERE u.nam=?1 AND u.dmlinhvuc_id=?2 AND u.daxoa = FALSE", nativeQuery = true)
	public List<KeHoach> findListByNamAndLinhVucId(Integer nam, Long linhVucId);
	
	@Query(value = "SELECT * FROM bc_kehoach u WHERE u.nam=?1 AND u.dmlinhvuc_id=?2 AND u.daxoa = FALSE", nativeQuery = true)
	public Optional<KeHoach> findByNamAndLinhVucId(Integer nam, Long linhVucId);
}
