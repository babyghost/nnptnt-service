package vn.dnict.microservice.nnptnt.vatnuoi.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class HoatDongChanNuoiOutput {
	private Long id;
	
	private Long loaiVatNuoiId;
	
	private String loaiVatNuoiTen;
	
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

	private String nam;
	
	private Integer quy;
	
	private Long coSoChanNuoiId;
	
	private String coSoChanNuoiTen;
	
	@Valid
	private List<CoSoChanNuoiOutput> coSoChanNuoiOutputs = new ArrayList<CoSoChanNuoiOutput>();
}
