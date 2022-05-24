package vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;
@Repository
public interface DmNganhNgheOCOPRepo extends JpaRepository<DmNganhNgheOCOP, Long>,JpaSpecificationExecutor<DmNganhNgheOCOP>{

}
