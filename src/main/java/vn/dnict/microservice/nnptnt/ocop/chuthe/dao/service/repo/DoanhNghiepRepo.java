package vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
@Repository
public interface DoanhNghiepRepo extends JpaRepository<DoanhNghiep, Long>,JpaSpecificationExecutor<DoanhNghiep>{

}
