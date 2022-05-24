package vn.dnict.microservice.nnptnt.dm.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class DmLoaiDoanhNghiepInput {
	@NotBlank(message = "Vui lòng nhập tên")
	private String Ten;
	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;
}
