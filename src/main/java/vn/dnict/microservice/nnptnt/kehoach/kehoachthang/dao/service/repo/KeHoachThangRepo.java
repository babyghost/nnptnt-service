package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

@Repository
public interface KeHoachThangRepo extends JpaRepository<KeHoachThang, Long>, JpaSpecificationExecutor<KeHoachThang> {

}
