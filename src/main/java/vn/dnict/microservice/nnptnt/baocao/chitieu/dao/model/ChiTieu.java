package vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model;

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
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;

@Entity
@Table(name = "bc_chitieu")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ChiTieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 500, nullable = false)
	private String ten;

	@Column(name = "donvitinh", length = 50)
	private String donViTinh;

	@Column(name = "cha_id")
	private Long chaId;
	
	@Column(name = "chitieunam_id")
	private Long chiTieuNamId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chitieunam_id", insertable=false, updatable=false)
	private ChiTieuNam chiTieuNam;

	@Column(name = "is_innghieng")
	private Boolean isInNghieng;

	@Column(name = "is_indam")
	private Boolean isInDam;

	@Column(name = "is_nhapgiatri")
	private Boolean isNhapGiaTri;

	@Column(name = "is_tinhtong")
	private Boolean isTinhTong;

	@Column(name = "sapxep")
	private Integer sapXep;

	@Column(name = "kieudanhso")
	private Integer kieuDanhSo;

	@Column(name = "trangthai")
	private Integer trangThai;

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
