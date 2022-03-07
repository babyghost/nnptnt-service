package vn.dnict.microservice.nnptnt.vatnuoi.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class HoatDongChanNuoiInput {

//	@NotNull(message = "Vui lòng chon Loai Vat Nuoi")
	private Long loaiVatNuoiId;
	
	
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
	
	private Long coSoChanNuoiId;

	private Long namChanNuoiId;
}
