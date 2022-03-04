package vn.dnict.microservice.danhmuc.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "danhmuc_canbo")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmCanBo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "hoten", length = 150, nullable = false)
	private String hoTen;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "phongban_id")
	private DmPhongBan phongBan;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "donvi_id")
	private DmDonVi donVi;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "gioitinh_id")
	private DmGioiTinh gioiTinh;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "chucvu_id")
	private DmChucVu chucVu;

	@Column(name = "email", length = 150, nullable = false, unique = true)
	private String email;

	@Column(name = "ngaysinh", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngaySinh;

	@Column(name = "diachi", length = 255)
	private String diaChi;

	@Column(name = "dienthoai", length = 50)
	private String dienThoai;

	@Column(name = "fax", length = 50)
	private String fax;

	@Column(name = "cmnd_so", length = 50, nullable = false, unique = true)
	private String cmndSo;

	@Column(name = "cmnd_ngaycap", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate cmndNgayCap;

	@Column(name = "cmnd_noicap", length = 255)
	private String cmndNoiCap;

	@Column(name = "nguoicapnhat", nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", length = 150, nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", length = 150, nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "daxoa")
	@JsonIgnore
	private Integer daXoa;

}
