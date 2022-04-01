package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;

public interface ThoiGianTiemPhongService {
	public ThoiGianTiemPhong save(ThoiGianTiemPhong entity);

	public void deleteById(Long id);

	public Optional<ThoiGianTiemPhong> findById(Long id);

	public Page<ThoiGianTiemPhong> findAll(Long phuongXaId,Long quanHuyenId,LocalDate thoiGianTiemDenNgay, LocalDate thoiGianTiemTuNgay,Long keHoachTiemPhongId,String diaDiem ,Pageable pageable);
}
