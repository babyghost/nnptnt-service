package vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmLoaiDoanhNghiepInput;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.DmLoaiDoanhNghiepOCOPService;
@Service
public class DmLoaiDoanhNghiepBussiness {
	@Autowired
	DmLoaiDoanhNghiepOCOPService serviceLoaiDoanhNghiepService;

	public Page<DmLoaiDoanhNghiepOCOP> findAll(int page, int size, String sortBy, String sortDir, String ten, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmLoaiDoanhNghiepOCOP> pageDmLoaiDoanhNghiep = serviceLoaiDoanhNghiepService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiDoanhNghiep;
	}

	public DmLoaiDoanhNghiepOCOP findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiepOCOP> optional = serviceLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiepOCOP.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmLoaiDoanhNghiepOCOP create(DmLoaiDoanhNghiepInput DmLoaiDoanhNghiepInput) {
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = new DmLoaiDoanhNghiepOCOP();
		loaiDoanhNghiep.setDaXoa(false);
		loaiDoanhNghiep.setTen(DmLoaiDoanhNghiepInput.getTen());
		loaiDoanhNghiep.setTrangThai(DmLoaiDoanhNghiepInput.getTrangThai());
		loaiDoanhNghiep = serviceLoaiDoanhNghiepService.save(loaiDoanhNghiep);
		return loaiDoanhNghiep;
	}

	public DmLoaiDoanhNghiepOCOP update(Long id, DmLoaiDoanhNghiepInput DmLoaiDoanhNghiepInput) throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiepOCOP> optional = serviceLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiepOCOP.class, "id", String.valueOf(id));
		}
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = optional.get();
		loaiDoanhNghiep.setTen(DmLoaiDoanhNghiepInput.getTen());

		loaiDoanhNghiep.setTrangThai(DmLoaiDoanhNghiepInput.getTrangThai());
		loaiDoanhNghiep = serviceLoaiDoanhNghiepService.save(loaiDoanhNghiep);
		return loaiDoanhNghiep;
	}
	public DmLoaiDoanhNghiepOCOP delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiDoanhNghiepOCOP> optional = serviceLoaiDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDoanhNghiepOCOP.class, "id", String.valueOf(id));
		}
		DmLoaiDoanhNghiepOCOP loaiDoanhNghiep = optional.get();
		loaiDoanhNghiep.setDaXoa(true);
		loaiDoanhNghiep = serviceLoaiDoanhNghiepService.save(loaiDoanhNghiep);
		return loaiDoanhNghiep;
	}
}
