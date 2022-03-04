package vn.dnict.microservice.uaa.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NewUser {

	@NotBlank(message = "{validation.NotBlank}")
	private String appCode;

	@NotBlank(message = "{validation.NotBlank}")
	private String email;

	@NotBlank(message = "{validation.NotEmpty}")
	private String username;

	private String password;

	@NotNull(message = "{validation.NotNull}")
	private List<String> roles = new ArrayList<String>();

	@NotNull(message = "{validation.NotNull}")
	private Boolean enabled;
}
