package vn.dnict.microservice.danhmuc.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;
import vn.dnict.microservice.danhmuc.dao.service.DmNguonKinhPhiService;
import vn.dnict.microservice.danhmuc.data.DmNguonKinhPhiInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service

public class DmNguonKinhPhiBusiness {
	@Autowired
	private DmNguonKinhPhiService dmNguonKinhPhiService;

	public Page<DmNguonKinhPhi> findAll(int page, int size, String sortBy, String sortDir, String search,
			Boolean trangThai, Long id, Long chaId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmNguonKinhPhi> pageDmNguonKinhPhi = dmNguonKinhPhiService.findAll(search, trangThai, id, chaId,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmNguonKinhPhi;
	}

	public DmNguonKinhPhi findById(Long id) throws EntityNotFoundException {
		Optional<DmNguonKinhPhi> optional = dmNguonKinhPhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNguonKinhPhi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public List<DmNguonKinhPhi> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return dmNguonKinhPhiService.findByIdIn(ids);
	}

	public DmNguonKinhPhi create(DmNguonKinhPhiInput dmDmNguonKinhPhiInput) {
		DmNguonKinhPhi dmNguonKinhPhi = new DmNguonKinhPhi();
		dmNguonKinhPhi.setTen(dmDmNguonKinhPhiInput.getTen());
		dmNguonKinhPhi.setDaXoa(false);
		dmNguonKinhPhi.setMa(dmDmNguonKinhPhiInput.getMa());
		dmNguonKinhPhi.setTrangThai(dmDmNguonKinhPhiInput.getTrangThai());
		dmNguonKinhPhi.setChaId(null);
		if (Objects.nonNull(dmDmNguonKinhPhiInput.getChaId())) {
			Optional<DmNguonKinhPhi> optionalDmNguonKinhPhiCha = dmNguonKinhPhiService
					.findById(dmDmNguonKinhPhiInput.getChaId());
			if (optionalDmNguonKinhPhiCha.isPresent()) {
				dmNguonKinhPhi.setChaId(optionalDmNguonKinhPhiCha.get().getId());
			}
		}
		dmNguonKinhPhi = dmNguonKinhPhiService.save(dmNguonKinhPhi);
		return dmNguonKinhPhi;
	}

	public DmNguonKinhPhi update(Long id, DmNguonKinhPhiInput dmDmNguonKinhPhiInput) throws EntityNotFoundException {
		Optional<DmNguonKinhPhi> optional = dmNguonKinhPhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNguonKinhPhi.class, "id", String.valueOf(id));
		}
		DmNguonKinhPhi dmNguonKinhPhi = optional.get();
		dmNguonKinhPhi.setTen(dmDmNguonKinhPhiInput.getTen());
		dmNguonKinhPhi.setMa(dmDmNguonKinhPhiInput.getMa());
		dmNguonKinhPhi.setTrangThai(dmDmNguonKinhPhiInput.getTrangThai());
		dmNguonKinhPhi.setChaId(null);
		if (Objects.nonNull(dmDmNguonKinhPhiInput.getChaId())) {
			Optional<DmNguonKinhPhi> optionalDmNguonKinhPhiCha = dmNguonKinhPhiService
					.findById(dmDmNguonKinhPhiInput.getChaId());
			if (optionalDmNguonKinhPhiCha.isPresent()) {
				dmNguonKinhPhi.setChaId(optionalDmNguonKinhPhiCha.get().getId());
			}
		}
		dmNguonKinhPhi = dmNguonKinhPhiService.save(dmNguonKinhPhi);
		return dmNguonKinhPhi;
	}

	public DmNguonKinhPhi delete(Long id) throws EntityNotFoundException {
		Optional<DmNguonKinhPhi> optional = dmNguonKinhPhiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNguonKinhPhi.class, "id", String.valueOf(id));
		}
		DmNguonKinhPhi dmNguonKinhPhi = optional.get();
		dmNguonKinhPhi.setDaXoa(true);
		dmNguonKinhPhi = dmNguonKinhPhiService.save(dmNguonKinhPhi);
		return dmNguonKinhPhi;
	}

}
