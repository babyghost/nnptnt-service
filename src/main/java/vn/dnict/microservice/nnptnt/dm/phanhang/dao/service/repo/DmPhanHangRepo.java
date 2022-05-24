package vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;

public interface DmPhanHangRepo extends JpaRepository<DmPhanHang, Long>,JpaSpecificationExecutor<DmPhanHang>  {

}
