package vn.dnict.microservice.uaa.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.dnict.microservice.uaa.dao.model.UserLog;


public interface UserLogRepo extends JpaRepository<UserLog, Long>, JpaSpecificationExecutor<UserLog> {

}
