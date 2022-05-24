package vn.dnict.microservice.nnptnt.dm.nhom.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;

@Repository
public interface DmNhomRepo extends JpaRepository<DmNhom, Long>,JpaSpecificationExecutor<DmNhom> {

}
