package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model;

import java.time.LocalDate;
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
import javax.persistence.SequenceGenerator;
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
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;

@Entity 
@Table(name = "qlvatnuoi_hoatdongchannuoi")
@EntityListeners(AuditingEntityListener.class)
@Data
public class HoatDongChanNuoi {
	@Id
	@Column(name = "id", unique = true, nullable = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "qlvatnuoi_hoatdongchannuoi_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlvatnuoi_hoatdongchannuoi_seq", sequenceName = "qlvatnuoi_hoatdongchannuoi_id_seq", allocationSize = 1)
	private Long id;

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

	@Column(name = "cosochannuoi_id") 
	private Long coSoChanNuoiId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "cosochannuoi_id", insertable=false, updatable=false)	
	@Where(clause = "daxoa = false")
	private CoSoChanNuoi coSoChanNuoi;
	
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
	
	@Column(name = "nam")
	private String nam;
	
	@Column(name = "quy")
	private Integer quy;
	
}
