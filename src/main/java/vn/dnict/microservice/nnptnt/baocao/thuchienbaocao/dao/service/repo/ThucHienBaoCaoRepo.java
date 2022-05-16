package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;

@Repository
public interface ThucHienBaoCaoRepo
		extends JpaRepository<ThucHienBaoCao, Long>, JpaSpecificationExecutor<ThucHienBaoCao> {
	@Query(value = "SELECT u.* FROM bc_thuchienbaocao u WHERE u.thangnam = ?1 AND u.daxoa = FALSE AND u.chitieu_id IN ( SELECT x.id FROM bc_chitieu x WHERE x.daxoa = FALSE AND x.chitieunam_id IN"
			+ "(SELECT d.id FROM bc_chitieunam d WHERE d.daxoa = FALSE AND d.dmlinhvuc_id=?2 AND d.nam=?3 ) ) LIMIT 1", nativeQuery = true)
	public Optional<ThucHienBaoCao> findByLinhVucIdAndThangNamAndDaXoa(LocalDate thangNam, Long linhVucId, Integer nam);
	
	@Query(value = "SELECT SUM(thuchien) FROM bc_thuchienbaocao u WHERE u.thangnam  BETWEEN ?1 AND ?2 AND u.daxoa = FALSE AND u.chitieu_id IN  ( SELECT x.id FROM bc_chitieu x WHERE x.daxoa = FALSE AND x.ten=?3 AND x.chitieunam_id IN\r\n"
			+ "	(SELECT d.id FROM bc_chitieunam d WHERE d.daxoa = FALSE AND d.dmlinhvuc_id=?4 AND d.nam=?5 )) ",nativeQuery = true)
	public Float TongThucHienNamTruoc (LocalDate thangBatDau , LocalDate thangKetThuc, String chiTieuTen , Long LinhVucId , Integer namCu );
	
	@Query(value = "SELECT SUM(thuchien) FROM bc_thuchienbaocao u WHERE u.thangnam  BETWEEN ?1 AND ?2 AND u.daxoa = FALSE AND u.chitieu_id= ?3 ",nativeQuery = true)
	public Float TongThucHienTrongNam( LocalDate thangBatDauTN , LocalDate thangKetThucTN , Long chiTieuId);
	
	@Query(value = "SELECT COUNT(thangnam) FROM bc_thuchienbaocao u WHERE u.thangnam  BETWEEN ?1 AND ?2 AND u.daxoa = FALSE AND u.chitieu_id= ?3 ",nativeQuery = true)
	public Long CountSoThangThucHien(LocalDate thangBatDau , LocalDate thangKetThuc, Long chiTieuId);
	
	@Query(value ="SELECT COUNT(thangnam) FROM bc_thuchienbaocao u WHERE u.thangnam  BETWEEN ?1 AND ?2 AND u.daxoa = FALSE AND u.chitieu_id IN  ( SELECT x.id FROM bc_chitieu x WHERE x.daxoa = FALSE AND x.ten=?3 AND x.chitieunam_id IN\r\n"
			+ "	(SELECT d.id FROM bc_chitieunam d WHERE d.daxoa = FALSE AND d.dmlinhvuc_id=?4 AND d.nam=?5 )) ",nativeQuery = true)
	public Long CountSoThangThucHienNamCu(LocalDate thangBatDau , LocalDate thangKetThuc, String chiTieuTen , Long LinhVucId , Integer namCu );
}
