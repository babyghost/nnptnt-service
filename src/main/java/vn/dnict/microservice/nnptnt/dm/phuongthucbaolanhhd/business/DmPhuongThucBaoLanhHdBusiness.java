package vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmPhuongThucBaoLanhHdInput;
import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;
import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service.DmPhuongThucBaoLanhHdService;

@Service
public class DmPhuongThucBaoLanhHdBusiness {
	@Autowired
	private DmPhuongThucBaoLanhHdService serviceDmPhuongThucBaoLanhHdService;

	public Page<DmPhuongThucBaoLanhHd> findAll(int page, int size, String sortBy, String sortDir, String ten, String ma,
			Boolean trangThai, Integer type) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmPhuongThucBaoLanhHd> pageDmPhuongThucBaoLanhHd = serviceDmPhuongThucBaoLanhHdService
				.findAll(ten, ma, trangThai, type, PageRequest.of(page, size, direction, sortBy));
		return pageDmPhuongThucBaoLanhHd;
	}

	public DmPhuongThucBaoLanhHd findById(Long id) throws EntityNotFoundException {
		Optional<DmPhuongThucBaoLanhHd> optional = serviceDmPhuongThucBaoLanhHdService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongThucBaoLanhHd.class, "id", id.toString());
		}
		return optional.get();
	}

	public DmPhuongThucBaoLanhHd create(DmPhuongThucBaoLanhHdInput dmPhuongThucBaoLanhHdInput) {
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = new DmPhuongThucBaoLanhHd();
		dmPhuongThucBaoLanhHd.setDaXoa(false);
		dmPhuongThucBaoLanhHd.setTen(dmPhuongThucBaoLanhHdInput.getTen());
		dmPhuongThucBaoLanhHd.setMa(dmPhuongThucBaoLanhHdInput.getMa());
		dmPhuongThucBaoLanhHd.setType(dmPhuongThucBaoLanhHdInput.getType());
		dmPhuongThucBaoLanhHd.setTrangThai(dmPhuongThucBaoLanhHdInput.getTrangThai());
		dmPhuongThucBaoLanhHd = serviceDmPhuongThucBaoLanhHdService.save(dmPhuongThucBaoLanhHd);
		return dmPhuongThucBaoLanhHd;
	}

	public DmPhuongThucBaoLanhHd update(Long id,
			DmPhuongThucBaoLanhHdInput dmPhuongThucBaoLanhHdInput) throws EntityNotFoundException {
		Optional<DmPhuongThucBaoLanhHd> optional = serviceDmPhuongThucBaoLanhHdService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongThucBaoLanhHd.class, "id", id.toString());
		}
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = optional.get();
		dmPhuongThucBaoLanhHd.setTen(dmPhuongThucBaoLanhHdInput.getTen());
		dmPhuongThucBaoLanhHd.setMa(dmPhuongThucBaoLanhHdInput.getMa());
		dmPhuongThucBaoLanhHd.setType(dmPhuongThucBaoLanhHdInput.getType());
		dmPhuongThucBaoLanhHd.setTrangThai(dmPhuongThucBaoLanhHdInput.getTrangThai());
		dmPhuongThucBaoLanhHd = serviceDmPhuongThucBaoLanhHdService.save(dmPhuongThucBaoLanhHd);
		return dmPhuongThucBaoLanhHd;
	}

	public DmPhuongThucBaoLanhHd delete(Long id) throws EntityNotFoundException {
		Optional<DmPhuongThucBaoLanhHd> optional = serviceDmPhuongThucBaoLanhHdService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongThucBaoLanhHd.class, "id", id.toString());
		}
		DmPhuongThucBaoLanhHd dmPhuongThucBaoLanhHd = optional.get();
		dmPhuongThucBaoLanhHd.setDaXoa(true);
		dmPhuongThucBaoLanhHd = serviceDmPhuongThucBaoLanhHdService.save(dmPhuongThucBaoLanhHd);
		return dmPhuongThucBaoLanhHd;
	}
}
