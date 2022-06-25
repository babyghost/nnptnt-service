package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;

public interface DmLoaiNhiemVuService {
	public DmLoaiNhiemVu save(DmLoaiNhiemVu dmLoaiNhiemVu);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<DmLoaiNhiemVu> findById(Long id);

	public Page<DmLoaiNhiemVu> findAll(String search, Boolean trangThai, Pageable pageable);

	public List<DmLoaiNhiemVu> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmLoaiNhiemVu> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);
	
	public List<DmLoaiNhiemVu> findByTrangThaiAndDaXoaOrderBySapXepAsc(Boolean trangThai, Boolean daXoa);
}
