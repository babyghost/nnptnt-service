package vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model;

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
@Table(name = "qlvatnuoi_hoatdongchannuoi")
@EntityListeners(AuditingEntityListener.class)
@Data
public class HoatDongChanNuoi {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
//	
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@OneToOne(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name = "loaivatnuoi_id")
	private Long loaiVatNuoiId;

	@Column(name = "donvitinh")
	private Integer donViTinh;


	@Column(name = "soluongnuoi")
	private Integer soLuongNuoi;
	
	
	@Column(name = "mucdichnuoi", length = 500)
	private String mucDichNuoi;
	
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "thoigianbatdaunuoi")
	private LocalDate thoiGianBatDauNuoi;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "thoigianxuat")
	private LocalDate thoiGianXuat;
	
	@Column(name = "slvatnuoixuat")
	private Integer slVatNuoiXuat;
	
	@Column(name = "sanluongxuat")
	private Float sanLuongXuat;


	@Column(name = "ghichu")
	private String ghiChu;

	
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@OneToOne(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name = "namchannuoi_id")
	private Long namChanNuoiId;
	
	
	
//	
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@OneToOne(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name = "cosochannuoi_id")
	private Long coSoChanNuoiId;
	
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
	private boolean daXoa;
}
