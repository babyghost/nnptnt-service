package vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoach2ChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;

@Service
public class KeHoach2ChoMeoBusiness {
	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;
	
	@Autowired
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	
	public Page<KeHoach2ChoMeo> findAll(int page, int size, String sortBy, String sortDir, String search, Long thongTinChoMeoId, Long keHoachTiemPhongId, boolean trangThaiTiem) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<KeHoach2ChoMeo> pageKeHoach2ChoMeo = serviceKeHoach2ChoMeoService.findAll(search, thongTinChoMeoId, keHoachTiemPhongId, trangThaiTiem,
				PageRequest.of(page, size, direction, sortBy));
		return pageKeHoach2ChoMeo;
	}
	public KeHoach2ChoMeo findById(Long id) throws EntityNotFoundException {
		Optional<KeHoach2ChoMeo> optional = serviceKeHoach2ChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach2ChoMeo.class, "id", String.valueOf(id));
		}
		return optional.get();
	}



	public KeHoach2ChoMeo create(KeHoach2ChoMeoInput KeHoach2ChoMeoInput) {
		KeHoach2ChoMeo keHoach2ChoMeo = new KeHoach2ChoMeo();
		keHoach2ChoMeo.setDaXoa(true);
		keHoach2ChoMeo.setThongTinChoMeoId(KeHoach2ChoMeoInput.getThongTinChoMeoId());
		keHoach2ChoMeo.setKeHoachTiemPhongId(KeHoach2ChoMeoInput.getKeHoachTiemPhongId());
		keHoach2ChoMeo.setNgayTiemPhong(KeHoach2ChoMeoInput.getNgayTiemPhong());
		keHoach2ChoMeo.setTrangThaiTiem(KeHoach2ChoMeoInput.isTrangThaiTiem());
		keHoach2ChoMeo = serviceKeHoach2ChoMeoService.save(keHoach2ChoMeo);
		return keHoach2ChoMeo;
	}

	public KeHoach2ChoMeo update(Long id, KeHoach2ChoMeoInput KeHoach2ChoMeoInput) throws EntityNotFoundException {
		Optional<KeHoach2ChoMeo> optional = serviceKeHoach2ChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach2ChoMeo.class, "id", String.valueOf(id));
		}
		KeHoach2ChoMeo keHoach2ChoMeo = optional.get();
		keHoach2ChoMeo.setThongTinChoMeoId(KeHoach2ChoMeoInput.getThongTinChoMeoId());
		keHoach2ChoMeo.setKeHoachTiemPhongId(KeHoach2ChoMeoInput.getKeHoachTiemPhongId());
		keHoach2ChoMeo.setNgayTiemPhong(KeHoach2ChoMeoInput.getNgayTiemPhong());
		keHoach2ChoMeo.setTrangThaiTiem(KeHoach2ChoMeoInput.isTrangThaiTiem());
		keHoach2ChoMeo = serviceKeHoach2ChoMeoService.save(keHoach2ChoMeo);
		return keHoach2ChoMeo;
	}

	@DeleteMapping(value = { "/{id}" })
	public KeHoach2ChoMeo delete(Long id) throws EntityNotFoundException {
		Optional<KeHoach2ChoMeo> optional = serviceKeHoach2ChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach2ChoMeo.class, "id", String.valueOf(id));
		}
		KeHoach2ChoMeo keHoach2ChoMeo = optional.get();
		keHoach2ChoMeo.setDaXoa(false);
		keHoach2ChoMeo = serviceKeHoach2ChoMeoService.save(keHoach2ChoMeo);
		return keHoach2ChoMeo;
	}
}
