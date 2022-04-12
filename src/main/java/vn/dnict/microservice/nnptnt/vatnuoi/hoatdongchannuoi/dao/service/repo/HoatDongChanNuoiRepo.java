package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;
 
@Repository

	public interface HoatDongChanNuoiRepo extends JpaRepository<HoatDongChanNuoi, Long>,JpaSpecificationExecutor
	<HoatDongChanNuoi>{
	public Optional<HoatDongChanNuoi> findByLoaiVatNuoiId(Long loaiVatNuoiId);

	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndDaXoa(Long coSoChanNuoiId, Boolean daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update HoatDongChanNuoi u set u.daXoa = ?1 where u.coSoChanNuoiId = ?2")
	public int setFixedDaXoaForCoSoChanNuoiId(Boolean daXoa, Long coSoChanNuoiId);
}
