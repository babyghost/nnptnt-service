package vn.dnict.microservice.nnptnt.hopdong.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.dao.model.FileList;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class ThongTinHopDongInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 500, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập số hiệu")
	@Size(max = 20, message = "Nhập số hiệu quá {max} ký tự")
	private String soHieu;

	@NotNull(message = "Vui lòng chọn ngày ký")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayKy;

	private Double giaTri;

	private Double giaTriConLai;

	@NotNull(message = "Vui lòng chọn loại hợp đồng")
	private Long loaiHopDongId;

	// @NotBlank(message="Vui lòng nhập tên đơn vị thực hiện")
	@Size(max = 250, message = "Nhập tên đơn vị thực hiện quá {max} ký tự")
	private String dvthTen;

	@Size(max = 20, message = "Nhập mã số thuế đơn vị thực hiện quá {max} ký tự")
	private String dvthMaSoThue;

	@Size(max = 1000, message = "Nhập địa chỉ đơn vị thực hiện quá {max} ký tự")
	private String dvthDiaChi;

	@NotNull(message = "Vui lòng chọn phòng ban theo dõi")
	private Long phongBanTheoDoiId;

	// @NotBlank(message="Vui lòng nhập tên cá nhân thực hiện")
	@Size(max = 150, message = "Nhập tên cá nhân thực hiện quá {max} ký tự")
	private String cnthTen;

	@Size(max = 20, message = "Nhập mã số thuế cá nhân thực hiện quá {max} ký tự")
	private String cnthMaSoThue;

	@Size(max = 1000, message = "Nhập địa chỉ cá nhân thực hiện quá {max} ký tự")
	private String cnthDiaChi;

	// @NotNull(message = "Vui lòng nhập thời gian thực hiện từ ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThTuNgay;

	// @NotNull(message = "Vui lòng nhập thời gian thực hiện đến ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianThDenNgay;

	// @NotNull(message = "Vui lòng nhập thời gian bảo hành từ ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianBhTuNgay;

	// @NotNull(message = "Vui lòng nhập thời gian bảo hành đến ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianBhDenNgay;

	private Long nguoiKyId;

	@Size(max = 20, message = "Nhập số giấy ủy quyền quá {max} ký tự")
	private String giayUyQuyenSo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate giayUyQuyenNgay;

//	@NotNull(message = "Vui lòng chọn file đính kèm giấy ủy quyền")
	private List<Long> listGiayUyQuyenFileDinhKemId;
	private List<FileList> listGiayUyQuyenFileDinhKem;

	@NotNull(message = "Vui lòng chọn file đính kèm hợp đồng")
	private List<Long> listHopDongFileDinhKemId;
	private List<FileList> listHopDongFileDinhKem;

	// @NotNull(message = "Vui lòng chọn phương thức bảo lãnh")
	private Long baoLanhThucHienId;

	// @NotNull(message = "Vui lòng nhập giá trị bảo lãnh")
	private Double baoLanhThucHienGiaTri;

	// @NotNull(message = "Vui lòng nhập thời gian bảo lãnh từ ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhThucHienTuNgay;

	// @NotNull(message = "Vui lòng nhập thời gian bảo lãnh đến ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhThucHienDenNgay;

	// @NotNull(message = "Vui lòng chọn phương thức bảo hành")
	private Long baoLanhBaoHanhId;

	// @NotNull(message = "Vui lòng nhập giá trị bảo hành")
	private Double baoLanhBaoHanhGiaTri;

	// @NotNull(message = "Vui lòng nhập thời gian bảo hành từ ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhBaoHanhTuNgay;

	// @NotNull(message = "Vui lòng nhập thời gian bảo hành đến ngày")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate baoLanhBaoHanhDenNgay;

	@Size(max = 300, message = "Nhập phương thức hoàn trả quá {max} ký tự")
	private String baoLanhBaoHanhPhuongThucHoanTra;

	@Size(max = 150, message = "Nhập người nhận quá {max} ký tự")
	private String baoLanhBaoHanhNguoiNhan;

	@NotNull(message = "Có cam kết chi không để trống")
	private Boolean coCamKetChi;

	@Size(max = 30, message = "Nhập số cam kết quá {max} ký tự")
	private String coCamKetChiSo;

	// @NotNull(message = "Vui lòng chọn file đính kèm cam kết chi")
	private List<Long> listCoCamKetChiFileDinhKemId;
	private List<FileList> listCoCamKetChiFileDinhKem;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate coCamKetNgay;

	// @NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

}
