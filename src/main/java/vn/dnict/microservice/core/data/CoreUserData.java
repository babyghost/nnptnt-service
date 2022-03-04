package vn.dnict.microservice.core.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreUserData {
	private Long id;

	@Size(max = 250, message = "Nhập tên đăng nhập không quá {max} ký tự")
	private String userName;

	@NotBlank(message = "Vui lòng nhập email")
	@Size(max = 250, message = "Nhập email không quá {max} ký tự")
	private String email;

	@Size(max = 10, message = "Nhập số điện thoại không quá {max} ký tự")
	private String phone;

	@Size(max = 250, message = "Nhập không qua {max} ký tự")
	private String hoTen;

	private Long canBoId;

	private String canBoTen;

	private Long donViId;

	private String donViTen;

	private Long phongBanId;

	private String phongBanTen;

	private Long chucVuId;

	private String chucVuTen;

	private List<String> roles = new ArrayList<>();
	
	// private List<String> roleMas = new ArrayList<>();
	
	private String role;
	
	private Integer daXoa;
	
	private CoreCanBoData dmCanBoData;
}
