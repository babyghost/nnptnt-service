package vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;
import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service.DmPhuongThucBaoLanhHdService;
import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service.DmPhuongThucBaoLanhHdSpecifications;
import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service.repo.DmPhuongThucBaoLanhHdRepo;

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

	public Page<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(String ten, String ma, Integer type, Pageable pageable) {
		return repo.findAll(DmPhuongThucBaoLanhHdSpecifications.findByDaXoaAndSearch(ten, ma, type), pageable);
	}

	public Page<DmPhuongThucBaoLanhHd> findAll(String ten, String ma, Boolean trangThai, Integer type, Pageable pageable) {
		return repo.findAll(DmPhuongThucBaoLanhHdSpecifications.quickSearch(ten, ma, trangThai, type), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}
}
