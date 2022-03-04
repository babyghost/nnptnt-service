package vn.dnict.microservice.danhmuc.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmLoaiDonViService;
import vn.dnict.microservice.danhmuc.data.DmLoaiDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service

public class DmLoaiDonViBusiness {
	@Autowired
	private DmLoaiDonViService loaiDonViService;

	public Page<DmLoaiDonVi> findAll(int page, int size, String sortBy, String sortDir, String search, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLoaiDonVi> pageLoaiDonVi = loaiDonViService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageLoaiDonVi;
	}

	public DmLoaiDonVi findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDonVi> optional = loaiDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDonVi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public List<DmLoaiDonVi> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return loaiDonViService.findByIdIn(ids);
	}

	public DmLoaiDonVi create(DmLoaiDonViInput loaiDonViInput) {
		DmLoaiDonVi loaiDonVi = new DmLoaiDonVi();
		loaiDonVi.setTen(loaiDonViInput.getTen());
		loaiDonVi.setDaXoa(0);
		loaiDonVi.setMa(loaiDonViInput.getMa());
		loaiDonVi.setTrangThai(loaiDonViInput.getTrangThai());
		//loaiDonVi.setAppCode(appCode);
		loaiDonVi = loaiDonViService.save(loaiDonVi);
		return loaiDonVi;
	}

	public DmLoaiDonVi update(Long id, DmLoaiDonViInput loaiDonViInput) throws EntityNotFoundException {
		Optional<DmLoaiDonVi> optional = loaiDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDonVi.class, "id", String.valueOf(id));
		}
		DmLoaiDonVi loaiDonVi = optional.get();
		loaiDonVi.setTen(loaiDonViInput.getTen());
		loaiDonVi.setMa(loaiDonViInput.getMa());
		loaiDonVi.setTrangThai(loaiDonViInput.getTrangThai());
		//loaiDonVi.setAppCode(appCode);
		loaiDonVi = loaiDonViService.save(loaiDonVi);
		return loaiDonVi;
	}

	public DmLoaiDonVi delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDonVi> optional = loaiDonViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDonVi.class, "id", String.valueOf(id));
		}
		DmLoaiDonVi loaiDonVi = optional.get();
		loaiDonVi.setDaXoa(1);
		loaiDonVi = loaiDonViService.save(loaiDonVi);
		return loaiDonVi;
	}

	public List<DmLoaiDonVi> getListLoaiDonViInUseLimit20(String ten) {
		Page<DmLoaiDonVi> pageLoaiDonVi = loaiDonViService.findAll(ten, true, PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmLoaiDonVi> listLoaiDonVi = pageLoaiDonVi.getContent();
		return listLoaiDonVi;
	}

	public List<DmLoaiDonVi> getListLoaiDonViInUseLimit20AndAppCode(String appCode, String ten) {
		Page<DmLoaiDonVi> pageLoaiDonVi = loaiDonViService.findAll(ten, true,
				PageRequest.of(0, 20, Direction.ASC, "ten"));
		List<DmLoaiDonVi> listLoaiDonVi = pageLoaiDonVi.getContent();
		return listLoaiDonVi;
	}
}
