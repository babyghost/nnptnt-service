package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.core.dao.model.CoreRole2ChucNang;

public interface CoreRole2ChucNangService {
	public CoreRole2ChucNang save(CoreRole2ChucNang coreRole2ChucNang);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<CoreRole2ChucNang> findById(Long id);

	public List<CoreRole2ChucNang> findByRoleIdAndDaXoa(Long roleId, Boolean daXoa);

	public List<CoreRole2ChucNang> findByRoleIdAndChucNangId(Long roleId, Long chucNangId);

	public int setFixedDaXoaForRoleId(Boolean daXoa, Long roleId);

	public List<CoreRole2ChucNang> findByChucNangIdAndDaXoa(Long chucNangId, Boolean daXoa);

}
