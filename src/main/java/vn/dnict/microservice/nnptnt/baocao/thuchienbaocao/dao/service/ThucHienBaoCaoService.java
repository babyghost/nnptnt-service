package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;

public interface ThucHienBaoCaoService {
	public ThucHienBaoCao save(ThucHienBaoCao entity);

	public void deleteById(Long id);

	public Optional<ThucHienBaoCao> findById(Long id);

	Optional<ThucHienBaoCao> findByLinhVucIdAndThangNamAndDaXoa(LocalDate thangNam, Long linhVucId, Integer nam);

	public List<ThucHienBaoCao> findListByLinhVucIdAndThangNamAndDaXoa(LocalDate thangNam, Long linhVucId, Integer nam);
	
	public Float TongThucHienNamTruoc(LocalDate thangBatDau, LocalDate thangKetThuc, String chiTieuTen, Long LinhVucId,
			Integer namCu);

	public Float TongThucHienTrongNam(LocalDate thangBatDauTN, LocalDate thangKetThucTN, Long chiTieuId);

	public Long CountSoThangThucHien(LocalDate thangBatDau, LocalDate thangKetThuc, Long chiTieuId);

	public Page<ThucHienBaoCao> findAll(Long linhVucId, LocalDate thangNam, LocalDate ngayThucHien, Pageable pageable);

	public Page<ThucHienBaoCao> thongKe(Long linhVucId, LocalDate thangNam, LocalDate ngayThucHien,
			LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN,
			Pageable pageable);

	public Long CountSoThangThucHienNamCu(LocalDate thangBatDau, LocalDate thangKetThuc, String chiTieuTen,
			Long LinhVucId, Integer namCu);

}
