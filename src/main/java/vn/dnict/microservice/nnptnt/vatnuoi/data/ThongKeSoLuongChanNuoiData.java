package vn.dnict.microservice.nnptnt.vatnuoi.data;

import lombok.Data;

@Data
public class ThongKeSoLuongChanNuoiData {
	private String nam;
	private  Integer quy;
	private Long loaiVatNuoiId;
	private String loaiVatNuoiTen;
	private String tongSoLuongVatNuoi;
	private String tongSlVatNuoiXuat;
	private String tongSanLuongXuat;
	
//	private List<ThongTinHoatDongChanNuoiOutput> thongTinHoatDongChanNuoiOutput;
}
