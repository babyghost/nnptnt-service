package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThongKeSoLuongData {
	
//	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThang;
	
	private List<ThongTinGietMoData> thongTinDatas = new ArrayList<ThongTinGietMoData>();
	
	private List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<ThongKeSoLuongData>();
}
