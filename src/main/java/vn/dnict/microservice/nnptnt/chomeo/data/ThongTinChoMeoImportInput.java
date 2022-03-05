package vn.dnict.microservice.nnptnt.chomeo.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ThongTinChoMeoImportInput {

	@NotBlank(message = "Vui lòng nhập tên chủ hộ")
	private String chuHo;

	@NotNull(message = "Vui lòng nhập trạng thái")
	private String trangThai;

}
