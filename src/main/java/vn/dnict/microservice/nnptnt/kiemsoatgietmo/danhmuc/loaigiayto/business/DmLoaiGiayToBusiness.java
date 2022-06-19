package vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.model.DmLoaiGiayTo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.DmLoaiGiayToService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.DmLoaiGiayToData;

@Service
public class DmLoaiGiayToBusiness {
	@Autowired
	DmLoaiGiayToService serviceDmLoaiGiayToService;
	
	public Page<DmLoaiGiayTo> findAll(int page, int size, String sortBy, String sortDir, String search,Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLoaiGiayTo> pageDmLoaiGiayTo = serviceDmLoaiGiayToService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiGiayTo;
	}
	
	public DmLoaiGiayTo findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiGiayTo> optional = serviceDmLoaiGiayToService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	public DmLoaiGiayTo create(DmLoaiGiayToData dmLoaiGiayToData) {
		DmLoaiGiayTo loaiGiayTo = new DmLoaiGiayTo();
		loaiGiayTo.setDaXoa(false);
		loaiGiayTo.setTen(dmLoaiGiayToData.getTen());
		loaiGiayTo.setMa(dmLoaiGiayToData.getMa());
		loaiGiayTo.setTrangThai(dmLoaiGiayToData.getTrangThai());
		loaiGiayTo = serviceDmLoaiGiayToService.save(loaiGiayTo);
		return loaiGiayTo;
	}
	
	public DmLoaiGiayTo update(Long id, DmLoaiGiayToData dmLoaiGiayToData) throws EntityNotFoundException {
		Optional<DmLoaiGiayTo> optional = serviceDmLoaiGiayToService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		DmLoaiGiayTo loaiGiayTo = optional.get();
		loaiGiayTo.setTen(dmLoaiGiayToData.getTen());
		loaiGiayTo.setMa(dmLoaiGiayToData.getMa());
		loaiGiayTo.setTrangThai(dmLoaiGiayToData.getTrangThai());
		loaiGiayTo = serviceDmLoaiGiayToService.save(loaiGiayTo);
		return loaiGiayTo;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public DmLoaiGiayTo delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiGiayTo> optional = serviceDmLoaiGiayToService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		DmLoaiGiayTo loaiGiayTo = optional.get();
		loaiGiayTo.setDaXoa(true);
		loaiGiayTo = serviceDmLoaiGiayToService.save(loaiGiayTo);
		return loaiGiayTo;
	}
}
