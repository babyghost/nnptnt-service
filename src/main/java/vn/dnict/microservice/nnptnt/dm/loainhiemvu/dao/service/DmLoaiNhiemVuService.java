package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;

public interface DmLoaiNhiemVuService {
	DmLoaiNhiemVu save(DmLoaiNhiemVu dmLoaiNhiemVu);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<DmLoaiNhiemVu> findById(Long id);

	Page<DmLoaiNhiemVu> findAll(String search, Boolean trangThai, Pageable pageable);

	List<DmLoaiNhiemVu> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa);

	List<DmLoaiNhiemVu> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa);
}
