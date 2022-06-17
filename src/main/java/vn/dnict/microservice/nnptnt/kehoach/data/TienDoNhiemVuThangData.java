package vn.dnict.microservice.nnptnt.kehoach.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class TienDoNhiemVuThangData {
	private Long id;
	
	private Integer mucDoHoanThanh;
	
	private String ketQua;
	
	private String tenNguoiCapNhat;
	
	private List<Long> fileDinhKemIds = new ArrayList<>();	
	private FileDinhKem fileDinhKem = new FileDinhKem();
}
