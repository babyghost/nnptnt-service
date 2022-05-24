package vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;

public interface DmNganhNgheOCOPService {
	public DmNganhNgheOCOP save(DmNganhNgheOCOP entity);

	public void deleteById(Long id);

	public Optional<DmNganhNgheOCOP> findById(Long id);
	

	public Page<DmNganhNgheOCOP> findAll(String ten, Integer trangThai, Pageable pageable);
}
