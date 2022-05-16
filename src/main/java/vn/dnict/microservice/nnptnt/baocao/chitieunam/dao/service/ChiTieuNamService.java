package vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;

public interface ChiTieuNamService {
	public  ChiTieuNam save( ChiTieuNam entity);

	public void deleteById(Long id);

	public Optional< ChiTieuNam> findById(Long id);
	
	Optional<ChiTieuNam> findByLinhVucIdAndNamAndDaXoa(Long linhVucId, Integer nam );

	
	public Page< ChiTieuNam> findAll(Long linhVucId, Integer nam, Pageable pageable);
}
