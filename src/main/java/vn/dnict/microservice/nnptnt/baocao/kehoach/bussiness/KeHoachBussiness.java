package vn.dnict.microservice.nnptnt.baocao.kehoach.bussiness;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.data.KeHoachData;
import vn.dnict.microservice.nnptnt.baocao.data.ThucHienBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.KeHoachService;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;

@Service
public class KeHoachBussiness {
	@Autowired
	KeHoachService serviceKeHoachService;
	
	@Autowired
	DmLinhVucService serviceDmLinhVucService;

	public Page<KeHoachData> findAll(int page, int size, String sortBy, String sortDir, Long linhVucId, Integer nam) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<KeHoach> pageKeHoach = serviceKeHoachService.findAll(linhVucId, nam,
				PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachData> pageKeHoachData = pageKeHoach
				.map(this::convertToKeHoachData);
		
		return pageKeHoachData;
	}
	
	private KeHoachData convertToKeHoachData(KeHoach keHoach) {
		KeHoachData keHoachData = new KeHoachData();
		keHoachData.setId(keHoach.getId());
		keHoachData.setLinhVucId(keHoach.getLinhVucId());
		keHoachData.setNam(keHoach.getNam());
		keHoachData.setChiTieuId(keHoach.getChiTieuId());
		keHoachData.setKeHoach(keHoach.getKeHoach());
		if(Objects.nonNull(keHoach.getLinhVucId())){
			Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(keHoach.getLinhVucId());
			if(optLinhVuc.isPresent()) {
				keHoachData.setLinhVucId(optLinhVuc.get().getId());
				keHoachData.setLinhVucTen(optLinhVuc.get().getTen());
			}
		}
		return keHoachData;
	}
	
	public KeHoachData create(KeHoachData keHoachData) {
		KeHoach keHoach = new KeHoach();
		keHoach.setDaXoa(false);
		keHoach.setChiTieuId(keHoachData.getChiTieuId());
		keHoach.setKeHoach(keHoachData.getKeHoach());
		keHoach.setLinhVucId(keHoachData.getLinhVucId());
		keHoach.setNam(keHoachData.getNam());
		keHoach = serviceKeHoachService.save(keHoach);
		
		
		return this.convertToKeHoachData(keHoach);
	}
	
	public KeHoachData update(Long id, KeHoachData keHoachData) throws EntityNotFoundException {
		Optional<KeHoach> optional = serviceKeHoachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach.class, "id", String.valueOf(id));
		}
		KeHoach keHoach = optional.get();
		 
		keHoach.setChiTieuId(keHoachData.getChiTieuId());
		keHoach.setKeHoach(keHoachData.getKeHoach());
		keHoach.setLinhVucId(keHoachData.getLinhVucId());
		keHoach.setNam(keHoachData.getNam());
		keHoach = serviceKeHoachService.save(keHoach);
		
		
		return this.convertToKeHoachData(keHoach);
		
	}
	
	public KeHoachData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoach> optional = serviceKeHoachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach.class, "id", String.valueOf(id));
		}
		KeHoach keHoach = optional.get();
		return this.convertToKeHoachData(keHoach);
	}
	public KeHoach delete(Long id) throws EntityNotFoundException {
		Optional<KeHoach> optional = serviceKeHoachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoach.class, "id", String.valueOf(id));
		}
		KeHoach keHoach = optional.get();
		keHoach.setDaXoa(true);
		keHoach = serviceKeHoachService.save(keHoach);
		return keHoach;
	}
	
	public KeHoachData findChiTieu( long linhVucId, Integer nam,Long chiTieuId)
			throws EntityNotFoundException {
		KeHoach keHoach = new KeHoach();
		Optional<KeHoach> optThucHienBaoCao = serviceKeHoachService
				.findByNamAndLinhVucIdAndChiTieuId(nam, chiTieuId, chiTieuId);
		//keHoach.setKeHoach(optThucHienBaoCao.get().getKeHoach());
		System.out.println(optThucHienBaoCao);
		return this.convertToKeHoachData(keHoach);
	}
}
