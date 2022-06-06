package vn.dnict.microservice.nnptnt.kehoach.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmTrangThaiKeHoachData {
	
	private Long id;
	
	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 500, message = "Nhập tên quá {max} ký tự")
	private String ten;
	
}
