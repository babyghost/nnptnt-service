package vn.dnict.microservice.uaa.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.uaa.dao.model.Role;
import vn.dnict.microservice.uaa.dao.model.User;
import vn.dnict.microservice.uaa.dao.service.UserService;
import vn.dnict.microservice.uaa.data.ChangePasswordInput;
import vn.dnict.microservice.uaa.data.ChangeRolesInput;
import vn.dnict.microservice.uaa.data.NewUser;
import vn.dnict.microservice.uaa.data.TokenUserDetails;

@CrossOrigin
@Slf4j
@RequestMapping("/uaa")
@RestController
public class UserRESTController {
	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public TokenUserDetails getUser(@AuthenticationPrincipal TokenUserDetails principal) {
		log.info("getUser {line 41}: "+ principal.getToken());
		return principal;
	}

	@GetMapping("/list")
	public ResponseEntity<Page<User>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "email", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai,
			@RequestParam(name = "isDungChung", required = false) Boolean isDungChung,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "userName", required = false) String userName,
			@RequestParam(name = "appCode", required = false) String appCode,
			@RequestParam(name = "role", required = false) String role) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<User> pageUser = userService.findAll(email, userName, role, trangThai, isDungChung, appCode,
				PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageUser);
	}

	@RequestMapping(value = { "/user" }, method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody NewUser newUser) throws Exception {
		String token = userService.create(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(token);
	}

	@RequestMapping(value = { "/user/{email}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("email") String email, @Valid @RequestBody NewUser newUser)
			throws Exception {
		String token = userService.update(newUser.getEmail(), newUser.getUsername(), newUser.getRoles());
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}

	@RequestMapping(value = { "/user/email/{email}" }, method = RequestMethod.GET)
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(userService.findByEmail(email));
	}

	@RequestMapping(value = { "/user/email/{email}/exists" }, method = RequestMethod.GET)
	public ResponseEntity<?> existsByEmail(@PathVariable("email") String email) {
		email = email.trim();
		return ResponseEntity.ok(userService.existsByEmail(email));
	}

	@RequestMapping(value = { "/user/roles" }, method = RequestMethod.PUT)
	public ResponseEntity<?> changeRoles(@Valid @RequestBody ChangeRolesInput changeRolesInput) throws Exception {
		List<Role> roles = new ArrayList<Role>();
		for (String role : changeRolesInput.getRoles()) {
			roles.add(new Role(changeRolesInput.getEmail(), role));
		}
		String token = userService.changeRoles(changeRolesInput.getEmail(), roles);
		return ResponseEntity.ok().body(token);
	}

	@RequestMapping(value = { "/user/password" }, method = RequestMethod.PUT)
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordInput changePasswordInput)
			throws Exception {
		String token = userService.changePassword(changePasswordInput.getEmail(), changePasswordInput.getNewPass());
		return ResponseEntity.ok().body(token);
	}
}
