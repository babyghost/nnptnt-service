package vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.model.DmLoaiHopDong;
import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.DmLoaiHopDongService;
import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.DmLoaiHopDongSpecifications;
import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.repo.DmLoaiHopDongRepo;

@Service
public class DmLoaiHopDongServiceImpl implements DmLoaiHopDongService {
	@Autowired
	private DmLoaiHopDongRepo repo;

	public DmLoaiHopDong save(DmLoaiHopDong LoaiHopDong) {
		return repo.save(LoaiHopDong);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmLoaiHopDong> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmLoaiHopDong> findAll(String ten, String ma, Boolean trangThai, Pageable pageable) {
		return repo.findAll(DmLoaiHopDongSpecifications.quickSearch(ten, ma, trangThai), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}
}
