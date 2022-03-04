package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreUser;

public interface CoreUserService {

	public Optional<CoreUser> findById(Long id);

	public CoreUser save(CoreUser coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Optional<CoreUser> findByUserNameAndDaXoaAndActive(String userName, Boolean daXoa, Integer active); //active == 1 disable
	
	public Page<CoreUser> findAll(String email, String hoTen, List<Long> roleIds, Pageable pageable);

	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Boolean daXoa);

	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Boolean daXoa);
	
}
