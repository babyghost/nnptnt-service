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

import jdk.internal.org.jline.utils.Log;
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
		System.out.println("pageHoatDongChanNuoi " + pageHoatDongChanNuoi.getContent().size());
		final Page<HoatDongChanNuoiOutput> pageHoatDongChanNuoiOutput = pageHoatDongChanNuoi
				.map(this::convertToHoatDongChanNuoiOutput);
		System.out.println("HoatDongChanNuoiOutput " + pageHoatDongChanNuoiOutput.getContent().size());
		return pageHoatDongChanNuoiOutput;
	}
	
	private HoatDongChanNuoiOutput convertToHoatDongChanNuoiOutput(HoatDongChanNuoi hoatDongChanNuoi) {
		System.out.println("hoatDongChanNuoi ... "+hoatDongChanNuoi.getId());
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
			}
		}
		
		List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuois = new ArrayList<ThongTinHoatDongChanNuoiOutput>();
		List<HoatDongChanNuoi> listHoatDongs = serviceHoatDongChanNuoiService
				.findByCoSoChanNuoiIdAndDaXoa(hoatDongChanNuoi.getCoSoChanNuoiId(), false);
		System.out.println("listHoatDongs size" +listHoatDongs.size());
		if(Objects.nonNull(listHoatDongs) &&! listHoatDongs.isEmpty()) {
			for(HoatDongChanNuoi listChanNuoi : listHoatDongs) {
				ThongTinHoatDongChanNuoiOutput thongTinChanNuoi = new ThongTinHoatDongChanNuoiOutput();
				thongTinChanNuoi.setId(listChanNuoi.getId());
				thongTinChanNuoi.setLoaiVatNuoiId(listChanNuoi.getLoaiVatNuoiId());
				if (thongTinChanNuoi.getLoaiVatNuoiId() != null && thongTinChanNuoi.getLoaiVatNuoiId() > 0) {
					Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService
							.findById(thongTinChanNuoi.getLoaiVatNuoiId());
					if (optionalLoaiVatNuoi.isPresent()) {
						thongTinChanNuoi.setLoaiVatNuoi(optionalLoaiVatNuoi.get().getTen());
					}
				}
				
				thongTinChanNuoi.setDonViTinh(listChanNuoi.getDonViTinh());
				thongTinChanNuoi.setSoLuongNuoi(listChanNuoi.getSoLuongNuoi());
				thongTinChanNuoi.setMucDichNuoi(listChanNuoi.getMucDichNuoi());
				thongTinChanNuoi.setThoiGianBatDauNuoi(listChanNuoi.getThoiGianBatDauNuoi());
				thongTinChanNuoi.setThoiGianXuat(listChanNuoi.getThoiGianXuat());
				thongTinChanNuoi.setSlVatNuoiXuat(listChanNuoi.getSlVatNuoiXuat());
				thongTinChanNuoi.setSanLuongXuat(listChanNuoi.getSanLuongXuat());
				thongTinChanNuoi.setGhiChu(listChanNuoi.getGhiChu());
				thongTinChanNuoi.setNam(listChanNuoi.getNam());
				thongTinChanNuoi.setQuy(listChanNuoi.getQuy());
				
				listHoatDongChanNuois.add(thongTinChanNuoi);
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
	public List<HoatDongChanNuoi> getHoatDongChanNuoiByCoSoAndNamAndQuy(Long coSoChanNuoiId, String nam, Integer quy) 
			throws EntityNotFoundException {
		List<HoatDongChanNuoi> list = serviceHoatDongChanNuoiService.findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(coSoChanNuoiId, 
				nam, quy, false);
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
	
