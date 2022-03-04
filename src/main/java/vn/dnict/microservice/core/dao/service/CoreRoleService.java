package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreRole;

public interface CoreRoleService {

	public Optional<CoreRole> findById(Long id);

	public CoreRole save(CoreRole coreRole);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreRole> findAll(String search, Boolean trangThai, Pageable pageable);

	public List<CoreRole> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreRole> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<CoreRole> findByIds(List<Long> ids);
}
