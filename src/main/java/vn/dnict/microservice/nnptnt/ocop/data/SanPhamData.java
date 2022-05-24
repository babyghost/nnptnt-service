package vn.dnict.microservice.nnptnt.ocop.data;

import lombok.Data;

@Data
public class SanPhamData {

	private Long id;

	private String ten;

	private String moTa;
	
	private Integer trangThai;
	
	private Long doanhNghiepId;	
	
	private Long fileDinhKemId;
	
	private Long nganhHangId;
	
	private Long phanNhomId;
	
	private Boolean daXoa;
}
