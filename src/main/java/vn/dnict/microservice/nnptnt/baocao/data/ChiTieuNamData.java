package vn.dnict.microservice.nnptnt.baocao.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
@Data
public class ChiTieuNamData {
	private Long id;


	private Long linhVucId;
	
	private String linhVucTen;
	
	private Integer nam;
	
	
	private Integer trangThai;
	
	@Valid
	private List<ChiTieuData> chiTieuDatas = new ArrayList<ChiTieuData>();
	
}
