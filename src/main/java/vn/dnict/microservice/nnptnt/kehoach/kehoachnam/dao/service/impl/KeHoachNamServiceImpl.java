package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.repo.KeHoachNamRepo;

@Service
@Transactional
public class KeHoachNamServiceImpl implements KeHoachNamService {
	@Autowired
	KeHoachNamRepo repo;
	@Override
	public KeHoachNam save(KeHoachNam entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);;
		
	}

	
	public Optional<KeHoachNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	
	public Page<KeHoachNam> findAll(Long donViChuTriId, Integer nam, String tenKeHoach, Boolean trangThai, String soKyHieu, LocalDate ngayBanHanhTuNgay,
			LocalDate ngayBanHanhDenNgay, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoachNamSpecifications.quickSearch(donViChuTriId, nam, tenKeHoach, trangThai, soKyHieu, ngayBanHanhTuNgay,
				ngayBanHanhDenNgay),pageable);
	}
}
