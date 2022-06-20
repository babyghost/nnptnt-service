package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
	
	@Valid
	private List<ThongTinSoLuongGietMoData> listThongTinGietMo = new ArrayList<ThongTinSoLuongGietMoData>();
}
