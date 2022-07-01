package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
import lombok.Getter;
import lombok.Setter;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

@Entity
@Table(name = "qlkh_nhiemvuthang")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class NhiemVuThang {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_nhiemvuthang_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_nhiemvuthang_seq", sequenceName = "qlkh_nhiemvuthang_id_seq", allocationSize = 1)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kehoachthang_id", insertable = false, updatable = false)
	private KeHoachThang keHoachThang;

	@Column(name = "kehoachthang_id", nullable = false)
	private Long keHoachThangId;

	@Column(name = "tennhiemvu", length = 1000, nullable = false)
	private String tenNhiemVu;

	@Column(name = "canbothuchien_id", nullable = false)
	private Long canBoThucHienId;

	@Column(name = "tencanbo", length = 250, nullable = false)
	private String tenCanBo;

	@Column(name = "donviphoihop", length = 1000)
	private String donViPhoiHop;

	@Column(name = "thoigian")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGian;

	@Column(name = "ghichu", length = 1000)
	private String ghiChu;

	@Column(name = "is_nhiemvuthangtruoc")
	private Boolean isNhiemVuThangTruoc;

	@Column(name = "nhiemvuthangtruoc_id")
	private Long nhiemVuThangTruocId;

	@Column(name = "tinhtrang")
	private Integer tinhTrang;

	@Column(name = "tiendonhiemvu_id")
	private Long tienDoNhiemVuId;

	@Column(name = "danhso")
	private Integer danhSo;
	
	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
	@Column(name = "nguoitao")
	@CreatedBy
	private String nguoiTao;
	
	@Column(name = "ngaytao")
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;
	
	@Column(name = "nguoicapnhat")
	@LastModifiedBy
	private String nguoiCapNhat;
	
	@Column(name = "ngaycapnhat")
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;
}
