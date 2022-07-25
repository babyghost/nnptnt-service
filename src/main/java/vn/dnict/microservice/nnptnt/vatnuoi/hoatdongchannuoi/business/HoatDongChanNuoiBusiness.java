package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongKeSoLuongData;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service.CoSoChanNuoiService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.BaoCaoHoatDongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.CoSoChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.HoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.data.NamData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business.view.MyExcelViewThongKeHoatDong;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.business.view.MyExcelViewThongKeSoLuong;
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

	public Page<CoSoChanNuoiData> findAll(int page, int size, String sortBy, String sortDir, String tenCoSo,
			String tenChuCoSo, String dienThoai, Long quanHuyenId, Long phuongXaId, String nam, Integer quy) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.findAll(tenCoSo, tenChuCoSo,
				dienThoai, quanHuyenId, phuongXaId, nam, quy, PageRequest.of(page, size, direction, sortBy));
		final Page<CoSoChanNuoiData> pageHoatDongChanNuoiOutput = pageHoatDongChanNuoi
				.map(this::convertToCoSoChanNuoiData);

		List<CoSoChanNuoiData> hoatDongChanNuoiOutputss = new ArrayList<>(pageHoatDongChanNuoiOutput.getContent());

		List<CoSoChanNuoiData> hoatDongChanNuoiOutputs = new ArrayList<>();

		for (CoSoChanNuoiData element : hoatDongChanNuoiOutputss) {
			// Check if element not exist in list, perform add element to list
			if (!hoatDongChanNuoiOutputs.contains(element)) {
				hoatDongChanNuoiOutputs.add(element);
			}
		}
		Page<CoSoChanNuoiData> hoatDongChanNuoiOutputImpl = new PageImpl<>(hoatDongChanNuoiOutputs);
		return hoatDongChanNuoiOutputImpl;
	}

	private HoatDongChanNuoiOutput convertToHoatDongChanNuoiOutput(HoatDongChanNuoi hoatDongChanNuoi) {
		HoatDongChanNuoiOutput hoatDongChanNuoiOutput = new HoatDongChanNuoiOutput();
//		hoatDongChanNuoiOutput.setId(hoatDongChanNuoi.getId());
		hoatDongChanNuoiOutput.setCoSoChanNuoiId(hoatDongChanNuoi.getCoSoChanNuoiId());

		if (hoatDongChanNuoi.getCoSoChanNuoiId() != null && hoatDongChanNuoi.getCoSoChanNuoiId() > 0) {
			Optional<CoSoChanNuoi> optionalCoSo = serviceCoSoChanNuoiService
					.findById(hoatDongChanNuoi.getCoSoChanNuoiId());

			if (optionalCoSo.isPresent()) {
				hoatDongChanNuoiOutput.setCoSoTen(optionalCoSo.get().getTenCoSo());
				hoatDongChanNuoiOutput.setTenChuCoSo(optionalCoSo.get().getTenChuCoSo());
				hoatDongChanNuoiOutput.setDienThoai(optionalCoSo.get().getDienThoai());
				hoatDongChanNuoiOutput.setDiaChi(optionalCoSo.get().getDiaChi());
				hoatDongChanNuoiOutput.setEmail(optionalCoSo.get().getEmail());
				hoatDongChanNuoiOutput.setQuanHuyenId(optionalCoSo.get().getQuanHuyenId());
				if (hoatDongChanNuoiOutput.getQuanHuyenId() != null && hoatDongChanNuoiOutput.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService
							.findById(hoatDongChanNuoiOutput.getQuanHuyenId());
					if (optionalQuanHuyen.isPresent()) {
						hoatDongChanNuoiOutput.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}
				}

				hoatDongChanNuoiOutput.setPhuongXaId(optionalCoSo.get().getPhuongXaId());
				if (hoatDongChanNuoiOutput.getPhuongXaId() != null && hoatDongChanNuoiOutput.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService
							.findById(hoatDongChanNuoiOutput.getPhuongXaId());
					if (optionalPhuongXa.isPresent()) {
						hoatDongChanNuoiOutput.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}
				}
			}
		}

		List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuois = new ArrayList<ThongTinHoatDongChanNuoiOutput>();
		List<HoatDongChanNuoi> listHoatDongs = serviceHoatDongChanNuoiService
				.findByCoSoChanNuoiIdAndDaXoa(hoatDongChanNuoi.getCoSoChanNuoiId(), false);
		if (Objects.nonNull(listHoatDongs) && !listHoatDongs.isEmpty()) {
			for (HoatDongChanNuoi listChanNuoi : listHoatDongs) {
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
		List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuoiss = new ArrayList<>();

		for (ThongTinHoatDongChanNuoiOutput element : listHoatDongChanNuois) {
			// Check if element not exist in list, perform add element to list
			if (!listHoatDongChanNuoiss.contains(element)) {
				listHoatDongChanNuoiss.add(element);
			}
		}

		hoatDongChanNuoiOutput.setListHoatDongChanNuoi(listHoatDongChanNuoiss);
		return hoatDongChanNuoiOutput;
	}

	public CoSoChanNuoiData convertToCoSoChanNuoiData(HoatDongChanNuoi hoatDongChanNuoi) {
		CoSoChanNuoiData hoatDongChanNuoiOutput = new CoSoChanNuoiData();
//		hoatDongChanNuoiOutput.setId(hoatDongChanNuoi.getId());
		hoatDongChanNuoiOutput.setCoSoChanNuoiId(hoatDongChanNuoi.getCoSoChanNuoiId());

		if (hoatDongChanNuoi.getCoSoChanNuoiId() != null && hoatDongChanNuoi.getCoSoChanNuoiId() > 0) {
			Optional<CoSoChanNuoi> optionalCoSo = serviceCoSoChanNuoiService
					.findById(hoatDongChanNuoi.getCoSoChanNuoiId());

			if (optionalCoSo.isPresent()) {
				hoatDongChanNuoiOutput.setCoSoTen(optionalCoSo.get().getTenCoSo());
				hoatDongChanNuoiOutput.setTenChuCoSo(optionalCoSo.get().getTenChuCoSo());
				hoatDongChanNuoiOutput.setDienThoai(optionalCoSo.get().getDienThoai());
				hoatDongChanNuoiOutput.setDiaChi(optionalCoSo.get().getDiaChi());
				hoatDongChanNuoiOutput.setEmail(optionalCoSo.get().getEmail());
				hoatDongChanNuoiOutput.setQuanHuyenId(optionalCoSo.get().getQuanHuyenId());
				if (hoatDongChanNuoiOutput.getQuanHuyenId() != null && hoatDongChanNuoiOutput.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService
							.findById(hoatDongChanNuoiOutput.getQuanHuyenId());
					if (optionalQuanHuyen.isPresent()) {
						hoatDongChanNuoiOutput.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}
				}
				hoatDongChanNuoiOutput.setNam(hoatDongChanNuoi.getNam());
				hoatDongChanNuoiOutput.setPhuongXaId(optionalCoSo.get().getPhuongXaId());
				if (hoatDongChanNuoiOutput.getPhuongXaId() != null && hoatDongChanNuoiOutput.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService
							.findById(hoatDongChanNuoiOutput.getPhuongXaId());
					if (optionalPhuongXa.isPresent()) {
						hoatDongChanNuoiOutput.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}
				}
			}
		}

		List<NamData> listNamss = new ArrayList<NamData>();
		List<HoatDongChanNuoi> listNams = serviceHoatDongChanNuoiService
				.findQuyByNamAndcoSoChanNuoiId(hoatDongChanNuoi.getNam(), hoatDongChanNuoi.getCoSoChanNuoiId());

		System.out.println(listNams);
		for (HoatDongChanNuoi Datas : listNams) {
			NamData namData = new NamData();
			namData.setQuy(Datas.getQuy());
	

			
			List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuois = new ArrayList<ThongTinHoatDongChanNuoiOutput>();
			List<HoatDongChanNuoi> listHoatDongs = serviceHoatDongChanNuoiService
					.findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(hoatDongChanNuoi.getCoSoChanNuoiId(), hoatDongChanNuoiOutput.getNam(), namData.getQuy(), false);
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
			List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuoiss = new ArrayList<>();

			for (ThongTinHoatDongChanNuoiOutput element : listHoatDongChanNuois) {
	            // Check if element not exist in list, perform add element to list
	            if (!listHoatDongChanNuoiss.contains(element)) {
	            	listHoatDongChanNuoiss.add(element);
	            }
	        }
			
			namData.setListHoatDongChanNuoi(listHoatDongChanNuoiss);
			
			
			
			listNamss.add(namData);

		}
		
		List<NamData> listNamData1 = new ArrayList<>();

		for (NamData element : listNamss) {
            // Check if element not exist in list, perform add element to list
            if (!listNamData1.contains(element)) {
            	listNamData1.add(element);
            }
        }
		
		
		
		hoatDongChanNuoiOutput.setListNamDatas(listNamData1);

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
		List<HoatDongChanNuoi> list = serviceHoatDongChanNuoiService
				.findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(coSoChanNuoiId, nam, quy, false);
		return list;
	}

	public List<HoatDongChanNuoi> create(HoatDongChanNuoiOutput hoatDongChanNuoiOutput, BindingResult result)
			throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}

		List<HoatDongChanNuoi> list = new ArrayList<HoatDongChanNuoi>();
		if (hoatDongChanNuoiOutput.getListHoatDongChanNuoi().size() > 0) {
			for (int i = 0; i < hoatDongChanNuoiOutput.getListHoatDongChanNuoi().size(); i++) {
				HoatDongChanNuoi hoatDongChanNuoi = new HoatDongChanNuoi();
				if (hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId() != null
						&& hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId() > 0) {
					Optional<HoatDongChanNuoi> optHdCn = serviceHoatDongChanNuoiService
							.findById(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getId());
					if (optHdCn.isPresent()) {
						hoatDongChanNuoi = optHdCn.get();
					}
				}
				hoatDongChanNuoi.setDonViTinh(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getDonViTinh());
				hoatDongChanNuoi
						.setLoaiVatNuoiId(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getLoaiVatNuoiId());
				hoatDongChanNuoi
						.setMucDichNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getMucDichNuoi());
				hoatDongChanNuoi
						.setSlVatNuoiXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getSlVatNuoiXuat());
				hoatDongChanNuoi
						.setSoLuongNuoi(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getSoLuongNuoi());
				hoatDongChanNuoi
						.setSanLuongXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getSanLuongXuat());
				hoatDongChanNuoi.setThoiGianBatDauNuoi(
						hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getThoiGianBatDauNuoi());
				hoatDongChanNuoi
						.setThoiGianXuat(hoatDongChanNuoiOutput.getListHoatDongChanNuoi().get(i).getThoiGianXuat());
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

	
	public Integer deletes(List<Long> ids) throws EntityNotFoundException {
		List<HoatDongChanNuoi> result = new ArrayList<HoatDongChanNuoi>();
		if (ids.size() > 0) {
			ids.stream().forEach(item -> {
				Optional<HoatDongChanNuoi> optional = serviceHoatDongChanNuoiService
						.findById(item);
				if (optional.isPresent()) {
					HoatDongChanNuoi object = optional.get();
					object.setDaXoa(true);			
					serviceHoatDongChanNuoiService.save(object);
					result.add(object);
				}
			});
		}
		return result.size();
	}
	
