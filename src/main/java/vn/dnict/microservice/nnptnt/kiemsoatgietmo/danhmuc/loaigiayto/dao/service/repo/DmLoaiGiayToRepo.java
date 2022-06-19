package vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.model.DmLoaiGiayTo;

public interface DmLoaiGiayToRepo extends JpaRepository<DmLoaiGiayTo, Long>, JpaSpecificationExecutor<DmLoaiGiayTo> {

}
