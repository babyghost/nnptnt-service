package vn.dnict.microservice.nnptnt.chomeo.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChuQuanLyData {
	private Long id;
	private String chuHo;
	
	private String diaChi;
	
	private Long phuongXa_Id;
	
	private String phuongXa_Ten;
	
	private Long quanHuyen_Id;
	
	private String quanHuyen_Ten;
	
	private String dienThoai;
	private List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();

}
