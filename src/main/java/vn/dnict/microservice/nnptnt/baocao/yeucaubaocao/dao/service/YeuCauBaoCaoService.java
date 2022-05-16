package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model.YeuCauBaoCao;

public interface YeuCauBaoCaoService {
	public  YeuCauBaoCao save( YeuCauBaoCao entity);

	public void deleteById(Long id);

	public Optional< YeuCauBaoCao> findById(Long id);
	


	
	public Page< YeuCauBaoCao> findAll(String tieuDe, LocalDate ngayYeuCauTuNgay, LocalDate ngayYeuCauDenNgay,LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Long linhVucId, Integer trangThai, Pageable pageable);
}
