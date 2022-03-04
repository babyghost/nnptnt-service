package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThoiGianTiemPhong;

@Service
@Transactional
public class ThoiGianTiemPhongServiceImpl implements ThoiGianTiemPhongService{

	@Autowired
	ThoiGianTiemPhongRepo repo;
	@Override
	public ThoiGianTiemPhong save(ThoiGianTiemPhong entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<ThoiGianTiemPhong> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<ThoiGianTiemPhong> findAll(Long phuongXaId, Long quanHuyenId, LocalDateTime thoiGianTiemDenNgay,
			LocalDateTime thoiGianTiemTuNgay, Long keHoachTiemPhongId, String diaDiem, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThoiGianTiemPhongSpecifications.quickSearch(diaDiem, phuongXaId, quanHuyenId, keHoachTiemPhongId, thoiGianTiemTuNgay, thoiGianTiemDenNgay),pageable);
	}




	
}
