package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NhiemVu2GiaHanData {

	private Long id;

	private Long keHoach2NhiemVuId;

	private Integer soLanGiaHan;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayHoanThanh;

	private Long nguoiGiaHanId;

	private String nguoiGiaHanTen;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHienGiaHan;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayHoanThanhLanDau;

}
