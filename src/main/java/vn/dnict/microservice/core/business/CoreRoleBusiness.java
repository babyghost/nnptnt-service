package vn.dnict.microservice.core.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.core.dao.model.CoreChucNang;
import vn.dnict.microservice.core.dao.model.CoreRole;
import vn.dnict.microservice.core.dao.model.CoreRole2ChucNang;
import vn.dnict.microservice.core.dao.service.CoreChucNangService;
import vn.dnict.microservice.core.dao.service.CoreRole2ChucNangService;
import vn.dnict.microservice.core.dao.service.CoreRoleService;
import vn.dnict.microservice.core.data.CoreRoleData;
import vn.dnict.microservice.core.data.MenuData;
import vn.dnict.microservice.core.data.validator.CoreRoleValidator;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class CoreRoleBusiness {

	@Autowired
	private CoreRoleService coreRoleService;
	@Autowired
	private CoreRoleValidator coreRoleValidator;
	@Autowired
	private CoreRole2ChucNangService coreRole2ChucNangService;
	@Autowired
	private CoreChucNangService coreChucNangService;

	public Page<CoreRole> findAll(int page, int size, String sortBy, String sortDir, String search, Boolean trangThai) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreRole> pageCoreRole = coreRoleService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));

		return pageCoreRole;
	}

	public CoreRole findById(Long id) throws EntityNotFoundException {
		Optional<CoreRole> optional = coreRoleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreRole.class, "id", String.valueOf(id));
		}
		CoreRole coreRole = optional.get();
		return coreRole;
	}

	public List<Long> getChucNangIdByRoleId(Long roleId) {
		List<Long> chucNangIds = new ArrayList<>();
		List<CoreRole2ChucNang> coreRole2ChucNangs = coreRole2ChucNangService.findByRoleIdAndDaXoa(roleId, false);
		if (Objects.nonNull(coreRole2ChucNangs) && !coreRole2ChucNangs.isEmpty()) {
			for (CoreRole2ChucNang coreRole2ChucNang : coreRole2ChucNangs) {
				if (Objects.nonNull(coreRole2ChucNang.getChucNangId())) {
					chucNangIds.add(coreRole2ChucNang.getChucNangId());
				}
			}
		}
		return chucNangIds;
	}

	public CoreRole create(CoreRoleData coreRoleData, BindingResult result) throws MethodArgumentNotValidException {
		coreRoleValidator.validate(coreRoleData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreRole coreRole = new CoreRole();

		coreRole.setDaXoa(false);
		coreRole.setTen(coreRoleData.getTen());
		coreRole.setMa(coreRoleData.getMa());
		if (Objects.nonNull(coreRoleData.getIsDefault())) {
			coreRole.setIsDefault(coreRoleData.getIsDefault());
		} else {
			coreRole.setIsDefault(false);
		}
		coreRole.setMoTa(coreRoleData.getMoTa());
		coreRole.setTrangThai(coreRoleData.getTrangThai());
		coreRole = coreRoleService.save(coreRole);
		coreRole2ChucNangService.setFixedDaXoaForRoleId(true, coreRole.getId());
		if (Objects.nonNull(coreRoleData.getMenu()) && !coreRoleData.getMenu().isEmpty()) {
			for (MenuData menuData : coreRoleData.getMenu()) {
				if (Objects.nonNull(menuData.getName()) && !menuData.getName().isEmpty()) {
					List<CoreChucNang> coreChucNangs = coreChucNangService
							.findByMaIgnoreCaseAndDaXoa(menuData.getName(), false);
					if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
						CoreRole2ChucNang coreRole2ChucNang = new CoreRole2ChucNang();
						List<CoreRole2ChucNang> coreRole2ChucNangs = coreRole2ChucNangService
								.findByRoleIdAndChucNangId(coreRole.getId(), coreChucNangs.get(0).getId());
						if (Objects.nonNull(coreRole2ChucNangs) && !coreRole2ChucNangs.isEmpty()) {
							coreRole2ChucNang = coreRole2ChucNangs.get(0);
						}
						coreRole2ChucNang.setDaXoa(false);
						coreRole2ChucNang.setChucNangId(coreChucNangs.get(0).getId());
						coreRole2ChucNang.setRoleId(coreRole.getId());
						coreRole2ChucNangService.save(coreRole2ChucNang);
					}
				}
			}
		}

		return coreRole;
	}

	public CoreRole update(Long id, CoreRoleData coreRoleData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreRoleValidator.validate(coreRoleData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreRole> optional = coreRoleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreRole.class, "id", String.valueOf(id));
		}
		CoreRole coreRole = optional.get();

		coreRole.setDaXoa(false);
		coreRole.setTen(coreRoleData.getTen());
		coreRole.setMa(coreRoleData.getMa());
		if (Objects.nonNull(coreRoleData.getIsDefault())) {
			coreRole.setIsDefault(coreRoleData.getIsDefault());
		} else {
			coreRole.setIsDefault(false);
		}
		coreRole.setMoTa(coreRoleData.getMoTa());
		coreRole.setTrangThai(coreRoleData.getTrangThai());
		coreRole = coreRoleService.save(coreRole);
		coreRole2ChucNangService.setFixedDaXoaForRoleId(true, coreRole.getId());
		if (Objects.nonNull(coreRoleData.getMenu()) && !coreRoleData.getMenu().isEmpty()) {
			for (MenuData menuData : coreRoleData.getMenu()) {
				if (Objects.nonNull(menuData.getName()) && !menuData.getName().isEmpty()) {
					List<CoreChucNang> coreChucNangs = coreChucNangService
							.findByMaIgnoreCaseAndDaXoa(menuData.getName(), false);
					if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
						CoreRole2ChucNang coreRole2ChucNang = new CoreRole2ChucNang();
						List<CoreRole2ChucNang> coreRole2ChucNangs = coreRole2ChucNangService
								.findByRoleIdAndChucNangId(coreRole.getId(), coreChucNangs.get(0).getId());
						if (Objects.nonNull(coreRole2ChucNangs) && !coreRole2ChucNangs.isEmpty()) {
							coreRole2ChucNang = coreRole2ChucNangs.get(0);
						}
						coreRole2ChucNang.setDaXoa(false);
						coreRole2ChucNang.setChucNangId(coreChucNangs.get(0).getId());
						coreRole2ChucNang.setRoleId(coreRole.getId());
						coreRole2ChucNangService.save(coreRole2ChucNang);
					}
				}
			}
		}
		return coreRole;
	}

	public CoreRole delete(@PathVariable("id") Long id) throws EntityNotFoundException {

		Optional<CoreRole> optional = coreRoleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreRole.class, "id", String.valueOf(id));
		}
		CoreRole coreRole = optional.get();
		coreRole.setDaXoa(true);
		coreRoleService.save(coreRole);

		return coreRole;
	}

}
