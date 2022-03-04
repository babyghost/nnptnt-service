package vn.dnict.microservice.uaa.data;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ChangeRolesInput {
	@NotBlank(message = "{validation.NotBlank}")
	private String email;
	@NotNull(message = "{validation.NotNull}")
	private List<String> roles;

}
