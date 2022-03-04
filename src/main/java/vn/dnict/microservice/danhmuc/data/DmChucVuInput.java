package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmChucVuInput {
	
	@NotBlank(message="vui lòng nhập tên")
	@Size(max=100, message="tên không nhập quá 100 ký tự")
	private String ten;

	@NotBlank(message="vui lòng nhập mã")
	@Size(max=20, message="mã không nhập quá 20 ký tự")
	private String ma;

	@NotNull(message="vui lòng chọn trạng thái")
	private Boolean trangThai;
}
