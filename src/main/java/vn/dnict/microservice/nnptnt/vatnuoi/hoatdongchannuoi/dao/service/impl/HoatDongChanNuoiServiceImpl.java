package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.impl;

import java.time.LocalDate; 
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
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
}
