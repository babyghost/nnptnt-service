package vn.dnict.microservice.danhmuc.dao.model;

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
@Table(name = "danhmuc_donvi")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmDonVi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "donvi_cha_id", nullable = false)
	private Long donViChaId;

	@Column(name = "cap_id")
	private Long capDonViId;

	@Column(name = "loaidonvi_id")
	private Long loaiDonViId;

	@Column(name = "tendonvi", length = 100, nullable = false)
	private String tenDonVi;

	@Column(name = "tenviettat", length = 20)
	private String tenVietTat;

	@Column(name = "diachi", length = 100)
	private String diaChi;

	@Column(name = "sodienthoai", length = 50)
	private String soDienThoai;

	@Column(name = "fax", length = 50)
	private String fax;

	@Column(name = "email", length = 50)
	private String email;

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

	@Column(name = "daxoa", nullable = false)
	@JsonIgnore
	private Boolean daXoa;

	@Column(name = "appcode", length = 255)
	private String appCode;

}
