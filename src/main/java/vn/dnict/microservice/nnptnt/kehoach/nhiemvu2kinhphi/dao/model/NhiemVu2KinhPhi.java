package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model;

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
@Table(name = "modulechung_kh_kehoach2kinhphi")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NhiemVu2KinhPhi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "kehoach2nhiemvu_id")
	private Long keHoach2NhiemVuId;
	
	@Column(name = "dmnguonkinhphi_id")
	private Long dmNguonKinhPhiId;
	
	@Column(name = "sotien")
	private Double soTien;
	
	@Column(name = "sotiendieuchinh")
	private Double soTienDieuChinh;
	
	@Column(name = "tanggiamkinhphi", length = 255)
	private String tangGiamKinhPhi;
	
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
