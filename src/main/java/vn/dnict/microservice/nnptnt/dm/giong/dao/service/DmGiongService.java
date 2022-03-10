
package vn.dnict.microservice.nnptnt.dm.giong.dao.service;


import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;


public interface DmGiongService {
	public DmGiong save(DmGiong entity);

	public void deleteById(Long id);

	public Optional<DmGiong> findById(Long id);



	public Page<DmGiong> findAll(String search,Long loaiVatNuoiId, Integer trangThai, Pageable pageable);



	

}