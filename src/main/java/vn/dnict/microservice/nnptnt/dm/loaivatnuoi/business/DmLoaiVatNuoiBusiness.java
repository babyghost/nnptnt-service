package vn.dnict.microservice.nnptnt.dm.loaivatnuoi.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.DmLoaiVatNuoiInput;

@Service
public class DmLoaiVatNuoiBusiness {
	@Autowired
	DmLoaiVatNuoiService serviceLoaiVatNuoiService;
	
	public Page<DmLoaiVatNuoi> findAll(int page, int size, String sortBy, String sortDir, String search,Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLoaiVatNuoi> pageDmLoaiVatNuoi = serviceLoaiVatNuoiService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiVatNuoi;
	}
	public DmLoaiVatNuoi findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiVatNuoi> optional = serviceLoaiVatNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}



	public DmLoaiVatNuoi create(DmLoaiVatNuoiInput DmLoaiVatNuoiInput) {
		DmLoaiVatNuoi loaiVatNuoi = new DmLoaiVatNuoi();
		loaiVatNuoi.setDaXoa(false);
		loaiVatNuoi.setTen(DmLoaiVatNuoiInput.getTen());
		loaiVatNuoi.setTrangThai(DmLoaiVatNuoiInput.getTrangThai());
		loaiVatNuoi = serviceLoaiVatNuoiService.save(loaiVatNuoi);
		return loaiVatNuoi;
	}

	public DmLoaiVatNuoi update(Long id, DmLoaiVatNuoiInput DmLoaiVatNuoiInput) throws EntityNotFoundException {
		Optional<DmLoaiVatNuoi> optional = serviceLoaiVatNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		DmLoaiVatNuoi loaiVatNuoi = optional.get();
		loaiVatNuoi.setTen(DmLoaiVatNuoiInput.getTen());
		loaiVatNuoi.setTrangThai(DmLoaiVatNuoiInput.getTrangThai());
		loaiVatNuoi = serviceLoaiVatNuoiService.save(loaiVatNuoi);
		return loaiVatNuoi;
	}

	@DeleteMapping(value = { "/{id}" })
	public DmLoaiVatNuoi delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiVatNuoi> optional = serviceLoaiVatNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		DmLoaiVatNuoi loaiVatNuoi = optional.get();
		loaiVatNuoi.setDaXoa(true);
		loaiVatNuoi = serviceLoaiVatNuoiService.save(loaiVatNuoi);
		return loaiVatNuoi;
	}
}
