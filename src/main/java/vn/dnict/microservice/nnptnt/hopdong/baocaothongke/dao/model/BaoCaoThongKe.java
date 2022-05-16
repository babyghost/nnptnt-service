package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BaoCaoThongKe {

	private Long id;

	private String tenHopDong;

	private Long loaiHopDongId;

	private String tenLoaiHopDong;

	private Integer thanhToanDot;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thanhToanNgay;
	
	private Double thanhToanGiaTri;
	
	private String thanhToanSoChungTu;
	
	private String hoaDonSo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate hoaDonNgay;

	private String dvthTen;

	private String cnthTen;

	private Integer trangThai;

	private String tenTrangThai;

}
