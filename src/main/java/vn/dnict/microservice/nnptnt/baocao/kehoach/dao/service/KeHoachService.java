package vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;

public interface KeHoachService {
	public  KeHoach save( KeHoach entity);

	public void deleteById(Long id);

	public Optional< KeHoach> findById(Long id);
	
	Optional<KeHoach> findByNamAndLinhVucIdAndChiTieuId(Integer nam, Long linhVucId,Long chiTieuId);
	public Page< KeHoach> findAll(Long linhVucId, Integer nam, Pageable pageable);
}
