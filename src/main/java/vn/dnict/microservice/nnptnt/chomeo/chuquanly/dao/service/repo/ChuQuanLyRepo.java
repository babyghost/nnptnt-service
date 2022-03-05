package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;



@Repository
public interface ChuQuanLyRepo extends JpaRepository<ChuQuanLy,Long> , JpaSpecificationExecutor<ChuQuanLy>{

}
