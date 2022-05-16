package vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;

public interface DmLinhVucService {
	public DmLinhVuc save(DmLinhVuc entity);

	public void deleteById(Long id);

	public Optional<DmLinhVuc> findById(Long id);
	

	public Page<DmLinhVuc> findAll(String search, Boolean trangThai, Pageable pageable);

}
