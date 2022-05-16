package vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service.DmPhuongThucBaoLanhHdService;
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service.DmPhuongThucBaoLanhHdSpecifications;
import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service.repo.DmPhuongThucBaoLanhHdRepo;

@Service
public class DmPhuongThucBaoLanhHdServiceImpl implements DmPhuongThucBaoLanhHdService {
	@Autowired
	private DmPhuongThucBaoLanhHdRepo repo;

	public DmPhuongThucBaoLanhHd save(DmPhuongThucBaoLanhHd PhuongThucBaoLanhHd) {
		return repo.save(PhuongThucBaoLanhHd);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmPhuongThucBaoLanhHd> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(String search, Integer type, Pageable pageable) {
		return repo.findAll(DmPhuongThucBaoLanhHdSpecifications.findByDaXoaAndSearch(search, type), pageable);
	}

	public Page<DmPhuongThucBaoLanhHd> findAll(String search, Integer type, Boolean trangThai,
			Pageable pageable) {
		return repo.findAll(DmPhuongThucBaoLanhHdSpecifications.quickSearch(search, type, trangThai), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}
}
