package vn.dnict.microservice.nnptnt.hopdong.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class DmLoaiHopDongInput {
	@NotBlank(message="Vui lòng nhập tên")
	@Size(max=255, message="Nhập tên quá {max} ký tự")
	private String ten;
	
	@NotBlank(message="Vui lòng nhập mã")
	@Size(max=50, message="Nhập mã quá {max} ký tự")
	private String ma;
	
	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;
}
