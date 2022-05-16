package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;

@Repository
public interface TinhHinhThucHienHopDongRepo extends JpaRepository<TinhHinhThucHienHopDong, Long>,
		JpaSpecificationExecutor<TinhHinhThucHienHopDong> {
	public List<TinhHinhThucHienHopDong> findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(Long hopDongId,
			boolean daXoa);

	public List<TinhHinhThucHienHopDong> findByHopDongIdAndThanhToanDotGreaterThanAndDaXoa(Long hopDongId,
			Integer thanhToanDot, boolean daXoa);
}
