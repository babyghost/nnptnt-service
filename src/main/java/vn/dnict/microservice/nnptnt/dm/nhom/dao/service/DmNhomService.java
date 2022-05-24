package vn.dnict.microservice.nnptnt.dm.nhom.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;

public interface DmNhomService {
	public DmNhom save(DmNhom entity);

	public void deleteById(Long id);

	public Optional<DmNhom> findById(Long id);
	

	public Page<DmNhom> findAll(String ten,Long dmNganhHangId , Boolean trangThai, Pageable pageable);
}
