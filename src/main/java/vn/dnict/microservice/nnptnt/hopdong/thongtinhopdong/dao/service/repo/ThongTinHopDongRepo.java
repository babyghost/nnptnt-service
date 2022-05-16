package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;


@Repository
public interface ThongTinHopDongRepo
		extends JpaRepository<ThongTinHopDong, Long>, JpaSpecificationExecutor<ThongTinHopDong> {
	public List<ThongTinHopDong> findByDaXoa(Boolean daXoa);

	public Long countByTrangThaiAndThoiGianThDenNgayBetween(Integer trangThai, LocalDate before5day, LocalDate nowDay);
}
