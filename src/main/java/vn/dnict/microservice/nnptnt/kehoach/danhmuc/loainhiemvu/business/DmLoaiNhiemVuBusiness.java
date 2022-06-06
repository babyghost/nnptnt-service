package vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;
import vn.dnict.microservice.nnptnt.kehoach.data.validator.DmLoaiNhiemVuValidator;

@Service
public class DmLoaiNhiemVuBusiness {
	@Autowired
	protected DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	@Autowired
	protected DmLoaiNhiemVuValidator validatorDmLoaiNhiemVuValidator;

	public Page<DmLoaiNhiemVu> findAll(int page, int size, String sortBy, String sortDir, String search, Boolean trangThai, Boolean isDefault) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		return serviceDmLoaiNhiemVuService.findAll(search, trangThai, isDefault, PageRequest.of(page, size, direction, sortBy));
	}

	public DmLoaiNhiemVuData findById(Long id) throws EntityNotFoundException {
		Optional<DmLoaiNhiemVu> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = optional.get();
		DmLoaiNhiemVuData dmLoaiNhiemVuData = new DmLoaiNhiemVuData();
		dmLoaiNhiemVuData.setId(dmLoaiNhiemVu.getId());
		dmLoaiNhiemVuData.setMa(dmLoaiNhiemVu.getMa());
		dmLoaiNhiemVuData.setTen(dmLoaiNhiemVu.getTen());
		dmLoaiNhiemVuData.setTrangThai(dmLoaiNhiemVu.getTrangThai());
		dmLoaiNhiemVuData.setIsDefault(dmLoaiNhiemVu.getIsDefault());
		dmLoaiNhiemVuData.setSapXep(dmLoaiNhiemVu.getSapXep());
		return dmLoaiNhiemVuData;
	}

	public DmLoaiNhiemVu create(DmLoaiNhiemVuData dmLoaiNhiemVuData, BindingResult result) throws MethodArgumentNotValidException {
		validatorDmLoaiNhiemVuValidator.validate(dmLoaiNhiemVuData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = new DmLoaiNhiemVu();
		dmLoaiNhiemVu.setDaXoa(false);
		dmLoaiNhiemVu.setMa(dmLoaiNhiemVuData.getMa());
		dmLoaiNhiemVu.setTen(dmLoaiNhiemVuData.getTen());
		dmLoaiNhiemVu.setTrangThai(dmLoaiNhiemVuData.getTrangThai());
		dmLoaiNhiemVu.setIsDefault(dmLoaiNhiemVuData.getIsDefault());
		dmLoaiNhiemVu.setSapXep(dmLoaiNhiemVuData.getSapXep());
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}

	public DmLoaiNhiemVu update(Long id, DmLoaiNhiemVuData dmLoaiNhiemVuData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(id);
		if (!optionalDmLoaiNhiemVu.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		validatorDmLoaiNhiemVuValidator.validate(dmLoaiNhiemVuData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = optionalDmLoaiNhiemVu.get();
		dmLoaiNhiemVu.setMa(dmLoaiNhiemVuData.getMa());
		dmLoaiNhiemVu.setTen(dmLoaiNhiemVuData.getTen());
		dmLoaiNhiemVu.setTrangThai(dmLoaiNhiemVuData.getTrangThai());
		dmLoaiNhiemVu.setIsDefault(dmLoaiNhiemVuData.getIsDefault());
		dmLoaiNhiemVu.setSapXep(dmLoaiNhiemVuData.getSapXep());
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}

	public DmLoaiNhiemVu delete(Long id) throws EntityNotFoundException {
		Optional<DmLoaiNhiemVu> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmLoaiNhiemVu.class, "id", String.valueOf(id));
		}
		DmLoaiNhiemVu dmLoaiNhiemVu = optional.get();
		dmLoaiNhiemVu.setDaXoa(true);
		dmLoaiNhiemVu = serviceDmLoaiNhiemVuService.save(dmLoaiNhiemVu);
		return dmLoaiNhiemVu;
	}

}
