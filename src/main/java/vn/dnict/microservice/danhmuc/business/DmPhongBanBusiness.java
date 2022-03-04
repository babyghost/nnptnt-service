package vn.dnict.microservice.danhmuc.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.danhmuc.dao.service.DmPhongBanService;
import vn.dnict.microservice.danhmuc.data.DmPhongBanInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class DmPhongBanBusiness {
	@Autowired
	private DmPhongBanService phongBanService;
	@Autowired
	private DmDonViService donViService;

	public Page<DmPhongBan> findAll(int page, int size, String sortBy, String sortDir, String search,
			Long donViId, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmPhongBan> pagePhongBan = phongBanService.findAll(search, donViId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pagePhongBan;
	}

	public DmPhongBan findById(Long id) throws EntityNotFoundException {
		Optional<DmPhongBan> optional = phongBanService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhongBan.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmDonVi findDonViByPhongBanId(Long id) throws EntityNotFoundException {
		Optional<DmPhongBan> optional = phongBanService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhongBan.class, "id", String.valueOf(id));
		}
		Optional<DmDonVi> optionalDonVi = donViService.findById(optional.get().getDonViId());
		if (!optionalDonVi.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		return optionalDonVi.get();
	}

	public List<DmPhongBan> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return phongBanService.findByIdIn(ids);
	}

	public DmPhongBan create(DmPhongBanInput phongBanInput) {
		DmPhongBan phongBan = new DmPhongBan();
		phongBan.setTen(phongBanInput.getTen());
		phongBan.setDaXoa(0);
		phongBan.setMa(phongBanInput.getMa());
		phongBan.setDonViId(phongBanInput.getDonViId());
		phongBan.setTrangThai(phongBanInput.getTrangThai());
		//phongBan.setAppCode(appCode);
		phongBan = phongBanService.save(phongBan);
		return phongBan;
	}

	public DmPhongBan update(Long id, DmPhongBanInput phongBanInput) throws EntityNotFoundException {
		Optional<DmPhongBan> optional = phongBanService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhongBan.class, "id", String.valueOf(id));
		}
		DmPhongBan phongBan = optional.get();
		phongBan.setTen(phongBanInput.getTen());
		phongBan.setMa(phongBanInput.getMa());
		phongBan.setDonViId(phongBanInput.getDonViId());
		phongBan.setTrangThai(phongBanInput.getTrangThai());
		//phongBan.setAppCode(appCode);
		phongBan = phongBanService.save(phongBan);
		return phongBan;
	}

	public DmPhongBan delete(Long id) throws EntityNotFoundException {
		Optional<DmPhongBan> optional = phongBanService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhongBan.class, "id", String.valueOf(id));
		}
		DmPhongBan phongBan = optional.get();
		phongBan.setDaXoa(1);
		phongBan = phongBanService.save(phongBan);
		return phongBan;
	}

	public List<DmPhongBan> getListPhongBanInUseLimit20(String search, Long donViId) {
		Page<DmPhongBan> pagePhongBan = phongBanService.findAll(search, donViId, true,
				PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmPhongBan> listPhongBan = pagePhongBan.getContent();
		return listPhongBan;
	}

	public List<DmPhongBan> getListPhongBanInUseLimit20AndAppCode(String appCode, String search, Long donViId) {
		Page<DmPhongBan> pagePhongBan = phongBanService.findAll(search, donViId, true,
				PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmPhongBan> listPhongBan = pagePhongBan.getContent();
		return listPhongBan;
	}
}
