package vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
@Entity
@Table(name = "ocop_sanpham")
@EntityListeners(AuditingEntityListener.class)
@Data
public class SanPham {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ten", length = 100)
	private String ten;

	@Column(name = "mota", length = 100)
	private String moTa;
	
	@Column(name = "trangthai")
	private Integer trangThai;
	
	@Column(name = "doanhnghiep_id")
	private Long doanhNghiepId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "doanhnghiep_id", insertable=false, updatable=false)	
	@Where(clause = "daxoa = false")
	private DoanhNghiep doanhNghiep;
	
	
	@Column(name = "filedinhkem_id")
	private Long fileDinhKemId;
	
	@Column(name = "nganhhang_id")
	private Long nganhHangId;
	
	@Column(name = "phannhom_id")
	private Long phanNhomId;
	
	@Column(name = "soquyetdinh")
	private String quyetDinh;
	
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
