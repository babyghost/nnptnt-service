package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;

public interface ThongTinGietMoService {
	public ThongTinGietMo save (ThongTinGietMo entity);
	
	public void deleteById(Long id);
	
	public Optional<ThongTinGietMo> findById(Long id);
	
	public Page<ThongTinGietMo> findAll(List<String> tenCoSos, String tenChuCoSo, String dienThoai, LocalDate gietMoTuNgay, LocalDate gietMoDenNgay,
			Long quanHuyenId, Long phuongXaId, Pageable pageable);
	
	public List<ThongTinGietMo> findByCoSoGietMoIdAndDaXoa(Long coSoGietMoId, Boolean daXoa);
	
	public List<ThongTinGietMo> findByCoSoGietMoIdAndNgayThangAndChuHangAndLoaiGiayToIdAndSoGiayToAndDaXoa(Long coSoGietMoId,
			LocalDate ngayThang, String chuHang, Long loaiGiayToId, String soGiayTo, Boolean daXoa);
	
	public int setFixedDaXoaForCoSoGietMoId(Boolean daXoa, Long coSoGietMoId);
}
