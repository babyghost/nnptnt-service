package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class KeHoach2NhiemVuData {

	private Long id;

	private String stt;

	private Long keHoachKhacId;

	private Long nhiemVuChaId;

	private Long dmLoaiNhiemVuId;

	private String dmLoaiNhiemVuTen;

	private String noiDung;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThucHienTuNgay;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThucHienDenNgay;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThanhToan;

	private String ghiChu;

	private Long donViThucHienId;

	private String donViThucHienTen;

	private Long phongBanThucHienId;

	private String phongBanThucHienTen;

	private Boolean isDieuChinh;

	private Boolean isBanHanh;

	private Boolean isThemMoiThucHien;

	private Integer sapXep;

	private Boolean isDaGui;

	private List<Long> donViPhoiHopIds = new ArrayList<>();

	private List<String> donViPhoiHopTens = new ArrayList<>();

	private List<Long> phongBanPhoiHopIds = new ArrayList<>();

	private List<String> phongBanPhoiHopTens = new ArrayList<>();

	private Integer tinhTrang;

	private Long lanhDaoId;

	private String lanhDaoTen;

	private String sanPhamDauRa;

	private List<Long> fileDinhKemIds = new ArrayList<>();

	private FileDinhKem fileDinhKem = new FileDinhKem();

	@Valid
	private List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiDatas = new ArrayList<>();

	private List<NhiemVu2GiaHanData> nhiemVu2GiaHanDatas = new ArrayList<>();

	private List<NhiemVu2ThongTinThucHienData> nhiemVu2ThongTinThucHienDatas = new ArrayList<>();

	private List<KeHoach2NhiemVuData> children = new ArrayList<>();

}
