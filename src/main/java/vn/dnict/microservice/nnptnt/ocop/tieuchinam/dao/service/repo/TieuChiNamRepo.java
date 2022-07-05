package vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;
@Repository
public interface TieuChiNamRepo extends JpaRepository<TieuChiNam, Long>,JpaSpecificationExecutor<TieuChiNam>{
    @Query("SELECT u FROM TieuChiNam u WHERE u.daXoa = false AND u.nam = ?1 AND u.phanNhomId =?2 AND u.nganhHangId=?3 AND u.nhomId=?4")
	Optional<TieuChiNam> findByNamAndPhanNhomAndNhomAndNganhHang(Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId );
}
