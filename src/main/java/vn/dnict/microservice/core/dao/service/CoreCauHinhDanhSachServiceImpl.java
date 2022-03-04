package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;

@Service
public class CoreCauHinhDanhSachServiceImpl implements CoreCauHinhDanhSachService {
	@Autowired
	private CoreCauHinhDanhSachRepo repo;

	public Optional<CoreCauHinhDanhSach> findById(Long id) {
		return repo.findById(id);
	}

	public CoreCauHinhDanhSach save(CoreCauHinhDanhSach coreCauHinhDanhSach) {
		return repo.save(coreCauHinhDanhSach);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreCauHinhDanhSach> findAll(String maDanhSach, String tenCot, Boolean trangThai, Pageable pageable) {
		return repo.findAll(CoreCauHinhDanhSachSpecifications.quickSearch(maDanhSach, tenCot, trangThai),
				pageable);
	}

	public List<CoreCauHinhDanhSach> findByMaDanhSachAndAppCodeAndTrangThaiAndDaXoaOrderBySapXepAsc(String maDanhSach,
			String appCode, Boolean trangThai, Boolean daXoa) {
		return repo.findByMaDanhSachAndAppCodeAndTrangThaiAndDaXoaOrderBySapXepAsc(maDanhSach, appCode, trangThai,
				daXoa);
	}

}
