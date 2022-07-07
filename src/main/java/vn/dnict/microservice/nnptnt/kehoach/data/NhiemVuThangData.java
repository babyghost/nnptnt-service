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
public class NhiemVuThangData {
	private Long id;

	private Long keHoachThangId;

	@NotBlank(message = "Vui lòng nhập tên nhiệm vụ")
	@Size(max = 1000, message = "Nhập tên nhiệm vụ quá {max} ký tự")
	private String tenNhiemVu;

	private Long canBoThucHienId;

	private String canBoThucHienTen;

	@Size(max = 1000, message = "Nhập đơn vị phối hợp quá {max} ký tự")
	private String donViPhoiHop;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thoiGian;

	// @NotBlank(message = "Vui lòng nhập ghi chú")
	@Size(max = 1000, message = "Nhập ghi chú quá {max} ký tự")
	private String ghiChu;

	private Boolean isNhiemVuThangTruoc;

	private Long nhiemVuThangTruocId;

	private Integer tinhTrang;
	
	private Integer danhSo;

	private List<NhiemVuThangLogData> nhiemVuThangLogDatas = new ArrayList<NhiemVuThangLogData>();

	@Valid
	private TienDoNhiemVuThangData tienDoNhiemVuThangData = new TienDoNhiemVuThangData();
	
	private List<NhiemVuThangData> children = new ArrayList<NhiemVuThangData>();
	
}
