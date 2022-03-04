package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmNganhNgheInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 100 ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 20, message = "mã không nhập quá 20 ký tự")
	private String ma;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

}
