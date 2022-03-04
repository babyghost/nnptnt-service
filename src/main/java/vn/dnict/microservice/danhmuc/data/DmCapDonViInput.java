package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmCapDonViInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 150, message = "Nhập tên quá ${max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 20, message = "Nhập mã quá ${max} ký tự")
	private String ma;
}
