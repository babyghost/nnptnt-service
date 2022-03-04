package vn.dnict.microservice.core.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreAttachmentTemp;

@Repository
public interface CoreAttachmentTempRepo extends JpaRepository<CoreAttachmentTemp, Long> {
	
}
