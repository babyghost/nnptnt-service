package vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.model.NamChanNuoi;

@Repository
public interface NamChanNuoiRepo extends JpaRepository<NamChanNuoi, Long>,JpaSpecificationExecutor<NamChanNuoi>{

}
