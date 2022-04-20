
package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.core.dao.model.CoreChucNang;
import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;
import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoach2ChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoOutput;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.view.MyExcelViewThongKeChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoService;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;
import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongTinHoatDongChanNuoiOutput;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public class ThongTinChoMeoBusiness {
	@Autowired
	ThongTinChoMeoService serviceThongTinChoMeoService;

	@Autowired
	DmGiongService serviceDmGiongService;

	@Autowired
	ChuQuanLyService serviceChuQuanLyService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;

	@Autowired
	DmLoaiDongVatService serviceDmLoaiDongVatService;
	
	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;

	public Page<ThongTinChoMeoData> findAll(int page, int size, String sortBy, String sortDir, Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,LocalDate tuNgayTiemPhong,LocalDate denNgayTiemPhong,
			Long quanHuyenId, Long phuongXaId, Long keHoachTiemPhongId, Integer trangThai, Boolean trangThaiTiem) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<ThongTinChoMeo> pageThongTinChoMeo = serviceThongTinChoMeoService.findAll(loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai,trangThaiTiem,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ThongTinChoMeoData> pageThongTinChoMeoData = pageThongTinChoMeo
				.map(this::convertToThongTinChoMeoData);
		return pageThongTinChoMeoData;
	}

	public ThongTinChoMeoData findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeoData.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo thongTinChoMeo = optional.get();
		return this.convertToThongTinChoMeoData(thongTinChoMeo);
	}
	
	
