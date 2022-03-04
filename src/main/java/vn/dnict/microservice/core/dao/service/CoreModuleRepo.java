package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreModule;

@Repository
public interface CoreModuleRepo extends JpaRepository<CoreModule, Long>, JpaSpecificationExecutor<CoreModule> {

	public List<CoreModule> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreModule> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);
}
