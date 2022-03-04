package vn.dnict.microservice.uaa.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.uaa.dao.model.UserLog;


@Service
public class UserLogServiceImpl implements UserLogService {
	@Autowired
	private UserLogRepo repo;

	public UserLog save(UserLog roles) {
		return repo.save(roles);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public Optional<UserLog> findById(Long id) {
		return repo.findById(id);
	}

	public Page<UserLog> findAll(String email, Pageable pageable) {
		return repo.findAll(UserLogSpecifications.quickSearch(email), pageable);
	}

}
