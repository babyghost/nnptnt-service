package vn.dnict.microservice.nnptnt.vatnuoi.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
import lombok.Data;

@Data
public class CoSoChanNuoiInput {

	@NotBlank(message = "Vui lòng nhập tên cơ sở")
	@Size(max = 250, message = "tên không nhập quá 250 ký tự")
	private String tenCoSo;
	
	@NotBlank(message = "Vui lòng nhập tên chủ cơ sở")
	@Size(max = 250, message = "tên  không nhập quá 250 ký tự")
	private String tenChuCoSo;
	
	@NotBlank(message = "Vui lòng nhập địa chỉ")
	@Size(max = 250, message = "tên không nhập quá 250 ký tự")
	private String diaChi;
	
	@NotBlank(message = "Vui lòng nhập điện thoại")
	@Size(max = 250, message = "tên không nhập quá 50 ký tự")
	private String dienThoai;
	
	@NotBlank(message = "Vui lòng nhập email")
	@Size(max = 250, message = "tên không nhập quá 50 ký tự")
	private String email;

	@NotNull(message = "Vui lòng chọn phường, xã")
	private Long phuongXaId;
	
	@NotNull(message = "Vui lòng chọn quận, huyện")
	private Long quanHuyenId;
	
	private String ghiChu;
}
