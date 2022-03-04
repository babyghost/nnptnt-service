package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmTinhThanhData {

	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 100 ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "mã không nhập quá 50 ký tự")
	private String ma;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;
}
