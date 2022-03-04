package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;

@Service
@Transactional
public class DmQuanHuyenServiceImpl implements DmQuanHuyenService {
	@Autowired
	private DmQuanHuyenRepo repo;

	public DmQuanHuyen save(DmQuanHuyen dmQuanHuyen) {
		return this.repo.save(dmQuanHuyen);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmQuanHuyen> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmQuanHuyen> findAll(String search, Boolean trangThai, String tinhThanhCode, Long duAnId,
			Pageable pageable) {
		return repo.findAll(DmQuanHuyenSpecifications.quickSearch(search, trangThai, tinhThanhCode, duAnId), pageable);
	}

	public List<DmQuanHuyen> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa) {
		return repo.findByTenIgnoreCaseEndingWithAndDaXoa(ten, daXoa);
	}

	public List<DmQuanHuyen> findByMa(String ma) {
		return repo.findByMa(ma);
	}

	public List<DmQuanHuyen> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<DmQuanHuyen> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<DmQuanHuyen> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa) {
		return repo.findByTinhThanhCodeIgnoreCaseAndDaXoa(tinhThanhCode, daXoa);
	}

	public List<DmQuanHuyen> findByIdIn(List<Long> ids) {
		return repo.findByIdIn(ids);
	}
	@Override
	public Optional<DmQuanHuyen> findQuanHuyenByMa(String ma) {
		// TODO Auto-generated method stub
		return repo.findQuanHuyenByMa(ma);
	}
}