//	----------------------------------------Baocaothongkesoluongvatnuoi----------------------------------------------

	public Page<Object> thongKeSoLuongDemo(int page, int size, String sortBy, String sortDir, String nam,
			List<Integer> quy, List<Long> loaiVatNuoiIds, Integer soLuongNuoi, Integer slVatNuoiXuat,
			Float sanLuongXuat) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<Object> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.thongKeSoVatNuoiDemo(nam,
				loaiVatNuoiIds, quy, PageRequest.of(page, size, direction, sortBy));

		// Page<Object> thongKeSoVatNuoi =
		// serviceHoatDongChanNuoiService.thongKeSoVatNuoi("2022", 2L , 2,
		// PageRequest.of(page, size, direction, sortBy));
		// log.info("thongke {} ",thongKeSoVatNuoi.getContent().get(0));

		return pageHoatDongChanNuoi;
	}

	public Page<ThongKeSoLuongChanNuoiData> thongKeSoLuong(int page, int size, String sortBy, String sortDir,
			String nam, List<Integer> quy, List<Long> loaiVatNuoiIds, Integer soLuongNuoi, Integer slVatNuoiXuat,
			Float sanLuongXuat) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.thongKeSoLuong(nam, quy,
				loaiVatNuoiIds, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeSoLuongChanNuoiData> ThongKeSoLuongChanNuoiData = pageHoatDongChanNuoi
				.map(this::convertToThongKeSoLuongChanNuoiDataThongKe);

		List<ThongKeSoLuongChanNuoiData> thongKeSoLuongChanNuoiDatass = new ArrayList<>(
				ThongKeSoLuongChanNuoiData.getContent());
		// Page<Object> thongKeSoVatNuoi =
		// serviceHoatDongChanNuoiService.thongKeSoVatNuoi("2022", 2L , 2,
		// PageRequest.of(page, size, direction, sortBy));
		// log.info("thongke {} ",thongKeSoVatNuoi.getContent().get(0));
		List<ThongKeSoLuongChanNuoiData> thongKeSoLuongChanNuoiDatas = new ArrayList<>();

		for (ThongKeSoLuongChanNuoiData element : thongKeSoLuongChanNuoiDatass) {
			// Check if element not exist in list, perform add element to list
			if (!thongKeSoLuongChanNuoiDatas.contains(element)) {
				thongKeSoLuongChanNuoiDatas.add(element);
			}
		}
		Page<ThongKeSoLuongChanNuoiData> thongKeSoLuongChanNuoiDataImpl = new PageImpl<>(thongKeSoLuongChanNuoiDatas);
		return thongKeSoLuongChanNuoiDataImpl;
	}

	private ThongKeSoLuongChanNuoiData convertToThongKeSoLuongChanNuoiDataThongKe(HoatDongChanNuoi hoatDongChanNuoi) {

		ThongKeSoLuongChanNuoiData thongKeSoLuongChanNuoiData = new ThongKeSoLuongChanNuoiData();
		thongKeSoLuongChanNuoiData.setLoaiVatNuoiId(hoatDongChanNuoi.getLoaiVatNuoiId());
		List<Long> loaiVatNuoiIds = new ArrayList<>();
		loaiVatNuoiIds.add(hoatDongChanNuoi.getLoaiVatNuoiId());
		List<Integer> quys = new ArrayList<>();
		quys.add(hoatDongChanNuoi.getQuy());
		List<Object[]> hDCN = serviceHoatDongChanNuoiService.thongKeSoVatNuoi(hoatDongChanNuoi.getNam(), loaiVatNuoiIds,
				quys);
		List<Object[]> filter = new ArrayList<Object[]>();
		for (Object[] element : hDCN) {
			// Check if element not exist in list, perform add element to list
			if (!filter.contains(element)) {
				filter.add(element);
			}
		}

		System.out.println(hoatDongChanNuoi.getCoSoChanNuoiId() + "99999");
		for (Object[] Datas : filter) {
			thongKeSoLuongChanNuoiData.setNam(String.valueOf(Datas[0]));
			thongKeSoLuongChanNuoiData.setQuy(Integer.valueOf(Datas[1].toString()));
			thongKeSoLuongChanNuoiData.setLoaiVatNuoiId(Long.valueOf(Datas[2].toString()));
			thongKeSoLuongChanNuoiData.setTongSoLuongVatNuoi(String.valueOf(Datas[3]));
			thongKeSoLuongChanNuoiData.setTongSlVatNuoiXuat(String.valueOf(Datas[4]));
			thongKeSoLuongChanNuoiData.setTongSanLuongXuat(String.valueOf(Datas[5]));

		}

		if (thongKeSoLuongChanNuoiData.getLoaiVatNuoiId() != null
				&& thongKeSoLuongChanNuoiData.getLoaiVatNuoiId() > 0) {
			Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService
					.findById(thongKeSoLuongChanNuoiData.getLoaiVatNuoiId());
			if (optionalLoaiVatNuoi.isPresent()) {
				;
				thongKeSoLuongChanNuoiData.setLoaiVatNuoiTen(optionalLoaiVatNuoi.get().getTen());

			}
		}

		return thongKeSoLuongChanNuoiData;
	}

