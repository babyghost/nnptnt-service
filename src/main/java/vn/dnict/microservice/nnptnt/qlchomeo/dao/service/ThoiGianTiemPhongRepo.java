package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThoiGianTiemPhong;

@Repository
public interface ThoiGianTiemPhongRepo extends JpaRepository<ThoiGianTiemPhong, Long>,JpaSpecificationExecutor<ThoiGianTiemPhong>{


}
