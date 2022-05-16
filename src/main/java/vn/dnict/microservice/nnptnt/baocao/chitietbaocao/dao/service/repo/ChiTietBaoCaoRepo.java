package vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.model.ChiTietBaoCao;

@Repository
public interface ChiTietBaoCaoRepo extends JpaRepository<ChiTietBaoCao, Long>, JpaSpecificationExecutor<ChiTietBaoCao> {

	@Modifying(clearAutomatically = true)
	@Query("update ChiTietBaoCao u set u.daXoa = ?1 where u.yeuCauBaoCaoId = ?2")
	public int setFixedDaXoaForYeuCauBaoCaoId(boolean daXoa, Long yeuCauBaoCaoId);

	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(Long yeuCauBaoCaoId, Long donViId, boolean daXoa);

	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDaXoa(Long yeuCauBaoCaoId, boolean daXoa);
}
