package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model;

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
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;

@Entity
@Table(name = "qlhd_thongtinhopdong")
@EntityListeners(AuditingEntityListener.class)
@Data
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "BaoCaoThongKe", classes = @ConstructorResult(targetClass = BaoCaoThongKe.class, columns = {
				@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "tenhopdong", type = String.class),
				@ColumnResult(name = "loaihopdong_id", type = Long.class),
				@ColumnResult(name = "tenloaihopdong", type = String.class),
				@ColumnResult(name = "thanhtoan_dot", type = Integer.class),
				@ColumnResult(name = "thanhtoan_ngay", type = LocalDate.class),
				@ColumnResult(name = "thanhtoan_giatri", type = Double.class),
				@ColumnResult(name = "thanhtoan_sochungtu", type = String.class),
				@ColumnResult(name = "hoadon_so", type = String.class),
				@ColumnResult(name = "hoadon_ngay", type = LocalDate.class),
				@ColumnResult(name = "dvth_ten", type = String.class),
				@ColumnResult(name = "cnth_ten", type = String.class),
				@ColumnResult(name = "trangthai", type = Integer.class),
				@ColumnResult(name = "tentrangthai", type = String.class) })), })
public class ThongTinHopDong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 500, nullable = false)
	private String ten;

	@Column(name = "sohieu", length = 20, nullable = false)
	private String soHieu;

	@Column(name = "ngayky", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayKy;

	@Column(name = "giatri")
	private Double giaTri;

	@Column(name = "giatriconlai")
	private Double giaTriConLai;

	@Column(name = "loaihopdong_id", nullable = false)
	private Long loaiHopDongId;

	@Column(name = "dvth_ten", length = 250, nullable = false)
	private String dvthTen;

	@Column(name = "dvth_masothue", length = 20)
	private String dvthMaSoThue;

	@Column(name = "dvth_diachi", length = 1000)
	private String dvthDiaChi;

	@Column(name = "phongbantheodoi_id", nullable = false)
	private Long phongBanTheoDoiId;

	@Column(name = "cnth_ten", length = 150, nullable = false)
	private String cnthTen;

	@Column(name = "cnth_masothue", length = 20)
	private String cnthMaSoThue;

	@Column(name = "cnth_diachi", length = 1000)
	private String cnthDiaChi;

	@Column(name = "thoigianth_tungay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThTuNgay;

	@Column(name = "thoigianth_denngay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThDenNgay;

	@Column(name = "thoigianbh_tungay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianBhTuNgay;

	@Column(name = "thoigianbh_denngay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianBhDenNgay;

	@Column(name = "nguoiky_id")
	private Long nguoiKyId;

	@Column(name = "giayuyquyen_so", length = 30)
	private String giayUyQuyenSo;

	@Column(name = "giayuyquyen_ngay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate giayUyQuyenNgay;

	@Column(name = "giayuyquyen_filedinhkemid")
	private Long giayUyQuyenFileDinhKemId;

	@Column(name = "hopdong_filedinhkemid")
	private Long hopDongFileDinhKemId;

	@Column(name = "baolanhthuchien_id", nullable = false)
	private Long baoLanhThucHienId;

	@Column(name = "baolanhthuchien_giatri", nullable = false)
	private Double baoLanhThucHienGiaTri;

	@Column(name = "baolanhthuchien_tungay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhThucHienTuNgay;

	@Column(name = "baolanhthuchien_denngay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhThucHienDenNgay;

	@Column(name = "baolanhbaohanh_id", nullable = false)
	private Long baoLanhBaoHanhId;

	@Column(name = "baolanhbaohanh_giatri", nullable = false)
	private Double baoLanhBaoHanhGiaTri;

	@Column(name = "baolanhbaohanh_tungay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhBaoHanhTuNgay;

	@Column(name = "baolanhbaohanh_denngay", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhBaoHanhDenNgay;

	@Column(name = "baolanhbaohanh_phuongthuchoantra", length = 300)
	private String baoLanhBaoHanhPhuongThucHoanTra;

	@Column(name = "baolanhbaohanh_nguoinhan", length = 150)
	private String baoLanhBaoHanhNguoiNhan;

	@Column(name = "cocamketchi")
	private Boolean coCamKetChi;

	@Column(name = "cocamketchi_so", length = 30)
	private String coCamKetChiSo;

	@Column(name = "cocamketchi_filedinhkemid")
	private Long coCamKetChiFileDinhKemId;

	@Column(name = "cocamket_ngay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate coCamKetNgay;

	@Column(name = "trangthai", nullable = false)
	private Integer trangThai;

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
