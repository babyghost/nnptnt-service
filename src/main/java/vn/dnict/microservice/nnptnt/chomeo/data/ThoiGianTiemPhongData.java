package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ThoiGianTiemPhongData {
	
	private Long id;
	
	private String diaDiem;
	
	private Long phuongXaId;
	
	private String phuongXaTen;

	private String quanHuyenTen;
	
	private Long quanHuyenId;
	
	private Long keHoachTiemPhongId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianTiemTuNgay;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianTiemDenNgay;
	
}	
