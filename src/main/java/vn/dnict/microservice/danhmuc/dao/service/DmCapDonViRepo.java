package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;
@Repository
public interface DmCapDonViRepo extends JpaRepository<DmCapDonVi, Long>, JpaSpecificationExecutor<DmCapDonVi> {
	public List<DmCapDonVi> findByIdIn(List<Long> idList);
}
