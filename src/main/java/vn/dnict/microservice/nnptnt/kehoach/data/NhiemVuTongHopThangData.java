package vn.dnict.microservice.nnptnt.kehoach.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class NhiemVuTongHopThangData {
	private String stt;

	private String ten;

	@Valid
	private List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<NhiemVuThangData>();
}
