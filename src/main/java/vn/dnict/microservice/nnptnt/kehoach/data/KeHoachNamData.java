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
public class KeHoachNamData {
	private Long id;
	
	private String tenKeHoach;
	
	private Long donViChuTriId;	
	private String donViChuTriTen;
	
	private Integer nam;

	private String soKyHieu;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;
	
	private Boolean trangThai;
	
	@Valid
	private List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
}
