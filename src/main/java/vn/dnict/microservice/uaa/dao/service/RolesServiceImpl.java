package vn.dnict.microservice.uaa.dao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.uaa.dao.model.Roles;


@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesRepo repo;

	public Roles save(Roles roles) {
		return repo.save(roles);
	}

	public void deleteByRoleName(String roleName) {
		repo.deleteById(roleName);
	}

	public Optional<Roles> findByRoleName(String roleName) {
		return repo.findById(roleName);
	}

	public boolean existsByRoleName(String roleName) {
		return repo.existsById(roleName);
	}

	public Page<Roles> findAll(String roleName, Pageable pageable) {
		return repo.findAll(RolesSpecifications.quickSearch(roleName), pageable);
	}

}
