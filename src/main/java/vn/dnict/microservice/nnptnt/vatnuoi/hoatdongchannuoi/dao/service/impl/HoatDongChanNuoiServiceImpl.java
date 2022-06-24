package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.HoatDongChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.HoatDongChanNuoiSpecifications;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.repo.HoatDongChanNuoiRepo;

@Service
@Transactional
public class HoatDongChanNuoiServiceImpl implements  HoatDongChanNuoiService{
	@Autowired
	HoatDongChanNuoiRepo repo;
	
	@Override
	public HoatDongChanNuoi save(HoatDongChanNuoi entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
	}
	
	public Optional<HoatDongChanNuoi> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	@Override
	public Page<HoatDongChanNuoi> findAll(String tenCoSo, String tenChuCoSo, String dienThoai, Long quanHuyenId, 
			Long phuongXaId, String nam, Integer quy, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(HoatDongChanNuoiSpecifications.quickSearch(tenCoSo, tenChuCoSo, dienThoai, quanHuyenId, 
				phuongXaId, nam, quy), pageable);
	}

	@Override
	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndDaXoa(Long coSoChanNuoiId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByCoSoChanNuoiIdAndDaXoa(coSoChanNuoiId, daXoa);
	}
	@Override
	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(Long coSoChanNuoiId, String nam, Integer quy,
			Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(coSoChanNuoiId, nam, quy, daXoa);
	}


	@Override
	public Long tongSoLuongVatNuoi( String nam, List< Integer> quy, List< Long> loaiVatNuoiIds) {
		// TODO Auto-generated method stub
		return repo.tongSoLuongVatNuoi( nam, quy, loaiVatNuoiIds);
	}
	@Override
	public Page<HoatDongChanNuoi> thongKeSoLuong(String nam, List< Integer> quy, List< Long> loaiVatNuoiIds, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(HoatDongChanNuoiSpecifications.thongKeSoLuong(nam, quy, loaiVatNuoiIds), pageable);
	}



	@Override
	public Page<HoatDongChanNuoi> thongKeHoatDong(String tenCoSo, String tenChuCoSo, List<Long> loaiVatNuoiIds,
			Long quanHuyenId, Long phuongXaId, String nam, List<Integer> quys, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(HoatDongChanNuoiSpecifications.thongKeHoatDong(tenCoSo, tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys), pageable);
	}

	@Override
	public List<Object[]> thongKeSoVatNuoi(String nam, List<Long> loaiVatNuoiIds, List<Integer> quy) {
		// TODO Auto-generated method stub
		return repo.thongKeSoVatNuoi(nam, loaiVatNuoiIds, quy);
	}

	@Override
	public Page<Object> thongKeSoVatNuoiDemo(String nam, List<Long> loaiVatNuoiIds, List<Integer> quy,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.thongKeSoVatNuoiDemo(nam, loaiVatNuoiIds, quy, pageable);
	}

}
