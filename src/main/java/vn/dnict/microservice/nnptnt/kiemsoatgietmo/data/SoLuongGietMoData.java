package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import lombok.Data;

@Data
public class SoLuongGietMoData {
	private Long id;
	
	private String nguonGoc;
	
	private Long loaiVatNuoiId;
	private String loaiVatNuoiTen;
	
	private Integer soLuongGietMo;
	
	private String ghiChu;
	
	private Long thongTinGietMoId;
}
