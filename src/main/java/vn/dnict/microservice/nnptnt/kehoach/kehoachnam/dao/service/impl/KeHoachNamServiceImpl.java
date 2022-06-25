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
	public KeHoachNam save(KeHoachNam keHoachNam) {
		// TODO Auto-generated method stub
		return repo.save(keHoachNam);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<KeHoachNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Page<KeHoachNam> findAll(Long donViChuTriId, Integer nam, String tenKeHoach, String soKyHieu,
			Boolean trangThai, LocalDate tuNgayBanHanh, LocalDate denNgayBanHanh, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(KeHoachNamSpecifications.quichSearch(donViChuTriId, nam, tenKeHoach, soKyHieu, trangThai, tuNgayBanHanh,
				denNgayBanHanh), pageable);
	}
}
