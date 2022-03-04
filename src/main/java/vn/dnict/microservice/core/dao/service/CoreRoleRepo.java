package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreRole;

@Repository
public interface CoreRoleRepo extends JpaRepository<CoreRole, Long>, JpaSpecificationExecutor<CoreRole> {

	public List<CoreRole> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreRole> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);
	
	@Query( "select o from CoreRole o where o.daXoa = false and id in ?1" )
	public List<CoreRole> findByIds(List<Long> ids);
}
