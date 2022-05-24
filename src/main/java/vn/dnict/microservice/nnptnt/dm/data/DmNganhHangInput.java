package vn.dnict.microservice.nnptnt.dm.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class DmNganhHangInput {
	@NotBlank(message = "Vui lòng nhập tên")
	private String Ten;
	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;
}
