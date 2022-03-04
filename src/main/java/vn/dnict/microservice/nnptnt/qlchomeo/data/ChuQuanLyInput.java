package vn.dnict.microservice.nnptnt.qlchomeo.data;

import lombok.Data;

@Data
public class ChuQuanLyInput {
	private String chuHo;
	
	private String diaCHi;
	
	private Long phuongXa_Id;
	
	private Long quanHuyen_Id;
	
	private Integer dienThoai;
	
	private Boolean daXoa;
	
	
}
