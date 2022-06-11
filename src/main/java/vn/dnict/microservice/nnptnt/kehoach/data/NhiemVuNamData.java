package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NhiemVuNamData {
	private Long id;
	
	private Long keHoachNamId;	
	private String keHoachNamTen;
	private Long khDonViChuTriId;
	private String khDonViChuTriTen;
	private Integer khNam;
	
	private String tenNhiemVu;

	private Long nhiemVuChaId;

	private Integer sapXep;

	private String donViPhoiHop;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate tuNgay;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate denNgay;

	private Long loaiNhiemVuId;
	private String loaiNhiemVuTen;
	private String loaiNvMa;

	private String ghiChu;
	
	private List<NhiemVuNamData> children = new ArrayList<NhiemVuNamData>();
	
	private List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
}
