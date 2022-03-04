package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoach2ChoMeo;

@Repository
public interface KeHoach2ChoMeoRepo extends JpaRepository<KeHoach2ChoMeo, Long>, JpaSpecificationExecutor<KeHoach2ChoMeo>{

}
