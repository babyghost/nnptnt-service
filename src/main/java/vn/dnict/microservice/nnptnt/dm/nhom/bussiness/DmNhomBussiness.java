package vn.dnict.microservice.nnptnt.dm.nhom.bussiness;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNhomData;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangService;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomService;

@Service
public class DmNhomBussiness {

	@Autowired
	DmNhomService serviceDmNhomService;
	@Autowired
	DmNganhHangService serviceDmNganhHangService;
	
	public Page<DmNhomData> findAll(int page, int size, String sortBy, String sortDir,String ten,Long DmNganhHangId , Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmNhom> pageDmNhom = serviceDmNhomService.findAll(ten, DmNganhHangId, trangThai, 
				PageRequest.of(page, size, direction, sortBy));
		final Page<DmNhomData> pageDmNhomData = pageDmNhom.map(this::convertToDmNhomData);

		return pageDmNhomData;
	}
	
	
	public DmNhomData convertToDmNhomData(DmNhom dmNhom) {
		DmNhomData dmNhomData = new DmNhomData();
		dmNhomData.setId(dmNhom.getId());
		dmNhomData.setTen(dmNhom.getTen());
		dmNhomData.setTrangThai(dmNhom.getTrangThai());
		dmNhomData.setMoTa(dmNhom.getMoTa());
		if (Objects.nonNull(dmNhom.getDmNganhHangId())) {
			Optional<DmNganhHang> optPhanNhom = serviceDmNganhHangService.findById(dmNhom.getDmNganhHangId());
			if (optPhanNhom.isPresent()) {
				dmNhomData.setDmNganhHangId(optPhanNhom.get().getId());
				dmNhomData.setDmNganhHangTen(optPhanNhom.get().getTen());
			}
		}
		return dmNhomData;
	}
	
	public DmNhomData create(DmNhomData dmNhomData) {
		DmNhom dmNhom = new DmNhom();
		dmNhom.setId(dmNhomData.getId());
		dmNhom.setTen(dmNhomData.getTen());
		dmNhom.setMoTa(dmNhomData.getMoTa());
		dmNhom.setDmNganhHangId(dmNhomData.getDmNganhHangId());
		dmNhom.setDaXoa(false);
		dmNhom.setTrangThai(dmNhomData.getTrangThai());
		dmNhom = serviceDmNhomService.save(dmNhom);
		return dmNhomData;

	}
	
	public DmNhomData update(Long id, DmNhomData dmNhomData) throws EntityNotFoundException {
		Optional<DmNhom> optDmNhom = serviceDmNhomService.findById(id);
		if (!optDmNhom.isPresent()) {
			throw new EntityNotFoundException(DmNhom.class, "id", String.valueOf(id));
		}
		DmNhom dmNhom = optDmNhom.get();
		dmNhom.setId(dmNhomData.getId());
		dmNhom.setTen(dmNhomData.getTen());
		dmNhom.setMoTa(dmNhomData.getMoTa());
		dmNhom.setDmNganhHangId(dmNhomData.getDmNganhHangId());
		dmNhom.setDaXoa(false);
		dmNhom.setTrangThai(dmNhomData.getTrangThai());
		dmNhom = serviceDmNhomService.save(dmNhom);
		
		return dmNhomData;
	}
	
	public DmNhomData findById(Long id) throws EntityNotFoundException {
		Optional<DmNhom> optional = serviceDmNhomService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNhom.class, "id", String.valueOf(id));
		}
		DmNhom dmNhom = optional.get();
		return this.convertToDmNhomData(dmNhom);
	}

	public DmNhomData delete(Long id) throws EntityNotFoundException {
		Optional<DmNhom> optional = serviceDmNhomService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmNhom.class, "id", String.valueOf(id));
		}
		DmNhom dmNhom = optional.get();
		dmNhom.setDaXoa(true);
		dmNhom = serviceDmNhomService.save(dmNhom);
		return this.convertToDmNhomData(dmNhom);
	}

	
}
