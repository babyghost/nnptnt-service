package vn.dnict.microservice.danhmuc.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCapDonViService;
import vn.dnict.microservice.danhmuc.data.DmCapDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class DmCapDonViBusiness {
	@Autowired
	private DmCapDonViService capDonViService;

	public Page<DmCapDonVi> findAll(int page, int size, String sortBy, String sortDir, String search) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmCapDonVi> pageCapDonVi = capDonViService.findAll(search,
				PageRequest.of(page, size, direction, sortBy));
		return pageCapDonVi;
	}

	public DmCapDonVi findById(Long id) throws EntityNotFoundException {
		Optional<DmCapDonVi> optional = capDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCapDonVi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public List<DmCapDonVi> findByIdIn(List<Long> ids) {
		return capDonViService.findByIdIn(ids);
	}

	public List<DmCapDonVi> getListCapDonViInUseLimit20(String ten) {
		Page<DmCapDonVi> pageCapDonVi = capDonViService.findAll(ten, PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmCapDonVi> listCapDonVi = pageCapDonVi.getContent();
		return listCapDonVi;
	}

	public List<DmCapDonVi> getListCapDonViInUseLimit20AndAppCode(String appCode, String ten) {
		Page<DmCapDonVi> pageCapDonVi = capDonViService.findAll(ten,
				PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmCapDonVi> listCapDonVi = pageCapDonVi.getContent();
		return listCapDonVi;
	}

	public DmCapDonVi create(DmCapDonViInput capDonViInput) {
		DmCapDonVi capDonVi = new DmCapDonVi();
		capDonVi.setDaXoa(0);
		capDonVi.setTen(capDonViInput.getTen());
		capDonVi.setMa(capDonViInput.getMa());
		//capDonVi.setAppCode(appCode);
		capDonVi = capDonViService.save(capDonVi);
		return capDonVi;
	}

	public DmCapDonVi update(Long id, DmCapDonViInput capDonViInput) throws EntityNotFoundException {
		Optional<DmCapDonVi> optional = capDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCapDonVi.class, "id", String.valueOf(id));
		}
		DmCapDonVi capDonVi = optional.get();
		capDonVi.setTen(capDonViInput.getTen());
		capDonVi.setMa(capDonViInput.getMa());
		//capDonVi.setAppCode(appCode);
		capDonVi = capDonViService.save(capDonVi);
		return capDonVi;
	}

	@DeleteMapping(value = { "/{id}" })
	public DmCapDonVi delete(Long id) throws EntityNotFoundException {
		Optional<DmCapDonVi> optional = capDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCapDonVi.class, "id", String.valueOf(id));
		}
		DmCapDonVi capDonVi = optional.get();
		capDonVi.setDaXoa(1);
		capDonVi = capDonViService.save(capDonVi);
		return capDonVi;
	}
}
