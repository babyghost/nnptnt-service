package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmQuocGiaInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 255, message = "tên không nhập quá 255 ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "mã không nhập quá 50 ký tự")
	private String ma;

	@NotBlank(message = "Vui lòng nhập tên Việt Nam")
	@Size(max = 255, message = "mã không nhập quá 255 ký tự")
	private String tenVietNam;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;

}
