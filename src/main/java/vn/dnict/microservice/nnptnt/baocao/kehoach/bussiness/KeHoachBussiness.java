package vn.dnict.microservice.nnptnt.baocao.kehoach.bussiness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.data.ChiTieuKeHoachData;
import vn.dnict.microservice.nnptnt.baocao.data.KeHoachData;
import vn.dnict.microservice.nnptnt.baocao.data.ThucHienBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.KeHoachService;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public class KeHoachBussiness {
	@Autowired
	KeHoachService serviceKeHoachService;
	
	@Autowired
	DmLinhVucService serviceDmLinhVucService;

	public Page<ChiTieuKeHoachData> findAll(int page, int size, String sortBy, String sortDir, Long linhVucId, Integer nam) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<KeHoach> pageKeHoach = serviceKeHoachService.findAll(linhVucId, nam,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ChiTieuKeHoachData> pageKeHoachData = pageKeHoach
				.map(this::convertToChiTieuKeHoachData);
		
		List<ChiTieuKeHoachData> keHoachDataDatass = new ArrayList<>(
				pageKeHoachData.getContent());
		 List<ChiTieuKeHoachData> keHoachDataDatas = new ArrayList<>();

			for (ChiTieuKeHoachData element : keHoachDataDatass) {
	           // Check if element not exist in list, perform add element to list
	           if (!keHoachDataDatas.contains(element)) {
	        	   keHoachDataDatas.add(element);
	           }
	       }
			Page<ChiTieuKeHoachData> keHoachDataImpl = new PageImpl<>(keHoachDataDatas);
		
		return keHoachDataImpl;
	}
	
	public Page<KeHoachData> findByKeHoach(int page, int size, String sortBy, String sortDir, Long linhVucId, Integer nam) {
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
		
		List<KeHoachData> keHoachDataDatass = new ArrayList<>(
				pageKeHoachData.getContent());
		 List<KeHoachData> keHoachDataDatas = new ArrayList<>();

			for (KeHoachData element : keHoachDataDatass) {
	           // Check if element not exist in list, perform add element to list
	           if (!keHoachDataDatas.contains(element)) {
	        	   keHoachDataDatas.add(element);
	           }
	       }
			Page<KeHoachData> keHoachDataImpl = new PageImpl<>(keHoachDataDatas);
		
		return keHoachDataImpl;
	}
	
	private ChiTieuKeHoachData convertToChiTieuKeHoachData(KeHoach keHoach) {
		ChiTieuKeHoachData chiTieuKeHoachData = new ChiTieuKeHoachData();
		
		if(Objects.nonNull(keHoach.getLinhVucId())){
			Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(keHoach.getLinhVucId());
			if(optLinhVuc.isPresent()) {
				chiTieuKeHoachData.setLinhVucId(optLinhVuc.get().getId());
				chiTieuKeHoachData.setLinhVucTen(optLinhVuc.get().getTen());
			}
		}
		chiTieuKeHoachData.setNam(keHoach.getNam());
		List<KeHoachData> listKeHoachDatas = new ArrayList<KeHoachData>();
		List<KeHoach> listKeHoachs = serviceKeHoachService.findListByNamAndLinhVucId(chiTieuKeHoachData.getNam(),chiTieuKeHoachData.getLinhVucId());
		if(Objects.nonNull(listKeHoachs) &&! listKeHoachs.isEmpty()) {

			for(KeHoach KeHoachs : listKeHoachs) {
				KeHoachData keHoachData = new KeHoachData();
				keHoachData.setId(KeHoachs.getId());
				keHoachData.setLinhVucId(KeHoachs.getLinhVucId());
				keHoachData.setNam(KeHoachs.getNam());
				keHoachData.setChiTieuId(KeHoachs.getChiTieuId());
				keHoachData.setKeHoach(KeHoachs.getKeHoach());
				if(Objects.nonNull(KeHoachs.getLinhVucId())){
					Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(KeHoachs.getLinhVucId());
					if(optLinhVuc.isPresent()) {
						keHoachData.setLinhVucId(optLinhVuc.get().getId());
						keHoachData.setLinhVucTen(optLinhVuc.get().getTen());
					}
				}
		
				listKeHoachDatas.add(keHoachData);
			}
		}
		chiTieuKeHoachData.setListKeHoach(listKeHoachDatas);
		return chiTieuKeHoachData;
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
	
	public Integer deletes(List<Long> ids) throws EntityNotFoundException {
		List<KeHoach> result = new ArrayList<KeHoach>();
		if (ids.size() > 0) {
			ids.stream().forEach(item -> {
				Optional<KeHoach> optional = serviceKeHoachService
						.findById(item);
				if (optional.isPresent()) {
					KeHoach object = optional.get();
					object.setDaXoa(true);			
					serviceKeHoachService.save(object);
					result.add(object);
				}
			});
		}
		return result.size();
	}
	

}
