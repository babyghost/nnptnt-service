package vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;
@Repository
public interface DmLoaiHinhRepo extends JpaRepository<DmLoaiHinh, Long>,JpaSpecificationExecutor<DmLoaiHinh> {

}
