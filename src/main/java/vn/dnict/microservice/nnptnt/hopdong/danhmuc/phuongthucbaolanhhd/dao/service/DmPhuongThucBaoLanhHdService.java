package vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;

public interface DmPhuongThucBaoLanhHdService {
	public DmPhuongThucBaoLanhHd save(DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<DmPhuongThucBaoLanhHd> findById(Long id);

	public Page<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(String search, Integer type, Pageable pageable);

	public Page<DmPhuongThucBaoLanhHd> findAll(String search, Integer type, Boolean trangThai,
			Pageable pageable);
}
