package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThongKeSoLuongData {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThang;
	
	private Long coSoGietMoId;
	private String coSoTen;
	
	private Long loaiVatNuoiId;
	private String loaiVatNuoiTen;
	
	private Integer soLuongGietMo;
	
	private List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<ThongKeSoLuongData>();
}
