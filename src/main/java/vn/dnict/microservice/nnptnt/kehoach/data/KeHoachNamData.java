package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class KeHoachNamData {
	private Long id;

	@NotBlank(message = "Vui lòng nhập tên kế hoạch")
	@Size(max = 500, message = "Nhập tên kế hoạch quá {max} ký tự")
	private String tenKeHoach;

	private Long donViChuTriId;

	private String donViChuTriTen;

	private Integer nam;

	@NotBlank(message = "Vui lòng nhập số ký hiệu")
	@Size(max = 100, message = "Nhập số ký hiệu quá {max} ký tự")
	private String soKyHieu;

	private Boolean trangThai;

	@NotNull(message = "Vui lòng nhập ngày ban hành")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;

	private List<DmLoaiNhiemVuData> dmLoaiNhiemVuDatas = new ArrayList<DmLoaiNhiemVuData>();
}
