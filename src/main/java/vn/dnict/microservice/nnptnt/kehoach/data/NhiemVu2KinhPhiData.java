package vn.dnict.microservice.nnptnt.kehoach.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NhiemVu2KinhPhiData {
	
	private Long id;
	
	private Long keHoach2NhiemVuId;
	
	private Long dmNguonKinhPhiId;
	
	private String dmNguonKinhPhiTen;
	
	private String soTien;
	
	private String soTienDieuChinh;
	
	private String tangGiamKinhPhi;
	
	private List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = new ArrayList<>();
	
}
