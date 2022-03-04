package vn.dnict.microservice.uaa.api;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.uaa.dao.model.Roles;
import vn.dnict.microservice.uaa.dao.service.RolesService;
import vn.dnict.microservice.uaa.data.NewRoles;

@CrossOrigin
@RequestMapping("/uaa")
@RestController
public class RolesRESTController {
	@Autowired
	private RolesService rolesService;

	@GetMapping("/roles")
	public ResponseEntity<Page<Roles>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "roleName", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "roleName", required = false) String roleName) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<Roles> pageRoles = rolesService.findAll(roleName, PageRequest.of(page, size, direction, sortBy));
		return ResponseEntity.ok(pageRoles);
	}

	@PostMapping(value = { "/roles" })
	public ResponseEntity<?> create(@Valid @RequestBody NewRoles newRoles) throws Exception {
		Roles roles = new Roles(newRoles.getRoleName(), newRoles.getDescription());
		rolesService.save(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(roles);
	}

	@RequestMapping(value = { "/roles/{roleName}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("roleName") String roleName, @Valid @RequestBody NewRoles newRoles)
			throws Exception {
		Optional<Roles> optionalRoles = rolesService.findByRoleName(roleName);
		if (!optionalRoles.isPresent()) {
			throw new EntityNotFoundException("Role không tồn tại");
		}
		Roles roles = optionalRoles.get();
		roles.setRoleName(newRoles.getRoleName());
		roles.setDescription(newRoles.getDescription());
		roles = rolesService.save(roles);
		return ResponseEntity.status(HttpStatus.OK).body(roles);
	}

	@DeleteMapping(value = { "/roles/{roleName}" })
	public void findByEmail(@PathVariable("roleName") String roleName) {
		Optional<Roles> optionalRoles = rolesService.findByRoleName(roleName);
		if (!optionalRoles.isPresent()) {
			throw new EntityNotFoundException("Role không tồn tại");
		}
		rolesService.deleteByRoleName(roleName);
	}

}