//	public ThongTinChoMeoData findByChuQuanLyId(Long ChuQuanLyId)throws EntityNotFoundException  {
//		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findByChuQuanLyId(ChuQuanLyId);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(ChuQuanLyId));
//		}
//		Optional<ChuQuanLy> optChuQuanLy = serviceChuQuanLyService.findById(ChuQuanLyId);
//		if(optChuQuanLy.isPresent()) {
//			
//		}
//		
//		return null;
//		
//	}
	public ChuQuanLyData findByChuQuanLyId(Long id) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLyData.class, "id", String.valueOf(id));
		}
		ChuQuanLy chuQuanLy = optional.get();
		ChuQuanLyData chuQuanLyData = new ChuQuanLyData();
		chuQuanLyData.setId(chuQuanLy.getId());
		chuQuanLyData.setChuHo(chuQuanLy.getChuHo());
		chuQuanLyData.setDiaChi(chuQuanLy.getDiaChi());
		chuQuanLyData.setDienThoai(chuQuanLy.getDienThoai());
		chuQuanLyData.setQuanHuyen_Id(chuQuanLy.getQuanHuyen_Id());
		chuQuanLyData.setPhuongXa_Id(chuQuanLy.getPhuongXa_Id());
		if (chuQuanLy.getQuanHuyen_Id() != null && chuQuanLy.getQuanHuyen_Id() > 0) {
			Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(chuQuanLy.getQuanHuyen_Id());
			if (optionalQuanHuyen.isPresent()) {
				chuQuanLyData.setQuanHuyen_Ten(optionalQuanHuyen.get().getTen());
			}
		}
		if (chuQuanLy.getPhuongXa_Id() != null && chuQuanLy.getPhuongXa_Id() > 0) {
			Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(chuQuanLy.getPhuongXa_Id());
			if (optionalPhuongXa.isPresent()) {
				chuQuanLyData.setPhuongXa_Ten(optionalPhuongXa.get().getTen());
			}
		}
		
		List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();
		List<ThongTinChoMeo> thongTinChoMeos = serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(),
				false);
		if (Objects.nonNull(thongTinChoMeos) && !thongTinChoMeos.isEmpty()) {
			for (ThongTinChoMeo thongTinChoMeo : thongTinChoMeos) {
				ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
				thongTinChoMeoData.setId(thongTinChoMeo.getId());
				thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
				thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
				thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
				thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
				thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
				thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
				thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
				
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}

//				Optional<KeHoach2ChoMeo> optionalKeHoach2 = serviceKeHoach2ChoMeoService.findById(thongTinChoMeoData.getId());
//				if(optionalKeHoach2.isPresent()) {
//					ThongTinChoMeoData.setNgayTiemPhong(optionalKeHoach2.get().getNgayTiemPhong());
//				}
			
				thongTinChoMeoDatas.add(thongTinChoMeoData);
			}
		} else {
			System.out
					.println("lol" + serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(), false));
		}
		chuQuanLyData.setThongTinChoMeoDatas(thongTinChoMeoDatas);
		return chuQuanLyData;
	}
	private ThongTinChoMeoData convertToThongTinChoMeoData(ThongTinChoMeo thongTinChoMeo) {
		ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
		thongTinChoMeoData.setId(thongTinChoMeo.getId());
		thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
		thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
		thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
		thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
		thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
		thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
		thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
		thongTinChoMeoData.setChuQuanLyId(thongTinChoMeo.getChuQuanLyId());
		if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
			Optional<DmGiong> optional = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
			if (optional.isPresent()) {
				
				thongTinChoMeoData.setGiong(optional.get().getTen());
			}
		}

		if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
			Optional<DmLoaiDongVat> optional = serviceDmLoaiDongVatService
					.findById(thongTinChoMeoData.getLoaiDongVatId());
			if (optional.isPresent()) {
				;
				thongTinChoMeoData.setLoaiDongVat(optional.get().getTen());
				
			}
		}
		if (thongTinChoMeoData.getChuQuanLyId() != null && thongTinChoMeoData.getChuQuanLyId() > 0) {
			Optional<ChuQuanLy> optional = serviceChuQuanLyService
					.findById(thongTinChoMeoData.getChuQuanLyId());
			if (optional.isPresent()) {	
				thongTinChoMeoData.setChuQuanLyTen(optional.get().getChuHo());
				thongTinChoMeoData.setChuQuanLyDiaChi(optional.get().getDiaChi());	
				thongTinChoMeoData.setDienthoai(optional.get().getDienThoai());	
				thongTinChoMeoData.setQuanHuyenId(optional.get().getQuanHuyen_Id());
				thongTinChoMeoData.setPhuongXaId(optional.get().getPhuongXa_Id());
				if(optional.get().getQuanHuyen_Id() != null && optional.get().getQuanHuyen_Id() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(optional.get().getQuanHuyen_Id());
					if (optionalQuanHuyen.isPresent()) {
						thongTinChoMeoData.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}	
				}
				if(optional.get().getPhuongXa_Id() != null && optional.get().getPhuongXa_Id() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(optional.get().getPhuongXa_Id());
					if (optionalPhuongXa.isPresent()) {
						thongTinChoMeoData.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}	
				}
				
				
			}
		}
		if (thongTinChoMeoData.getId() != null && thongTinChoMeoData.getId() > 0) {
		
			List<KeHoach2ChoMeo> listKeHoach2ChoMeos = serviceKeHoach2ChoMeoService
					.findByThongTinChoMeoIdAndDaXoa(thongTinChoMeo.getId(), false);
			for (KeHoach2ChoMeo KeHoach2ChoMeo : listKeHoach2ChoMeos) {
				thongTinChoMeoData.setNgayTiemPhong(KeHoach2ChoMeo.getNgayTiemPhong());
				ArrayList<LocalDate> list = new ArrayList<>();
				list.add(thongTinChoMeoData.getNgayTiemPhong());				thongTinChoMeoData.setNgayTiemPhongList(list);	}
				thongTinChoMeoData.setListKeHoach2ChoMeo(listKeHoach2ChoMeos);
				
			
		}
	
		
		

		return thongTinChoMeoData;

	}
	
	
	
	public ChuQuanLyData findChuQuanLyByThongTinChoMeoId(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "chuQuanLyId", String.valueOf(id));
		}
		ThongTinChoMeo ThongTinChoMeo = optional.get();
		Optional<ChuQuanLy> optionalChuQuanLy = serviceChuQuanLyService.findById(ThongTinChoMeo.getChuQuanLyId());
		if (!optionalChuQuanLy.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLy.class, "chuQuanLyId", String.valueOf(id));
		}
		ChuQuanLy chuQuanLy= optionalChuQuanLy.get();
		
		ChuQuanLyData chuQuanLyData = new ChuQuanLyData();
		chuQuanLyData.setId(chuQuanLy.getId());
		chuQuanLyData.setChuHo(chuQuanLy.getChuHo());
		chuQuanLyData.setDiaChi(chuQuanLy.getDiaChi());
		chuQuanLyData.setDienThoai(chuQuanLy.getDienThoai()	);
		chuQuanLyData.setQuanHuyen_Id(chuQuanLy.getQuanHuyen_Id());
		chuQuanLyData.setPhuongXa_Id(chuQuanLy.getPhuongXa_Id());
		if(chuQuanLy.getQuanHuyen_Id() != null && chuQuanLy.getQuanHuyen_Id() > 0) {
			Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(chuQuanLy.getQuanHuyen_Id());
			if (optionalQuanHuyen.isPresent()) {
				chuQuanLyData.setQuanHuyen_Ten(optionalQuanHuyen.get().getTen());
			}	
		}
		if(chuQuanLy.getPhuongXa_Id() != null && chuQuanLy.getPhuongXa_Id() > 0) {
			Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(chuQuanLy.getPhuongXa_Id());
			if (optionalPhuongXa.isPresent()) {
				chuQuanLyData.setPhuongXa_Ten(optionalPhuongXa.get().getTen());
			}	
		}
		List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();
		List<ThongTinChoMeo> thongTinChoMeos = serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(ThongTinChoMeo.getChuQuanLyId(), false);
		if (Objects.nonNull(thongTinChoMeos) && !thongTinChoMeos.isEmpty()) {
			for (ThongTinChoMeo thongTinChoMeo : thongTinChoMeos) {
				ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
				thongTinChoMeoData.setId(thongTinChoMeo.getId());
				thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
				thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
				thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
				thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
				thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
				thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
				thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						;
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}
				if (Objects.nonNull(thongTinChoMeoData.getId())) {
					
				}
				thongTinChoMeoDatas.add(thongTinChoMeoData);
				}
		}else {
			System.out.println("lol" + serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(), false));
			}
		chuQuanLyData.setThongTinChoMeoDatas(thongTinChoMeoDatas);
		System.out.println("lol"+ chuQuanLyData.getId());
		return chuQuanLyData;
	}
