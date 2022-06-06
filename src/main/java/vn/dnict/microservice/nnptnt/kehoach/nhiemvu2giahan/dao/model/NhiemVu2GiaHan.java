package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.model;

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
@Table(name = "modulechung_kh_nhiemvu2giahan")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NhiemVu2GiaHan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "kehoach2nhiemvu_id")
	private Long keHoach2NhiemVuId;
	
	@Column(name = "solangiahan")
	private Integer soLanGiaHan;
	
	@Column(name = "ngayhoanthanh")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayHoanThanh;
	
	@Column(name = "nguoigiahan_id")
	private Long nguoiGiaHanId;
	
	@Column(name = "ngaythuchiengiahan")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHienGiaHan;
	
	@Column(name = "ngayhoanthanh_landau")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayHoanThanhLanDau;
	
	@Column(name = "nguoicapnhat")
	@LastModifiedBy
	private String nguoiCapNhat;
	
	@Column(name = "ngaycapnhat")
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;
	
	@Column(name = "nguoitao")
	@CreatedBy
	private String nguoiTao;
	
	@Column(name = "ngaytao")
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;
	
	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;
}
