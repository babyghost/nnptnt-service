package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;

@Repository
public interface KeHoachNamRepo extends JpaRepository<KeHoachNam, Long>, JpaSpecificationExecutor<KeHoachNam>{

}