//
//	public ThongTinChoMeoData findByChuQuanLyId(Long id) throws EntityNotFoundException {
//		
//
//	
//		
//		
//		List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();
//		List<ThongTinChoMeo> thongTinChoMeos = serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(id, false);
//		
//		if (Objects.nonNull(thongTinChoMeos) && !thongTinChoMeos.isEmpty()) {
//			for (ThongTinChoMeo thongTinChoMeo : thongTinChoMeos) {
//				ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
//				thongTinChoMeoData.setId(thongTinChoMeo.getId());
//				thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
//				thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
//				thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
//				thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
//				thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
//				thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
//				thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
//				thongTinChoMeoDatas.add(thongTinChoMeoData);
//				}
//		}
//		
//		
//		return null;
//		
//	}
	


	public ThongTinChoMeoData create(ThongTinChoMeoData thongTinChoMeoData) {
		ThongTinChoMeo thongTinChoMeo = new ThongTinChoMeo();
		thongTinChoMeo.setDaXoa(false);
		thongTinChoMeo.setLoaiDongVatId(thongTinChoMeoData.getLoaiDongVatId());
		thongTinChoMeo.setNamSinh(thongTinChoMeoData.getNamSinh());
		thongTinChoMeo.setTenConVat(thongTinChoMeoData.getTenConVat());
		thongTinChoMeo.setGiongId(thongTinChoMeoData.getGiongId());
		thongTinChoMeo.setMauLong(thongTinChoMeoData.getMauLong());
		thongTinChoMeo.setTinhBiet(thongTinChoMeoData.getTinhBiet());
		thongTinChoMeo.setTrangThai(thongTinChoMeoData.getTrangThai());
		thongTinChoMeo.setChuQuanLyId(thongTinChoMeoData.getChuQuanLyId());
		thongTinChoMeo = serviceThongTinChoMeoService.save(thongTinChoMeo);
		return this.convertToThongTinChoMeoData(thongTinChoMeo);
	}

	public ThongTinChoMeoData update(Long id, ThongTinChoMeoData thongTinChoMeoData) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo thongTinChoMeo = optional.get();
		thongTinChoMeo.setLoaiDongVatId(thongTinChoMeoData.getLoaiDongVatId());
		thongTinChoMeo.setNamSinh(thongTinChoMeoData.getNamSinh());
		thongTinChoMeo.setTenConVat(thongTinChoMeoData.getTenConVat());
		thongTinChoMeo.setGiongId(thongTinChoMeoData.getGiongId());
		thongTinChoMeo.setMauLong(thongTinChoMeoData.getMauLong());
		thongTinChoMeo.setTinhBiet(thongTinChoMeoData.getTinhBiet());
		thongTinChoMeo.setTrangThai(thongTinChoMeoData.getTrangThai());
		thongTinChoMeo.setChuQuanLyId(thongTinChoMeoData.getChuQuanLyId());
		thongTinChoMeo = serviceThongTinChoMeoService.save(thongTinChoMeo);
		return this.convertToThongTinChoMeoData(thongTinChoMeo);
	}

	public ThongTinChoMeoData delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinChoMeo> optional = serviceThongTinChoMeoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeo.class, "id", String.valueOf(id));
		}
		ThongTinChoMeo thongTinChoMeo = optional.get();
		thongTinChoMeo.setDaXoa(true);
		thongTinChoMeo = serviceThongTinChoMeoService.save(thongTinChoMeo);
		return this.convertToThongTinChoMeoData(thongTinChoMeo);
	}
	
	public Page<ThongTinChoMeoData> thongKe(int page, int size, String sortBy, String sortDir, Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,LocalDate tuNgayTiemPhong,LocalDate denNgayTiemPhong,
			Long quanHuyenId, Long phuongXaId, Long keHoachTiemPhongId, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<ThongTinChoMeo> pageThongTinChoMeo = serviceThongTinChoMeoService.thongKe(loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ThongTinChoMeoData> pageThongTinChoMeoData = pageThongTinChoMeo
				.map(this::convertToThongTinChoMeoDataThongKe);
		return pageThongTinChoMeoData;
	}
	
	private ThongTinChoMeoData convertToThongTinChoMeoDataThongKe(ThongTinChoMeo thongTinChoMeo) {
		ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
		thongTinChoMeoData.setId(thongTinChoMeo.getId());
		thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
		thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
		thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
		thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
		thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
		thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
		thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
		thongTinChoMeoData.setChuQuanLyId(thongTinChoMeo.getChuQuanLyId());
		if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
			Optional<DmGiong> optional = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
			if (optional.isPresent()) {
				
				thongTinChoMeoData.setGiong(optional.get().getTen());
			}
		}

		if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
			Optional<DmLoaiDongVat> optional = serviceDmLoaiDongVatService
					.findById(thongTinChoMeoData.getLoaiDongVatId());
			if (optional.isPresent()) {
				;
				thongTinChoMeoData.setLoaiDongVat(optional.get().getTen());
				
			}
		}
		if (thongTinChoMeoData.getChuQuanLyId() != null && thongTinChoMeoData.getChuQuanLyId() > 0) {
			Optional<ChuQuanLy> optional = serviceChuQuanLyService
					.findById(thongTinChoMeoData.getChuQuanLyId());
			if (optional.isPresent()) {	
				thongTinChoMeoData.setChuQuanLyTen(optional.get().getChuHo());
				thongTinChoMeoData.setChuQuanLyDiaChi(optional.get().getDiaChi());	
				thongTinChoMeoData.setDienthoai(optional.get().getDienThoai());	
				thongTinChoMeoData.setQuanHuyenId(optional.get().getQuanHuyen_Id());
				thongTinChoMeoData.setPhuongXaId(optional.get().getPhuongXa_Id());
				if(optional.get().getQuanHuyen_Id() != null && optional.get().getQuanHuyen_Id() > 0) {
					Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(optional.get().getQuanHuyen_Id());
					if (optionalQuanHuyen.isPresent()) {
						thongTinChoMeoData.setQuanHuyenTen(optionalQuanHuyen.get().getTen());
					}	
				}
				if(optional.get().getPhuongXa_Id() != null && optional.get().getPhuongXa_Id() > 0) {
					Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(optional.get().getPhuongXa_Id());
					if (optionalPhuongXa.isPresent()) {
						thongTinChoMeoData.setPhuongXaTen(optionalPhuongXa.get().getTen());
					}	
				}
				
				
			}
		}
		if (thongTinChoMeoData.getId() != null && thongTinChoMeoData.getId() > 0) {
		
			List<KeHoach2ChoMeo> listKeHoach2ChoMeos = serviceKeHoach2ChoMeoService
					.findByThongTinChoMeoIdAndDaXoa(thongTinChoMeo.getId(), false);
			for (KeHoach2ChoMeo KeHoach2ChoMeo : listKeHoach2ChoMeos) {
				thongTinChoMeoData.setNgayTiemPhong(KeHoach2ChoMeo.getNgayTiemPhong());
				ArrayList<LocalDate> list = new ArrayList<>();
				list.add(thongTinChoMeoData.getNgayTiemPhong());
				thongTinChoMeoData.setNgayTiemPhongList(list);	}
				thongTinChoMeoData.setListKeHoach2ChoMeo(listKeHoach2ChoMeos);
				
			
		}
		
		return thongTinChoMeoData;
		}
	
	public ModelAndView exportExcelThongKeChoMeo(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,Long loaiDongVatId, Long giongId, String tenChuHo, String dienThoai,LocalDate tuNgayTiemPhong,LocalDate denNgayTiemPhong,
			Long quanHuyenId, Long phuongXaId, Long keHoachTiemPhongId, Integer trangThai) {
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

		Page<ThongTinChoMeo> pageThongTinChoMeo = serviceThongTinChoMeoService.thongKe(loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai, PageRequest.of(page, size, direction, sortBy));
		Page<ThongTinChoMeoData> pageThongTinChoMeoData = pageThongTinChoMeo
				.map(this::convertToThongTinChoMeoDataThongKe);

		List<ThongTinChoMeoData> thongKeThongTinChoMeoDatas = new ArrayList<>(
				pageThongTinChoMeoData.getContent());

	System.out.println(thongKeThongTinChoMeoDatas+"----------//"+ response);

		// All the remaining employees
		while (pageThongTinChoMeo.hasNext()) {
			Page<ThongTinChoMeo> nextPageOfEmployees = serviceThongTinChoMeoService.thongKe(loaiDongVatId,giongId, tenChuHo, dienThoai, tuNgayTiemPhong,denNgayTiemPhong, quanHuyenId, phuongXaId, keHoachTiemPhongId, trangThai,
					pageThongTinChoMeo.nextPageable());
			Page<ThongTinChoMeoData> nextPageOfThongTinChoMeoData = nextPageOfEmployees
					.map(this::convertToThongTinChoMeoDataThongKe);
			if (Objects.nonNull(nextPageOfThongTinChoMeoData.getContent())) {
				thongKeThongTinChoMeoDatas.addAll(nextPageOfThongTinChoMeoData.getContent());
			}
			// update the page reference to the current page
			pageThongTinChoMeo = nextPageOfEmployees;
			System.out.println(pageThongTinChoMeo+"++++++++++++");
		}

		model.put("thongKeThongTinChoMeoDatas", thongKeThongTinChoMeoDatas);
		
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKe.xls");
		return new ModelAndView(new MyExcelViewThongKeChoMeo(), model);
		
	}
	
}
