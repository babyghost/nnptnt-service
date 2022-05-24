package vn.dnict.microservice.nnptnt.dm.phanhang.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmPhanHangInput;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.DmPhanHangService;
@Service
public class DmPhanHangBussiness {
	@Autowired
	DmPhanHangService servicePhanHangService;

	public Page<DmPhanHang> findAll(int page, int size, String sortBy, String sortDir, String ten, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DmPhanHang> pageDmPhanHang = servicePhanHangService.findAll(ten, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageDmPhanHang;
	}

	public DmPhanHang findById(Long id) throws EntityNotFoundException {
		Optional<DmPhanHang> optional = servicePhanHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanHang.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public DmPhanHang create(DmPhanHangInput DmPhanHangInput) {
		DmPhanHang phanHang = new DmPhanHang();
		phanHang.setDaXoa(false);
		phanHang.setTen(DmPhanHangInput.getTen());
		phanHang.setDiemTrungBinhTu(DmPhanHangInput.getDiemTrungBinhTu());
		phanHang.setDiemTrungBinhDen(DmPhanHangInput.getDiemTrungBinhDen());
		phanHang.setMoTa(DmPhanHangInput.getMoTa());
		phanHang.setTrangThai(DmPhanHangInput.getTrangThai());
		phanHang = servicePhanHangService.save(phanHang);
		return phanHang;
	}

	public DmPhanHang update(Long id, DmPhanHangInput DmPhanHangInput) throws EntityNotFoundException {
		Optional<DmPhanHang> optional = servicePhanHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanHang.class, "id", String.valueOf(id));
		}
		DmPhanHang phanHang = optional.get();
		phanHang.setTen(DmPhanHangInput.getTen());
		phanHang.setDiemTrungBinhTu(DmPhanHangInput.getDiemTrungBinhTu());
		phanHang.setDiemTrungBinhDen(DmPhanHangInput.getDiemTrungBinhDen());
		phanHang.setMoTa(DmPhanHangInput.getMoTa());
		phanHang.setTrangThai(DmPhanHangInput.getTrangThai());
		phanHang = servicePhanHangService.save(phanHang);
		return phanHang;
	}
	public DmPhanHang delete(Long id) throws EntityNotFoundException {
		Optional<DmPhanHang> optional = servicePhanHangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhanHang.class, "id", String.valueOf(id));
		}
		DmPhanHang phanHang = optional.get();
		phanHang.setDaXoa(true);
		phanHang = servicePhanHangService.save(phanHang);
		return phanHang;
	}
}
