package vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;
@Repository
public interface DmPhanNhomRepo extends JpaRepository<DmPhanNhom, Long>,JpaSpecificationExecutor<DmPhanNhom>  {

}