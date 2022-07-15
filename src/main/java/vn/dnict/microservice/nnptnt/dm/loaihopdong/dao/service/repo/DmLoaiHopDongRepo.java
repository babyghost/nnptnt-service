package vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.model.DmLoaiHopDong;

@Repository
public interface DmLoaiHopDongRepo extends JpaRepository<DmLoaiHopDong, Long>,JpaSpecificationExecutor<DmLoaiHopDong>{

}
