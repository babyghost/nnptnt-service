
package vn.dnict.microservice.nnptnt.qlchomeo.business;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.qlchomeo.data.DmGiongInput;


@Service
public class DmGiongBusiness {
	
	@Autowired
	DmGiongService serviceGiongService;
	
	public Page<DmGiong> findAll(int page, int size, String sortBy, String sortDir, String search,Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmGiong> pageDmGiong = serviceGiongService.findAll(search, trangThai,
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



	public DmGiong create(DmGiongInput DmGiongInput) {
		DmGiong Giong = new DmGiong();
		Giong.setDaXoa(0);
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

