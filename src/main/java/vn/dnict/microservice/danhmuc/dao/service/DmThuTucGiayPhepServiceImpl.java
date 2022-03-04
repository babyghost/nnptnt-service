package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DmThuTucGiayPhep;

@Service
public class DmThuTucGiayPhepServiceImpl implements DmThuTucGiayPhepService {

	@Autowired
	private DmThuTucGiayPhepRepo repo;

	public DmThuTucGiayPhep save(DmThuTucGiayPhep dmThuTucGiayPhep) {
		return repo.save(dmThuTucGiayPhep);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmThuTucGiayPhep> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmThuTucGiayPhep> findAll(String search, Pageable pageable) {
		return repo.findAll(DmThuTucGiayPhepSpecifications.quickSearch(search), pageable);
	}

	public List<DmThuTucGiayPhep> findByMaAndDaXoa(String maThuTuc, Integer daXoa) {
		return repo.findByMaAndDaXoa(maThuTuc, daXoa);
	}

}
