package vn.dnict.microservice.nnptnt.dm.nganhnghe.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNganhNgheInput;

import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.DmNganhNgheOCOPService;
@Service
public class DmNganhNgheOCOPBussiness {
	@Autowired
	DmNganhNgheOCOPService serviceNganhNgheService;

	public Page<DmNganhNgheOCOP> findAll(int page, int size, String sortBy, String sortDir, String ten, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmNganhNgheOCOP> pageDmnganhNghe = serviceNganhNgheService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmnganhNghe;
	}

	public DmNganhNgheOCOP findById(Long id) throws EntityNotFoundException {
		Optional<DmNganhNgheOCOP> optional = serviceNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNgheOCOP.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmNganhNgheOCOP create(DmNganhNgheInput DmnganhNgheInput) {
		DmNganhNgheOCOP nganhNghe = new DmNganhNgheOCOP();
		nganhNghe.setDaXoa(false);
		nganhNghe.setTen(DmnganhNgheInput.getTen());
		nganhNghe.setTrangThai(DmnganhNgheInput.getTrangThai());
		nganhNghe = serviceNganhNgheService.save(nganhNghe);
		return nganhNghe;
	}

	public DmNganhNgheOCOP update(Long id, DmNganhNgheInput DmnganhNgheInput) throws EntityNotFoundException {
		Optional<DmNganhNgheOCOP> optional = serviceNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNgheOCOP.class, "id", String.valueOf(id));
		}
		DmNganhNgheOCOP nganhNghe = optional.get();
		nganhNghe.setTen(DmnganhNgheInput.getTen());

		nganhNghe.setTrangThai(DmnganhNgheInput.getTrangThai());
		nganhNghe = serviceNganhNgheService.save(nganhNghe);
		return nganhNghe;
	}
	public DmNganhNgheOCOP delete(Long id) throws EntityNotFoundException {
		Optional<DmNganhNgheOCOP> optional = serviceNganhNgheService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhNgheOCOP.class, "id", String.valueOf(id));
		}
		DmNganhNgheOCOP nganhNghe = optional.get();
		nganhNghe.setDaXoa(true);
		nganhNghe = serviceNganhNgheService.save(nganhNghe);
		return nganhNghe;
	}
}
