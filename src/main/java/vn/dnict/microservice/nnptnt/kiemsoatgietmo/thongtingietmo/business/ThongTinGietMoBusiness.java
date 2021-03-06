package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.business;

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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaigiayto.dao.model.DmLoaiGiayTo;
import vn.dnict.microservice.nnptnt.dm.loaigiayto.dao.service.DmLoaiGiayToService;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.CoSoGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.CoSoGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.SoLuongGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongKeSoLuongData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongTinGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongTinSoLuongGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.model.SoLuongGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.SoLuongGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.business.view.MyExcelViewTongHopSoLuongNgay;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.ThongTinGietMoService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public class ThongTinGietMoBusiness {
	@Autowired
	ThongTinGietMoService serviceThongTinGietMoService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmLoaiGiayToService serviceDmLoaiGiayToService;
	
	@Autowired
	DmLoaiVatNuoiService serviceDmLoaiVatNuoiService;
	
	@Autowired
	CoSoGietMoService serviceCoSoGietMoService;
	
	@Autowired
	SoLuongGietMoService serviceSoLuongGietMoService;
	
	public Page<ThongTinGietMoData> findAll(int page, int size, String sortBy, String sortDir, List<String> tenCoSos,
			String tenChuCoSo, String dienThoai, LocalDate gietMoTuNgay, LocalDate gietMoDenNgay, Long quanHuyenId,
			Long phuongXaId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<ThongTinGietMo> pageThongTinGietMo = serviceThongTinGietMoService.findAll(tenCoSos, tenChuCoSo, dienThoai, gietMoTuNgay,
				gietMoDenNgay, quanHuyenId, phuongXaId, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongTinGietMoData> pageThongTinGietMoData = pageThongTinGietMo.map(this::convertToThongTinGietMoData);
		return pageThongTinGietMoData;
	}
	
	public ThongTinGietMoData convertToThongTinGietMoData(ThongTinGietMo thongTinGietMo) {
		ThongTinGietMoData thongTinGietMoData = new ThongTinGietMoData();
		thongTinGietMoData.setId(thongTinGietMo.getId());
		thongTinGietMoData.setCoSoGietMoId(thongTinGietMo.getCoSoGietMoId());
		if(thongTinGietMo.getCoSoGietMoId() != null && thongTinGietMo.getCoSoGietMoId() > 0) {
			Optional<CoSoGietMo> optCoSo = serviceCoSoGietMoService.findById(thongTinGietMo.getCoSoGietMoId());
			if(optCoSo.isPresent()) {
				thongTinGietMoData.setCoSoTen(optCoSo.get().getTenCoSo());
				thongTinGietMoData.setTenChuCoSo(optCoSo.get().getTenChuCoSo());
				thongTinGietMoData.setDiaChi(optCoSo.get().getDiaChi());
				thongTinGietMoData.setDienThoai(optCoSo.get().getDienThoai());
				thongTinGietMoData.setEmail(optCoSo.get().getEmail());
				thongTinGietMoData.setQuanHuyenId(optCoSo.get().getQuanHuyenId());
				if(optCoSo.get().getQuanHuyenId() != null && optCoSo.get().getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optQuanHuyen = serviceDmQuanHuyenService.findById(optCoSo.get().getQuanHuyenId());
					if(optQuanHuyen.isPresent()) {
						thongTinGietMoData.setQuanHuyenTen(optQuanHuyen.get().getTen());
					}
				}
				thongTinGietMoData.setPhuongXaId(optCoSo.get().getPhuongXaId());
				if(optCoSo.get().getPhuongXaId() != null && optCoSo.get().getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optPhuongXa = serviceDmPhuongXaService.findById(optCoSo.get().getPhuongXaId());
					if(optPhuongXa.isPresent()) {
						thongTinGietMoData.setPhuongXaTen(optPhuongXa.get().getTen());
					}
				}
				thongTinGietMoData.setGiayKinhDoanh(optCoSo.get().getGiayKinhDoanh());
			}
		}
		thongTinGietMoData.setNgayThang(thongTinGietMo.getNgayThang());
		thongTinGietMoData.setChuHang(thongTinGietMo.getChuHang());
		thongTinGietMoData.setLoaiGiayToId(thongTinGietMo.getLoaiGiayToId());
		if(thongTinGietMo.getLoaiGiayToId() != null && thongTinGietMo.getLoaiGiayToId() > 0) {
			Optional<DmLoaiGiayTo> optGiayTo = serviceDmLoaiGiayToService.findById(thongTinGietMo.getLoaiGiayToId());
			if(optGiayTo.isPresent()) {
				thongTinGietMoData.setLoaiGiayToTen(optGiayTo.get().getTen());
			}
		}
		thongTinGietMoData.setSoGiayTo(thongTinGietMo.getSoGiayTo());
		thongTinGietMoData.setCapNgay(thongTinGietMo.getCapNgay());
		
		List<SoLuongGietMoData> listSoLuongGietMos = new ArrayList<SoLuongGietMoData>();
		List<SoLuongGietMo> listGietMos = serviceSoLuongGietMoService.findByThongTinGietMoIdAndDaXoa(thongTinGietMo.getId(), false);
		if(Objects.nonNull(listGietMos) && !listGietMos.isEmpty()) {
			for(SoLuongGietMo soLuong : listGietMos) {
				SoLuongGietMoData soLuongData = new SoLuongGietMoData();
				soLuongData.setId(soLuong.getId());
				soLuongData.setThongTinGietMoId(soLuong.getThongTinGietMoId());
				soLuongData.setNguonGoc(soLuong.getNguonGoc());
				soLuongData.setLoaiVatNuoiId(soLuong.getLoaiVatNuoiId());
				if(soLuong.getLoaiVatNuoiId() != null && soLuong.getLoaiVatNuoiId() > 0) {
					Optional<DmLoaiVatNuoi> optVatNuoi = serviceDmLoaiVatNuoiService.findById(soLuong.getLoaiVatNuoiId());
					if(optVatNuoi.isPresent()) {
						soLuongData.setLoaiVatNuoiTen(optVatNuoi.get().getTen());
					}
				}
				soLuongData.setSoLuongGietMo(soLuong.getSoLuongGietMo());
				soLuongData.setGhiChu(soLuong.getGhiChu());
				
				listSoLuongGietMos.add(soLuongData);
			}
		}
		thongTinGietMoData.setListSoLuongGietMo(listSoLuongGietMos);
		return thongTinGietMoData;
	}
	
	public ThongTinGietMoData findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinGietMo> optional = serviceThongTinGietMoService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinGietMoData.class, "id", String.valueOf(id));
		}
		ThongTinGietMo thongTinGietMo = optional.get();
		return this.convertToThongTinGietMoData(thongTinGietMo);
	}
	
	public ThongTinGietMo create(ThongTinGietMoData thongTinGietMoData) throws MethodArgumentNotValidException {
		ThongTinGietMo thongTinGietMo = new ThongTinGietMo();
		thongTinGietMo.setDaXoa(false);
		thongTinGietMo.setCoSoGietMoId(thongTinGietMoData.getCoSoGietMoId());
		thongTinGietMo.setNgayThang(thongTinGietMoData.getNgayThang());
		thongTinGietMo.setChuHang(thongTinGietMoData.getChuHang());
		thongTinGietMo.setLoaiGiayToId(thongTinGietMoData.getLoaiGiayToId());
		thongTinGietMo.setSoGiayTo(thongTinGietMoData.getSoGiayTo());
		thongTinGietMo.setCapNgay(thongTinGietMoData.getCapNgay());;
		thongTinGietMo = serviceThongTinGietMoService.save(thongTinGietMo);
				
		serviceSoLuongGietMoService.setFixedDaXoaAndThongTinGietMoId(false, thongTinGietMo.getId());
		List<SoLuongGietMoData> listSoLuongGietMos = thongTinGietMoData.getListSoLuongGietMo();
		if(Objects.nonNull(listSoLuongGietMos) && !listSoLuongGietMos.isEmpty()) {
			for(SoLuongGietMoData soLuongData : listSoLuongGietMos) {
				SoLuongGietMo soLuong = new SoLuongGietMo();
				soLuong.setId(soLuongData.getId());
				soLuong.setNguonGoc(soLuongData.getNguonGoc());
				soLuong.setLoaiVatNuoiId(soLuongData.getLoaiVatNuoiId());
				soLuong.setSoLuongGietMo(soLuongData.getSoLuongGietMo());
				soLuong.setGhiChu(soLuongData.getGhiChu());
				soLuong.setThongTinGietMoId(thongTinGietMo.getId());
				serviceSoLuongGietMoService.save(soLuong);
			}
		}
		return thongTinGietMo;
	}
	
	public ThongTinGietMo update(Long id, ThongTinGietMoData thongTinGietMoData) throws EntityNotFoundException {
		Optional<ThongTinGietMo> optional = serviceThongTinGietMoService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
			
		}
		ThongTinGietMo thongTinGietMo = optional.get();
		thongTinGietMo.setCoSoGietMoId(thongTinGietMoData.getCoSoGietMoId());
		thongTinGietMo.setNgayThang(thongTinGietMoData.getNgayThang());
		thongTinGietMo.setChuHang(thongTinGietMoData.getChuHang());
		thongTinGietMo.setLoaiGiayToId(thongTinGietMoData.getLoaiGiayToId());
		thongTinGietMo.setSoGiayTo(thongTinGietMoData.getSoGiayTo());
		thongTinGietMo.setCapNgay(thongTinGietMoData.getCapNgay());
		thongTinGietMo = serviceThongTinGietMoService.save(thongTinGietMo);
		System.out.println(optional + "+++++++++" + thongTinGietMo.getNgayThang());
		
		serviceSoLuongGietMoService.setFixedDaXoaAndThongTinGietMoId(true, thongTinGietMo.getId());
		List<SoLuongGietMoData> listSoLuongGietMos = thongTinGietMoData.getListSoLuongGietMo();
		if(Objects.nonNull(listSoLuongGietMos) && !listSoLuongGietMos.isEmpty()) {
			for(SoLuongGietMoData soLuongData : listSoLuongGietMos) {
				SoLuongGietMo soLuong = new SoLuongGietMo();
				soLuong.setId(soLuongData.getId());
				soLuong.setNguonGoc(soLuongData.getNguonGoc());
				soLuong.setLoaiVatNuoiId(soLuongData.getLoaiVatNuoiId());
				soLuong.setSoLuongGietMo(soLuongData.getSoLuongGietMo());
				soLuong.setGhiChu(soLuongData.getGhiChu());
				soLuong.setThongTinGietMoId(thongTinGietMo.getId());
				serviceSoLuongGietMoService.save(soLuong);
			}
		}
		return thongTinGietMo;
	}

	
	@DeleteMapping(value = { "/{id}" })
	public ThongTinGietMoData delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinGietMo> optional = serviceThongTinGietMoService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		ThongTinGietMo thongTinGietMo = optional.get();
		thongTinGietMo.setDaXoa(true);
		thongTinGietMo = serviceThongTinGietMoService.save(thongTinGietMo);
		return this.convertToThongTinGietMoData(thongTinGietMo);
	}

	//-----------------Tong Hop So Luong Ngay-----------------------------------------
	public Page<ThongKeSoLuongData> tongHopSoLuongNgay(int page, int size, String sortBy, String sortDir, List<String> tenCoSos,
			List<Long> loaiVatNuoiIds, LocalDate gietMoTuNgay, LocalDate gietMoDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThongTinGietMo> pageThongTin = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds, gietMoTuNgay,
				gietMoDenNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeSoLuongData> pageThongKeNgay = pageThongTin.map(this::convertToThongKeSoLuongNgayData);
		
		List<ThongKeSoLuongData> thongKeSoLuongDatass = new ArrayList<>(
				pageThongKeNgay.getContent());

		List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<>();

		for (ThongKeSoLuongData element : thongKeSoLuongDatass) {
           // Check if element not exist in list, perform add element to list
           if (!thongKeSoLuongDatas.contains(element)) {
           	thongKeSoLuongDatas.add(element);
           }
        }
		System.out.println("------->"+thongKeSoLuongDatas);
		Page<ThongKeSoLuongData> thongKeSoLuongDataImpl = new PageImpl<>(thongKeSoLuongDatas);
		return thongKeSoLuongDataImpl;
	}
	
	public ThongKeSoLuongData convertToThongKeSoLuongNgayData(ThongTinGietMo thongTinGietMo) {
		ThongKeSoLuongData thongKeNgayData = new ThongKeSoLuongData();
		thongKeNgayData.setNgayThang(thongTinGietMo.getNgayThang());
		List<ThongTinGietMoData> thongTinDatas = new ArrayList<ThongTinGietMoData>();
		List<ThongTinGietMo> listThongTins = serviceThongTinGietMoService
				.findByNgayThangAndDaXoa(thongTinGietMo.getNgayThang(), false);
		if(Objects.nonNull(listThongTins) && !listThongTins.isEmpty()) {		
			for(ThongTinGietMo listThongTin : listThongTins) {
				ThongTinGietMoData thongTinData = new ThongTinGietMoData();		
				thongTinData.setCoSoGietMoId(listThongTin.getCoSoGietMoId());
				if(listThongTin.getCoSoGietMoId() != null && listThongTin.getCoSoGietMoId() > 0) {
					Optional<CoSoGietMo> optCoSo = serviceCoSoGietMoService.findById(listThongTin.getCoSoGietMoId());
					if(optCoSo.isPresent()) {
						thongTinData.setCoSoTen(optCoSo.get().getTenCoSo());
					}
				}
				
				List<SoLuongGietMoData> soLuongGietMoDatas = new ArrayList<SoLuongGietMoData>();
				List<SoLuongGietMo> listSoLuongs = serviceSoLuongGietMoService
						.findByThongTinGietMoIdAndDaXoa(listThongTin.getId(), false);
				if(Objects.nonNull(listSoLuongs) && !listSoLuongs.isEmpty()) {
					for(SoLuongGietMo soLuongGietMo : listSoLuongs) {
						SoLuongGietMoData soLuongData = new SoLuongGietMoData();
						soLuongData.setId(soLuongGietMo.getId());
						soLuongData.setLoaiVatNuoiId(soLuongGietMo.getLoaiVatNuoiId());
						if(soLuongGietMo.getLoaiVatNuoiId() != null && soLuongGietMo.getLoaiVatNuoiId() > 0) {
							Optional<DmLoaiVatNuoi> optVatNuoi = serviceDmLoaiVatNuoiService
									.findById(soLuongGietMo.getLoaiVatNuoiId());
							if(optVatNuoi.isPresent()) {
								soLuongData.setLoaiVatNuoiTen(optVatNuoi.get().getTen());
							}
						}
						soLuongData.setSoLuongGietMo(soLuongGietMo.getSoLuongGietMo());
						soLuongGietMoDatas.add(soLuongData);
					}
				}
				thongTinData.setListSoLuongGietMo(soLuongGietMoDatas);
				thongTinDatas.add(thongTinData);
			}
			
		}

		List<ThongTinGietMoData> thongTinDatass = new ArrayList<>();
	
		for (ThongTinGietMoData element : thongTinDatas) {
           // Check if element not exist in list, perform add element to list
           if (!thongTinDatass.contains(element)) {
        	   thongTinDatass.add(element);
           }
        }
		thongKeNgayData.setThongTinDatas(thongTinDatass);
		return thongKeNgayData;
	}
	
	public ModelAndView exportExcelTongHopSoLuongNgay(HttpServletRequest request, HttpServletResponse response, int page, int size,
			String sortBy, String sortDir, List<String> tenCoSos, List<Long> loaiVatNuoiIds, LocalDate gietMoTuNgay,
			LocalDate gietMoDenNgay) {
		
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
		
		Page<ThongTinGietMo> pageThongTin = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds, gietMoTuNgay,
				gietMoDenNgay, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeSoLuongData> pageThongKeNgay = pageThongTin.map(this::convertToThongKeSoLuongNgayData);
		
		List<ThongKeSoLuongData> thongKeSoLuongDatass = new ArrayList<>(pageThongKeNgay.getContent());
		
		while(pageThongKeNgay.hasNext()) {
			Page<ThongTinGietMo> nextPageOfEmployees = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds,
					gietMoTuNgay, gietMoDenNgay, PageRequest.of(page, size, direction, sortBy));
			Page<ThongKeSoLuongData> nextPageOfThongKeSoLuongNgayData = nextPageOfEmployees
					.map(this::convertToThongKeSoLuongNgayData);
			if(Objects.nonNull(nextPageOfThongKeSoLuongNgayData.getContent())) {
				thongKeSoLuongDatass.addAll(nextPageOfThongKeSoLuongNgayData.getContent());
			}
			pageThongTin = nextPageOfEmployees;
		}
		List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<>();

		for (ThongKeSoLuongData element : thongKeSoLuongDatass) {
            // Check if element not exist in list, perform add element to list
            if (!thongKeSoLuongDatas.contains(element)) {
            	thongKeSoLuongDatas.add(element);
            }
        }
		
		model.put("thongKeSoLuongDatas", thongKeSoLuongDatas);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKeSoLuongGietMoNgay.xls");
		return new ModelAndView(new MyExcelViewTongHopSoLuongNgay(), model);
	}
	
	//-----------------Tong Hop So Luong Thang-----------------------------------------
		public Page<ThongKeSoLuongData> tongHopSoLuongThang(int page, int size, String sortBy, String sortDir,
				List<String> tenCoSos, List<Long> loaiVatNuoiIds, LocalDate gietMoTuThang, LocalDate gietMoDenThang) {
			Direction direction;
			if (sortDir.equals("ASC")) {
				direction = Direction.ASC;
			} else {
				direction = Direction.DESC;
			}
			Page<ThongTinGietMo> pageThongTin = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds,
					gietMoTuThang, gietMoDenThang, PageRequest.of(page, size, direction, sortBy));
			final Page<ThongKeSoLuongData> pageThongKeNgay = pageThongTin.map(this::convertToThongKeSoLuongNgayData);
			
			List<ThongKeSoLuongData> thongKeSoLuongDatass = new ArrayList<>(
					pageThongKeNgay.getContent());

			 List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<>();

			for (ThongKeSoLuongData element : thongKeSoLuongDatass) {
	           // Check if element not exist in list, perform add element to list
	           if (!thongKeSoLuongDatas.contains(element)) {
	           	thongKeSoLuongDatas.add(element);
	           }
	       }
			Page<ThongKeSoLuongData> thongKeSoLuongDataImpl = new PageImpl<>(thongKeSoLuongDatas);
			return thongKeSoLuongDataImpl;
		}
		
		public ThongKeSoLuongData convertToThongKeSoLuongThangData(ThongTinGietMo thongTinGietMo) {
			ThongKeSoLuongData thongKeThangData = new ThongKeSoLuongData();
//			thongKeThangData.setId(thongTinGietMo.getId());
			thongKeThangData.setNgayThang(thongTinGietMo.getNgayThang());
			
			List<ThongTinGietMoData> thongTinDatas = new ArrayList<ThongTinGietMoData>();
			List<ThongTinGietMo> listThongTins = serviceThongTinGietMoService.findByCoSoGietMoIdAndDaXoa(thongTinGietMo.getCoSoGietMoId(), false);
			if(Objects.nonNull(listThongTins) && !listThongTins.isEmpty()) {
				for(ThongTinGietMo listThongTin : listThongTins) {
					ThongTinGietMoData thongTinData = new ThongTinGietMoData();
					thongTinData.setId(listThongTin.getId());
					thongTinData.setCoSoGietMoId(listThongTin.getCoSoGietMoId());
					if(listThongTin.getCoSoGietMoId() != null && listThongTin.getCoSoGietMoId() > 0) {
						Optional<CoSoGietMo> optCoSo = serviceCoSoGietMoService.findById(listThongTin.getCoSoGietMoId());
						if(optCoSo.isPresent()) {
							thongTinData.setCoSoTen(optCoSo.get().getTenCoSo());
						}
					}
					
					List<SoLuongGietMoData> soLuongGietMoDatas = new ArrayList<SoLuongGietMoData>();
					List<SoLuongGietMo> listSoLuongs = serviceSoLuongGietMoService.findByThongTinGietMoIdAndDaXoa(thongTinGietMo.getId(), false);
					if(Objects.nonNull(listSoLuongs) && !listSoLuongs.isEmpty()) {
						for(SoLuongGietMo soLuongGietMo : listSoLuongs) {
							SoLuongGietMoData soLuongData = new SoLuongGietMoData();
							soLuongData.setId(soLuongGietMo.getId());
							soLuongData.setLoaiVatNuoiId(soLuongGietMo.getLoaiVatNuoiId());
							if(soLuongGietMo.getLoaiVatNuoiId() != null && soLuongGietMo.getLoaiVatNuoiId() > 0) {
								Optional<DmLoaiVatNuoi> optVatNuoi = serviceDmLoaiVatNuoiService.findById(soLuongGietMo.getLoaiVatNuoiId());
								if(optVatNuoi.isPresent()) {
									soLuongData.setLoaiVatNuoiTen(optVatNuoi.get().getTen());
								}
							}
							soLuongData.setSoLuongGietMo(soLuongGietMo.getSoLuongGietMo());
							soLuongGietMoDatas.add(soLuongData);
						}
					}
					thongTinData.setListSoLuongGietMo(soLuongGietMoDatas);
					thongTinDatas.add(thongTinData);
				}
			}		

			thongKeThangData.setThongTinDatas(thongTinDatas);
			return thongKeThangData;
		}
		
		public ModelAndView exportExcelTongHopSoLuongThang(HttpServletRequest request, HttpServletResponse response, int page, int size,
				String sortBy, String sortDir, List<String> tenCoSos, List<Long> loaiVatNuoiIds, LocalDate gietMoTuNgay,
				LocalDate gietMoDenNgay) {
			
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
			
			Page<ThongTinGietMo> pageThongTin = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds, gietMoTuNgay,
					gietMoDenNgay, PageRequest.of(page, size, direction, sortBy));
			Page<ThongKeSoLuongData> pageThongKeNgay = pageThongTin.map(this::convertToThongKeSoLuongNgayData);
			
			List<ThongKeSoLuongData> thongKeSoLuongDatass = new ArrayList<>(pageThongKeNgay.getContent());
			
			while(pageThongKeNgay.hasNext()) {
				Page<ThongTinGietMo> nextPageOfEmployees = serviceThongTinGietMoService.tongHopSoLuongNgay(tenCoSos, loaiVatNuoiIds,
						gietMoTuNgay, gietMoDenNgay, PageRequest.of(page, size, direction, sortBy));
				Page<ThongKeSoLuongData> nextPageOfThongKeSoLuongNgayData = nextPageOfEmployees.map(this::convertToThongKeSoLuongNgayData);
				if(Objects.nonNull(nextPageOfThongKeSoLuongNgayData.getContent())) {
					thongKeSoLuongDatass.addAll(nextPageOfThongKeSoLuongNgayData.getContent());
				}
				pageThongTin = nextPageOfEmployees;
			}
			List<ThongKeSoLuongData> thongKeSoLuongDatas = new ArrayList<>();

			for (ThongKeSoLuongData element : thongKeSoLuongDatass) {
	            // Check if element not exist in list, perform add element to list
	            if (!thongKeSoLuongDatas.contains(element)) {
	            	thongKeSoLuongDatas.add(element);
	            }
	        }
			
			model.put("thongKeSoLuongDatas", thongKeSoLuongDatas);
			response.setContentType("application/ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=ThongKeSoLuongGietMoNgay.xls");
			return new ModelAndView(new MyExcelViewTongHopSoLuongNgay(), model);
		}
	
	
}
