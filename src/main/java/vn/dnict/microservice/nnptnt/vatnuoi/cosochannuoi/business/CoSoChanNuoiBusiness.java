package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiInput;

@Service
public class CoSoChanNuoiBusiness {
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	public Page<CoSoChanNuoi> findAll(int page, int size, String sortBy, String sortDir, String search, String tenChuCoSo, 
			String dienThoai, String email, Long phuongXaId, Long quanHuyenId) { 
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoSoChanNuoi> pageCoSoChanNuoi = serviceCoSoChanNuoiService.findAll(search, tenChuCoSo, dienThoai, email, 
				phuongXaId, quanHuyenId, PageRequest.of(page, size, direction, sortBy));
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

	public CoSoChanNuoi create(CoSoChanNuoiInput CoSoChanNuoiInput) {
		CoSoChanNuoi coSoChanNuoi = new CoSoChanNuoi();
		coSoChanNuoi.setDaXoa(true);
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
		coSoChanNuoi.setDaXoa(false);
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return coSoChanNuoi;
	}
}
