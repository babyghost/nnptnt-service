package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;

@Repository
public interface TienDoNhiemVuThangRepo extends JpaRepository<TienDoNhiemVuThang, Long>, JpaSpecificationExecutor<TienDoNhiemVuThang>{

}
