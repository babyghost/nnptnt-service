package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

@Repository
public interface ThoiGianTiemPhongRepo extends JpaRepository<ThoiGianTiemPhong, Long>,JpaSpecificationExecutor<ThoiGianTiemPhong>{

	public List<ThoiGianTiemPhong> findByKeHoachTiemPhongIdAndDaXoa(Long keHoachTiemPhongId, Boolean daXoa);
	
	

	@Modifying(clearAutomatically = true)
	@Query("update ThoiGianTiemPhong u set u.daXoa = ?1 where u.keHoachTiemPhongId = ?2")
	public int setFixedDaXoaForKeHoachTiemPhongId(Boolean daXoa, Long keHoachTiemPhongId);
}
