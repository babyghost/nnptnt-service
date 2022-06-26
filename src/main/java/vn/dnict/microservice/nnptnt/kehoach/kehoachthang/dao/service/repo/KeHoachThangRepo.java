package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

@Repository
public interface KeHoachThangRepo extends JpaRepository<KeHoachThang, Long>, JpaSpecificationExecutor<KeHoachThang> {
	@Query("SELECT u FROM KeHoachThang u WHERE u.donViChuTriId = ?1 AND TO_CHAR(u.thang, 'MM/YYYY') = TO_CHAR(?2, 'MM/YYYY') AND u.daXoa = ?3")
	public List<KeHoachThang> findByDonViChuTriIdAndThangAndDaXoa(Long donViChuTriId, LocalDate thang, Boolean daXoa);
}
