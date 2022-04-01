package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.business;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.data.ThoiGianTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;

@Service
public class ThoiGianTiemPhongBusiness {
	@Autowired
	ThoiGianTiemPhongService serviceThoiGianTiemPhongService;
	@Autowired 
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	
	public Page<ThoiGianTiemPhong> findAll(int page, int size, String sortBy, String sortDir,Long phuongXaId,Long quanHuyenId,LocalDate thoiGianTiemDenNgay, LocalDate thoiGianTiemTuNgay, Long keHoachTiemPhongId, String diaDiem) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		} 
		Page<ThoiGianTiemPhong> pageThoiGianTiemPhong = serviceThoiGianTiemPhongService.findAll(phuongXaId, quanHuyenId, thoiGianTiemDenNgay, thoiGianTiemTuNgay, keHoachTiemPhongId, diaDiem, 
				PageRequest.of(page, size, direction, sortBy));
		return pageThoiGianTiemPhong;
	}
	public ThoiGianTiemPhong findById(Long id) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public ThoiGianTiemPhong create(ThoiGianTiemPhongInput ThoiGianTiemPhongInput) {
		ThoiGianTiemPhong ThoiGianTiemPhong = new ThoiGianTiemPhong();
		ThoiGianTiemPhong.setDaXoa(false);
		ThoiGianTiemPhong.setPhuongXaId(ThoiGianTiemPhongInput.getPhuongXaId());
		ThoiGianTiemPhong.setQuanHuyenId(ThoiGianTiemPhongInput.getQuanHuyenId());
		ThoiGianTiemPhong.setKeHoachTiemPhongId(ThoiGianTiemPhongInput.getKeHoachTiemPhongId());
		ThoiGianTiemPhong.setThoiGianTiemTuNgay(ThoiGianTiemPhongInput.getThoiGianTiemTuNgay());
		ThoiGianTiemPhong.setThoiGianTiemDenNgay(ThoiGianTiemPhongInput.getThoiGianTiemDenNgay());
		ThoiGianTiemPhong.setDiaDiem(ThoiGianTiemPhongInput.getDiaDiem());
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		return ThoiGianTiemPhong;
	}

	public ThoiGianTiemPhong update(Long id, ThoiGianTiemPhongInput ThoiGianTiemPhongInput) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		ThoiGianTiemPhong ThoiGianTiemPhong = optional.get();
		ThoiGianTiemPhong.setPhuongXaId(ThoiGianTiemPhongInput.getPhuongXaId());
		ThoiGianTiemPhong.setQuanHuyenId(ThoiGianTiemPhongInput.getQuanHuyenId());
		ThoiGianTiemPhong.setKeHoachTiemPhongId(ThoiGianTiemPhongInput.getKeHoachTiemPhongId());
		ThoiGianTiemPhong.setThoiGianTiemTuNgay(ThoiGianTiemPhongInput.getThoiGianTiemTuNgay());
		ThoiGianTiemPhong.setThoiGianTiemDenNgay(ThoiGianTiemPhongInput.getThoiGianTiemDenNgay());
		ThoiGianTiemPhong.setDiaDiem(ThoiGianTiemPhongInput.getDiaDiem());
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		return ThoiGianTiemPhong;
	}

	@DeleteMapping(value = { "/{id}" })
	public ThoiGianTiemPhong delete(Long id) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		ThoiGianTiemPhong ThoiGianTiemPhong = optional.get();
		ThoiGianTiemPhong.setDaXoa(true);
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		return ThoiGianTiemPhong;
	}
}

