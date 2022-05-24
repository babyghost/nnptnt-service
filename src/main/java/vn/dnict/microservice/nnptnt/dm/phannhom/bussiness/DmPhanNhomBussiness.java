package vn.dnict.microservice.nnptnt.dm.phannhom.bussiness;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmPhanNhomInput;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomService;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.DmPhanNhomService;
@Service
public class DmPhanNhomBussiness {
	@Autowired
	DmPhanNhomService servicephanNhomService;
	
	@Autowired
	DmNhomService serviceDmNhomService;

	public Page<DmPhanNhomInput> findAll(int page, int size, String sortBy, String sortDir, String ten, Long DmNhomId ,Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmPhanNhom> pageDmPhanNhom = servicephanNhomService.findAll(ten,DmNhomId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<DmPhanNhomInput> pageDmPhanNhomInput = pageDmPhanNhom.map(this::convertToPhanNhomInput);
		return pageDmPhanNhomInput;
	}
	
	public DmPhanNhomInput convertToPhanNhomInput(DmPhanNhom dmPhanNhom) {
		DmPhanNhomInput dmPhanNhomInput = new DmPhanNhomInput();
		dmPhanNhomInput.setId(dmPhanNhom.getId());
		dmPhanNhomInput.setTen(dmPhanNhom.getTen());
		dmPhanNhomInput.setTrangThai(dmPhanNhom.getTrangThai());
		dmPhanNhomInput.setMoTa(dmPhanNhom.getMoTa());
		if (Objects.nonNull(dmPhanNhom.getDmNhomId())) {
			Optional<DmNhom> optNganhHang = serviceDmNhomService.findById(dmPhanNhom.getDmNhomId());
			if (optNganhHang.isPresent()) {
				dmPhanNhomInput.setDmNhomId(optNganhHang.get().getId());
				dmPhanNhomInput.setDmNhomTen(optNganhHang.get().getTen());
			}
		}
		return dmPhanNhomInput;
	}

	public DmPhanNhom findById(Long id) throws EntityNotFoundException {
		Optional<DmPhanNhom> optional = servicephanNhomService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanNhom.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmPhanNhom create(DmPhanNhomInput DmPhanNhomInput) {
		DmPhanNhom phanNhom = new DmPhanNhom();
		phanNhom.setDaXoa(false);
		phanNhom.setTen(DmPhanNhomInput.getTen());
		phanNhom.setMoTa(DmPhanNhomInput.getMoTa());
		phanNhom.setDmNhomId(DmPhanNhomInput.getDmNhomId());
		phanNhom.setTrangThai(DmPhanNhomInput.getTrangThai());
		phanNhom = servicephanNhomService.save(phanNhom);
		return phanNhom;
	}

	public DmPhanNhom update(Long id, DmPhanNhomInput DmPhanNhomInput) throws EntityNotFoundException {
		Optional<DmPhanNhom> optional = servicephanNhomService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanNhom.class, "id", String.valueOf(id));
		}
		DmPhanNhom phanNhom = optional.get();
		phanNhom.setTen(DmPhanNhomInput.getTen());
		phanNhom.setMoTa(DmPhanNhomInput.getMoTa());
		phanNhom.setDmNhomId(DmPhanNhomInput.getDmNhomId());
		phanNhom.setTrangThai(DmPhanNhomInput.getTrangThai());
		phanNhom = servicephanNhomService.save(phanNhom);
		return phanNhom;
	}
	public DmPhanNhom delete(Long id) throws EntityNotFoundException {
		Optional<DmPhanNhom> optional = servicephanNhomService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanNhom.class, "id", String.valueOf(id));
		}
		DmPhanNhom phanNhom = optional.get();
		phanNhom.setDaXoa(true);
		phanNhom = servicephanNhomService.save(phanNhom);
		return phanNhom;
	}
}
