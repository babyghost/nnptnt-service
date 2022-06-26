package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class TienDoNhiemVuThangData {
	private Long id;
	
	private Integer mucDoHoanThanh;
	
	private String ketQua;
	
	private String tenNguoiCapNhat;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayCapNhat;
	
	private List<Long> fileDinhKemIds = new ArrayList<>();
	
	private FileDinhKem fileDinhKem = new FileDinhKem();
}
