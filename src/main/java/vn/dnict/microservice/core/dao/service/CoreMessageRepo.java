package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreMessage;

@Repository
public interface CoreMessageRepo extends JpaRepository<CoreMessage, Long>, JpaSpecificationExecutor<CoreMessage> {
	public List<CoreMessage> findByIdIn(List<Long> idList);
}