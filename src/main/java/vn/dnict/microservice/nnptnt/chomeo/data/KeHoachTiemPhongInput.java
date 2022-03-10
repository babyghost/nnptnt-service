package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;

import lombok.Data;
@Data
public class KeHoachTiemPhongInput {

	private String tenKeHoach;

	private String soKeHoach;

	private LocalDate ngayBanHanh;
	
	private String noiDung;

	private LocalDate ngayDuKienTuNgay;
	
	private LocalDate ngayDuKienDenNgay;
	

	
}