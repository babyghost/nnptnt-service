package vn.dnict.microservice.nnptnt.dm.loaihinh.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmLoaiHinhInput;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.DmLoaiHinhService;
@Service
public class DmLoaiHinhBussiness {
	@Autowired
	DmLoaiHinhService serviceLoaiHinhService;

	public Page<DmLoaiHinh> findAll(int page, int size, String sortBy, String sortDir, String ten, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmLoaiHinh> pageDmLoaiHinh = serviceLoaiHinhService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiHinh;
	}

	public DmLoaiHinh findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiHinh> optional = serviceLoaiHinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinh.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmLoaiHinh create(DmLoaiHinhInput DmLoaiHinhInput) {
		DmLoaiHinh loaiHinh = new DmLoaiHinh();
		loaiHinh.setDaXoa(false);
		loaiHinh.setTen(DmLoaiHinhInput.getTen());
		loaiHinh.setTrangThai(DmLoaiHinhInput.getTrangThai());
		loaiHinh = serviceLoaiHinhService.save(loaiHinh);
		return loaiHinh;
	}

	public DmLoaiHinh update(Long id, DmLoaiHinhInput DmLoaiHinhInput) throws EntityNotFoundException {
		Optional<DmLoaiHinh> optional = serviceLoaiHinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinh.class, "id", String.valueOf(id));
		}
		DmLoaiHinh loaiHinh = optional.get();
		loaiHinh.setTen(DmLoaiHinhInput.getTen());

		loaiHinh.setTrangThai(DmLoaiHinhInput.getTrangThai());
		loaiHinh = serviceLoaiHinhService.save(loaiHinh);
		return loaiHinh;
	}
	public DmLoaiHinh delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiHinh> optional = serviceLoaiHinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHinh.class, "id", String.valueOf(id));
		}
		DmLoaiHinh loaiHinh = optional.get();
		loaiHinh.setDaXoa(true);
		loaiHinh = serviceLoaiHinhService.save(loaiHinh);
		return loaiHinh;
	}
}
