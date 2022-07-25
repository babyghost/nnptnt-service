package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.ThucHienBaoCaoService;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.ThucHienBaoCaoSpecifications;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.repo.ThucHienBaoCaoRepo;
@Service
@Transactional
public class ThucHienBaoCaoServiceImpl implements ThucHienBaoCaoService{

	@Autowired
	ThucHienBaoCaoRepo repo;
	
	@Override
	public ThucHienBaoCao save(ThucHienBaoCao entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<ThucHienBaoCao> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Optional<ThucHienBaoCao> findByLinhVucIdAndThangNamAndDaXoa(LocalDate thangNam, Long linhVucId,
			Integer nam) {
		// TODO Auto-generated method stub
		return repo.findByLinhVucIdAndThangNamAndDaXoa(thangNam, linhVucId, nam);
	}

	@Override
	public Page<ThucHienBaoCao> findAll(Long linhVucId, LocalDate thangNam, LocalDate ngayThucHien, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThucHienBaoCaoSpecifications.quickSearch(linhVucId, thangNam, ngayThucHien),pageable);
	}



	@Override
	public Float TongThucHienTrongNam(LocalDate thangBatDauTN, LocalDate thangKetThucTN, Long chiTieuId) {
		// TODO Auto-generated method stub
		return repo.TongThucHienTrongNam(thangBatDauTN, thangKetThucTN, chiTieuId);
	}

	@Override
	public Long CountSoThangThucHien(LocalDate thangBatDau, LocalDate thangKetThuc, Long chiTieuId) {
		// TODO Auto-generated method stub
		return repo.CountSoThangThucHien(thangBatDau, thangKetThuc, chiTieuId);
	}

	@Override
	public Page<ThucHienBaoCao> thongKe(Long linhVucId, LocalDate thangNam, LocalDate ngayThucHien, LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThucHienBaoCaoSpecifications.thongKe(linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc,thangBatDauTN, thangKetThucTN),pageable);
	}

	@Override
	public Float TongThucHienNamTruoc(LocalDate thangBatDau, LocalDate thangKetThuc, String chiTieuTen, Long LinhVucId,
			Integer namCu) {
		// TODO Auto-generated method stub
		return repo.TongThucHienNamTruoc(thangBatDau, thangKetThuc, chiTieuTen, LinhVucId, namCu);
	}

	@Override
	public Long CountSoThangThucHienNamCu(LocalDate thangBatDau, LocalDate thangKetThuc, String chiTieuTen,
			Long LinhVucId, Integer namCu) {
		// TODO Auto-generated method stub
		return repo.CountSoThangThucHienNamCu(thangBatDau, thangKetThuc, chiTieuTen, LinhVucId, namCu);
	}

	@Override
	public List<ThucHienBaoCao> findListByLinhVucIdAndThangNamAndDaXoa(LocalDate thangNam, Long linhVucId,
			Integer nam) {
		// TODO Auto-generated method stub
		return repo.findListByLinhVucIdAndThangNamAndDaXoa(thangNam, linhVucId, nam);
	}

}
