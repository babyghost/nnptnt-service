package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiOutput;

@Service
public class CoSoChanNuoiBusiness {
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	public Page<CoSoChanNuoiOutput> findAll(int page, int size, String sortBy, String sortDir, String search, String tenChuCoSo, 
			String dienThoai, String email, Long phuongXaId, Long quanHuyenId) { 
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<CoSoChanNuoi> pageCoSoChanNuoi = serviceCoSoChanNuoiService.findAll(search, tenChuCoSo, dienThoai, email, 
				phuongXaId, quanHuyenId, PageRequest.of(page, size, direction, sortBy));
		final Page<CoSoChanNuoiOutput> pageCoSoChanNuoiOutput = pageCoSoChanNuoi
				.map(this::convertToCoSoChanNuoiOutput);
		return pageCoSoChanNuoiOutput;
	}
	
	private CoSoChanNuoiOutput convertToCoSoChanNuoiOutput(
			CoSoChanNuoi coSoChanNuoi) {
		CoSoChanNuoiOutput coSoChanNuoiOutput = new CoSoChanNuoiOutput();
		coSoChanNuoiOutput.setId(coSoChanNuoi.getId());
		coSoChanNuoiOutput.setTenCoSo(coSoChanNuoi.getTenCoSo());
		coSoChanNuoiOutput.setTenChuCoSo(coSoChanNuoi.getTenChuCoSo());
		coSoChanNuoiOutput.setDienThoai(coSoChanNuoi.getDienThoai());
		coSoChanNuoiOutput.setEmail(coSoChanNuoi.getEmail());
		coSoChanNuoiOutput.setDiaChi(coSoChanNuoi.getDiaChi());
		coSoChanNuoiOutput.setPhuongXaId(coSoChanNuoi.getPhuongXaId());
		coSoChanNuoiOutput.setQuanHuyenId(coSoChanNuoi.getQuanHuyenId());
		coSoChanNuoiOutput.setGhiChu(coSoChanNuoi.getGhiChu());
		if(coSoChanNuoi.getQuanHuyenId() != null && coSoChanNuoi.getQuanHuyenId() > 0) {
			Optional<DmQuanHuyen> optional = serviceDmQuanHuyenService.findById(coSoChanNuoi.getQuanHuyenId());
			if (optional.isPresent()) {
				coSoChanNuoiOutput.setQuanHuyenTen(optional.get().getTen());
			}	
		}
		if(coSoChanNuoi.getPhuongXaId() != null && coSoChanNuoi.getPhuongXaId() > 0) {
			Optional<DmPhuongXa> optional = serviceDmPhuongXaService.findById(coSoChanNuoi.getPhuongXaId());
			if (optional.isPresent()) {
				coSoChanNuoiOutput.setPhuongXaTen(optional.get().getTen());
			}	
		}
		return coSoChanNuoiOutput;
	}
	
	public CoSoChanNuoiOutput findById(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoiOutput.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi coSoChanNuoi = optional.get();
		return this.convertToCoSoChanNuoiOutput(coSoChanNuoi);
	}

	public CoSoChanNuoiOutput create(CoSoChanNuoiOutput CoSoChanNuoiOutput,
			BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoSoChanNuoi coSoChanNuoi = new CoSoChanNuoi();
		coSoChanNuoi.setDaXoa(false);
		coSoChanNuoi.setTenCoSo(CoSoChanNuoiOutput.getTenCoSo());
		coSoChanNuoi.setTenChuCoSo(CoSoChanNuoiOutput.getTenChuCoSo());
		coSoChanNuoi.setDiaChi(CoSoChanNuoiOutput.getDiaChi());
		coSoChanNuoi.setDienThoai(CoSoChanNuoiOutput.getDienThoai());
		coSoChanNuoi.setEmail(CoSoChanNuoiOutput.getEmail());
		coSoChanNuoi.setPhuongXaId(CoSoChanNuoiOutput.getPhuongXaId());
		coSoChanNuoi.setQuanHuyenId(CoSoChanNuoiOutput.getQuanHuyenId());
		coSoChanNuoi.setGhiChu(CoSoChanNuoiOutput.getGhiChu());
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return this.convertToCoSoChanNuoiOutput(coSoChanNuoi);
	}

	public CoSoChanNuoiOutput update(Long id, CoSoChanNuoiOutput CoSoChanNuoiOutput,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi coSoChanNuoi = optional.get();
		coSoChanNuoi.setTenCoSo(CoSoChanNuoiOutput.getTenCoSo());
		coSoChanNuoi.setTenChuCoSo(CoSoChanNuoiOutput.getTenChuCoSo());
		coSoChanNuoi.setDiaChi(CoSoChanNuoiOutput.getDiaChi());
		coSoChanNuoi.setDienThoai(CoSoChanNuoiOutput.getDienThoai());
		coSoChanNuoi.setEmail(CoSoChanNuoiOutput.getEmail());
		coSoChanNuoi.setPhuongXaId(CoSoChanNuoiOutput.getPhuongXaId());
		coSoChanNuoi.setQuanHuyenId(CoSoChanNuoiOutput.getQuanHuyenId());
		coSoChanNuoi.setGhiChu(CoSoChanNuoiOutput.getGhiChu());
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return this.convertToCoSoChanNuoiOutput(coSoChanNuoi);
	}

	@DeleteMapping(value = { "/{id}" })
	public CoSoChanNuoiOutput delete(Long id) throws EntityNotFoundException {
		Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		CoSoChanNuoi coSoChanNuoi = optional.get();
		coSoChanNuoi.setDaXoa(true);
		coSoChanNuoi = serviceCoSoChanNuoiService.save(coSoChanNuoi);
		return this.convertToCoSoChanNuoiOutput(coSoChanNuoi);
	}
}
