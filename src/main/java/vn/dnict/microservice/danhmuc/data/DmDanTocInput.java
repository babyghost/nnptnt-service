package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class DmDanTocInput {

	@NotBlank(message="vui lòng nhập tên")
	@Size(max=250, message="tên không nhập quá 250 ký tự")
	private String ten;

	@NotBlank(message="vui lòng nhập mã")
	@Size(max=20, message="mã không nhập quá 20 ký tự")
	private String ma;
	
	@NotNull(message="vui lòng chọn sắp xếp")
	private Integer sapXep;
	
	@NotNull(message="vui lòng chọn trạng thái")
	private Integer trangThai;
}
