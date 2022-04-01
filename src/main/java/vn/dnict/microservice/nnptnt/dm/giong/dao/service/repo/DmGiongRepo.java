
package vn.dnict.microservice.nnptnt.dm.giong.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;

@Repository
public interface DmGiongRepo 
		extends JpaRepository<DmGiong, Long>, JpaSpecificationExecutor<DmGiong>{

	public List<DmGiong> findByTen(String ten);

}