package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoSpecifications;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.repo.ThongTinChoMeoRepo;

@Service
@Transactional
public class ThongTinChoMeoServiceImpl implements ThongTinChoMeoService{

	@Autowired
	ThongTinChoMeoRepo repo;
	@Override
	public ThongTinChoMeo save(ThongTinChoMeo entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	@Override
	public Optional<ThongTinChoMeo> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}



	@Override
	public List<ThongTinChoMeo> findByChuQuanLyIdAndDaXoa(Long chuQuanLyId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByChuQuanLyIdAndDaXoa(chuQuanLyId, daXoa);
	}

	@Override
	public int setFixedDaXoaForChuQuanLyId(Boolean daXoa, Long chuQuanLyId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForChuQuanLyId(daXoa, chuQuanLyId);
	}

	@Override
	public Optional<ThongTinChoMeo> findByChuQuanLyId(Long chuQuanLyId) {
		// TODO Auto-generated method stub
		return repo.findByChuQuanLyId(chuQuanLyId);
	}

	@Override
	public Page<ThongTinChoMeo> findAll(Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,
			LocalDate tuNgayTiemPhong, LocalDate denNgayTiemPhong, Integer trangThai, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(ThongTinChoMeoSpecifications.quickSearch(loaiDongVatId, giongId, tenChuHo, dienThoai, tuNgayTiemPhong, denNgayTiemPhong, trangThai),pageable);
	}



//	@Override
//	public Page<ThongTinChoMeo> findAll(String loaiDongVat,String Integer trangThai, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return repo.findAll(ThongTinChoMeoSpecifications.quickSearch(search, trangThai), pageable);
//	}

}
