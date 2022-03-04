package vn.dnict.microservice.core.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreCaiDatHienThiInput {
	private Long id;

	@NotBlank(message = "Vui lòng nhập người sử dụng")
	@Size(max = 100, message = "Nhập tên quá ${max} ký tự")
	private String nguoiSuDung;

	@NotBlank(message = "Vui lòng nhập mã danh sách")
	@Size(max = 50, message = "Nhập mã quá ${max} ký tự")
	private String madanhsach;

	@NotNull(message = "Vui lòng chọn cấu hình danh sách id")
	private Long cauHinhDanhSachId;

	@NotNull(message = "Vui lòng chọn is_hienthi")
	private Boolean isHienThi;

}