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

import vn.dnict.microservice.core.business.CoreRoleBusiness;
import vn.dnict.microservice.core.dao.model.CoreRole;
import vn.dnict.microservice.core.data.CoreRoleData;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin()
@RestController
@RequestMapping(value = "/core/role")
public class CoreRoleController {
	@Autowired
	private CoreRoleBusiness coreRoleBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreRole>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Page<CoreRole> pageCoreRole = coreRoleBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageCoreRole);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreRole> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreRole coreRole = coreRoleBusiness.findById(id);
		return ResponseEntity.ok(coreRole);
	}

	@GetMapping(value = { "/{roleId}/chucnang" })
	public ResponseEntity<List<Long>> getChucNangIdByRoleId(@PathVariable("roleId") long roleId) {
		List<Long> chucNangIds = coreRoleBusiness.getChucNangIdByRoleId(roleId);
		return ResponseEntity.ok(chucNangIds);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreRole> create(@Valid @RequestBody CoreRoleData coreRoleData, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreRole coreRole = coreRoleBusiness.create(coreRoleData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreRole);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreRole> update(@PathVariable("id") Long id, @Valid @RequestBody CoreRoleData coreRoleData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreRole coreRole = coreRoleBusiness.update(id, coreRoleData, result);
		return ResponseEntity.ok(coreRole);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreRole> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreRole coreRole = coreRoleBusiness.delete(id);
		return ResponseEntity.ok(coreRole);
	}

}
