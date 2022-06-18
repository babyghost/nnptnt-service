package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.repo.NhiemVuThangRepo;

@Service
@Transactional
public class NhiemVuThangServiceImpl implements NhiemVuThangService{
	
	@Autowired
	NhiemVuThangRepo repo;
	
	@Override
	public NhiemVuThang save(NhiemVuThang entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVuThang> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<NhiemVuThang> findAll(Long donViChuTriId, List<LocalDate> thangs, String tenNhiemVu, Long canBoThucHienId,
			LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuThangSpecifications.quickSearch(donViChuTriId, thangs, tenNhiemVu, canBoThucHienId,
				thoiHanTuNgay, thoiHanDenNgay, tinhTrang), pageable);
	}

	@Override
	public List<NhiemVuThang> findByKeHoachThangIdAndDaXoa(Long keHoachThangId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachThangIdAndDaXoa(keHoachThangId, daXoa);
	}

	@Override
	public int setFixedDaXoaForKeHoachThangId(Boolean daXoa, Long keHoachThangId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForKeHoachThangId(daXoa, keHoachThangId);
	}

	@Override
	public Optional<NhiemVuThang> findByKeHoachThangId(Long keHoachThangId) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachThangId(keHoachThangId);
	}

	@Override
	public Page<NhiemVuThang> tongHopKeHoachThang(Long donViChuTriId, List<LocalDate> thangs, String tenNhiemVu,
			Integer tinhTrang, Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuThangSpecifications.tongHopKeHoachThang(donViChuTriId, thangs, tenNhiemVu, tinhTrang,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay), pageable);
	}

}
