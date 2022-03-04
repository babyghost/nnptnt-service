package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmThuTucGiayPhep;

public interface DmThuTucGiayPhepService {
	public DmThuTucGiayPhep save(DmThuTucGiayPhep dmThuTucGiayPhep);

	public void deleteById(Long id);

	public Optional<DmThuTucGiayPhep> findById(Long id);

	public Page<DmThuTucGiayPhep> findAll(String search, Pageable pageable);

	public List<DmThuTucGiayPhep> findByMaAndDaXoa(String maThuTuc, Integer daXoa);
}
