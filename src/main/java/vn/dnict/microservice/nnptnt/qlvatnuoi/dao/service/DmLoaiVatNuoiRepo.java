package vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.DmLoaiVatNuoi;

@Repository
public interface DmLoaiVatNuoiRepo extends JpaRepository<DmLoaiVatNuoi, Long>,JpaSpecificationExecutor<DmLoaiVatNuoi>{

}
