package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ThongTinGietMoData {
	private Long id;
	
	private Long coSoGietMoId;
	private String coSoTen;
	private String tenChuCoSo;
	private String diaChi;
	private String dienThoai;
	private String email;
	private Long quanHuyenId;
	private String quanHuyenTen;
	private Long phuongXaId;
	private String phuongXaTen;
	private String giayKinhDoanh;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThang;
	
	private String chuHang;
	
	private String soGiayTo;
	
	private Long loaiGiayToId;
	private String loaiGiayToTen;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate capNgay;
	
	@Valid
	private List<SoLuongGietMoData> listSoLuongGietMo = new ArrayList<SoLuongGietMoData>();
}
