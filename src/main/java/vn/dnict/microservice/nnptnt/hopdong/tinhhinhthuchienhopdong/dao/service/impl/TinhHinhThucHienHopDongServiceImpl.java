package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.TinhHinhThucHienHopDongService;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.TinhHinhThucHienHopDongSpecifications;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.repo.TinhHinhThucHienHopDongRepo;

@Service
public class TinhHinhThucHienHopDongServiceImpl implements TinhHinhThucHienHopDongService {
	@Autowired
	private TinhHinhThucHienHopDongRepo repo;

	public TinhHinhThucHienHopDong save(TinhHinhThucHienHopDong TinhHinhThucHienHopDong) {
		return repo.save(TinhHinhThucHienHopDong);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<TinhHinhThucHienHopDong> findById(Long id) {
		return repo.findById(id);
	}

	public Page<TinhHinhThucHienHopDong> findByDaXoaAndSearch(String search, Pageable pageable) {
		return repo.findAll(TinhHinhThucHienHopDongSpecifications.findByDaXoaAndSearch(search), pageable);
	}

	public Page<TinhHinhThucHienHopDong> findAll(String search, Pageable pageable) {
		return repo.findAll(pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<TinhHinhThucHienHopDong> findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(Long hopDongId,
			boolean daXoa) {
		return repo.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(hopDongId, daXoa);
	}

	public List<TinhHinhThucHienHopDong> findByHopDongIdAndThanhToanDotGreaterThanAndDaXoa(Long hopDongId,
			Integer thanhToanDot, boolean daXoa) {
		return repo.findByHopDongIdAndThanhToanDotGreaterThanAndDaXoa(hopDongId, thanhToanDot, daXoa);
	}
}
