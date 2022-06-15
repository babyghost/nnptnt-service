package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

public interface TienDoNhiemVuNamService {
	public TienDoNhiemVuNam save(TienDoNhiemVuNam entity);
	
	public void deleteById(Long id);
	
	public Optional<TienDoNhiemVuNam> findById(Long id);
	
	public List<TienDoNhiemVuNam> findByNhiemVuNamIdAndDaXoa(Long nhiemVuNamId, Boolean daXoa);
	
	public Optional<TienDoNhiemVuNam> findByNhiemVuNamId(Long nhiemVuNamId);
	
	public int setFixedDaXoaForNhiemVuNamId(Boolean daXoa, Long nhiemVuNamId);
	
	public Page<TienDoNhiemVuNam> findAll(LocalDate ngayBaoCao, Integer tinhTrang, Integer mucDoHoanThanh, Pageable pageable);
}
