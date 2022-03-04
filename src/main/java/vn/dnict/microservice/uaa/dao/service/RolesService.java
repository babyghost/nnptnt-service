package vn.dnict.microservice.uaa.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.uaa.dao.model.Roles;


public interface RolesService {

	public Roles save(Roles roles);

	public void deleteByRoleName(String roleName);

	public boolean existsByRoleName(String roleName);

	public Optional<Roles> findByRoleName(String roleName);

	public Page<Roles> findAll(String roleName, Pageable pageable);

}
