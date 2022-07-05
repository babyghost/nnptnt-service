package vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;
@Repository
public interface DanhGiaSanPhamRepo extends JpaRepository<DanhGiaSanPham, Long>,JpaSpecificationExecutor<DanhGiaSanPham>{
	@Query(value="SELECT  u.dmphanhang_id FROM ocop_danhgiasanpham u WHERE u.sanpham_id = ?1 AND u.daxoa=FALSE ORDER BY u.ngaytao DESC LIMIT 1" , nativeQuery=true)
	public Long getPhanHangBySanPhamId(Long sanPhamId);
}
