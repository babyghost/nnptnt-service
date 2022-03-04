package vn.dnict.microservice.danhmuc.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDoanhNghiep;

@Repository
public interface DmLoaiDoanhNghiepRepo
		extends JpaRepository<DmLoaiDoanhNghiep, Long>, JpaSpecificationExecutor<DmLoaiDoanhNghiep> {

}
