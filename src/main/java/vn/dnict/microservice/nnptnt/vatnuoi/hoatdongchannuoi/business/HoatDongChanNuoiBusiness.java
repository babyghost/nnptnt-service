package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business;

import java.time.LocalDate;
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
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiInput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.HoatDongChanNuoiService;

@Service
public class HoatDongChanNuoiBusiness {
	
	@Autowired
	HoatDongChanNuoiService serviceHoatDongChanNuoiService;
	
	@Autowired
	DmLoaiVatNuoiService serviceDmLoaiVatNuoiService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	public Page<HoatDongChanNuoiOutput> findAll(int page, int size, String sortBy, String sortDir, String search, Integer donViTinh, 
			Integer soLuongNuoi, String mucDichNuoi, LocalDate thoiGianBatDauNuoi, LocalDate thoiGianXuat, 
			Integer slVatNuoiXuat, Float sanLuongXuat, String ghiChu, Long loaiVatNuoiId, Long coSoChanNuoiId, String nam, 
			Integer quy) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.findAll(search, donViTinh, soLuongNuoi,
				mucDichNuoi, thoiGianBatDauNuoi, thoiGianXuat,slVatNuoiXuat, sanLuongXuat,ghiChu, loaiVatNuoiId,coSoChanNuoiId, 
				nam, quy, PageRequest.of(page, size, direction, sortBy));
		final Page<HoatDongChanNuoiOutput> pageHoatDongChanNuoiOutput = pageHoatDongChanNuoi
				.map(this::convertToHoatDongChanNuoiOutput);
		return pageHoatDongChanNuoiOutput;
	}
	
	private HoatDongChanNuoiOutput convertToHoatDongChanNuoiOutput(HoatDongChanNuoi hoatDongChanNuoi) {
		HoatDongChanNuoiOutput hoatDongChanNuoiOutput = new HoatDongChanNuoiOutput();
		hoatDongChanNuoiOutput.setId(hoatDongChanNuoi.getId());
		hoatDongChanNuoiOutput.setCoSoChanNuoiId(hoatDongChanNuoi.getCoSoChanNuoiId());
		hoatDongChanNuoiOutput.setLoaiVatNuoiId(hoatDongChanNuoi.getLoaiVatNuoiId());
		hoatDongChanNuoiOutput.setDonViTinh(hoatDongChanNuoi.getDonViTinh());
		hoatDongChanNuoiOutput.setSoLuongNuoi(hoatDongChanNuoi.getSoLuongNuoi());
		hoatDongChanNuoiOutput.setMucDichNuoi(hoatDongChanNuoi.getMucDichNuoi());
		hoatDongChanNuoiOutput.setSanLuongXuat(hoatDongChanNuoi.getSanLuongXuat());
		hoatDongChanNuoiOutput.setThoiGianBatDauNuoi(hoatDongChanNuoi.getThoiGianBatDauNuoi());
		hoatDongChanNuoiOutput.setThoiGianXuat(hoatDongChanNuoi.getThoiGianXuat());
		hoatDongChanNuoiOutput.setSlVatNuoiXuat(hoatDongChanNuoi.getSlVatNuoiXuat());
		hoatDongChanNuoiOutput.setGhiChu(hoatDongChanNuoi.getGhiChu());
		hoatDongChanNuoiOutput.setNam(hoatDongChanNuoi.getNam());
		hoatDongChanNuoiOutput.setQuy(hoatDongChanNuoi.getQuy());
		if(hoatDongChanNuoi.getCoSoChanNuoiId() != null && hoatDongChanNuoi.getCoSoChanNuoiId() > 0) {
			Optional<CoSoChanNuoi> optional = serviceCoSoChanNuoiService.findById(hoatDongChanNuoi.getCoSoChanNuoiId());
			if (optional.isPresent()) {
				hoatDongChanNuoiOutput.setCoSoChanNuoiTen(optional.get().getTenCoSo());
			}	
		}
		if(hoatDongChanNuoi.getLoaiVatNuoiId() != null && hoatDongChanNuoi.getLoaiVatNuoiId() > 0) {
			Optional<DmLoaiVatNuoi> optional = serviceDmLoaiVatNuoiService.findById(hoatDongChanNuoi.getLoaiVatNuoiId());
			if (optional.isPresent()) {
				hoatDongChanNuoiOutput.setLoaiVatNuoiTen(optional.get().getTen());
			}	
		}
		return hoatDongChanNuoiOutput;
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
	
	public HoatDongChanNuoiOutput findById(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoiOutput.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi hoatDongChanNuoi = optional.get();
		return this.convertToHoatDongChanNuoiOutput(hoatDongChanNuoi);
	}
	
	public HoatDongChanNuoiOutput create(HoatDongChanNuoiOutput hoatDongChanNuoiOutput,
			BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		HoatDongChanNuoi hoatDongChanNuoi = new HoatDongChanNuoi();
		hoatDongChanNuoi.setDaXoa(false);
		hoatDongChanNuoi.setDonViTinh(hoatDongChanNuoiOutput.getDonViTinh());
		hoatDongChanNuoi.setLoaiVatNuoiId(hoatDongChanNuoiOutput.getLoaiVatNuoiId());
		hoatDongChanNuoi.setCoSoChanNuoiId(hoatDongChanNuoiOutput.getCoSoChanNuoiId());
		hoatDongChanNuoi.setSlVatNuoiXuat(hoatDongChanNuoiOutput.getSlVatNuoiXuat());
		hoatDongChanNuoi.setMucDichNuoi(hoatDongChanNuoiOutput.getMucDichNuoi());
		hoatDongChanNuoi.setSoLuongNuoi(hoatDongChanNuoiOutput.getSoLuongNuoi());
		hoatDongChanNuoi.setSanLuongXuat(hoatDongChanNuoiOutput.getSanLuongXuat());
		hoatDongChanNuoi.setThoiGianBatDauNuoi(hoatDongChanNuoiOutput.getThoiGianBatDauNuoi());
		hoatDongChanNuoi.setThoiGianXuat(hoatDongChanNuoiOutput.getThoiGianXuat());
		hoatDongChanNuoi.setGhiChu(hoatDongChanNuoiOutput.getGhiChu());
		hoatDongChanNuoi.setNam(hoatDongChanNuoiOutput.getNam());
		hoatDongChanNuoi.setQuy(hoatDongChanNuoiOutput.getQuy());
		hoatDongChanNuoi = serviceHoatDongChanNuoiService.save(hoatDongChanNuoi);
		return this.convertToHoatDongChanNuoiOutput(hoatDongChanNuoi);
	}
	
	
	public HoatDongChanNuoiOutput update(Long id, HoatDongChanNuoiOutput hoatDongChanNuoiOutput, 
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException{
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi hoatDongChanNuoi = optional.get();
		hoatDongChanNuoi.setDonViTinh(hoatDongChanNuoiOutput.getDonViTinh());
		hoatDongChanNuoi.setLoaiVatNuoiId(hoatDongChanNuoiOutput.getLoaiVatNuoiId());
		hoatDongChanNuoi.setCoSoChanNuoiId(hoatDongChanNuoiOutput.getCoSoChanNuoiId());
		hoatDongChanNuoi.setMucDichNuoi(hoatDongChanNuoiOutput.getMucDichNuoi());
		hoatDongChanNuoi.setSlVatNuoiXuat(hoatDongChanNuoiOutput.getSlVatNuoiXuat());
		hoatDongChanNuoi.setSoLuongNuoi(hoatDongChanNuoiOutput.getSoLuongNuoi());
		hoatDongChanNuoi.setSanLuongXuat(hoatDongChanNuoiOutput.getSanLuongXuat());
		hoatDongChanNuoi.setThoiGianBatDauNuoi(hoatDongChanNuoiOutput.getThoiGianBatDauNuoi());
		hoatDongChanNuoi.setThoiGianXuat(hoatDongChanNuoiOutput.getThoiGianXuat());
		hoatDongChanNuoi.setGhiChu(hoatDongChanNuoiOutput.getGhiChu());
		hoatDongChanNuoi.setNam(hoatDongChanNuoiOutput.getNam());
		hoatDongChanNuoi.setQuy(hoatDongChanNuoiOutput.getQuy());
		hoatDongChanNuoi = serviceHoatDongChanNuoiService.save(hoatDongChanNuoi);
		return this.convertToHoatDongChanNuoiOutput(hoatDongChanNuoi);		
	}
	
	@DeleteMapping(value = { "/{id}" })
	public HoatDongChanNuoiOutput delete(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi hoatDongChanNuoi = optional.get();
		hoatDongChanNuoi.setDaXoa(true);
		hoatDongChanNuoi = serviceHoatDongChanNuoiService.save(hoatDongChanNuoi);
		return this.convertToHoatDongChanNuoiOutput(hoatDongChanNuoi);	
	}
	
}
	
