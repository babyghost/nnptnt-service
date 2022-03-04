package vn.dnict.microservice.danhmuc.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DmDonViInput {

	private Long donViChaId;

	@NotBlank(message = "Vui lòng nhập tên đơn vị")
	@Size(max = 100, message = "Nhập tên đơn vị quá ${max} ký tự")
	private String tenDonVi;

	@Size(max = 20, message = "Nhập tên viết tắt quá ${max} ký tự")
	private String tenVietTat;

	@Size(max = 100, message = "Nhập địa chỉ quá ${max} ký tự")
	private String diaChi;

	@Size(max = 50, message = "Nhập số điện thoại quá ${max} ký tự")
	private String soDienThoai;

	@Size(max = 50, message = "Nhập fax quá ${max} ký tự")
	private String fax;

	@Email(message = "Vui lòng nhập đúng email")
	@Size(max = 50, message = "Nhập email quá ${max} ký tự")
	private String email;

	@NotNull(message = "Vui lòng chọn cấp đơn vị")
	private Long capDonViId;

	@NotNull(message = "Vui lòng chọn loại đơn vị")
	private Long loaiDonViId;
}
