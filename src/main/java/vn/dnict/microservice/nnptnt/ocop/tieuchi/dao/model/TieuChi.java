package vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;
@Entity
@Table(name = "ocop_tieuchi")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TieuChi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 500, nullable = false)
	private String ten;

	@Column(name = "diem", length = 50)
	private String diem;

	@Column(name = "cha_id")
	private Long chaId;
	
	@Column(name = "tieuchinam_id")
	private Long tieuChiNamId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tieuchinam_id", insertable=false, updatable=false)
	private TieuChiNam tieuChiNam;

	@Column(name = "phantieuchi_id")
	private Long phanTieuChiId;



	@Column(name = "is_tinhdiem")
	private Boolean isTinhDiem;


	@Column(name = "is_tinhtong")
	private Boolean isTinhTong;


	@Column(name = "danhso")
	private Integer kieuDanhSo;
	
	

	@Column(name = "nam")
	private Integer nam;


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
