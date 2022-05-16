
package vn.dnict.microservice.nnptnt.dm.linhvuc.business;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;
import vn.dnict.microservice.nnptnt.dm.linhvuc.data.DmLinhVucInput;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;


@Service
public class DmLinhVucBusiness {
	
	@Autowired
	DmLinhVucService serviceLinhVucService;
	@Autowired
	DmLoaiDongVatService serviceDmLoaiDongVatService;
	
	public Page<DmLinhVuc> findAll(int page, int size, String sortBy, String sortDir, String search,Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLinhVuc> pageDmLinhVuc = serviceLinhVucService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLinhVuc;
	}
	public DmLinhVuc findById(Long id) throws EntityNotFoundException {
		Optional<DmLinhVuc> optional = serviceLinhVucService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLinhVuc.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	
	


	public DmLinhVuc create(DmLinhVucInput DmLinhVucInput) {
		DmLinhVuc linhVuc = new DmLinhVuc();
		linhVuc.setDaXoa(false);
		linhVuc.setTen(DmLinhVucInput.getTen());
		linhVuc.setTrangThai(DmLinhVucInput.getTrangThai());
		linhVuc = serviceLinhVucService.save(linhVuc);
		return linhVuc;
	}

	public DmLinhVuc update(Long id, DmLinhVucInput DmLinhVucInput) throws EntityNotFoundException {
		Optional<DmLinhVuc> optional = serviceLinhVucService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLinhVuc.class, "id", String.valueOf(id));
		}
		DmLinhVuc linhVuc = optional.get();
		linhVuc.setTen(DmLinhVucInput.getTen());
		linhVuc.setTrangThai(DmLinhVucInput.getTrangThai());
		linhVuc = serviceLinhVucService.save(linhVuc);
		return linhVuc;
	}

	@DeleteMapping(value = { "/{id}" })
	public DmLinhVuc delete(Long id) throws EntityNotFoundException {
		Optional<DmLinhVuc> optional = serviceLinhVucService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLinhVuc.class, "id", String.valueOf(id));
		}
		DmLinhVuc linhVuc = optional.get();
		linhVuc.setDaXoa(true);
		linhVuc = serviceLinhVucService.save(linhVuc);
		return linhVuc;
	}
}

