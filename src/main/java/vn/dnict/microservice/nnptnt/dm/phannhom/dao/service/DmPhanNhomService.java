package vn.dnict.microservice.nnptnt.dm.phannhom.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;

public interface DmPhanNhomService {
	public DmPhanNhom save(DmPhanNhom entity);

	public void deleteById(Long id);

	public Optional<DmPhanNhom> findById(Long id);
	

	public Page<DmPhanNhom> findAll(String ten,Long dmNhomId , Boolean trangThai, Pageable pageable);
}
