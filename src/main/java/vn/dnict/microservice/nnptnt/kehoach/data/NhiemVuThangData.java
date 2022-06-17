package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class NhiemVuThangData {
	private Long id;
	
	private String tenNhiemVu;
	
	private Long keHoachThangId;
	private Long keHoachDonViChuTriId;
	private String keHoachDonViChuTriTen;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate keHoachthang;
	
	private Long canBoThucHienId;
	private String canBoThucHienTen;
	
	private String donViPhoiHop;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGian;
	
	private String ghiChu;
	
	private Boolean isNhiemVuThangTruoc;
	
	private Long nhiemVuThangTruocId;
	
	private Integer tinhTrang;
	
	private Long tienDoNhiemVuId;
	private Integer tienDoMucDoHoanThanh;
	private String tienDoKetQua;
	private String tienDoTenNguoiCapNhat;
	
	private List<Long> fileDinhKemIds = new ArrayList<>();	
	private FileDinhKem fileDinhKem = new FileDinhKem();
}
