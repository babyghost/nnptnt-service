package vn.dnict.microservice.nnptnt.vatnuoi.data;

import java.time.LocalDate; 

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThongTinHoatDongChanNuoiOutput {
	private Long id; 
	
	private String nam;
	
	private Integer quy;
	
	private Long loaiVatNuoiId;
	
	private String loaiVatNuoi;
	
	private Integer donViTinh;
	
	private Integer soLuongNuoi;
		
	private String mucDichNuoi;
	
	private Float sanLuongXuat;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianBatDauNuoi;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGianXuat;

	private Integer slVatNuoiXuat;
	
	private String ghiChu;
}
