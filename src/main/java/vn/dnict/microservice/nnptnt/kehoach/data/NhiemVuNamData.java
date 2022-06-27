package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NhiemVuNamData {
	private Long id;

	private Long keHoachId;

	@NotBlank(message = "Vui lòng nhập tên nhiệm vụ")
	@Size(max = 500, message = "Nhập tên nhiệm vụ quá {max} ký tự")
	private String tenNhiemVu;

	private Long nhiemVuChaId;

	@Size(max = 1000, message = "Nhập đơn vị phối hợp quá {max} ký tự")
	private String donViPhoiHop;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate tuNgay;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate denNgay;

	private String thoiGianThucHien;

	private Long loaiNhiemVuId;

	private String loaiNhiemVuTen;

	@Size(max = 1000, message = "Nhập ghi chú quá {max} ký tự")
	private String ghiChu;

	private List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<TienDoNhiemVuNamData>();

//	private TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();

	@Valid
	private List<NhiemVuNamData> children = new ArrayList<NhiemVuNamData>();
}
