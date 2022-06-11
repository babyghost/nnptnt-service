package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class TienDoNhiemVuNamData {
	private Long id;
	
	private Long nhiemVuNamId;	
	private String nhiemVuNamTen;
	private Long nvNamNhiemVuChaId;	
	private String nvNamDonViPhoiHop;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate nvNamTuNgay;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate nvNamDenNgay;
	
	private String nvNamGhiChu;
	
	private Boolean tinhTrang;
	
	private Integer mucDoHoanThanh;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBaoCao;
	
	private String ketQua;
	
	private List<Long> fileDinhKemIds = new ArrayList<>();	
	private FileDinhKem fileDinhKem = new FileDinhKem();
}
