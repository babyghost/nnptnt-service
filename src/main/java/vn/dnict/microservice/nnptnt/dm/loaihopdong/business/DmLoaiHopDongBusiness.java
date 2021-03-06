package vn.dnict.microservice.nnptnt.dm.loaihopdong.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmLoaiHopDongInput;
import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.model.DmLoaiHopDong;
import vn.dnict.microservice.nnptnt.dm.loaihopdong.dao.service.DmLoaiHopDongService;

@Service
public class DmLoaiHopDongBusiness {
	@Autowired
	private DmLoaiHopDongService serviceDmLoaiHopDongService;

	public Page<DmLoaiHopDong> findAll(int page, int size, String sortBy, String sortDir, String ten, String ma,
			Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmLoaiHopDong> pageDmLoaiHopDong = serviceDmLoaiHopDongService.findAll(ten, ma, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmLoaiHopDong;
	}

	public DmLoaiHopDong findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiHopDong> optional = serviceDmLoaiHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHopDong.class, "id", id.toString());
		}
		return optional.get();
	}

	public DmLoaiHopDong create(DmLoaiHopDongInput dmLoaiHopDongInput) {
		DmLoaiHopDong dmLoaiHopDong = new DmLoaiHopDong();
		dmLoaiHopDong.setDaXoa(false);
		dmLoaiHopDong.setTen(dmLoaiHopDongInput.getTen());
		dmLoaiHopDong.setMa(dmLoaiHopDongInput.getMa());
		dmLoaiHopDong.setTrangThai(dmLoaiHopDongInput.getTrangThai());
		dmLoaiHopDong = serviceDmLoaiHopDongService.save(dmLoaiHopDong);
		return dmLoaiHopDong;
	}

	public DmLoaiHopDong update(Long id, DmLoaiHopDongInput dmLoaiHopDongInput)
			throws EntityNotFoundException {
		Optional<DmLoaiHopDong> optional = serviceDmLoaiHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHopDong.class, "id", id.toString());
		}
		DmLoaiHopDong dmLoaiHopDong = optional.get();
		dmLoaiHopDong.setTen(dmLoaiHopDongInput.getTen());
		dmLoaiHopDong.setMa(dmLoaiHopDongInput.getMa());
		dmLoaiHopDong.setTrangThai(dmLoaiHopDongInput.getTrangThai());
		dmLoaiHopDong = serviceDmLoaiHopDongService.save(dmLoaiHopDong);
		return dmLoaiHopDong;
	}

	public DmLoaiHopDong delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiHopDong> optional = serviceDmLoaiHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiHopDong.class, "id", id.toString());
		}
		DmLoaiHopDong loaiHopDong = optional.get();
		loaiHopDong.setDaXoa(true);
		loaiHopDong = serviceDmLoaiHopDongService.save(loaiHopDong);
		return loaiHopDong;
	}
}