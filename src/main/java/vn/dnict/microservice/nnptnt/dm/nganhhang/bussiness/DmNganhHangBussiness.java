package vn.dnict.microservice.nnptnt.dm.nganhhang.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNganhHangInput;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangService;
@Service
public class DmNganhHangBussiness {
	@Autowired
	DmNganhHangService serviceNganhHangService;

	public Page<DmNganhHang> findAll(int page, int size, String sortBy, String sortDir, String ten, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmNganhHang> pageDmNganhHang = serviceNganhHangService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmNganhHang;
	}

	public DmNganhHang findById(Long id) throws EntityNotFoundException {
		Optional<DmNganhHang> optional = serviceNganhHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhHang.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmNganhHang create(DmNganhHangInput DmNganhHangInput) {
		DmNganhHang nganhHang = new DmNganhHang();
		nganhHang.setDaXoa(false);
		nganhHang.setTen(DmNganhHangInput.getTen());
		nganhHang.setTrangThai(DmNganhHangInput.getTrangThai());
		nganhHang = serviceNganhHangService.save(nganhHang);
		return nganhHang;
	}

	public DmNganhHang update(Long id, DmNganhHangInput DmNganhHangInput) throws EntityNotFoundException {
		Optional<DmNganhHang> optional = serviceNganhHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhHang.class, "id", String.valueOf(id));
		}
		DmNganhHang nganhHang = optional.get();
		nganhHang.setTen(DmNganhHangInput.getTen());

		nganhHang.setTrangThai(DmNganhHangInput.getTrangThai());
		nganhHang = serviceNganhHangService.save(nganhHang);
		return nganhHang;
	}
	public DmNganhHang delete(Long id) throws EntityNotFoundException {
		Optional<DmNganhHang> optional = serviceNganhHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNganhHang.class, "id", String.valueOf(id));
		}
		DmNganhHang nganhHang = optional.get();
		nganhHang.setDaXoa(true);
		nganhHang = serviceNganhHangService.save(nganhHang);
		return nganhHang;
	}
}
