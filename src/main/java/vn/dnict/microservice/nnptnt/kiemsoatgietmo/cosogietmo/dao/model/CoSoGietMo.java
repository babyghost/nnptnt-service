package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model;

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

import lombok.Data;

@Entity
@Table(name = "qlgietmo_cosogietmo")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoSoGietMo {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tencoso", length = 250)
	private String tenCoSo;
	
	@Column(name = "tenchucoso", length = 250)
	private String tenChuCoSo;
	
	@Column(name = "diachi", length = 250)
	private String diaChi;
	
	@Column(name = "dienthoai", length = 50)
	private String dienThoai;
	
	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phuongxa_id")
	private Long phuongXaId;
	
	@Column(name = "quanhuyen_id")
	private Long quanHuyenId;

	@Column(name = "ghichu")
	private String ghiChu;

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
	
	@Column(name = "giaykinhdoanh", length = 50)
	private String giayKinhDoanh;
	
	@Column(name = "daxoa")
	private boolean daXoa;
}
