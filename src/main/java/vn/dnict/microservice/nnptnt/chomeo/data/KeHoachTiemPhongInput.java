package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class KeHoachTiemPhongInput {
	
	private Long id;

	private String tenKeHoach;

	private String soKeHoach;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate ngayBanHanh;
	
	private String noiDung;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate ngayDuKienTuNgay;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate ngayDuKienDenNgay;
	

	
}