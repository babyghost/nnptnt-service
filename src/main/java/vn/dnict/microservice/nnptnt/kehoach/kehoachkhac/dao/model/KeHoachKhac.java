package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model.DanhSachKeHoach;

@Entity
@Table(name = "modulechung_kh_kehoachkhac")
@EntityListeners(AuditingEntityListener.class)
@Data
@SqlResultSetMapping(name = "KhDanhSachKeHoach", classes = @ConstructorResult(targetClass = DanhSachKeHoach.class, columns = {
		@ColumnResult(name = "tenkehoach", type = String.class), @ColumnResult(name = "nam", type = Integer.class),
		@ColumnResult(name = "tongkinhphi", type = Double.class), @ColumnResult(name = "yeucau2donvi_id", type = Long.class),
		@ColumnResult(name = "kehoachkhac_id", type = Long.class), @ColumnResult(name = "donvilapkh_id", type = Long.class),
		@ColumnResult(name = "phongbanlapkh_id", type = Long.class), @ColumnResult(name = "phongbanthuchien_ids", type = Long[].class),
		@ColumnResult(name = "donvithuchien_ids", type = Long[].class), @ColumnResult(name = "loaikehoach", type = Integer.class),
		@ColumnResult(name = "trangthaikehoach_id", type = Long.class), @ColumnResult(name = "trangthai", type = Integer.class),
		@ColumnResult(name = "nguoinhan_ids", type = Long[].class), @ColumnResult(name = "soquyetdinh", type = String.class),
		@ColumnResult(name = "ngaybanhanh", type = LocalDate.class), @ColumnResult(name = "filedinhkem", type = String.class),
		@ColumnResult(name = "is_dieuchinh", type = Boolean.class), @ColumnResult(name = "is_banhanh", type = Boolean.class),
		@ColumnResult(name = "yeucaulapkh_id", type = Long.class), @ColumnResult(name = "slnhiemvudenhan", type = Long.class)}))
public class KeHoachKhac {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tenkehoach")
	private String tenKeHoach;

	@Column(name = "nam")
	private Integer nam;

	@Column(name = "tongkinhphi")
	private Double tongKinhPhi;

	@Column(name = "trangthai")
	private Integer trangThai;

	@Column(name = "donvilapkh_id")
	private Long donViLapKhId;

	@Column(name = "phongbanlapkh_id")
	private Long phongBanLapKhId;

	@Column(name = "is_dieuchinh")
	private Boolean isDieuChinh;

	@Column(name = "isloainhiemvu")
	private Boolean isLoaiNhiemVu;

	@Column(name = "loaikehoach")
	private Integer loaiKeHoach;

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
