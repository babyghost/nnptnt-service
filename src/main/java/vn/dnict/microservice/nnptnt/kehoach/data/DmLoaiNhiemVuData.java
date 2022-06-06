package vn.dnict.microservice.nnptnt.kehoach.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmLoaiNhiemVuData {

	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 500, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;

	private Boolean isDefault;

	private Integer sapXep;

}
