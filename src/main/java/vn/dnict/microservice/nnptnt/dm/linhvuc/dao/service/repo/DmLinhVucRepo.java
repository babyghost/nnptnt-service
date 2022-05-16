package vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
@Repository
public interface DmLinhVucRepo extends JpaRepository<DmLinhVuc, Long>,JpaSpecificationExecutor<DmLinhVuc>{

}
