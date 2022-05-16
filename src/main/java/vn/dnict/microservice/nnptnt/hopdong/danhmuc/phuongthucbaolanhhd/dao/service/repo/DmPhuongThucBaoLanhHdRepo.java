package vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;

@Repository
public interface DmPhuongThucBaoLanhHdRepo extends JpaRepository<DmPhuongThucBaoLanhHd, Long>,JpaSpecificationExecutor<DmPhuongThucBaoLanhHd>{

}
