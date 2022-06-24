package vn.dnict.microservice.nnptnt.vatnuoi.data;

import lombok.Data;

@Data
public class BaoCaoHoatDongChanNuoiData {
	
	private Long id;
	
	private String tenCoSo;
	
	private String tenChuCoSo;
	
	private String diaChi;
	
	private Long quanHuyenId;
	
	private String quanHuyenTen;
	
	private Long phuongXaId;
	
	private String phuongXaTen;

	private String nam;

	private Integer quy;
	
	private Long loaiVatNuoiId;
	
	private String loaiVatNuoi;
	
	private Integer soLuongNuoi;
	
	private Integer slVatNuoiXuat;
	
	private Float sanLuongXuat;
}
