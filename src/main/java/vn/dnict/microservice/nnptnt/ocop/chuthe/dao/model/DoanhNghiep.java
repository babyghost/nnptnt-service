package vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Table(name = "core_doanhnghiep")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DoanhNghiep {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ten", length = 100)
	private String ten;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "ngaycap")
	private LocalDate ngayCap;
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column(name = "quanhuyen_id")
	private Long quanHuyenId;
	
	@Column(name = "phuongxa_id")
	private Long phuongXaId;
	
	@Column(name = "dienthoai")
	private String dienThoai;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "chusohuu")
	private String chuSoHuu;
	
	@Column(name = "nguoidaidien")
	private String nguoiDaiDien;
	
	@Column(name = "loaihinh_id")
	private Long loaiHinhId;
	
	@Column(name = "loaidoanhnghiep_id")
	private Long loaiDoanhNghiepId;
	
	@Column(name = "nganhnghe_id")
	private Long nganhNgheId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "ngaythanhlap")
	private LocalDate ngayThanhLap;
	
	@Column(name = "chusohuu_diachi")
	private String chuSoHuuDiaChi;
	
	@Column(name = "chusohuu_dienthoai")
	private String chuSoHuuDienThoai;
	
	@Column(name = "chusohuu_email")
	private String chuSoHuuEmail;
	
	@Column(name = "nguoidaidien_dienthoai")
	private String nguoiDaiDienDienThoai;
	
	@Column(name = "nguoidaidien_email")
	private String nguoiDaiDienEmail;
	
	@Column(name = "giayphepkd")
	private String giayPhepKinhDoanh;
	
	
	@Column(name = "tinhthanh_id")
	private Long tinhThanhId;
	
	@Column(name = "trangthai")
	private Integer trangThai;

	@CreatedBy
	@Column(name = "nguoitao")
	private String nguoiTao;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;

	@LastModifiedBy
	@Column(name = "nguoicapnhat")
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
}
