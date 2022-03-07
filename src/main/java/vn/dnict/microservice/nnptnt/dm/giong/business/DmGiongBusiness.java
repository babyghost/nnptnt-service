
package vn.dnict.microservice.nnptnt.dm.giong.business;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.DmGiongInput;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;


@Service
public class DmGiongBusiness {
	
	@Autowired
	DmGiongService serviceGiongService;
	@Autowired
	DmLoaiDongVatService serviceDmLoaiDongVatService;
	
	public Page<DmGiong> findAll(int page, int size, String sortBy, String sortDir, String search,Long LoaiVatNuoiId,Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmGiong> pageDmGiong = serviceGiongService.findAll(search,LoaiVatNuoiId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmGiong;
	}
	public DmGiong findById(Long id) throws EntityNotFoundException {
		Optional<DmGiong> optional = serviceGiongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGiong.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	
	public DmLoaiDongVat findDmLoaiDongVatByDmGiongId(Long id) throws EntityNotFoundException {
		Optional<DmGiong> optional = serviceGiongService.findById(id);
		
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGiong.class, "id", String.valueOf(id));
		}
		DmGiong DmGiong = optional.get();
		Optional<DmLoaiDongVat> optionalDmLoaiDongVat = serviceDmLoaiDongVatService.findById(DmGiong.getLoaiDongVatId());
		if (!optionalDmLoaiDongVat.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDongVat.class, "id", String.valueOf(id));
		}
		return optionalDmLoaiDongVat.get();
	}
	


	public DmGiong create(DmGiongInput DmGiongInput) {
		DmGiong Giong = new DmGiong();
		Giong.setDaXoa(0);
		Giong.setLoaiDongVatId(DmGiongInput.getLoaiDongVatId());
		Giong.setTen(DmGiongInput.getTen());
		Giong.setMa(DmGiongInput.getMa());
		Giong.setTrangThai(DmGiongInput.getTrangThai());
		Giong = serviceGiongService.save(Giong);
		return Giong;
	}

	public DmGiong update(Long id, DmGiongInput DmGiongInput) throws EntityNotFoundException {
		Optional<DmGiong> optional = serviceGiongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGiong.class, "id", String.valueOf(id));
		}
		DmGiong Giong = optional.get();
		Giong.setLoaiDongVatId(DmGiongInput.getLoaiDongVatId());
		Giong.setTen(DmGiongInput.getTen());
		Giong.setMa(DmGiongInput.getMa());
		Giong.setTrangThai(DmGiongInput.getTrangThai());
		Giong = serviceGiongService.save(Giong);
		return Giong;
	}

	@DeleteMapping(value = { "/{id}" })
	public DmGiong delete(Long id) throws EntityNotFoundException {
		Optional<DmGiong> optional = serviceGiongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmGiong.class, "id", String.valueOf(id));
		}
		DmGiong Giong = optional.get();
		Giong.setDaXoa(1);
		Giong = serviceGiongService.save(Giong);
		return Giong;
	}
}

