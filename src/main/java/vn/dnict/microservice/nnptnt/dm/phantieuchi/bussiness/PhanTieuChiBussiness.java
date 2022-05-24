package vn.dnict.microservice.nnptnt.dm.phantieuchi.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNganhHangInput;
import vn.dnict.microservice.nnptnt.dm.data.PhanTieuChiInput;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.model.PhanTieuChi;
import vn.dnict.microservice.nnptnt.dm.phantieuchi.dao.service.PhanTieuChiService;
@Service
public class PhanTieuChiBussiness {
	@Autowired
	PhanTieuChiService servicePhanTieuChiService;

	public Page<PhanTieuChi> findAll(int page, int size, String sortBy, String sortDir, String ten, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<PhanTieuChi> pagePhanTieuChi = servicePhanTieuChiService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pagePhanTieuChi;
	}

	public PhanTieuChi findById(Long id) throws EntityNotFoundException {
		Optional<PhanTieuChi> optional = servicePhanTieuChiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(PhanTieuChi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public PhanTieuChi create(PhanTieuChiInput phanTieuChiInput) {
		PhanTieuChi phanTieuChi = new PhanTieuChi();
		phanTieuChi.setDaXoa(false);
		phanTieuChi.setTen(phanTieuChiInput.getTen());
		phanTieuChi.setTrangThai(phanTieuChiInput.getTrangThai());
		phanTieuChi = servicePhanTieuChiService.save(phanTieuChi);
		return phanTieuChi;
	}

	public PhanTieuChi update(Long id, PhanTieuChiInput PhanTieuChiInput) throws EntityNotFoundException {
		Optional<PhanTieuChi> optional = servicePhanTieuChiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(PhanTieuChi.class, "id", String.valueOf(id));
		}
		PhanTieuChi phanTieuChi = optional.get();
		phanTieuChi.setTen(PhanTieuChiInput.getTen());

		phanTieuChi.setTrangThai(PhanTieuChiInput.getTrangThai());
		phanTieuChi = servicePhanTieuChiService.save(phanTieuChi);
		return phanTieuChi;
	}
	public PhanTieuChi delete(Long id) throws EntityNotFoundException {
		Optional<PhanTieuChi> optional = servicePhanTieuChiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(PhanTieuChi.class, "id", String.valueOf(id));
		}
		PhanTieuChi phanTieuChi = optional.get();
		phanTieuChi.setDaXoa(true);
		phanTieuChi = servicePhanTieuChiService.save(phanTieuChi);
		return phanTieuChi;
	}
}
