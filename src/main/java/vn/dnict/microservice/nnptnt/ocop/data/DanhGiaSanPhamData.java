package vn.dnict.microservice.nnptnt.ocop.data;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class DanhGiaSanPhamData {
	private Long id;
	

	private Long sanPhamId;
	

	private Long tieuChiId;
	

	private Boolean isChamDiem;
	
	private Integer tongDiemBaPhan;

	private Long phanHangId;
	
	private String phanHangTen;
	
	private LocalDateTime ngayTao;
}
