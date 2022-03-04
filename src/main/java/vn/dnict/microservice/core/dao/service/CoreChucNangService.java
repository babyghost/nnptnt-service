package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreChucNang;

public interface CoreChucNangService {

	public Optional<CoreChucNang> findById(Long id);

	public CoreChucNang save(CoreChucNang coreChucNang);

	public void delete(Long id);

	public Boolean existsById(Long id);

	public Page<CoreChucNang> findAll(String search, Long nhomChucNangId, Boolean trangThai, Pageable pageable);

	public List<CoreChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<CoreChucNang> findByCoreNhomChucNangIdAndTrangThaiAndDaXoa(Long nhomChucNangId, Boolean trangThai,
			Boolean daXoa);

	public int setFixedDaXoaForNhomChucNangId(Boolean daXoa, Long nhomChucNangId);

	public int setFixedDaXoa(Boolean daXoa);

	public List<CoreChucNang> findByMaIgnoreCase(String ma);

	public List<String> getChucNangMas(String roleName);

}
