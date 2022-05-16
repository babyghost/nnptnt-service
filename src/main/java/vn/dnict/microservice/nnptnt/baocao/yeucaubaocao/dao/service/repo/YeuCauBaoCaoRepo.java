package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model.YeuCauBaoCao;
@Repository
public interface YeuCauBaoCaoRepo extends JpaRepository<YeuCauBaoCao, Long>,JpaSpecificationExecutor<YeuCauBaoCao>{

}
