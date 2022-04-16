package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongSpecifications;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.repo.ThoiGianTiemPhongRepo;

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
	public Page<ThoiGianTiemPhong> findAll(Long phuongXaId, Long quanHuyenId, LocalDate thoiGianTiemDenNgay,
			LocalDate thoiGianTiemTuNgay, Long keHoachTiemPhongId, String diaDiem, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThoiGianTiemPhongSpecifications.quickSearch(diaDiem, phuongXaId, quanHuyenId, keHoachTiemPhongId, thoiGianTiemTuNgay, thoiGianTiemDenNgay),pageable);
	}

	@Override
	public List<ThoiGianTiemPhong> findByKeHoachTiemPhongIdAndDaXoa(Long keHoachTiemPhongId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachTiemPhongIdAndDaXoa(keHoachTiemPhongId, daXoa);
	}

	@Override
	public int setFixedDaXoaForKeHoachTiemPhongId(Boolean daXoa, Long keHoachTiemPhongId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForKeHoachTiemPhongId(daXoa, keHoachTiemPhongId);
	}




	
}
