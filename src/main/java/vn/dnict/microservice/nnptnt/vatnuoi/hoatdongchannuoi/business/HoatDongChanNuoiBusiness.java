package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service.HoatDongChanNuoiService;

@Service
public class HoatDongChanNuoiBusiness {
	
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	HoatDongChanNuoiService serviceHoatDongChanNuoiService;
	
	@Autowired
	DmLoaiVatNuoiService serviceDmLoaiVatNuoiService;
	
	@Autowired
	CoSoChanNuoiService serviceCoSoChanNuoiService;
	
	public Page<HoatDongChanNuoiOutput> findAll(int page, int size, String sortBy, String sortDir, String tenCoSo, 
			String tenChuCoSo, String dienThoai, Long quanHuyenId, Long phuongXaId, String nam, Integer quy) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.findAll(tenCoSo, tenChuCoSo, 
				dienThoai, quanHuyenId, phuongXaId, nam, quy, PageRequest.of(page, size, direction, sortBy));
		final Page<HoatDongChanNuoiOutput> pageHoatDongChanNuoiOutput = pageHoatDongChanNuoi
				.map(this::convertToHoatDongChanNuoiOutput);
		return pageHoatDongChanNuoiOutput;
	}
	
	private HoatDongChanNuoiOutput convertToHoatDongChanNuoiOutput(HoatDongChanNuoi hoatDongChanNuoi) {
	
		HoatDongChanNuoiOutput hoatDongChanNuoiOutput = new HoatDongChanNuoiOutput();
		hoatDongChanNuoiOutput.setId(hoatDongChanNuoi.getId());
		hoatDongChanNuoiOutput.setCoSoChanNuoiId(hoatDongChanNuoi.getCoSoChanNuoiId());

		if(hoatDongChanNuoi.getCoSoChanNuoiId() != null && hoatDongChanNuoi.getCoSoChanNuoiId() > 0) {
			Optional<CoSoChanNuoi> optionalCoSo = serviceCoSoChanNuoiService.findById(hoatDongChanNuoi
					.getCoSoChanNuoiId());
		
			if (optionalCoSo.isPresent()) {
				hoatDongChanNuoiOutput.setCoSoTen(optionalCoSo.get().getTenCoSo());
				hoatDongChanNuoiOutput.setTenChuCoSo(optionalCoSo.get().getTenChuCoSo());
				hoatDongChanNuoiOutput.setDienThoai(optionalCoSo.get().getDienThoai());
				hoatDongChanNuoiOutput.setDiaChi(optionalCoSo.get().getDiaChi());
				hoatDongChanNuoiOutput.setEmail(optionalCoSo.get().getEmail());
				hoatDongChanNuoiOutput.setQuanHuyenId(optionalCoSo.get().getQuanHuyenId());
				if (hoatDongChanNuoiOutput.getQuanHuyenId() != null && hoatDongChanNuoiOutput.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(hoatDongChanNuoiOutput
							.getQuanHuyenId());
					if (optionalQuanHuyen.isPresent()) {
						hoatDongChanNuoiOutput.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}
				}
				
				hoatDongChanNuoiOutput.setPhuongXaId(optionalCoSo.get().getPhuongXaId());
				if (hoatDongChanNuoiOutput.getPhuongXaId() != null && hoatDongChanNuoiOutput.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(hoatDongChanNuoiOutput
							.getPhuongXaId());
					if (optionalPhuongXa.isPresent()) {
						hoatDongChanNuoiOutput.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}
				}
				
				hoatDongChanNuoiOutput.setListHoatDongChanNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi());				
			}
		}
		
		List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuois = new ArrayList<ThongTinHoatDongChanNuoiOutput>();
		List<HoatDongChanNuoi> listHoatDongs = serviceHoatDongChanNuoiService
				.findByCoSoChanNuoiIdAndDaXoa(hoatDongChanNuoi.getCoSoChanNuoiId(), false);
		if(Objects.nonNull(listHoatDongs) &&! listHoatDongs.isEmpty()) {
			for(HoatDongChanNuoi listChanNuoi : listHoatDongs) {
				ThongTinHoatDongChanNuoiOutput listHoatDongChanNuoi = new ThongTinHoatDongChanNuoiOutput();
				listHoatDongChanNuoi.setId(listChanNuoi.getId());
				listHoatDongChanNuoi.setLoaiVatNuoiId(listChanNuoi.getLoaiVatNuoiId());
				if (listHoatDongChanNuoi.getLoaiVatNuoiId() != null && listHoatDongChanNuoi.getLoaiVatNuoiId() > 0) {
					Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService
							.findById(listHoatDongChanNuoi.getLoaiVatNuoiId());
					if (optionalLoaiVatNuoi.isPresent()) {
						listHoatDongChanNuoi.setLoaiVatNuoi(optionalLoaiVatNuoi.get().getTen());
					}
				}
				
				listHoatDongChanNuoi.setDonViTinh(listChanNuoi.getDonViTinh());
				listHoatDongChanNuoi.setSoLuongNuoi(listChanNuoi.getSoLuongNuoi());
				listHoatDongChanNuoi.setMucDichNuoi(listChanNuoi.getMucDichNuoi());
				listHoatDongChanNuoi.setThoiGianBatDauNuoi(listChanNuoi.getThoiGianBatDauNuoi());
				listHoatDongChanNuoi.setThoiGianXuat(listChanNuoi.getThoiGianXuat());
				listHoatDongChanNuoi.setSlVatNuoiXuat(listChanNuoi.getSlVatNuoiXuat());
				listHoatDongChanNuoi.setSanLuongXuat(listChanNuoi.getSanLuongXuat());
				listHoatDongChanNuoi.setGhiChu(listChanNuoi.getGhiChu());
				listHoatDongChanNuoi.setNam(listChanNuoi.getNam());
				listHoatDongChanNuoi.setQuy(listChanNuoi.getQuy());
				
				listHoatDongChanNuois.add(listHoatDongChanNuoi);
			}
		}
		
		hoatDongChanNuoiOutput.setListHoatDongChanNuoi(listHoatDongChanNuois);
		return hoatDongChanNuoiOutput;
	}
	
	public HoatDongChanNuoiOutput findById(Long id) throws EntityNotFoundException {
		Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoiOutput.class, "id", String.valueOf(id));
		}
		HoatDongChanNuoi hoatDongChanNuoi = optional.get();
		return this.convertToHoatDongChanNuoiOutput(hoatDongChanNuoi);
	}
	public List<HoatDongChanNuoi> getHoatDongChanNuoiByCoSoAndNamAndQuy(Long coSoChanNuoiId, String nam, Integer quy) throws EntityNotFoundException {
		List<HoatDongChanNuoi> list = serviceHoatDongChanNuoiService.findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(coSoChanNuoiId, nam, quy, false);
		return list;
	}
	
	
	public List<HoatDongChanNuoi> create(HoatDongChanNuoiOutput hoatDongChanNuoiOutput,
			BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
			
		List<HoatDongChanNuoi> list = new ArrayList<HoatDongChanNuoi>();
		if(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().size() > 0) {
			for(int i = 0; i < hoatDongChanNuoiOutput.getListHoatDongChanNuoi().size(); i++) {
				HoatDongChanNuoi hoatDongChanNuoi = new HoatDongChanNuoi();
				if(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId() != null && 
						hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId() > 0) {
					Optional<HoatDongChanNuoi> optHdCn = serviceHoatDongChanNuoiService
							.findById(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId());
					if(optHdCn.isPresent()) {
						hoatDongChanNuoi = optHdCn.get();
					}
				}
				hoatDongChanNuoi.setDonViTinh(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getDonViTinh());
				hoatDongChanNuoi.setLoaiVatNuoiId(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getLoaiVatNuoiId());
//				if (hoatDongChanNuoi.getLoaiVatNuoiId() != null && hoatDongChanNuoi.getLoaiVatNuoiId() > 0) {
//					Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService
//							.findById(hoatDongChanNuoi.getLoaiVatNuoiId());
//					if (optionalLoaiVatNuoi.isPresent()) {
//						hoatDongChanNuoi.setLoaiVatNuoi(optionalLoaiVatNuoi.get().getTen());
//					}
//				}
				hoatDongChanNuoi.setMucDichNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getMucDichNuoi());
				hoatDongChanNuoi.setSlVatNuoiXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getSlVatNuoiXuat());
				hoatDongChanNuoi.setSoLuongNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getSoLuongNuoi());
				hoatDongChanNuoi.setSanLuongXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getSanLuongXuat());
				hoatDongChanNuoi.setThoiGianBatDauNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getThoiGianBatDauNuoi());
				hoatDongChanNuoi.setThoiGianXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i)
						.getThoiGianXuat());
				hoatDongChanNuoi.setGhiChu(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getGhiChu());
				hoatDongChanNuoi.setNam(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getNam());
				hoatDongChanNuoi.setQuy(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getQuy());
				hoatDongChanNuoi.setCoSoChanNuoiId(hoatDongChanNuoiOutput.getCoSoChanNuoiId());
				
				hoatDongChanNuoi = serviceHoatDongChanNuoiService.save(hoatDongChanNuoi);
				list.add(hoatDongChanNuoi);
			}
		}
		return list;
	}
	
