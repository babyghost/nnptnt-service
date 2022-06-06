package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.KeHoachKhacService;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.KeHoachKhacSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.repo.KeHoachKhacRepo;

@Service
@Transactional
public class KeHoachKhacServiceImpl implements KeHoachKhacService {
	@Autowired
	private KeHoachKhacRepo repo;

	@Override
	public KeHoachKhac save(KeHoachKhac keHoach) {
		return repo.save(keHoach);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<KeHoachKhac> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Page<KeHoachKhac> findAll(String tenKeHoach, Integer nam, Pageable pageable) {
		return repo.findAll(KeHoachKhacSpecifications.quichSearch(tenKeHoach, nam), pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Integer getMaxNam() {
		return repo.getMaxNam();
	}

}
