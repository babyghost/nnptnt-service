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
@Table(name = "danhmuc_quanhuyen")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmQuanHuyen {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ten", length = 100)
	private String ten;

	@Column(name = "ma", length = 50)
	private String ma;

	@Column(name = "tinhthanh_code", length = 50)
	private String tinhThanhCode;

	@Column(name = "trangthai")
	private Boolean trangThai;

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

//	@JsonIgnore
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "qlhoso_congtrinh_dmduan2diadiem", joinColumns = @JoinColumn(name = "quanhuyen_id"), inverseJoinColumns = @JoinColumn(name = "duan_id"))
//	@WhereJoinTable(clause = "daxoa=false")
//	private List<QlHoSoCongTrinhDmDuAn> qlHoSoCongTrinhDmDuAns = new ArrayList<>();
//	
//	@JsonIgnore
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "kchtgt_cau_diadanhhanhchinh", joinColumns = @JoinColumn(name = "quanhuyen_id"), inverseJoinColumns = @JoinColumn(name = "thongtincau_id"))
//	@WhereJoinTable(clause = "daxoa=false")
//	private List<KchtgtCauDiaDanhHanhChinh> kchtgtCauDiaDanhHanhChinhs = new ArrayList<>();
}
