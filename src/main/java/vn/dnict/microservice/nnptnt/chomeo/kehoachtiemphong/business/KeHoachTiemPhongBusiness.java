package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;

@Service
public class KeHoachTiemPhongBusiness {
	@Autowired
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	@Autowired
	ThoiGianTiemPhongService serviceThoiGianTiemPhongService;
	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;
	
	public Page<KeHoachTiemPhong> findAll(int page, int size, String sortBy, String sortDir, String search,String noiDung, String soKeHoach, String tenKeHoach, LocalDateTime ngayBanHanh,LocalDateTime ngayDuKienTuNgay,LocalDateTime ngayDuKienDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<KeHoachTiemPhong> pageKeHoachTiemPhong = serviceKeHoachTiemPhongService.findAll(search, noiDung, soKeHoach, tenKeHoach, ngayBanHanh, ngayDuKienTuNgay, ngayDuKienDenNgay,
				PageRequest.of(page, size, direction, sortBy));
		return pageKeHoachTiemPhong;
	}
	public KeHoachTiemPhong findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		return optional.get();
	}



	public KeHoachTiemPhong create(KeHoachTiemPhongInput KeHoachTiemPhongInput) {
		KeHoachTiemPhong KeHoachTiemPhong = new KeHoachTiemPhong();
		KeHoachTiemPhong.setDaXoa(false);
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongInput.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongInput.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongInput.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongInput.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongInput.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongInput.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return KeHoachTiemPhong;
	}

	public KeHoachTiemPhong update(Long id, KeHoachTiemPhongInput KeHoachTiemPhongInput) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongInput.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongInput.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongInput.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongInput.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongInput.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongInput.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return KeHoachTiemPhong;
	}

	@DeleteMapping(value = { "/{id}" })
	public KeHoachTiemPhong delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setDaXoa(true);
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return KeHoachTiemPhong;
	}
}