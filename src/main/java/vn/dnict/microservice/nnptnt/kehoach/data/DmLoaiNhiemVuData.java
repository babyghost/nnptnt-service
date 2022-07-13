package vn.dnict.microservice.nnptnt.kehoach.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmLoaiNhiemVuData {

	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 2500 ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 50 ký tự")
	private String ma;

	private Boolean trangThai;

	private Integer sapXep;

}
