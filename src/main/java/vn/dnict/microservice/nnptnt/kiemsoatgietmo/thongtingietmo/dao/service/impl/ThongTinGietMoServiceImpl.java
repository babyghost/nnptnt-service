package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.ThongTinGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.ThongTinGietMoSpecifications;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.repo.ThongTinGietMoRepo;

@Service
@Transactional
public class ThongTinGietMoServiceImpl implements ThongTinGietMoService {

	@Autowired
	ThongTinGietMoRepo repo;
	
	@Override
	public ThongTinGietMo save(ThongTinGietMo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<ThongTinGietMo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ThongTinGietMo> findAll(List<String> tenCoSos, String tenChuCoSo, String dienThoai, LocalDate gietMoTuNgay,
			LocalDate gietMoDenNgay, Long quanHuyenId, Long phuongXaId, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThongTinGietMoSpecifications.quickSearch(tenCoSos, tenChuCoSo, dienThoai, gietMoTuNgay, gietMoDenNgay,
				quanHuyenId, phuongXaId), pageable);
	}

	@Override
	public List<ThongTinGietMo> findByCoSoGietMoIdAndDaXoa(Long coSoGietMoId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByCoSoGietMoIdAndDaXoa(coSoGietMoId, daXoa);
	}

	@Override
	public int setFixedDaXoaForCoSoGietMoId(Boolean daXoa, Long coSoGietMoId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForCoSoGietMoId(daXoa, coSoGietMoId);
	}

	@Override
	public Page<ThongTinGietMo> tongHopSoLuongNgay(List<String> tenCoSos, List<Long> loaiVatNuoiIds,
			LocalDate gietMoTuNgay, LocalDate gietMoDenNgay, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThongTinGietMoSpecifications.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds, gietMoTuNgay, gietMoDenNgay),
				pageable);
	}

	@Override
	public Page<ThongTinGietMo> tongHopSoLuongThang(List<String> tenCoSos, List<Long> loaiVatNuoiIds,
			LocalDate gietMoTuThang, LocalDate gietMoDenThang, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThongTinGietMoSpecifications.tongHopSoLuongThang(tenCoSos, loaiVatNuoiIds, gietMoTuThang,
				gietMoDenThang), pageable);
	}

	@Override
	public List<ThongTinGietMo> findByNgayThangAndDaXoa(LocalDate ngayThang, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByNgayThangAndDaXoa(ngayThang, daXoa);
	}

}
