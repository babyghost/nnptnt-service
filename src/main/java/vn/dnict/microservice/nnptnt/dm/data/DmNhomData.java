package vn.dnict.microservice.nnptnt.dm.data;

import lombok.Data;

@Data	
public class DmNhomData {

	private Long id;
	
	private String ten;
	
	private String moTa;
	
	private Boolean trangThai;
	
	private Long dmNganhHangId;
	
	private String dmNganhHangTen;
	
}
