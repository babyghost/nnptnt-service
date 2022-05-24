package vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
@Repository
public interface DmNganhHangRepo extends JpaRepository<DmNganhHang, Long>,JpaSpecificationExecutor<DmNganhHang> {

}
