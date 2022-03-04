package vn.dnict.microservice.uaa.data;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NewRoles {

	@NotBlank(message = "Tên role bắt buộc nhập")
	private String roleName;
	private String description;

}
