package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

@Data
public class ThongKeKeHoachThangData {

	private Long keHoachThangId;
	
	private Long donViChuTriId;
	private String doViChuTriTen;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thang;
	
	private String tenNhiemVu;
	
	private Integer tinhTrang;
	
	private Long canBoThucHienId;
	private String canBoThucHienTen;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGian;
	
	private String donViPhoiHop;
	
	private String ghiChu;
	
	private Boolean isNhiemVuThangTruoc;
	
	private Long nhiemVuThangTruocId;
	
	private Long tienDoNhiemVuId;
	private Integer tienDoMucDoHoanThanh;
	private String tienDoKetQua;
	
	private List<ThongKeKeHoachThangData> thongKeThangDatas = new ArrayList<ThongKeKeHoachThangData>();
}
