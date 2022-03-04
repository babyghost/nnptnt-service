package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreAttachment;

@Repository
public interface CoreAttachmentRepo extends JpaRepository<CoreAttachment, Long> {

	public CoreAttachment findByCode(String code);

	public List<CoreAttachment> findByIdIn(List<Long> idList);

	public List<CoreAttachment> findByIdInAndAppCodeAndDaXoa(List<Long> idList, String appCode, int daXoa);

	@Modifying
	@Query("update CoreAttachment u set u.daXoa = ?1 where u.objectId = ?2 and u.appCode = ?3")
	public int setFixedDaXoaForObjectIdAndAppCode(int daXoa, Long objectId, String appCode);
	
	@Modifying
	@Query("update CoreAttachment u set u.daXoa = ?1 where u.objectId = ?2 and u.appCode = ?3 and u.type = ?4")
	public int setFixedDaXoaForObjectIdAndAppCodeAndType(int daXoa, Long objectId, String appCode, int type);

	public List<CoreAttachment> findByObjectIdAndAppCodeAndDaXoa(Long objectId, String appCode, int daXoa);

	public List<CoreAttachment> findByObjectIdAndAppCodeAndTypeAndDaXoa(Long objectId, String appCode, Integer type,
			int daXoa);
}
