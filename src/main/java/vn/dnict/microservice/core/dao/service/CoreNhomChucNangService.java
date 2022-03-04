package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;

public interface CoreNhomChucNangService {

	public Optional<CoreNhomChucNang> findById(Long id);

	public CoreNhomChucNang save(CoreNhomChucNang coreNhomChucNang);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreNhomChucNang> findAll(String search, Boolean trangThai, Long id, Long nhomChucNangChaId,
			Pageable pageable);

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Boolean trangThai, Boolean daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId, Boolean trangThai,
			Boolean daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Boolean trangThai, Boolean daXoa);

	public int setFixedDaXoa(Boolean daXoa);

	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma);

}
