package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmPhuongXaData {

	private Long id;

	@NotBlank(message = "vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 100 ký tự")
	private String ten;

	@NotBlank(message = "vui lòng nhập mã")
	@Size(max = 50, message = "mã không nhập quá 50 ký tự")
	private String ma;

	@NotBlank(message = "vui lòng nhập mã")
	@Size(max = 50, message = "quận huyện không nhập quá 50 ký tự")
	private String quanHuyenCode;

	private DmQuanHuyenData dmQuanHuyenData;

	@NotBlank(message = "vui lòng nhập mã")
	@Size(max = 50, message = "tỉnh thành không nhập quá 50 ký tự")
	private String tinhThanhCode;

	private DmTinhThanhData dmTinhThanhData;

	@NotNull(message = "vui lòng chọn trạng thái")
	private Boolean trangThai;

}
