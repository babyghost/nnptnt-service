package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model;

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

import lombok.Data;

@Entity
@Table(name = "qlhd_tinhhinhthuchienhopdong")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TinhHinhThucHienHopDong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "hopdong_id", nullable = false)
	private Long hopDongId;
	
	@Column(name = "thanhtoan_dot", nullable = false)
	private Integer thanhToanDot;

	@Column(name = "thanhtoan_noidung", length = 1000)
	private String thanhToanNoiDung;
	
	@Column(name = "thanhtoan_giatri", nullable = false)
	private Double thanhToanGiaTri;

	@Column(name = "thanhtoan_sochungtu", length = 20, nullable = false)
	private String thanhToanSoChungTu;
	
	@Column(name = "thanhtoan_ngay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thanhToanNgay;
	
	@Column(name = "thanhtoan_filedinhkemid")
	private Long thanhToanFileDinhKemId;
	
	@Column(name = "hoadon_so", length = 20, nullable = false)
	private String hoaDonSo;
	
	@Column(name = "hoadon_filedinhkemid", nullable = false)
	private Long hoaDonFileDinhKemId;
	
	@Column(name = "hoadon_ngay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate hoaDonNgay;
	
	@Column(name = "giatriconlai")
	private Double giaTriConLai;
	
	@Column(name = "lydo", length = 1000)
	private String lyDo;

	@Column(name = "nguoicapnhat", length = 150, nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", length = 150, nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "daxoa", nullable = false)
	private Boolean daXoa;
}
