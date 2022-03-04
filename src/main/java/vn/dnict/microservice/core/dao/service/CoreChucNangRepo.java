package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreChucNang;

@Repository
public interface CoreChucNangRepo extends JpaRepository<CoreChucNang, Long>, JpaSpecificationExecutor<CoreChucNang> {

	public List<CoreChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<CoreChucNang> findByCoreNhomChucNangIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangId,
			Boolean trangThai, Boolean daXoa);
	
	
	@Modifying(clearAutomatically = true)
	@Query("update CoreChucNang u set u.daXoa = ?1 where u.coreNhomChucNang.id = ?2")
	public int setFixedDaXoaForNhomChucNangId(Boolean daXoa, Long nhomChucNangId);

	@Modifying(clearAutomatically = true)
	@Query("update CoreChucNang u set u.daXoa = ?1")
	public int setFixedDaXoa(Boolean daXoa);

	public List<CoreChucNang> findByMaIgnoreCase(String ma);

	@Query(value = "SELECT chucnang_ma FROM view_corechucnang WHERE chucnang_id IS NOT NULL AND ?1 = ANY (roles)", nativeQuery = true)
	public List<String> getChucNangMas(String roleName);

}
