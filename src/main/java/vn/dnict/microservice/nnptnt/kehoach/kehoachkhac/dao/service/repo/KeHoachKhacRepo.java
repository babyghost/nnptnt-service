package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;

@Repository
public interface KeHoachKhacRepo extends JpaRepository<KeHoachKhac, Long>, JpaSpecificationExecutor<KeHoachKhac> {

	@Query(value = "SELECT MAX(x.nam) FROM modulechung_kh_kehoachkhac x where x.daxoa = false) a", nativeQuery = true)
	Integer getMaxNam();
}
