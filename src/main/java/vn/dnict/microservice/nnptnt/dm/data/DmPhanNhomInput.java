package vn.dnict.microservice.nnptnt.dm.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class DmPhanNhomInput {
	private Long id;
	
	@NotBlank(message = "Vui lòng nhập tên")
	private String ten;

	private String moTa;

	private Long dmNhomId;
	
	private String dmNhomTen;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;
}
