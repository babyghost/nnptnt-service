
package vn.dnict.microservice.nnptnt.dm.giong.dao.service;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;


public interface DmGiongService {
	public DmGiong save(DmGiong entity);

	public void deleteById(Long id);

	public Optional<DmGiong> findById(Long id);



	public Page<DmGiong> findAll(String search,Long loaiDongVatId, Integer trangThai, Pageable pageable);

	public List<DmGiong> findByTen(String ten);


	

}