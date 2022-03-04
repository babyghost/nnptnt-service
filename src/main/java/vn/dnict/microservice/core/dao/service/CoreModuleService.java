package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreModule;

public interface CoreModuleService {

	public Optional<CoreModule> findById(Long id);

	public CoreModule save(CoreModule coreModule);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreModule> findAll(String search, Boolean trangThai, Pageable pageable);

	public List<CoreModule> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreModule> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

}
