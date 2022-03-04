package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;

@Service
public class DmCapDonViServiceImpl implements DmCapDonViService {
	@Autowired
	private DmCapDonViRepo capDonViRepo;

	public DmCapDonVi save(DmCapDonVi capDonVi) {
		return capDonViRepo.save(capDonVi);
	}

	public Optional<DmCapDonVi> findById(Long id) {
		return capDonViRepo.findById(id);
	}

	public void delete(Long id) {
		capDonViRepo.deleteById(id);
	}

	public Page<DmCapDonVi> findAll(String search, Pageable pageable) {
		return capDonViRepo.findAll(DmCapDonViSpecifications.quickSearch(search), pageable);
	}

	public Page<DmCapDonVi> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable) {
		return capDonViRepo.findAll(DmCapDonViSpecifications.findByDaXoaAndSearch(daXoa, search), pageable);
	}

	public List<DmCapDonVi> findByIdIn(List<Long> idList) {
		return capDonViRepo.findByIdIn(idList);
	}

}
