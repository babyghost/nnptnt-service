package vn.dnict.microservice.core.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;

@Repository
public interface CoreNhomChucNangRepo
		extends JpaRepository<CoreNhomChucNang, Long>, JpaSpecificationExecutor<CoreNhomChucNang> {

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Boolean trangThai, Boolean daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId, Boolean trangThai,
			Boolean daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Boolean trangThai,
			Boolean daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update CoreNhomChucNang u set u.daXoa = ?1")
	public int setFixedDaXoa(Boolean daXoa);
	
	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma);
}
