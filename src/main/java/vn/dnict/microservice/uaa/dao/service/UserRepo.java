package vn.dnict.microservice.uaa.dao.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import vn.dnict.microservice.uaa.dao.model.User;




public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1")
	public Optional<User> findByEmail(String username);

	public boolean existsByEmail(String email);

}
