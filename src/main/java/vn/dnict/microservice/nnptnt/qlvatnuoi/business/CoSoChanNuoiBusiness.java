package vn.dnict.microservice.nnptnt.qlvatnuoi.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.CoSoChanNuoiInput;

@Service
public class CoSoChanNuoiBusiness {
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	public Page<CoSoChanNuoi> findAll(int page, int size, String sortBy, String sortDir, String search,Long phuongXaId,
			Long quanHuyenId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoSoChanNuoi> pageCoSoChanNuoi = serviceCoSoChanNuoiService.findAll(search, phuongXaId, quanHuyenId,
				PageRequest.of(page, size, direction, sortBy));
		return pageCoSoChanNuoi;
	}
	public CoSoChanNuoi findById(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}
	
	public CoSoChanNuoi findByTenCoSo(String tenCoSo) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findByTenCoSo(tenCoSo);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "tenCoSo", String.valueOf(tenCoSo));
		}
		return optional.get();
	}
	
	public DmPhuongXa findDmPhuongXaByCoSoChanNuoiId(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi CoSoChanNuoi = optional.get();
		Optional<DmPhuongXa> optionalDmPhuongXa = serviceDmPhuongXaService.findById(CoSoChanNuoi.getPhuongXaId());
		if (!optionalDmPhuongXa.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		return optionalDmPhuongXa.get();
	}
	
	public DmQuanHuyen findDmQuanHuyenByCoSoChanNuoiId(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi CoSoChanNuoi = optional.get();
		Optional<DmQuanHuyen> optionalDmQuanHuyen = serviceDmQuanHuyenService.findById(CoSoChanNuoi.getQuanHuyenId());
		if (!optionalDmQuanHuyen.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		return optionalDmQuanHuyen.get();
	}

	public CoSoChanNuoi create(CoSoChanNuoiInput CoSoChanNuoiInput) {
		CoSoChanNuoi coSoChanNuoi = new CoSoChanNuoi();
		coSoChanNuoi.setTenCoSo(CoSoChanNuoiInput.getTenCoSo());
		coSoChanNuoi.setTenChuCoSo(CoSoChanNuoiInput.getTenChuCoSo());
		coSoChanNuoi.setDiaChi(CoSoChanNuoiInput.getDiaChi());
		coSoChanNuoi.setDienThoai(CoSoChanNuoiInput.getDienThoai());
		coSoChanNuoi.setEmail(CoSoChanNuoiInput.getEmail());
		coSoChanNuoi.setPhuongXaId(CoSoChanNuoiInput.getPhuongXaId());
		coSoChanNuoi.setQuanHuyenId(CoSoChanNuoiInput.getQuanHuyenId());
		coSoChanNuoi.setGhiChu(CoSoChanNuoiInput.getGhiChu());
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return coSoChanNuoi;
	}

	public CoSoChanNuoi update(Long id, CoSoChanNuoiInput CoSoChanNuoiInput) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi coSoChanNuoi = optional.get();
		coSoChanNuoi.setTenCoSo(CoSoChanNuoiInput.getTenCoSo());
		coSoChanNuoi.setTenChuCoSo(CoSoChanNuoiInput.getTenChuCoSo());
		coSoChanNuoi.setDiaChi(CoSoChanNuoiInput.getDiaChi());
		coSoChanNuoi.setDienThoai(CoSoChanNuoiInput.getDienThoai());
		coSoChanNuoi.setEmail(CoSoChanNuoiInput.getEmail());
		coSoChanNuoi.setPhuongXaId(CoSoChanNuoiInput.getPhuongXaId());
		coSoChanNuoi.setQuanHuyenId(CoSoChanNuoiInput.getQuanHuyenId());
		coSoChanNuoi.setGhiChu(CoSoChanNuoiInput.getGhiChu());
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return coSoChanNuoi;
	}

	@DeleteMapping(value = { "/{id}" })
	public CoSoChanNuoi delete(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi coSoChanNuoi = optional.get();
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return coSoChanNuoi;
	}
}
