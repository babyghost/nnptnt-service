package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

 
@Repository

	public interface HoatDongChanNuoiRepo extends JpaRepository<HoatDongChanNuoi, Long>,JpaSpecificationExecutor<HoatDongChanNuoi>{
	
	public Optional<HoatDongChanNuoi> findByLoaiVatNuoiId(Long loaiVatNuoiId);

	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndDaXoa(Long coSoChanNuoiId, Boolean daXoa);

	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(Long coSoChanNuoiId, String nam, Integer quy, Boolean daXoa);
	

    @Query("SELECT a.nam, a.quy, a.loaiVatNuoiId, SUM(a.soLuongNuoi) as tongSoLuongNuoi, SUM(a.slVatNuoiXuat) as tongSLVatNuoi, SUM(a.sanLuongXuat) as tongSanLuongXuat FROM HoatDongChanNuoi AS a WHERE daXoa = false AND a.nam = ?1 AND  a.loaiVatNuoiId in ?2 AND a.quy in  ?3 GROUP BY (a.loaiVatNuoiId, a.nam, a.quy)")
    public List<Object[]> thongKeSoVatNuoi( String nam , List< Long> loaiVatNuoiIds ,List< Integer> quy);

	

    @Query("SELECT SUM(u.soLuongNuoi) FROM HoatDongChanNuoi u WHERE u.daXoa = false AND u.nam = ?1 AND u.quy in ?2 AND u.loaiVatNuoiId in ?3")
    public Long tongSoLuongVatNuoi( String nam ,List< Integer> quy, List< Long> loaiVatNuoiIds);


    @Query("SELECT a.nam, a.quy, a.loaiVatNuoiId, SUM(a.soLuongNuoi) as tongSoLuongNuoi, SUM(a.slVatNuoiXuat) as tongSLVatNuoi, SUM(a.sanLuongXuat) as tongSanLuongXuat FROM HoatDongChanNuoi AS a WHERE daXoa = false AND a.nam = ?1 AND  a.loaiVatNuoiId in ?2 AND a.quy in  ?3 GROUP BY (a.loaiVatNuoiId, a.nam, a.quy)")
    public Page<Object> thongKeSoVatNuoiDemo( String nam , List< Long> loaiVatNuoiIds ,List< Integer> quy, Pageable pageable);
    
}
