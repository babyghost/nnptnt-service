package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;

@Repository
public interface DmNguonKinhPhiRepo
		extends JpaRepository<DmNguonKinhPhi, Long>, JpaSpecificationExecutor<DmNguonKinhPhi> {
	public List<DmNguonKinhPhi> findByIdIn(List<Long> idList);

	@Query("SELECT q FROM DmNguonKinhPhi q WHERE q.daXoa=?1")
	public List<DmNguonKinhPhi> findByDaXoa(Boolean daXoa);

	public List<DmNguonKinhPhi> findByTrangThaiAndDaXoa(boolean trangThai, boolean daXoa);

	@Query(value = "SELECT * FROM danhmuc_nguonkinhphi WHERE id IN (\r\n" + "\r\n" + "SELECT\r\n"
			+ "	kp.dmnguonkinhphi_id \r\n" + "FROM\r\n" + "	modulechung_kh_kehoach2kinhphi kp\r\n"
			+ "	JOIN modulechung_kh_kehoach2nhiemvu nv ON nv.ID = kp.kehoach2nhiemvu_id\r\n"
			+ "	JOIN modulechung_kh_kehoach kh ON kh.ID = nv.kehoach_id\r\n"
			+ "	JOIN modulechung_kh_yeucau2donvi yc ON yc.id = kh.yeucau2donvi_id\r\n"
			+ "	WHERE kh.daxoa = false AND nv.daxoa = false AND kp.daxoa = false AND kp.dmnguonkinhphi_id IS NOT NULL AND kh.is_hientai = true AND yc.id = ?1 GROUP BY kp.dmnguonkinhphi_id )", nativeQuery = true)
	public List<DmNguonKinhPhi> getDmNguonKinhPhis(Long yeuCau2DonViId);
	
	@Query(value = "SELECT * FROM danhmuc_nguonkinhphi WHERE id IN (SELECT\r\n" + 
			"			kp.dmnguonkinhphi_id FROM modulechung_kh_nvkhac2kinhphikhac kp\r\n" + 
			"			JOIN modulechung_kh_khkhac2nhiemvukhac nv ON nv.ID = kp.khkhac2nhiemvu_id\r\n" + 
			"			JOIN modulechung_kh_kehoachkhac kh ON kh.ID = nv.kehoachkhac_id\r\n" + 
			"			WHERE kh.daxoa = false AND nv.daxoa = false AND kp.daxoa = false AND kp.dmnguonkinhphi_id IS NOT NULL AND kh.id = ?1 GROUP BY kp.dmnguonkinhphi_id )", nativeQuery = true)
	public List<DmNguonKinhPhi> getDmNguonKinhPhisByKeHoachKhacId(Long keHoachKhacId);
	
	@Query(value = "SELECT\r\n" + 
			"	sum(kp.sotien) AS tongtien\r\n" + 
			"FROM\r\n" + 
			"	modulechung_kh_kehoach2kinhphi kp\r\n" + 
			"	JOIN modulechung_kh_kehoach2nhiemvu nv ON nv.ID = kp.kehoach2nhiemvu_id\r\n" + 
			"	JOIN modulechung_kh_kehoach kh ON kh.ID = nv.kehoach_id\r\n" + 
			"	JOIN modulechung_kh_yeucau2donvi yc ON yc.id = kh.yeucau2donvi_id\r\n" + 
			"	WHERE kh.daxoa = false AND nv.daxoa = false AND kp.daxoa = false AND kp.dmnguonkinhphi_id IS NOT NULL AND kh.is_hientai = true AND yc.id = ?1 AND kp.dmnguonkinhphi_id = ?2 GROUP BY kp.dmnguonkinhphi_id", nativeQuery = true)
	public Double getTongTienDmNguonKinhPhis(Long yeuCau2DonViId, Long dmNguonKinhPhiId);

}
