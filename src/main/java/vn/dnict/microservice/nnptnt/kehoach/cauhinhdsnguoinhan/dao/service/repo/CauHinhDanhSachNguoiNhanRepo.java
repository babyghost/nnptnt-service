package vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.model.CauHinhDanhSachNguoiNhan;

@Repository
public interface CauHinhDanhSachNguoiNhanRepo
		extends
			JpaRepository<CauHinhDanhSachNguoiNhan, Long>,
			JpaSpecificationExecutor<CauHinhDanhSachNguoiNhan> {

	@Query(value = "SELECT nguoinhan_id FROM modulechung_kh_cauhinhdanhsachnguoinhan\r\n"
			+ "WHERE trangthaikehoach_id = ?1 AND phongban_id = ?2", nativeQuery = true)
	List<Long> getDanhSachCanBoIdsByTrangThaiKeHoachIdAndPhongBanId(Long trangThaiKeHoachId, Long phongBanId);
	
}
