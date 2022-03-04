package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.core.dao.model.CoreAttachment;

public interface CoreAttachmentService {
	public CoreAttachment save(CoreAttachment coreAttachment);

	public void delete(CoreAttachment coreAttachment);
	
	public Optional<CoreAttachment> findById(Long id);

	public CoreAttachment findByCode(String code);

	public CoreAttachment saveAndMove(CoreAttachment coreAttachment);

	public List<CoreAttachment> findByIdIn(List<Long> idList);

	public List<CoreAttachment> findByIdInAndAppCodeAndDaXoa(List<Long> idList, String appCode, int daXoa);

	public int setFixedDaXoaForObjectIdAndAppCode(int daXoa, Long objectId, String appCode);
	
	public int setFixedDaXoaForObjectIdAndAppCodeAndType(int daXoa, Long objectId, String appCode, int type);

	public List<CoreAttachment> findByObjectIdAndAppCodeAndDaXoa(Long objectId, String appCode, int daXoa);

	public List<CoreAttachment> findByObjectIdAndAppCodeAndTypeAndDaXoa(Long objectId, String appCode, Integer type,
			int daXoa);
}
