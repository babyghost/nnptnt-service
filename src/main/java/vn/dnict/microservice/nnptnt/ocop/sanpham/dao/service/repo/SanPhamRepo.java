package vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;
@Repository
public interface SanPhamRepo extends JpaRepository<SanPham, Long>,JpaSpecificationExecutor<SanPham>{

}
