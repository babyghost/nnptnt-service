package vn.dnict.microservice.nnptnt.vatnuoi.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data; 


@Data
public class DmLoaiVatNuoiInput {

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 100, message = "tên không nhập quá 100 ký tự")
	private String ten;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;
}
