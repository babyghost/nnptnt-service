package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

@Repository
public interface ThongTinChoMeoRepo extends JpaRepository<ThongTinChoMeo, Long>,JpaSpecificationExecutor<ThongTinChoMeo>{

}

