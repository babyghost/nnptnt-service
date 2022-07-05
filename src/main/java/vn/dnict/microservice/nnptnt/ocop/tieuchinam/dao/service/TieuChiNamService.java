package vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;

public interface TieuChiNamService {
	public  TieuChiNam save( TieuChiNam entity);

	public void deleteById(Long id);

	public Optional< TieuChiNam> findById(Long id);
	
	Optional<TieuChiNam> findByNamAndPhanNhomAndNhomAndNganhHang( Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId );

	
	public Page< TieuChiNam> findAll( Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId,Integer trangThai, Pageable pageable);
	
	public Page<TieuChiNam> findChiTiet( Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId, Pageable pageable);
}
