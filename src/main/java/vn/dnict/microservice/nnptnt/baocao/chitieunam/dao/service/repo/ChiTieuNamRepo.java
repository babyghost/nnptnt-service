package vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;
@Repository
public interface ChiTieuNamRepo extends JpaRepository<ChiTieuNam, Long>,JpaSpecificationExecutor<ChiTieuNam>{
    @Query("SELECT u FROM ChiTieuNam u WHERE u.daXoa = false AND u.linhVucId = ?1 AND u.nam =?2")
	Optional<ChiTieuNam> findByLinhVucIdAndNamAndDaXoa(Long linhVucId, Integer nam );
	
}
