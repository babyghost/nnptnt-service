package vn.dnict.microservice.core.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.core.dao.model.CoreMessage;

public interface CoreMessageService {
	public Page<CoreMessage> findAll(Long donViId, Long phongBanId, String nguoiDungId, Pageable pageable);
	public Optional<CoreMessage> findById(Long id);
	public CoreMessage save(CoreMessage CoreNotification);
	public void delete(Long id);
}
