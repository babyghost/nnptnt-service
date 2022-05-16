package vn.dnict.microservice.nnptnt.baocao.data;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThucHienBaoCaoData {
	private Long id;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangNam;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHien;
	
	private Long chiTieuId;
	
	private float thucHien;
	
	private Long linhVucId;
	
	private String linhVucTen;
	
	private Float tongThucHienCu;
	
	private Float keHoach;
	
}

