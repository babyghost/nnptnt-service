package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;

public interface DmCapDonViService {
	
	public DmCapDonVi save(DmCapDonVi capDonVi);

	public Optional<DmCapDonVi> findById(Long id);

	public void delete(Long id);

	public Page<DmCapDonVi> findAll(String search, Pageable pageable);

	public Page<DmCapDonVi> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable);

	public List<DmCapDonVi> findByIdIn(List<Long> idList);
}
