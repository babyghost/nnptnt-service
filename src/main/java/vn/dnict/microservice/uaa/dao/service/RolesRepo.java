package vn.dnict.microservice.uaa.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.dnict.microservice.uaa.dao.model.Roles;


public interface RolesRepo extends JpaRepository<Roles, String>, JpaSpecificationExecutor<Roles> {

}
