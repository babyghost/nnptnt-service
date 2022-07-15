package vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;

public interface DmPhuongThucBaoLanhHdService {
	public DmPhuongThucBaoLanhHd save(DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<DmPhuongThucBaoLanhHd> findById(Long id);

	public Page<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(String ten, String ma, Integer type, Pageable pageable);

	public Page<DmPhuongThucBaoLanhHd> findAll(String ten, String ma, Boolean trangThai, Integer type, Pageable pageable);
}
