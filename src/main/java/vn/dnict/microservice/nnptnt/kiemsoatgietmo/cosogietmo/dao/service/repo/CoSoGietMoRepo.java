package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;

@Repository
public interface CoSoGietMoRepo extends JpaRepository<CoSoGietMo, Long>,JpaSpecificationExecutor<CoSoGietMo> {
	public Optional<CoSoGietMo> findByTenCoSo(String tenCoSo);
}
