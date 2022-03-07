package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Repository

	public interface HoatDongChanNuoiRepo extends JpaRepository<HoatDongChanNuoi, Long>,JpaSpecificationExecutor<HoatDongChanNuoi>{
	public Optional<HoatDongChanNuoi> findByLoaiVatNuoiId(Long loaiVatNuoiId);
//	public List<HoatDongChanNuoi> findHoatDongChanNuoiByLoaiIdAndDaXoa(Long loaiVatNuoiId, Integer daXoa);


}
