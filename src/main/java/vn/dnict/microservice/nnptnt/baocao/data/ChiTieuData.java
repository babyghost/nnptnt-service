package vn.dnict.microservice.nnptnt.baocao.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class ChiTieuData {

	private Long id;
	
	private String ten;
	
	private String donViTinh;
	
	private Boolean isInNghieng;

	private Boolean isInDam;

	private Boolean isNhapGiaTri;

	private Boolean isTinhTong;

	private Integer sapXep;
	
	private Integer kieuDanhSo;
	
	private Long chaId;
	
	private Long chiTieuNamId;
	
	private Integer trangThai;
	
	@Valid
	private List<ChiTieuData> children = new ArrayList<ChiTieuData>();
}
