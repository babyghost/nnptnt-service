package vn.dnict.microservice.nnptnt.chomeo.data;

import lombok.Data;

@Data
public class ChuQuanLyInput {
	private String chuHo;
	
	private String diaCHi;
	
	private Long phuongXa_Id;
	
	private Long quanHuyen_Id;
	
	private String dienThoai;
	
	private Boolean daXoa;
	
	
}
