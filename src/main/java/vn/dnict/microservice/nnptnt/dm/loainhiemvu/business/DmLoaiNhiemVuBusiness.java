package vn.dnict.microservice.nnptnt.dm.loainhiemvu.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;

@Service
public class DmLoaiNhiemVuBusiness {
	@Autowired
	DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	
	public Page<DmLoaiNhiemVu> findAll(int page, int size, String sortBy, String sortDir, String search, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		Page<DmLoaiNhiemVu> pageDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiNhiemVu;
	}
	
	public DmLoaiNhiemVu findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiNhiemVu> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	public DmLoaiNhiemVu create(DmLoaiNhiemVuData dmLoaiNhiemVuData) {
		DmLoaiNhiemVu dmLoaiNhiemVu = new DmLoaiNhiemVu();
		dmLoaiNhiemVu.setDaXoa(false);
		dmLoaiNhiemVu.setTen(dmLoaiNhiemVuData.getTen());
		dmLoaiNhiemVu.setMa(dmLoaiNhiemVuData.getMa());
		dmLoaiNhiemVu.setSapXep(dmLoaiNhiemVuData.getSapXep());
		dmLoaiNhiemVu.setTrangThai(dmLoaiNhiemVuData.getTrangThai());
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}
	
	public DmLoaiNhiemVu update(Long id, DmLoaiNhiemVuData dmLoaiNhiemVuData) throws EntityNotFoundException {
		Optional<DmLoaiNhiemVu> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = optional.get();
		dmLoaiNhiemVu.setTen(dmLoaiNhiemVuData.getTen());
		dmLoaiNhiemVu.setMa(dmLoaiNhiemVuData.getMa());
		dmLoaiNhiemVu.setSapXep(dmLoaiNhiemVuData.getSapXep());
		dmLoaiNhiemVu.setTrangThai(dmLoaiNhiemVuData.getTrangThai());
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public DmLoaiNhiemVu delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiNhiemVu> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = optional.get();
		dmLoaiNhiemVu.setDaXoa(true);
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}
}
