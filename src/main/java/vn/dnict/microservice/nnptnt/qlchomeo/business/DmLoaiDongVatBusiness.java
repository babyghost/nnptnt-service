package vn.dnict.microservice.nnptnt.qlchomeo.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.DmLoaiDongVatService;
import vn.dnict.microservice.nnptnt.qlchomeo.data.DmLoaiDongVatInput;

@Service
public class DmLoaiDongVatBusiness {
	@Autowired
	DmLoaiDongVatService serviceLoaiDongVatService;
	
	public Page<DmLoaiDongVat> findAll(int page, int size, String sortBy, String sortDir, String search,Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		Page<DmLoaiDongVat> pageDmLoaiDongVat = serviceLoaiDongVatService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiDongVat;
	}
	public DmLoaiDongVat findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDongVat> optional = serviceLoaiDongVatService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDongVat.class, "id", String.valueOf(id));
		}
		return optional.get();
	}



	public DmLoaiDongVat create(DmLoaiDongVatInput DmLoaiDongVatInput) {
		DmLoaiDongVat loaiDongVat = new DmLoaiDongVat();
		loaiDongVat.setDaXoa(0);
		loaiDongVat.setTen(DmLoaiDongVatInput.getTen());
		loaiDongVat.setMa(DmLoaiDongVatInput.getMa());
		loaiDongVat.setTrangThai(DmLoaiDongVatInput.getTrangThai());
		loaiDongVat = serviceLoaiDongVatService.save(loaiDongVat);
		return loaiDongVat;
	}

	public DmLoaiDongVat update(Long id, DmLoaiDongVatInput DmLoaiDongVatInput) throws EntityNotFoundException {
		Optional<DmLoaiDongVat> optional = serviceLoaiDongVatService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDongVat.class, "id", String.valueOf(id));
		}
		DmLoaiDongVat loaiDongVat = optional.get();
		loaiDongVat.setTen(DmLoaiDongVatInput.getTen());
		loaiDongVat.setMa(DmLoaiDongVatInput.getMa());
		loaiDongVat.setTrangThai(DmLoaiDongVatInput.getTrangThai());
		loaiDongVat = serviceLoaiDongVatService.save(loaiDongVat);
		return loaiDongVat;
	}

	@DeleteMapping(value = { "/{id}" })
	public DmLoaiDongVat delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDongVat> optional = serviceLoaiDongVatService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDongVat.class, "id", String.valueOf(id));
		}
		DmLoaiDongVat loaiDongVat = optional.get();
		loaiDongVat.setDaXoa(1);
		loaiDongVat = serviceLoaiDongVatService.save(loaiDongVat);
		return loaiDongVat;
	}
}
