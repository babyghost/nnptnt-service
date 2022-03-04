package vn.dnict.microservice.danhmuc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiHinhKinhTeDn;

@Repository
public interface DmLoaiHinhKinhTeDnRepo 
		extends JpaRepository<DmLoaiHinhKinhTeDn, Long>, JpaSpecificationExecutor<DmLoaiHinhKinhTeDn>{

}
