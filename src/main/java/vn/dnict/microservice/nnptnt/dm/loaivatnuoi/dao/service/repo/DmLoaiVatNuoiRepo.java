package vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;

@Repository
public interface DmLoaiVatNuoiRepo extends JpaRepository<DmLoaiVatNuoi, Long>,JpaSpecificationExecutor<DmLoaiVatNuoi>{

}
