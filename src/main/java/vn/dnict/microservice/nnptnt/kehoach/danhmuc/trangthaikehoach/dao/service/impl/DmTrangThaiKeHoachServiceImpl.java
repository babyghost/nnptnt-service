package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.DmTrangThaiKeHoachService;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.DmTrangThaiKeHoachSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.repo.DmTrangThaiKeHoachRepo;

@Service
public class DmTrangThaiKeHoachServiceImpl implements DmTrangThaiKeHoachService {
	@Autowired
	private DmTrangThaiKeHoachRepo repo;

	@Override
	public DmTrangThaiKeHoach save(DmTrangThaiKeHoach dmTrangThaiKeHoach) {
		return repo.save(dmTrangThaiKeHoach);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<DmTrangThaiKeHoach> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Page<DmTrangThaiKeHoach> findAll(String search, Pageable pageable) {
		return repo.findAll(DmTrangThaiKeHoachSpecifications.quichSearch(search), pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<DmTrangThaiKeHoach> findByThuHoiIdIsNotNull() {
		return repo.findByThuHoiIdIsNotNull();
	}

	@Override
	public List<DmTrangThaiKeHoach> findByThuHoiKhtcIdIsNotNull() {
		return repo.findByThuHoiKhtcIdIsNotNull();
	}

}
