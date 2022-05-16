package vn.dnict.microservice.nnptnt.baocao.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThongKeData {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangNam;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHien;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangBatDau;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangKetThuc;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangKetThucTN;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangBatDauTN;
	
	private Long chiTieuId;
	
	private String chiTieuTen;
	
	private String donViTinh;
	
	private float thucHien;
	
	private Long linhVucId;
	
	private String linhVucTen;
	
	private Integer nam;
	
	private Float tongThucHienCu;
	
	private Float tongThucHienTN;
	
	private Long countThang;
	
	private Long countThangTN;
	
	private Float keHoachNam;
	
	private Float uocThang;
	
	private Float cungKy;
	
	private Float keHoach;
	
	private List<ThongKeData> thongKeData = new ArrayList<ThongKeData>();
}
