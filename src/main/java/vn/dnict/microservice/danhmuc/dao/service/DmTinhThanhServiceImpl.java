package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;

@Service
@Transactional
public class DmTinhThanhServiceImpl implements DmTinhThanhService {

	@Autowired
	private DmTinhThanhRepo repo;

	public DmTinhThanh save(DmTinhThanh dmTinhThanh) {
		return repo.save(dmTinhThanh);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmTinhThanh> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmTinhThanh> findAll(String search, Boolean trangThai, Pageable pageable) {
		return repo.findAll(DmTinhThanhSpecifications.quickSearch(search, trangThai), pageable);
	}

	public List<DmTinhThanh> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa) {
		return repo.findByTenIgnoreCaseEndingWithAndDaXoa(ten, daXoa);
	}

	public List<DmTinhThanh> findByMa(String ma) {
		return repo.findByMa(ma);
	}

	public List<DmTinhThanh> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<DmTinhThanh> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<DmTinhThanh> findByIdIn(List<Long> ids) {
		return repo.findByIdIn(ids);
	}
	@Override
	public Optional<DmTinhThanh> findTinhThanhByMa(String ma) {
		// TODO Auto-generated method stub
		return repo.findTinhThanhByMa(ma);
	}

}
