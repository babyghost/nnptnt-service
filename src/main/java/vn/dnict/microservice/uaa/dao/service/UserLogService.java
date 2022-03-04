package vn.dnict.microservice.uaa.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.uaa.dao.model.UserLog;


public interface UserLogService {

	public UserLog save(UserLog userLog);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<UserLog> findById(Long id);

	public Page<UserLog> findAll(String email, Pageable pageable);

}
