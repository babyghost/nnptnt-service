package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoSoGietMoData {
	
	private Long id;
	
	@NotBlank(message = "Vui lòng nhập tên cơ sở")
	@Size(max = 250, message = "Tên cơ sở không nhập quá 250 ký tự")
	private String tenCoSo;
	
	@NotBlank(message = "Vui lòng nhập tên chủ cơ sở")
	@Size(max = 250, message = "Tên chủ cơ sở  không nhập quá 250 ký tự")
	private String tenChuCoSo;
	
	@NotBlank(message = "Vui lòng nhập địa chỉ")
	@Size(max = 250, message = "Địa chỉ không nhập quá 250 ký tự")
	private String diaChi;
	
	@NotBlank(message = "Vui lòng nhập điện thoại")
	@Size(max = 250, message = "Điện thoại không nhập quá 250 ký tự")
	private String dienThoai;
	
	@NotBlank(message = "Vui lòng nhập email")
	@Size(max = 250, message = "Email không nhập quá 250 ký tự")
	private String email;

	private Long phuongXaId;	
	private String phuongXaTen;
	
	private Long quanHuyenId;	
	private String quanHuyenTen;
	
	private String ghiChu;
	
	@Size(max = 50, message = "Giấy kinh doanh không nhập quá 50 ký tự")
	private String giayKinhDoanh;
}
