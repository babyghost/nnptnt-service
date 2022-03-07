package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;

@Repository
public interface CoSoChanNuoiRepo extends JpaRepository<CoSoChanNuoi, Long>,JpaSpecificationExecutor<CoSoChanNuoi>{

	public Optional<CoSoChanNuoi> findByTenCoSo(String tenCoSo); 

}
