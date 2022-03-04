package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;

public interface DmQuanHuyenService {
	public DmQuanHuyen save(DmQuanHuyen dmQuanHuyen);

	public void deleteById(Long id);

	public Optional<DmQuanHuyen> findById(Long id);

	public Page<DmQuanHuyen> findAll(String search, Boolean trangThai, String tinhThanhCode, Long duAnId,
			Pageable pageable);

	public List<DmQuanHuyen> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmQuanHuyen> findByMa(String ma);

	public List<DmQuanHuyen> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmQuanHuyen> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmQuanHuyen> findByTinhThanhCodeIgnoreCaseAndDaXoa(String tinhThanhCode, Boolean daXoa);

	public List<DmQuanHuyen> findByIdIn(List<Long> ids);
	public Optional<DmQuanHuyen> findQuanHuyenByMa(String ma);
}
