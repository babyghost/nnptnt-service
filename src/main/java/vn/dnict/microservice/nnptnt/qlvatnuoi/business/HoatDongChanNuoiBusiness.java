package vn.dnict.microservice.nnptnt.qlvatnuoi.business;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.NamChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service.HoatDongChanNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service.NamChanNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.HoatDongChanNuoiInput;

@Service
public class HoatDongChanNuoiBusiness {
	
	@Autowired
	HoatDongChanNuoiService serviceHoatDongChanNuoiService;
	
	@Autowired
	DmLoaiVatNuoiService serviceDmLoaiVatNuoiService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	@Autowired
	NamChanNuoiService serviceNamChanNuoiService;
	
	public Page<HoatDongChanNuoi> findAll(int page, int size, String sortBy, String sortDir,String search,
			Integer donViTinh, Integer soLuongNuoi,
			String mucDichNuoi, LocalDate thoiGianBatDauNuoi, LocalDate thoiGianXuat, Integer slVatNuoiXuat,
			Float sanLuongXuat, String ghiChu, Long loaiVatNuoiId, Long namChanNuoiId,Long coSoChanNuoiId
			) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.findAll(search,donViTinh,soLuongNuoi,
				mucDichNuoi,thoiGianBatDauNuoi,thoiGianXuat,slVatNuoiXuat,sanLuongXuat,ghiChu,
				loaiVatNuoiId,namChanNuoiId,coSoChanNuoiId,
				PageRequest.of(page, size, direction, sortBy));
		
//		 Optional<CoSoChanNuoi> coSoChanNuoi= serviceCoSoChanNuoiService.findById(coSoChanNuoiId);
		return pageHoatDongChanNuoi;
	}
	
	
		
	
	
	
	public CoSoChanNuoi findCoSoChanNuoiByHoatDongChanNuoiId(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi HoatDongChanNuoi = optional.get();
		Optional<CoSoChanNuoi> optionalCoSoChanNuoi = serviceCoSoChanNuoiService.findById(HoatDongChanNuoi.getCoSoChanNuoiId());
		if (!optionalCoSoChanNuoi.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		return optionalCoSoChanNuoi.get();
	}
	
//	public List<HoatDongChanNuoi> findByLoaiDongVatId(Long loaiDongVatId, Integer daXoa) {
//		List<HoatDongChanNuoi> listHoatDongChanNuoi = serviceHoatDongChanNuoiService.findHoatDongChanNuoiByLoaiVatNuoiIdAndDaXoa(loaiDongVatId, 0);
//		System.out.println("listCanBo: " + listHoatDongChanNuoi);
//		return listHoatDongChanNuoi;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public NamChanNuoi findNamChanNuoiByHoatDongChanNuoiId(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi HoatDongChanNuoi = optional.get();
		Optional<NamChanNuoi> optionalNamChanNuoi = serviceNamChanNuoiService.findById(HoatDongChanNuoi.getNamChanNuoiId());
		if (!optionalNamChanNuoi.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		return optionalNamChanNuoi.get();
	}
	
	
	
	public DmLoaiVatNuoi findLoaiVatNuoiByHoatDongChanNuoiId(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi HoatDongChanNuoi = optional.get();
		Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService.findById(HoatDongChanNuoi.getLoaiVatNuoiId());
		if (!optionalLoaiVatNuoi.isPresent()) {
			throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
		}
		return optionalLoaiVatNuoi.get();
	}
	
	
	
	
	

	
	public HoatDongChanNuoi findById(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	
	
	public HoatDongChanNuoi create(HoatDongChanNuoiInput HoatDongChanNuoiInput) {
		HoatDongChanNuoi HoatDongChanNuoi = new HoatDongChanNuoi();
		HoatDongChanNuoi.setDaXoa(false);
		HoatDongChanNuoi.setDonViTinh(HoatDongChanNuoiInput.getDonViTinh());
		HoatDongChanNuoi.setLoaiVatNuoiId(HoatDongChanNuoiInput.getLoaiVatNuoiId());
		HoatDongChanNuoi.setNamChanNuoiId(HoatDongChanNuoiInput.getNamChanNuoiId());
		HoatDongChanNuoi.setCoSoChanNuoiId(HoatDongChanNuoiInput.getCoSoChanNuoiId());
		HoatDongChanNuoi.setSlVatNuoiXuat(HoatDongChanNuoiInput.getSlVatNuoiXuat());
		HoatDongChanNuoi.setMucDichNuoi(HoatDongChanNuoiInput.getMucDichNuoi());
		HoatDongChanNuoi.setSoLuongNuoi(HoatDongChanNuoiInput.getSoLuongNuoi());
		HoatDongChanNuoi.setSanLuongXuat(HoatDongChanNuoiInput.getSanLuongXuat());
		HoatDongChanNuoi.setThoiGianBatDauNuoi(HoatDongChanNuoiInput.getThoiGianBatDauNuoi());
		HoatDongChanNuoi.setThoiGianXuat(HoatDongChanNuoiInput.getThoiGianXuat());
		HoatDongChanNuoi.setGhiChu(HoatDongChanNuoiInput.getGhiChu());
		HoatDongChanNuoi = serviceHoatDongChanNuoiService.save(HoatDongChanNuoi);

		return HoatDongChanNuoi;
	}
	
	public HoatDongChanNuoi update(Long id, HoatDongChanNuoiInput HoatDongChanNuoiInput) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi HoatDongChanNuoi = optional.get();
//		HoatDongChanNuoi.setDaXoa(true);
		HoatDongChanNuoi.setDonViTinh(HoatDongChanNuoiInput.getDonViTinh());
		HoatDongChanNuoi.setLoaiVatNuoiId(HoatDongChanNuoiInput.getLoaiVatNuoiId());
		HoatDongChanNuoi.setNamChanNuoiId(HoatDongChanNuoiInput.getNamChanNuoiId());
		HoatDongChanNuoi.setCoSoChanNuoiId(HoatDongChanNuoiInput.getCoSoChanNuoiId());
		HoatDongChanNuoi.setMucDichNuoi(HoatDongChanNuoiInput.getMucDichNuoi());
		HoatDongChanNuoi.setSlVatNuoiXuat(HoatDongChanNuoiInput.getSlVatNuoiXuat());
		HoatDongChanNuoi.setSoLuongNuoi(HoatDongChanNuoiInput.getSoLuongNuoi());
		HoatDongChanNuoi.setSanLuongXuat(HoatDongChanNuoiInput.getSanLuongXuat());
		HoatDongChanNuoi.setThoiGianBatDauNuoi(HoatDongChanNuoiInput.getThoiGianBatDauNuoi());
		HoatDongChanNuoi.setThoiGianXuat(HoatDongChanNuoiInput.getThoiGianXuat());
		HoatDongChanNuoi.setGhiChu(HoatDongChanNuoiInput.getGhiChu());
		HoatDongChanNuoi = serviceHoatDongChanNuoiService.save(HoatDongChanNuoi);
		return HoatDongChanNuoi;
		
	}
	@DeleteMapping(value = { "/{id}" })
	public HoatDongChanNuoi delete(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi HoatDongChanNuoi = optional.get();
		HoatDongChanNuoi.setDaXoa(true);
		HoatDongChanNuoi = serviceHoatDongChanNuoiService.save(HoatDongChanNuoi);
		return HoatDongChanNuoi;
	}

	
	
	
//	public List<HoatDongChanNuoi> getListHoatDongChanNuoiInUseLimit20AndAppCode(String appCode, String search,
//			Integer donViTinh, Integer soLuongNuoi,
//			String mucDichNuoi, LocalDate thoiGianBatDauNuoi, LocalDate thoiGianXuat, Integer slVatNuoiXuat,
//			Float sanLuongXuat, String ghiChu, Long loaiVatNuoiId, Long namChanNuoiId,Long coSoChanNuoiId) {
//		Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.findAll(search,donViTinh,soLuongNuoi,
//				mucDichNuoi,thoiGianBatDauNuoi,thoiGianXuat,slVatNuoiXuat,sanLuongXuat,ghiChu,
//				loaiVatNuoiId,namChanNuoiId,coSoChanNuoiId, PageRequest.of(0, 20, Direction.ASC, "tenCoSo"));
//		List<HoatDongChanNuoi> listHoatDongChanNuoi = pageHoatDongChanNuoi.getContent();
//		return listHoatDongChanNuoi;
//	}
	
	}
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
