package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;

@Repository
public interface ThongTinGietMoRepo extends JpaRepository<ThongTinGietMo, Long>, JpaSpecificationExecutor<ThongTinGietMo> {
	public List<ThongTinGietMo> findByCoSoGietMoIdAndDaXoa(Long coSoGietMoId, Boolean daXoa);
	
	@Query("SELECT DISTINCT a FROM ThongTinGietMo a WHERE a.ngayThang = ?1 AND a.daXoa= ?2")
	public List<ThongTinGietMo> findByNgayThangAndDaXoa(LocalDate ngayThang, Boolean daXoa);
	
	@Modifying(clearAutomatically = true)
	@Query("update ThongTinGietMo u set u.daXoa = ?1 where u.coSoGietMoId = ?2")
	public int setFixedDaXoaForCoSoGietMoId(Boolean daXoa, Long coSoGietMoId);
}
