package vn.dnict.microservice.nnptnt.ocop.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
@Data
public class TieuChiNamData {
	private Long id;

	private Long phanNhomId;
	
	private String phanNhomTen;

	private Long nganhHangId;
	
	private String nganhHangTen;

	private Long nhomId;
	
	private String nhomTen;

	private Integer nam;
		
	private Integer trangThai;
	
	@Valid
	private List<TieuChiData> tieuChiDatas = new ArrayList<TieuChiData>();
}
