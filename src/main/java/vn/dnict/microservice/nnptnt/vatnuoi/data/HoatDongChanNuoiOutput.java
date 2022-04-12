package vn.dnict.microservice.nnptnt.vatnuoi.data;

import java.util.List;

import lombok.Data;

@Data
public class HoatDongChanNuoiOutput {
	
	private Long coSoChanNuoiId;
	
	private String coSoTen;
	
	private String tenChuCoSo;
	
	private String dienThoai;
	
	private String email;
	
	private String diaChi;
	
	private Long quanHuyenId;
	
	private String quanHuyenTen;
	
	private Long phuongXaId;
	
	private String phuongXaTen;
	
	private String nam;
	
	private Integer quy;
	
	private List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuoi;
}
