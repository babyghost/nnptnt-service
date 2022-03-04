package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreUser;

@Repository
public interface CoreUserRepo extends JpaRepository<CoreUser, Long>, JpaSpecificationExecutor<CoreUser> {

	public Optional<CoreUser> findByUserNameAndDaXoaAndActive(String userName, Boolean daXoa, Integer active); //active == 1 disable
	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Boolean daXoa);

//	@Query("SELECT u FROM CoreUser LEFT JOIN FETCH u.coreRoles WHERE u.email=?1")
//	public Optional<CoreUser> findByEmail(String username);
	public boolean existsByEmail(String email);
	
	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Boolean daXoa);
	
}