//	
	public ModelAndView exportExcelThongKeSoLuong(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir, String nam, List<Integer> quy, List<Long> loaiVatNuoiIds) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		Map<String, Object> model = new HashMap<String, Object>();

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.thongKeSoLuong(nam, quy,
				loaiVatNuoiIds, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeSoLuongChanNuoiData> pageThongKeSoLuongChanNuoiData = pageHoatDongChanNuoi
				.map(this::convertToThongKeSoLuongChanNuoiDataThongKe);

		List<ThongKeSoLuongChanNuoiData> thongKeSoLuongChanNuoiDatass = new ArrayList<>(
				pageThongKeSoLuongChanNuoiData.getContent());

		System.out.println(thongKeSoLuongChanNuoiDatass + "----------//" + response + pageHoatDongChanNuoi);

		// All the remaining employees
		while (pageHoatDongChanNuoi.hasNext()) {
			Page<HoatDongChanNuoi> nextPageOfEmployees = serviceHoatDongChanNuoiService.thongKeSoLuong(nam, quy,
					loaiVatNuoiIds, pageHoatDongChanNuoi.nextPageable());
			Page<ThongKeSoLuongChanNuoiData> nextPageOfThongKeSoLuongChanNuoiData = nextPageOfEmployees
					.map(this::convertToThongKeSoLuongChanNuoiDataThongKe);
			if (Objects.nonNull(nextPageOfThongKeSoLuongChanNuoiData.getContent())) {
				thongKeSoLuongChanNuoiDatass.addAll(nextPageOfThongKeSoLuongChanNuoiData.getContent());
			}
			// update the page reference to the current page
			pageHoatDongChanNuoi = nextPageOfEmployees;

		}
		List<ThongKeSoLuongChanNuoiData> thongKeSoLuongChanNuoiDatas = new ArrayList<>();

		for (ThongKeSoLuongChanNuoiData element : thongKeSoLuongChanNuoiDatass) {
			// Check if element not exist in list, perform add element to list
			if (!thongKeSoLuongChanNuoiDatas.contains(element)) {
				thongKeSoLuongChanNuoiDatas.add(element);
			}
		}

		model.put("thongKeSoLuongChanNuoiDatas", thongKeSoLuongChanNuoiDatas);

		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=" + formattedString + "ThongKe.xls");
		return new ModelAndView(new MyExcelViewThongKeSoLuong(), model);

	}

