package vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
@Repository
public interface DmLoaiDongVatRepo extends JpaRepository<DmLoaiDongVat, Long>,JpaSpecificationExecutor<DmLoaiDongVat>{
	public List<DmLoaiDongVat> findByTen(String ten);
}
