package vn.dnict.microservice.core.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.core.business.CoreUserBusiness;
import vn.dnict.microservice.core.dao.model.CoreUser;
import vn.dnict.microservice.core.data.CoreUserData;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin()
@RestController
@RequestMapping(value = "/core/user")
public class CoreUserController {
	
	@Autowired
	private CoreUserBusiness coreUserBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreUserData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "email", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "roleIds", required = false) List<Long> roleIds,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "hoTen", required = false) String hoTen) {
		Page<CoreUserData> pageCoreUserData = coreUserBusiness.findAll(page, size, sortBy, sortDir, email, hoTen,
				roleIds);
		return ResponseEntity.ok(pageCoreUserData);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreUserData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreUserData coreUserData = coreUserBusiness.findById(id);
		return ResponseEntity.ok(coreUserData);
	}

	@GetMapping(value = { "/email/{email}" })
	public ResponseEntity<CoreUserData> findByEmail(@PathVariable("email") String email) {
		CoreUserData coreUserData = coreUserBusiness.findByEmail(email);
		return ResponseEntity.ok(coreUserData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreUser> create(@Valid @RequestBody CoreUserData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreUser coreUser = coreUserBusiness.create(coreUserData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreUser> update(@PathVariable("id") Long id, @Valid @RequestBody CoreUserData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreUser coreUser = coreUserBusiness.update(id, coreUserData, result);
		return ResponseEntity.ok(coreUser);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreUser> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreUser coreUser = coreUserBusiness.delete(id);
		return ResponseEntity.ok(coreUser);
	}
}
