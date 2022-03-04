package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmThuTucGiayPhepInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 1500, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;
	@NotBlank(message = "Vui lòng nhập url form")
	@Size(max = 500, message = "Nhập không quá {max} ký tự")
	private String urlForm;
}
