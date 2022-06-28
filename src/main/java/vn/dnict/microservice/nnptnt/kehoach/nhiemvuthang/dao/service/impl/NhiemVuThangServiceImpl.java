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
	public Page<NhiemVuThang> findAll(Long donViChuTriId, List<LocalDate> thangs, List<Integer> tinhTrangs, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuThangSpecifications.quichSearch(donViChuTriId, thangs, tinhTrangs, tenNhiemVu, tuNgay, denNgay), pageable);
	}

	@Override
	public List<NhiemVuThang> getThongKeSoLuong(Long donViChuTriId, List<LocalDate> thangs, Long keHoachThangId,
			String tenNhiemVu, List<Integer> tinhTrangs, Long canBoThucHienId, LocalDate tuNgay, LocalDate denNgay) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuThangSpecifications.thongKeThang(donViChuTriId, thangs, tenNhiemVu, tinhTrangs, canBoThucHienId, tuNgay, denNgay));
	}

}
