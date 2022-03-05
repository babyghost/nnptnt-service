package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoService;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;

@Service
public class ThongTinChoMeoBusiness {
	@Autowired
	ThongTinChoMeoService serviceThongTinChoMeoService;
	
	@Autowired
	DmGiongService serviceGiongService;
	
	@Autowired
	ChuQuanLyService serviceChuQuanLyService;

	public Page<ThongTinChoMeo> findAll(int page, int size, String sortBy, String sortDir, String search,
			Integer trangThai ) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThongTinChoMeo> pageThongTinChoMeo = serviceThongTinChoMeoService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));	
		return pageThongTinChoMeo;
	}
	public ThongTinChoMeo findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	public ChuQuanLy findChuQuanLyByThongTinChoMeoId(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo ThongTinChoMeo = optional.get();
		Optional<ChuQuanLy> optionalChuQuanLy = serviceChuQuanLyService.findById(ThongTinChoMeo.getChuQuanLyId());
		if (!optionalChuQuanLy.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}
		return optionalChuQuanLy.get();
	}

	public ThongTinChoMeo create(ThongTinChoMeoInput ThongTinChoMeoInput) {
		ThongTinChoMeo ThongTinChoMeo = new ThongTinChoMeo();
		ThongTinChoMeo.setDaXoa(true);
		ThongTinChoMeo.setLoaiDongVatId(ThongTinChoMeoInput.getLoaiDongVatId());
		ThongTinChoMeo.setNamSinh(ThongTinChoMeoInput.getNamSinh());
		ThongTinChoMeo.setTenConVat(ThongTinChoMeoInput.getTenConVat());
		ThongTinChoMeo.setGiongId(ThongTinChoMeoInput.getGiongId());
		ThongTinChoMeo.setMauLong(ThongTinChoMeoInput.getMauLong());
		ThongTinChoMeo.setTinhBiet(ThongTinChoMeoInput.getTinhBiet());
		ThongTinChoMeo.setTrangThai(ThongTinChoMeoInput.getTrangThai());
		ThongTinChoMeo.setChuQuanLyId(ThongTinChoMeoInput.getChuQuanLyId());
		ThongTinChoMeo = serviceThongTinChoMeoService.save(ThongTinChoMeo);
		return ThongTinChoMeo;
	}

	public ThongTinChoMeo update(Long id, ThongTinChoMeoInput ThongTinChoMeoInput) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo ThongTinChoMeo = optional.get();
		ThongTinChoMeo.setLoaiDongVatId(ThongTinChoMeoInput.getLoaiDongVatId());
		ThongTinChoMeo.setNamSinh(ThongTinChoMeoInput.getNamSinh());
		ThongTinChoMeo.setTenConVat(ThongTinChoMeoInput.getTenConVat());
		ThongTinChoMeo.setGiongId(ThongTinChoMeoInput.getGiongId());
		ThongTinChoMeo.setMauLong(ThongTinChoMeoInput.getMauLong());
		ThongTinChoMeo.setTinhBiet(ThongTinChoMeoInput.getTinhBiet());
		ThongTinChoMeo.setTrangThai(ThongTinChoMeoInput.getTrangThai());
		ThongTinChoMeo.setChuQuanLyId(ThongTinChoMeoInput.getChuQuanLyId());
		ThongTinChoMeo = serviceThongTinChoMeoService.save(ThongTinChoMeo);
		return ThongTinChoMeo;
	}

	@DeleteMapping(value = { "/{id}" })
	public ThongTinChoMeo delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo ThongTinChoMeo = optional.get();
		ThongTinChoMeo.setDaXoa(true);
		ThongTinChoMeo = serviceThongTinChoMeoService.save(ThongTinChoMeo);
		return ThongTinChoMeo;
	}
}
