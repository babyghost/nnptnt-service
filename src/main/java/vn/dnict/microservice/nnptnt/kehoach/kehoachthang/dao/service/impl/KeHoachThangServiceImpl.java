package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.repo.KeHoachThangRepo;

@Service
@Transactional
public class KeHoachThangServiceImpl implements KeHoachThangService {
	
	@Autowired
	KeHoachThangRepo repo;

	@Override
	public KeHoachThang save(KeHoachThang entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<KeHoachThang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<KeHoachThang> findAll(Long donViChuTriId, LocalDate thang, String tenNhiemVu, Long canBoThucHienId,
			LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoachThangSpecifications.quickSearch(donViChuTriId, thang, tenNhiemVu, canBoThucHienId, 
				thoiHanTuNgay, thoiHanDenNgay, tinhTrang), pageable);
	}

}
