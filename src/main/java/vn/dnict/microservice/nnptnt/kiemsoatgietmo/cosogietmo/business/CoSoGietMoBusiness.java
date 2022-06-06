package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.business;

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
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.CoSoGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.CoSoGietMoData;

@Service
public class CoSoGietMoBusiness {
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	CoSoGietMoService serviceCoSoGietMoService;
	
	public Page<CoSoGietMoData> findAll(int page, int size, String sortBy, String sortDir, String search, String tenChuCoSo, 
			String dienThoai, String email, Long phuongXaId, Long quanHuyenId) { 
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<CoSoGietMo> pageCoSoGietMo = serviceCoSoGietMoService.findAll(search, tenChuCoSo, dienThoai, email, 
				phuongXaId, quanHuyenId, PageRequest.of(page, size, direction, sortBy));
		final Page<CoSoGietMoData> pageCoSoGietMoData = pageCoSoGietMo.map(this::convertToCoSoGietMoData);
		return pageCoSoGietMoData;
	}
	
	private CoSoGietMoData convertToCoSoGietMoData(CoSoGietMo coSoGietMo) {
		CoSoGietMoData coSoGietMoData = new CoSoGietMoData();
		coSoGietMoData.setId(coSoGietMo.getId());
		coSoGietMoData.setTenCoSo(coSoGietMo.getTenCoSo());
		coSoGietMoData.setTenChuCoSo(coSoGietMo.getTenChuCoSo());
		coSoGietMoData.setDienThoai(coSoGietMo.getDienThoai());
		coSoGietMoData.setEmail(coSoGietMo.getEmail());
		coSoGietMoData.setDiaChi(coSoGietMo.getDiaChi());
		coSoGietMoData.setPhuongXaId(coSoGietMo.getPhuongXaId());
		coSoGietMoData.setQuanHuyenId(coSoGietMo.getQuanHuyenId());
		coSoGietMoData.setGhiChu(coSoGietMo.getGhiChu());
		coSoGietMoData.setGiayKinhDoanh(coSoGietMo.getGiayKinhDoanh());
		if(coSoGietMo.getQuanHuyenId() != null && coSoGietMo.getQuanHuyenId() > 0) {
			Optional<DmQuanHuyen> optional = serviceDmQuanHuyenService.findById(coSoGietMo.getQuanHuyenId());
			if (optional.isPresent()) {
				coSoGietMoData.setQuanHuyenTen(optional.get().getTen());
			}	
		}
		if(coSoGietMo.getPhuongXaId() != null && coSoGietMo.getPhuongXaId() > 0) {
			Optional<DmPhuongXa> optional = serviceDmPhuongXaService.findById(coSoGietMo.getPhuongXaId());
			if (optional.isPresent()) {
				coSoGietMoData.setPhuongXaTen(optional.get().getTen());
			}	
		}
		return coSoGietMoData;
	}
	
	public CoSoGietMoData findById(Long id) throws EntityNotFoundException {
		Optional<CoSoGietMo> optional = serviceCoSoGietMoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoGietMoData.class, "id", String.valueOf(id));
		}
		CoSoGietMo coSoGietMo = optional.get();
		return this.convertToCoSoGietMoData(coSoGietMo);
	}

	public CoSoGietMoData create(CoSoGietMoData coSoGietMoData,
			BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoSoGietMo coSoGietMo = new CoSoGietMo();
		coSoGietMo.setDaXoa(false);
		coSoGietMo.setTenCoSo(coSoGietMoData.getTenCoSo());
		coSoGietMo.setTenChuCoSo(coSoGietMoData.getTenChuCoSo());
		coSoGietMo.setDiaChi(coSoGietMoData.getDiaChi());
		coSoGietMo.setDienThoai(coSoGietMoData.getDienThoai());
		coSoGietMo.setEmail(coSoGietMoData.getEmail());
		coSoGietMo.setPhuongXaId(coSoGietMoData.getPhuongXaId());
		coSoGietMo.setQuanHuyenId(coSoGietMoData.getQuanHuyenId());
		coSoGietMo.setGhiChu(coSoGietMoData.getGhiChu());
		coSoGietMo.setGiayKinhDoanh(coSoGietMoData.getGiayKinhDoanh());
		coSoGietMo = serviceCoSoGietMoService.save(coSoGietMo);
		return this.convertToCoSoGietMoData(coSoGietMo);
	}

	public CoSoGietMoData update(Long id, CoSoGietMoData coSoGietMoData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<CoSoGietMo> optional = serviceCoSoGietMoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoGietMo.class, "id", String.valueOf(id));
		}
		CoSoGietMo coSoGietMo = optional.get();
		coSoGietMo.setTenCoSo(coSoGietMoData.getTenCoSo());
		coSoGietMo.setTenChuCoSo(coSoGietMoData.getTenChuCoSo());
		coSoGietMo.setDiaChi(coSoGietMoData.getDiaChi());
		coSoGietMo.setDienThoai(coSoGietMoData.getDienThoai());
		coSoGietMo.setEmail(coSoGietMoData.getEmail());
		coSoGietMo.setPhuongXaId(coSoGietMoData.getPhuongXaId());
		coSoGietMo.setQuanHuyenId(coSoGietMoData.getQuanHuyenId());
		coSoGietMo.setGhiChu(coSoGietMoData.getGhiChu());
		coSoGietMo.setGiayKinhDoanh(coSoGietMoData.getGiayKinhDoanh());
		coSoGietMo = serviceCoSoGietMoService.save(coSoGietMo);
		return this.convertToCoSoGietMoData(coSoGietMo);
	}

	@DeleteMapping(value = { "/{id}" })
	public CoSoGietMoData delete(Long id) throws EntityNotFoundException {
		Optional<CoSoGietMo> optional = serviceCoSoGietMoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoGietMo.class, "id", String.valueOf(id));
		}
		CoSoGietMo coSoGietMo = optional.get();
		coSoGietMo.setDaXoa(true);
		coSoGietMo = serviceCoSoGietMoService.save(coSoGietMo);
		return this.convertToCoSoGietMoData(coSoGietMo);
	}

}
