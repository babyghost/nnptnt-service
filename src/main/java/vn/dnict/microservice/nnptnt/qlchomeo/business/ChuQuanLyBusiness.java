package vn.dnict.microservice.nnptnt.qlchomeo.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.qlchomeo.data.ChuQuanLyInput;

@Service
public class ChuQuanLyBusiness {
	@Autowired
	ChuQuanLyService serviceChuQuanLyService;

	public Page<ChuQuanLy> findAll(int page, int size, String sortBy, String sortDir, String chuHo,
			String diaChi, Integer dienThoai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		Page<ChuQuanLy> pageChuQuanLy = serviceChuQuanLyService.findAll(chuHo, diaChi, dienThoai,
				PageRequest.of(page, size, direction, sortBy));
		return pageChuQuanLy;
	}

	public ChuQuanLy findById(Long id) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public ChuQuanLy create(ChuQuanLyInput ChuQuanLyInput) {
		ChuQuanLy chuQuanLy = new ChuQuanLy();
		chuQuanLy.setChuHo(ChuQuanLyInput.getChuHo());
		chuQuanLy.setDiaChi(ChuQuanLyInput.getDiaCHi());
		chuQuanLy.setDienThoai(ChuQuanLyInput.getDienThoai());
		chuQuanLy.setQuanHuyen_Id(ChuQuanLyInput.getQuanHuyen_Id());
		chuQuanLy.setPhuongXa_Id(ChuQuanLyInput.getPhuongXa_Id());
		chuQuanLy.setDaXoa(false);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);
		return chuQuanLy;
	}

	public ChuQuanLy update(Long id, ChuQuanLyInput ChuQuanLyInput) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}
		ChuQuanLy chuQuanLy = optional.get();
		chuQuanLy.setChuHo(ChuQuanLyInput.getChuHo());
		chuQuanLy.setDiaChi(ChuQuanLyInput.getDiaCHi());
		chuQuanLy.setDienThoai(ChuQuanLyInput.getDienThoai());
		chuQuanLy.setQuanHuyen_Id(ChuQuanLyInput.getQuanHuyen_Id());
		chuQuanLy.setPhuongXa_Id(ChuQuanLyInput.getPhuongXa_Id());
		chuQuanLy.setDaXoa(false);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);
		return chuQuanLy;
	}

	@DeleteMapping(value = { "/{id}" })
	public ChuQuanLy delete(Long id) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			System.out.println("X");
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}
		
		ChuQuanLy chuQuanLy = optional.get();
		chuQuanLy.setDaXoa(true);
		System.out.println(chuQuanLy);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);
		return chuQuanLy;
	}
}
