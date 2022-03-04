package vn.dnict.microservice.nnptnt.qlvatnuoi.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NamChanNuoiInput {
	@NotBlank(message = "Vui lòng nhập nam")
	@Size(max = 100, message = "tên không nhập quá 100 ký tự")
	private String nam;

	@NotNull(message = "Vui lòng chọn quy")
	private Integer quy;

	
}
