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

import lombok.Data;
@Entity
@Table(name = "danhmuc_thutucgiayphep")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmThuTucGiayPhep {
/*
 *
 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable=false)
	private Long id;
	@Column(name = "ma", nullable=false)
	private String ma;
	@Column(name = "ten", nullable=false)
	private String ten;	
	@Column(name = "urlform",length=500)
	private String urlForm;	
	
	@Column(name = "daxoa", nullable=false)
	private Integer daXoa;

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
}
