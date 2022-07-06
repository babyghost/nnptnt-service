package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

@Entity
@Table(name = "qlkh_tiendonhiemvunam")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class TienDoNhiemVuNam {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_tiendonhiemvunam_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_tiendonhiemvunam_seq", sequenceName = "qlkh_tiendonhiemvunam_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "nhiemvunam_id", nullable = false)
	private Long nhiemVuNamId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nhiemvunam_id", referencedColumnName = "id", updatable = false, insertable = false)
	private NhiemVuNam nhiemVuNam;

	@Column(name = "tinhtrang", nullable = false)
	private Integer tinhTrang;

	@Column(name = "mucdohoanthanh", nullable = false)
	private Integer mucDoHoanThanh;

	@Column(name = "ngaybaocao")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBaoCao;

	@Column(name = "ketqua", length = 4000)
	private String ketQua;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
	@CreatedBy
	@Column(name = "nguoitao", length = 250)
	private String nguoiTao;
	
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;
	
	@Column(name = "nguoicapnhat", length = 250)
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;
}