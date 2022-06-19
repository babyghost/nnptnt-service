package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class ThongTinGietMoData {
	private Long id;
	
	private Long coSoGietMoId;
	private String coSoGietMoTen;
	private String coSoTenChuCoSo;
	private String coSoDiaChi;
	private String coSoDienThoai;
	private String coSoEmail;
	private Long coSoQuanHuyenId;
	private String coSoQuanHuyenTen;
	private Long coSoPhuongXaId;
	private String coSoPhuongXaTen;
	private String coSoGiayKinhDoanh;
	
	@Valid
	private List<ThongTinSoLuongGietMoData> listThongTinGietMo = new ArrayList<ThongTinSoLuongGietMoData>();
}
