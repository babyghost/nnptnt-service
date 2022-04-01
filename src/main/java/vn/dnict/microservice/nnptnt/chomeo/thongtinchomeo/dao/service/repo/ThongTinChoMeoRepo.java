package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

@Repository
public interface ThongTinChoMeoRepo
		extends JpaRepository<ThongTinChoMeo, Long>, JpaSpecificationExecutor<ThongTinChoMeo> {

	public List<ThongTinChoMeo> findByChuQuanLyIdAndDaXoa(Long chuQuanLyId, Boolean daXoa);
	
	public Optional<ThongTinChoMeo> findByChuQuanLyId(Long chuQuanLyId);

	@Modifying(clearAutomatically = true)
	@Query("update ThongTinChoMeo u set u.daXoa = ?1 where u.chuQuanLyId = ?2")
	public int setFixedDaXoaForChuQuanLyId(Boolean daXoa, Long chuQuanLyId);
}

