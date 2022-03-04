package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.dao.model.CoreUser;

@Service
public class CoreUserServiceImpl implements CoreUserService {

	@Autowired
	private CoreUserRepo repo;

	public Optional<CoreUser> findById(Long id) {
		return repo.findById(id);
	}

	public CoreUser save(CoreUser coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreUser> findAll(String email, String hoTen, List<Long> roleIds, Pageable pageable) {
		return repo.findAll(CoreUserSpecifications.quickSearch(email, hoTen, roleIds), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Boolean daXoa) {
		return repo.findByEmailIgnoreCaseAndDaXoa(email, daXoa);
	}

	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Boolean daXoa) {
		return repo.findByIdAndEmailIgnoreCaseAndDaXoa(id, email, daXoa);
	}
	public Optional<CoreUser> findByUserNameAndDaXoaAndActive(String userName, Boolean daXoa, Integer active) {
		return repo.findByUserNameAndDaXoaAndActive(userName, daXoa, active);
	}
	
}
