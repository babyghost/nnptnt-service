package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NhiemVuThangLogData {
	private Long id;

	private Long nhiemVuThangId;

	@Size(max = 1000, message = "Nhập tên nhiệm vụ quá {max} ký tự")
	private String tenNhiemVu;

	private Long canBoThucHienId;

	private String canBoThucHienTen;

	@Size(max = 250, message = "Nhập tên người cập nhật quá {max} ký tự")
	private String tenNguoiCapNhat;

	private Integer tinhTrang;

	private Integer mucDoHoanThanh;

	@Size(max = 4000, message = "Nhập kết quả quá {max} ký tự")
	private String ketQua;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayCapNhat;
}
