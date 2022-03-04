package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;

public interface DmTinhThanhService {
	public DmTinhThanh save(DmTinhThanh dmTinhThanh);

	public void deleteById(Long id);

	public Optional<DmTinhThanh> findById(Long id);

	public Page<DmTinhThanh> findAll(String search, Boolean trangThai, Pageable pageable);

	public List<DmTinhThanh> findByTenIgnoreCaseEndingWithAndDaXoa(String ten, Boolean daXoa);

	public List<DmTinhThanh> findByMa(String ma);

	public List<DmTinhThanh> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmTinhThanh> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmTinhThanh> findByIdIn(List<Long> ids);
	public Optional<DmTinhThanh> findTinhThanhByMa(String ma);
}
