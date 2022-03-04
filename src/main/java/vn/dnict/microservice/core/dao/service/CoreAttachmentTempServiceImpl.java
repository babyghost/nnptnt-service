package vn.dnict.microservice.core.dao.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.dao.model.CoreAttachmentTemp;
@Service
@Transactional
public class CoreAttachmentTempServiceImpl implements CoreAttachmentTempService{
	@Autowired private CoreAttachmentTempRepo repository;
	public Long getCoreAttachmentTempId(){
		CoreAttachmentTemp coreAttachmentTemp = new CoreAttachmentTemp();
		coreAttachmentTemp = repository.save(coreAttachmentTemp);
		return coreAttachmentTemp.getId();
	}
}
