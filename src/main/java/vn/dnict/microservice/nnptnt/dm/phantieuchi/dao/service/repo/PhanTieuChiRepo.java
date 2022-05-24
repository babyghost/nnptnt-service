package vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.model.PhanTieuChi;
@Repository
public interface PhanTieuChiRepo extends JpaRepository<PhanTieuChi, Long>,JpaSpecificationExecutor<PhanTieuChi> {

}
