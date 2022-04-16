package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class KeHoachTiemPhongData {
	private Long id;
	private String tenKeHoach;
	private String soKeHoach;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;
	private String noiDung;	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayDuKienTuNgay;	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayDuKienDenNgay;
	List<ThoiGianTiemPhongData> thoiGianTiemPhongDatas;	
	private List<Long> fileDinhKemIds = new ArrayList<>();	
	private FileDinhKem fileDinhKem = new FileDinhKem();
	
	
}
