package vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.model.SoLuongGietMo;

public interface SoLuongGietMoRepo extends JpaRepository<SoLuongGietMo, Long>, JpaSpecificationExecutor<SoLuongGietMo> {
	
	public List<SoLuongGietMo> findByThongTinGietMoIdAndDaXoa(Long thongTinGietMoId, Boolean daXoa);
	
	@Modifying
	@Query("update SoLuongGietMo u set u.daXoa = ?1 where u.thongTinGietMoId = ?2")
	public int setFixedDaXoaAndThongTinGietMoId(Boolean daXoa, Long thongTinGietMoId);
	
	public Optional<SoLuongGietMo> findByThongTinGietMoId(Long thongTinGietMoId);
}
