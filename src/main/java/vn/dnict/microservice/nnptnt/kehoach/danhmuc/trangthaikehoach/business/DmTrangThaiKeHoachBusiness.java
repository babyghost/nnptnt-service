package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.DmTrangThaiKeHoachService;
import vn.dnict.microservice.nnptnt.kehoach.data.DmTrangThaiKeHoachData;

@Service
public class DmTrangThaiKeHoachBusiness {
	@Autowired
	protected DmTrangThaiKeHoachService serviceDmLoaiNhiemVuService;

	public Page<DmTrangThaiKeHoach> findAll(int page, int size, String sortBy, String sortDir, String search) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		return serviceDmLoaiNhiemVuService.findAll(search, PageRequest.of(page, size, direction, sortBy));
	}

	private DmTrangThaiKeHoachData convertToDmTrangThaiKeHoachData(DmTrangThaiKeHoach dmTrangThaiKeHoach) {
		DmTrangThaiKeHoachData dmTrangThaiKeHoachData = new DmTrangThaiKeHoachData();
		dmTrangThaiKeHoachData.setId(dmTrangThaiKeHoach.getId());
		dmTrangThaiKeHoachData.setTen(dmTrangThaiKeHoach.getTen());
		return dmTrangThaiKeHoachData;
	}

	public DmTrangThaiKeHoachData findById(Long id) throws EntityNotFoundException {
		Optional<DmTrangThaiKeHoach> optional = serviceDmLoaiNhiemVuService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmTrangThaiKeHoach.class, "id", String.valueOf(id));
		}
		DmTrangThaiKeHoach dmTrangThaiKeHoach = optional.get();
		return convertToDmTrangThaiKeHoachData(dmTrangThaiKeHoach);
	}

}
