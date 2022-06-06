package vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.model.NhiemVu2DonViPhoiHop;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model.NhiemVu2KinhPhi;

@Entity
@Table(name = "modulechung_kh_kehoach2nhiemvu")
@EntityListeners(AuditingEntityListener.class)
@Data
public class KeHoach2NhiemVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "kehoachkhac_id")
	private Long keHoachKhacId;

	@Column(name = "nhiemvucha_id")
	private Long nhiemVuChaId;

	@Column(name = "dmloainhiemvu_id")
	private Long dmLoaiNhiemVuId;

	@Column(name = "noidung")
	private String noiDung;

	@Column(name = "thoigianthuchien_tungay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThucHienTuNgay;

	@Column(name = "thoigianthuchien_denngay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThucHienDenNgay;

	@Column(name = "thoigianthanhtoan")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThanhToan;

	@Column(name = "ghichu")
	private String ghiChu;

	@Column(name = "donvithuchien_id")
	private Long donViThucHienId;

	@Column(name = "phongbanthuchien_id")
	private Long phongBanThucHienId;

	@Column(name = "is_dieuchinh")
	private Boolean isDieuChinh;

	@Column(name = "is_banhanh")
	private Boolean isBanHanh;

	@Column(name = "is_themmoithuchien")
	private Boolean isThemMoiThucHien;

	@Column(name = "sapxep")
	private Integer sapXep;

	@Column(name = "is_dagui")
	private Boolean isDaGui;

	@Column(name = "tinhtrang")
	private Integer tinhTrang;

	@Column(name = "lanhdao_id")
	private Long lanhDaoId;

	@Column(name = "sanphamdaura")
	private String sanPhamDauRa;

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

	@JsonIgnore
	@OneToMany(mappedBy = "keHoach2NhiemVuId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Where(clause = "daxoa = false")
	private Set<NhiemVu2KinhPhi> nhiemVu2KinhPhis;

	@JsonIgnore
	@OneToMany(mappedBy = "keHoach2NhiemVuId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Where(clause = "daxoa = false")
	private Set<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops;
}
