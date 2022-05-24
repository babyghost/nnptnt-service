package vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;
@Repository
public interface DmLoaiDoanhNghiepOCOPRepo extends JpaRepository<DmLoaiDoanhNghiepOCOP, Long>,JpaSpecificationExecutor<DmLoaiDoanhNghiepOCOP>{

}
