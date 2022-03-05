package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class KeHoachTiemPhongInput {

	private String tenKeHoach;

	private String soKeHoach;

	private LocalDateTime ngayBanHanh;
	
	private String noiDung;

	private LocalDateTime ngayDuKienTuNgay;
	
	private LocalDateTime ngayDuKienDenNgay;
	

	
}