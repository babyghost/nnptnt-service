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
public class TinhHinhThucHienHopDongInput {

	private Long id;

	@NotNull(message = "Dữ liệu hợp đồng không tồn tại")
	private Long hopDongId;

	@NotNull(message = "Vui lòng nhập đợt thanh toán")
	private Integer thanhToanDot;

	@Size(max = 1000, message = "Nhập nội dung thanh toán quá {max} ký tự")
	private String thanhToanNoiDung;

	@NotNull(message = "Vui lòng nhập giá trị thanh toán")
	private Double thanhToanGiaTri;

	@NotBlank(message = "Vui lòng nhập số chứng từ thanh toán")
	@Size(max = 20, message = "Nhập số chứng từ thanh toán quá {max} ký tự")
	private String thanhToanSoChungTu;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thanhToanNgay;

	@NotNull(message = "Vui lòng chọn file đính kèm thanh toán")
	private List<Long> listThanhToanFileDinhKemId;
	private List<FileList> listThanhToanFileDinhKem;

	@NotBlank(message = "Vui lòng nhập số hóa đơn")
	@Size(max = 20, message = "Nhập số hóa đơn quá {max} ký tự")
	private String hoaDonSo;

	private List<Long> listHoaDonFileDinhKemId;
	private List<FileList> listHoaDonFileDinhKem;

	@NotNull(message = "Vui lòng nhập ngày hóa đơn")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate hoaDonNgay;

	private Double giaTriConLai;

	@Size(max = 1000, message = "Nhập lý do quá {max} ký tự")
	private String lyDo;

	private List<Long> fileDinhKemIds = new ArrayList<>();	
	private FileDinhKem fileDinhKem = new FileDinhKem();
}
