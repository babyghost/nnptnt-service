package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;


public interface TinhHinhThucHienHopDongService {
	public TinhHinhThucHienHopDong save(TinhHinhThucHienHopDong TinhHinhThucHienHopDong);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<TinhHinhThucHienHopDong> findById(Long id);

	public Page<TinhHinhThucHienHopDong> findByDaXoaAndSearch(String search, Pageable pageable);

	public Page<TinhHinhThucHienHopDong> findAll(String search, Pageable pageable);

	public List<TinhHinhThucHienHopDong> findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(Long hopDongId,
			boolean daXoa);

	public List<TinhHinhThucHienHopDong> findByHopDongIdAndThanhToanDotGreaterThanAndDaXoa(Long hopDongId,
			Integer thanhToanDot, boolean daXoa);
}
