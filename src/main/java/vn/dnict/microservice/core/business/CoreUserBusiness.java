package vn.dnict.microservice.core.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.core.dao.model.CoreRole;
import vn.dnict.microservice.core.dao.model.CoreUser;
import vn.dnict.microservice.core.dao.model.CoreUser2Role;
import vn.dnict.microservice.core.dao.service.CoreRoleService;
import vn.dnict.microservice.core.dao.service.CoreUser2RoleService;
import vn.dnict.microservice.core.dao.service.CoreUserService;
import vn.dnict.microservice.core.data.CoreCanBoData;
import vn.dnict.microservice.core.data.CoreUserData;
import vn.dnict.microservice.core.data.validator.CoreUserValidator;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
@CrossOrigin()
@Service
public class CoreUserBusiness {

	@Autowired
	private CoreUserService coreUserService;
	@Autowired
	private CoreUserValidator coreUserValidator;
	@Autowired
	private CoreRoleService coreRoleService;
	@Autowired
	private CoreUser2RoleService coreUser2RoleService;
	@Autowired
	private DmCanBoService dmCanBoService;

	public Page<CoreUserData> findAll(int page, int size, String sortBy, String sortDir, String email, String hoTen,
			List<Long> roleIds) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<CoreUser> pageCoreUser = coreUserService.findAll(email, hoTen, roleIds,
				PageRequest.of(page, size, direction, sortBy));
		final Page<CoreUserData> pageCoreUserData = pageCoreUser.map(x -> this.convertToCoreUserData(x, x.getEmail()));
		return pageCoreUserData;
	}

	private CoreUserData convertToCoreUserData(CoreUser coreUser, String email) {
		CoreUserData coreUserData = new CoreUserData();
		coreUserData.setId(coreUser.getId());
		coreUserData.setUserName(coreUser.getUserName());
		coreUserData.setEmail(coreUser.getEmail());
		coreUserData.setHoTen(coreUser.getHoTen());
		coreUserData.setPhone(coreUser.getPhone());
		String role = "";
		List<String> roles = new ArrayList<String>();
		if (Objects.nonNull(coreUser.getCoreRoles()) && !coreUser.getCoreRoles().isEmpty()) {
			for (CoreRole coreRole : coreUser.getCoreRoles()) {
				roles.add(coreRole.getTen());
				//MUNG NEW roles.add(coreRole.getMa());
			}
			role = StringUtils.join(roles, ", ");
		}
		coreUserData.setRoles(roles);
		coreUserData.setRole(role);
		List<DmCanBo> dmCanBos = dmCanBoService.findByEmailIgnoreCaseAndDaXoa(coreUser.getEmail(), 0);
		if (Objects.nonNull(dmCanBos) && !dmCanBos.isEmpty()) {
			DmCanBo dmCanBo = dmCanBos.get(0);
			coreUserData.setCanBoId(dmCanBo.getId());
			coreUserData.setCanBoTen(dmCanBo.getHoTen());
			if (Objects.nonNull(dmCanBo.getChucVu())) {
				coreUserData.setChucVuId(dmCanBo.getChucVu().getId());
				coreUserData.setChucVuTen(dmCanBo.getChucVu().getTen());
			}
			if (Objects.nonNull(dmCanBo.getDonVi())) {
				coreUserData.setDonViId(dmCanBo.getDonVi().getId());
				coreUserData.setDonViTen(dmCanBo.getDonVi().getTenDonVi());
			}
			if (Objects.nonNull(dmCanBo.getPhongBan())) {
				coreUserData.setPhongBanId(dmCanBo.getPhongBan().getId());
				coreUserData.setPhongBanTen(dmCanBo.getPhongBan().getTen());
			}
			CoreCanBoData dmCanBoData = new CoreCanBoData(dmCanBo);
			coreUserData.setDmCanBoData(dmCanBoData);
		}
		
		return coreUserData;
	}

	public CoreUserData findById(Long id) throws EntityNotFoundException {
		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();
		return this.convertToCoreUserData(coreUser, coreUser.getEmail());
	}

	public CoreUserData findByEmail(String email) {
		List<CoreUser> coreUsers = coreUserService.findByEmailIgnoreCaseAndDaXoa(email,false);
		CoreUser coreUser = new CoreUser();
		if (Objects.nonNull(coreUsers) && !coreUsers.isEmpty()) {
			coreUser = coreUsers.get(0);
		}
		return this.convertToCoreUserData(coreUser, email);
	}

	public CoreUser create(CoreUserData coreUserData, BindingResult result) throws MethodArgumentNotValidException {
		coreUserValidator.validate(coreUserData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreUser coreUser = new CoreUser();

		coreUser.setDaXoa(false);
		coreUser.setUserName(coreUserData.getUserName());
		coreUser.setEmail(coreUserData.getEmail());
		coreUser.setHoTen(coreUserData.getHoTen());
		coreUser.setPhone(coreUserData.getPhone());

		coreUser = coreUserService.save(coreUser);
		List<String> roles = coreUserData.getRoles();
		List<CoreRole> cRoles = new ArrayList<>();
		if (Objects.nonNull(roles) && !roles.isEmpty()) {
			for (String role : roles) {
				List<CoreRole> coreRoles = coreRoleService.findByMaIgnoreCaseAndDaXoa(role, false);
				if (Objects.nonNull(coreRoles) && !coreRoles.isEmpty()) {
					cRoles.add(coreRoles.get(0));
				}
			}
		}
		coreUser2RoleService.setFixedDaXoaForUserId(true, coreUser.getId());
		if (Objects.nonNull(cRoles) && !cRoles.isEmpty()) {
			for (CoreRole coreRole : cRoles) {
				CoreUser2Role coreUser2Role = new CoreUser2Role();
				List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByRoleIdAndUserId(coreRole.getId(),
						coreUser.getId());
				if (Objects.nonNull(coreUser2Roles) && !coreUser2Roles.isEmpty()) {
					coreUser2Role = coreUser2Roles.get(0);
				}
				coreUser2Role.setDaXoa(false);
				coreUser2Role.setRoleId(coreRole.getId());
				coreUser2Role.setUserId(coreUser.getId());
				coreUser2RoleService.save(coreUser2Role);
			}
		}

		return coreUser;
	}

	public CoreUser update(Long id, CoreUserData coreUserData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreUserValidator.validate(coreUserData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();

		coreUser.setDaXoa(false);
		coreUser.setUserName(coreUserData.getUserName());
		coreUser.setEmail(coreUserData.getEmail());
		coreUser.setHoTen(coreUserData.getHoTen());
		coreUser.setPhone(coreUserData.getPhone());
		coreUser = coreUserService.save(coreUser);
		List<String> roles = coreUserData.getRoles();
		List<CoreRole> cRoles = new ArrayList<>();
		if (Objects.nonNull(roles) && !roles.isEmpty()) {
			for (String role : roles) {
				// Mung khai bao
				List<CoreRole> coreRoles = coreRoleService.findByMaIgnoreCaseAndDaXoa(role, false);
				if (Objects.nonNull(coreRoles) && !coreRoles.isEmpty()) {
					cRoles.add(coreRoles.get(0));
				}
			}
		}
		coreUser2RoleService.setFixedDaXoaForUserId(true, coreUser.getId());
		if (Objects.nonNull(cRoles) && !cRoles.isEmpty()) {
			for (CoreRole coreRole : cRoles) {
				CoreUser2Role coreUser2Role = new CoreUser2Role();
				List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByRoleIdAndUserId(coreRole.getId(),
						coreUser.getId());
				if (Objects.nonNull(coreUser2Roles) && !coreUser2Roles.isEmpty()) {
					coreUser2Role = coreUser2Roles.get(0);
				}
				coreUser2Role.setDaXoa(false);
				coreUser2Role.setRoleId(coreRole.getId());
				coreUser2Role.setUserId(coreUser.getId());
				coreUser2RoleService.save(coreUser2Role);
			}
		}
		return coreUser;
	}

	public CoreUser delete(@PathVariable("id") Long id) throws EntityNotFoundException {

		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();
		coreUser.setDaXoa(true);
		coreUserService.save(coreUser);

		return coreUser;
	}

}
