package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class KeHoachThangData {
	private Long id;

	private Long donViChuTriId;

	private String donViChuTriTen;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thang;

	@Valid
	private List<NhiemVuTongHopThangData> nhiemVuTongHopThangDatas = new ArrayList<NhiemVuTongHopThangData>();
}