//------------------------------------------Thống Kê Hoạt Động -------------------------------------------------

	public Page<BaoCaoHoatDongChanNuoiData> thongKeHoatDong(int page, int size, String sortBy, String sortDir,
			String tenCoSo, String tenChuCoSo, List<Long> loaiVatNuoiIds, Long quanHuyenId, Long phuongXaId, String nam,
			List<Integer> quys) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.thongKeHoatDong(tenCoSo,
				tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys,
				PageRequest.of(page, size, direction, sortBy));
		final Page<BaoCaoHoatDongChanNuoiData> pageBaoCaoHoatDongChanNuoiData = pageHoatDongChanNuoi
				.map(this::convertToBaoCaoHoatDongChanNuoi);
		return pageBaoCaoHoatDongChanNuoiData;
	}

	private BaoCaoHoatDongChanNuoiData convertToBaoCaoHoatDongChanNuoi(HoatDongChanNuoi hoatDongChanNuoi) {

		BaoCaoHoatDongChanNuoiData baoCaoHoatDongChanNuoiData = new BaoCaoHoatDongChanNuoiData();
		baoCaoHoatDongChanNuoiData.setId(hoatDongChanNuoi.getId());
		baoCaoHoatDongChanNuoiData.setNam(hoatDongChanNuoi.getNam());
		baoCaoHoatDongChanNuoiData.setQuy(hoatDongChanNuoi.getQuy());
		baoCaoHoatDongChanNuoiData.setSoLuongNuoi(hoatDongChanNuoi.getSoLuongNuoi());
		baoCaoHoatDongChanNuoiData.setSlVatNuoiXuat(hoatDongChanNuoi.getSlVatNuoiXuat());
		baoCaoHoatDongChanNuoiData.setSanLuongXuat(hoatDongChanNuoi.getSanLuongXuat());

		if (hoatDongChanNuoi.getLoaiVatNuoiId() != null && hoatDongChanNuoi.getLoaiVatNuoiId() > 0) {
			Optional<DmLoaiVatNuoi> optionalLoaiVatNuoi = serviceDmLoaiVatNuoiService
					.findById(hoatDongChanNuoi.getLoaiVatNuoiId());
			if (optionalLoaiVatNuoi.isPresent()) {
				baoCaoHoatDongChanNuoiData.setLoaiVatNuoi(optionalLoaiVatNuoi.get().getTen());
				baoCaoHoatDongChanNuoiData.setLoaiVatNuoiId(optionalLoaiVatNuoi.get().getId());
			}
		}

		if (hoatDongChanNuoi.getCoSoChanNuoiId() != null && hoatDongChanNuoi.getCoSoChanNuoiId() > 0) {
			Optional<CoSoChanNuoi> optionalCoSo = serviceCoSoChanNuoiService
					.findById(hoatDongChanNuoi.getCoSoChanNuoiId());

			if (optionalCoSo.isPresent()) {
				baoCaoHoatDongChanNuoiData.setTenCoSo(optionalCoSo.get().getTenCoSo());
				baoCaoHoatDongChanNuoiData.setTenChuCoSo(optionalCoSo.get().getTenChuCoSo());
				baoCaoHoatDongChanNuoiData.setDiaChi(optionalCoSo.get().getDiaChi());
				baoCaoHoatDongChanNuoiData.setQuanHuyenId(optionalCoSo.get().getQuanHuyenId());
				if (baoCaoHoatDongChanNuoiData.getQuanHuyenId() != null
						&& baoCaoHoatDongChanNuoiData.getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService
							.findById(baoCaoHoatDongChanNuoiData.getQuanHuyenId());
					if (optionalQuanHuyen.isPresent()) {
						baoCaoHoatDongChanNuoiData.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}
				}

				baoCaoHoatDongChanNuoiData.setPhuongXaId(optionalCoSo.get().getPhuongXaId());
				if (baoCaoHoatDongChanNuoiData.getPhuongXaId() != null
						&& baoCaoHoatDongChanNuoiData.getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService
							.findById(baoCaoHoatDongChanNuoiData.getPhuongXaId());
					if (optionalPhuongXa.isPresent()) {
						baoCaoHoatDongChanNuoiData.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}
				}
			}
		}

		return baoCaoHoatDongChanNuoiData;

	}

	public ModelAndView exportExcelThongKeHoatDong(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir, String tenCoSo, String tenChuCoSo, List<Long> loaiVatNuoiIds,
			Long quanHuyenId, Long phuongXaId, String nam, List<Integer> quys) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		Map<String, Object> model = new HashMap<String, Object>();

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<HoatDongChanNuoi> pageHoatDongChanNuoi = serviceHoatDongChanNuoiService.thongKeHoatDong(tenCoSo,
				tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys,
				PageRequest.of(page, size, direction, sortBy));
		Page<BaoCaoHoatDongChanNuoiData> pageBaoCaoHoatDongChanNuoiData = pageHoatDongChanNuoi
				.map(this::convertToBaoCaoHoatDongChanNuoi);

		List<BaoCaoHoatDongChanNuoiData> baoCaoHoatDongChanNuoiDatas = new ArrayList<>(
				pageBaoCaoHoatDongChanNuoiData.getContent());

		// All the remaining employees
		while (pageHoatDongChanNuoi.hasNext()) {
			Page<HoatDongChanNuoi> nextPageOfEmployees = serviceHoatDongChanNuoiService.thongKeHoatDong(tenCoSo,
					tenChuCoSo, loaiVatNuoiIds, quanHuyenId, phuongXaId, nam, quys,
					pageHoatDongChanNuoi.nextPageable());
			Page<BaoCaoHoatDongChanNuoiData> nextPageOfBaoCaoHoatDongChanNuoiData = nextPageOfEmployees
					.map(this::convertToBaoCaoHoatDongChanNuoi);
			if (Objects.nonNull(nextPageOfBaoCaoHoatDongChanNuoiData.getContent())) {
				baoCaoHoatDongChanNuoiDatas.addAll(nextPageOfBaoCaoHoatDongChanNuoiData.getContent());
			}
			// update the page reference to the current page
			pageHoatDongChanNuoi = nextPageOfEmployees;
		}

		model.put("baoCaoHoatDongChanNuoiDatas", baoCaoHoatDongChanNuoiDatas);

		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=" + formattedString + "ThongKe.xls");
		return new ModelAndView(new MyExcelViewThongKeHoatDong(), model);

	}
}
