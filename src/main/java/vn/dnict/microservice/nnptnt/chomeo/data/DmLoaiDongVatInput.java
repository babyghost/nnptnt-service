package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Data;
@Data
public class DmLoaiDongVatInput {

	@NotBlank(message = "Vui lòng nhập tên")
	private String Ten;
	
	@NotBlank(message = "Vui lòng nhập mã")
	private String ma;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

}
