package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreRole2ChucNang;

@Repository
public interface CoreRole2ChucNangRepo
		extends JpaRepository<CoreRole2ChucNang, Long>, JpaSpecificationExecutor<CoreRole2ChucNang> {
	public List<CoreRole2ChucNang> findByRoleIdAndDaXoa(Long roleId, Boolean daXoa);

	public List<CoreRole2ChucNang> findByRoleIdAndChucNangId(Long roleId, Long chucNangId);

	@Modifying(clearAutomatically = true)
	@Query("update CoreRole2ChucNang u set u.daXoa = ?1 where u.roleId = ?2")
	public int setFixedDaXoaForRoleId(Boolean daXoa, Long roleId);

	public List<CoreRole2ChucNang> findByChucNangIdAndDaXoa(Long chucNangId, Boolean daXoa);
}
