package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;

@Service
@Transactional
public class DmPhuongXaServiceImpl implements DmPhuongXaService {

	@Autowired
	private DmPhuongXaRepo repo;

	public DmPhuongXa save(DmPhuongXa dmPhuongXa) {
		return repo.save(dmPhuongXa);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<DmPhuongXa> findById(Long id) {
		return repo.findById(id);
	}

	public Page<DmPhuongXa> findAll(String search, Integer trangThai, String quanHuyenCode, String tinhThanhCode,
			List<String> quanHuyenCodes, List<String> tinhThanhCodes, Pageable pageable) {
		return repo.findAll(DmPhuongXaSpecifications.quickSearch(search, trangThai, quanHuyenCode, tinhThanhCode, quanHuyenCodes, tinhThanhCodes),
				pageable);
	}

	public List<DmPhuongXa> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa) {
		return repo.findByTenIgnoreCaseEndingWithAndDaXoa(ten, daXoa);
	}

	public List<DmPhuongXa> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<DmPhuongXa> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<DmPhuongXa> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa) {
		return repo.findByTinhThanhCodeIgnoreCaseAndDaXoa(tinhThanhCode, daXoa);
	}

	public List<DmPhuongXa> findByQuanHuyenCodeIgnoreCaseAndDaXoa(String quanHuyenCode, Boolean daXoa) {
		return repo.findByQuanHuyenCodeIgnoreCaseAndDaXoa(quanHuyenCode, daXoa);
	}
}
