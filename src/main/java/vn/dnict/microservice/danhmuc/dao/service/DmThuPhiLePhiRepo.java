package vn.dnict.microservice.danhmuc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmThuPhiLePhi;

@Repository
public interface DmThuPhiLePhiRepo 
		extends JpaRepository<DmThuPhiLePhi, Long>, JpaSpecificationExecutor<DmThuPhiLePhi>{

}