//	public void update( HoatDongChanNuoiOutput hoatDongChanNuoiOutput) 
//			throws EntityNotFoundException, MethodArgumentNotValidException{
//
//		
//		List<ThongTinHoatDongChanNuoiOutput> lisHoatDongChanNuois = hoatDongChanNuoiOutput.getListHoatDongChanNuoi();
//		if(Objects.nonNull(lisHoatDongChanNuois) && !lisHoatDongChanNuois.isEmpty()) {
//			for (ThongTinHoatDongChanNuoiOutput listHoatDongChanNuoi : lisHoatDongChanNuois) {
//				HoatDongChanNuoi hoatDong = new HoatDongChanNuoi();
//				if (Objects.nonNull(hoatDong.getId())) {
//					Optional<HoatDongChanNuoi> optinalHoatDong = serviceHoatDongChanNuoiService
//							.findById(listHoatDongChanNuoi.getId());
//					if (optinalHoatDong.isPresent()) {
//						hoatDong = optinalHoatDong.get();
//					}
//				}
//
//				hoatDong.setCoSoChanNuoiId(hoatDongChanNuoiOutput.getCoSoChanNuoiId());
//				
//				hoatDong.setId(listHoatDongChanNuoi.getId());
//				hoatDong.setLoaiVatNuoiId(listHoatDongChanNuoi.getLoaiVatNuoiId());
//				hoatDong.setDonViTinh(listHoatDongChanNuoi.getDonViTinh());
//				hoatDong.setSoLuongNuoi(listHoatDongChanNuoi.getSoLuongNuoi());
//				hoatDong.setMucDichNuoi(listHoatDongChanNuoi.getMucDichNuoi());
//				hoatDong.setThoiGianBatDauNuoi(listHoatDongChanNuoi.getThoiGianBatDauNuoi());
//				hoatDong.setThoiGianXuat(listHoatDongChanNuoi.getThoiGianXuat());
//				hoatDong.setSlVatNuoiXuat(listHoatDongChanNuoi.getSlVatNuoiXuat());
//				hoatDong.setSanLuongXuat(listHoatDongChanNuoi.getSanLuongXuat());
//				hoatDong.setGhiChu(listHoatDongChanNuoi.getGhiChu());
//				hoatDong.setNam(listHoatDongChanNuoi.getNam());
//				hoatDong.setQuy(listHoatDongChanNuoi.getQuy());
//				
//				hoatDong = serviceHoatDongChanNuoiService.save(hoatDong);
//			}
//		}
//	}
	
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
	
