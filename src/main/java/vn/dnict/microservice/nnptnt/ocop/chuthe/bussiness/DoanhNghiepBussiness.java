package vn.dnict.microservice.nnptnt.ocop.chuthe.bussiness;

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
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.danhmuc.dao.service.DmTinhThanhService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.model.DmLoaiDoanhNghiepOCOP;
import vn.dnict.microservice.nnptnt.dm.loaidoanhnghiep.dao.service.DmLoaiDoanhNghiepOCOPService;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.model.DmLoaiHinh;
import vn.dnict.microservice.nnptnt.dm.loaihinh.dao.service.DmLoaiHinhService;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.DmNganhNgheOCOPService;
import vn.dnict.microservice.nnptnt.ocop.chuthe.bussiness.view.MyExcelViewChuThe;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;
import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service.DoanhNghiepService;
import vn.dnict.microservice.nnptnt.ocop.data.DoanhNghiepData;
@Service
public class DoanhNghiepBussiness {
	
	@Autowired
	DoanhNghiepService serviceDoanhNghiepService;
	
	@Autowired
	DmLoaiHinhService serviceDmLoaiHinhService;
	
	@Autowired
	DmLoaiDoanhNghiepOCOPService serviceDmLoaiDoanhNghiepService;
	
	@Autowired
	DmNganhNgheOCOPService serviceDmNganhNgheOCOPService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmTinhThanhService serviceDmTinhThanhService;
	
	
	public Page<DoanhNghiepData> findAll(int page, int size, String sortBy, String sortDir, String ten, String chuSoHuu, Long loaiDoanhNghiepId,
			Long loaiHinhId, Long nganhNgheId , Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DoanhNghiep> pageDoanhNghiep= serviceDoanhNghiepService.findAll( ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<DoanhNghiepData> pageDoanhNghiepData = pageDoanhNghiep.map(this::convertToDoanhNghiepData);
		return pageDoanhNghiepData;
	}
	
	
	public DoanhNghiepData convertToDoanhNghiepData(DoanhNghiep doanhNghiep) {
	DoanhNghiepData doanhNghiepData = new DoanhNghiepData();
	doanhNghiepData.setId(doanhNghiep.getId());
	doanhNghiepData.setTen(doanhNghiep.getTen());
	doanhNghiepData.setDiaChi(doanhNghiep.getDiaChi());
	doanhNghiepData.setDienThoai(doanhNghiep.getDienThoai());
	doanhNghiepData.setEmail(doanhNghiep.getEmail());
	doanhNghiepData.setWebsite(doanhNghiep.getWebsite());
	doanhNghiepData.setQuanHuyenId(doanhNghiep.getQuanHuyenId());
	doanhNghiepData.setPhuongXaId(doanhNghiep.getPhuongXaId());
	doanhNghiepData.setTinhThanhId(doanhNghiep.getTinhThanhId());
	doanhNghiepData.setGiayPhepKinhDoanh(doanhNghiep.getGiayPhepKinhDoanh());
	doanhNghiepData.setTrangThai(doanhNghiep.getTrangThai());
	doanhNghiepData.setNgayCap(doanhNghiep.getNgayCap());
	doanhNghiepData.setChuSoHuu(doanhNghiep.getChuSoHuu());
	doanhNghiepData.setChuSoHuuDienThoai(doanhNghiep.getChuSoHuuDienThoai());
	doanhNghiepData.setChuSoHuuEmail(doanhNghiep.getChuSoHuuEmail());
	doanhNghiepData.setNguoiDaiDien(doanhNghiep.getNguoiDaiDien());
	doanhNghiepData.setNguoiDaiDienDienThoai(doanhNghiep.getNguoiDaiDienDienThoai());
	doanhNghiepData.setNguoiDaiDienEmail(doanhNghiep.getNguoiDaiDienEmail());
	if (Objects.nonNull(doanhNghiep.getLoaiHinhId())) {
		Optional<DmLoaiHinh> optLoaiHinh = serviceDmLoaiHinhService.findById(doanhNghiep.getLoaiHinhId());
		if (optLoaiHinh.isPresent()) {
		doanhNghiepData.setLoaiHinhId(optLoaiHinh.get().getId());
		doanhNghiepData.setLoaiHinhTen(optLoaiHinh.get().getTen());
		}
	}
	if (Objects.nonNull(doanhNghiep.getLoaiDoanhNghiepId())) {
		Optional<DmLoaiDoanhNghiepOCOP> optLoaiDoanhNghiep = serviceDmLoaiDoanhNghiepService.findById(doanhNghiep.getLoaiDoanhNghiepId());
		if (optLoaiDoanhNghiep.isPresent()) {
		doanhNghiepData.setLoaiDoanhNghiepId(optLoaiDoanhNghiep.get().getId());
		doanhNghiepData.setLoaiDoanhNghiepTen(optLoaiDoanhNghiep.get().getTen());
		}
	}
	if (Objects.nonNull(doanhNghiep.getNganhNgheId())) {
		Optional<DmNganhNgheOCOP> optNganhNghe = serviceDmNganhNgheOCOPService.findById(doanhNghiep.getNganhNgheId());
		if (optNganhNghe.isPresent()) {
		doanhNghiepData.setNganhNgheId(optNganhNghe.get().getId());
		doanhNghiepData.setNganhNgheTen(optNganhNghe.get().getTen());
		}
	}
	if(Objects.nonNull(doanhNghiepData.getQuanHuyenId())) {
		Optional<DmQuanHuyen> optQuanHuyen = serviceDmQuanHuyenService.findById(doanhNghiepData.getQuanHuyenId());
		if(optQuanHuyen.isPresent()) {
			doanhNghiepData.setQuanHuyenTen(optQuanHuyen.get().getTen());
		}
	}
	if(Objects.nonNull(doanhNghiepData.getPhuongXaId())) {
		Optional<DmPhuongXa> optPhuongXa = serviceDmPhuongXaService.findById(doanhNghiepData.getPhuongXaId());
		if(optPhuongXa.isPresent()) {
			doanhNghiepData.setPhuongXaTen(optPhuongXa.get().getTen());
		}
	}
	if(Objects.nonNull(doanhNghiepData.getTinhThanhId())) {
		Optional<DmTinhThanh> optTinhThanh = serviceDmTinhThanhService.findById(doanhNghiepData.getTinhThanhId());
		if(optTinhThanh.isPresent()) {
			doanhNghiepData.setTinhThanhTen(optTinhThanh.get().getTen());
		}
	}
	
	return doanhNghiepData;
	}
	
	
	public DoanhNghiepData findById(Long id) throws EntityNotFoundException {
		Optional<DoanhNghiep> optional = serviceDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DoanhNghiep.class, "id", String.valueOf(id));
		}
		return this.convertToDoanhNghiepData(optional.get());
	}
	
	public DoanhNghiep create(DoanhNghiepData doanhNghiepData) {
		DoanhNghiep doanhNghiep = new DoanhNghiep();
		doanhNghiep.setDaXoa(false);
		doanhNghiep.setTen(doanhNghiepData.getTen());
		doanhNghiep.setDiaChi(doanhNghiepData.getDiaChi());
		doanhNghiep.setDienThoai(doanhNghiepData.getDienThoai());
		doanhNghiep.setEmail(doanhNghiepData.getEmail());
		doanhNghiep.setWebsite(doanhNghiepData.getWebsite());
		doanhNghiep.setGiayPhepKinhDoanh(doanhNghiepData.getGiayPhepKinhDoanh());
		doanhNghiep.setNgayCap(doanhNghiepData.getNgayCap());
		doanhNghiep.setPhuongXaId(doanhNghiepData.getPhuongXaId());
		doanhNghiep.setQuanHuyenId(doanhNghiepData.getQuanHuyenId());
		doanhNghiep.setTinhThanhId(doanhNghiepData.getTinhThanhId());
		doanhNghiep.setTrangThai(doanhNghiepData.getTrangThai());
		doanhNghiep.setLoaiDoanhNghiepId(doanhNghiepData.getLoaiDoanhNghiepId());
		doanhNghiep.setLoaiHinhId(doanhNghiepData.getLoaiHinhId());
		doanhNghiep.setNganhNgheId(doanhNghiepData.getNganhNgheId());
		doanhNghiep.setChuSoHuu(doanhNghiepData.getChuSoHuu());
		doanhNghiep.setChuSoHuuDienThoai(doanhNghiepData.getChuSoHuuDienThoai());
		doanhNghiep.setChuSoHuuEmail(doanhNghiepData.getChuSoHuuEmail());
		doanhNghiep.setNguoiDaiDien(doanhNghiepData.getNguoiDaiDien());
		doanhNghiep.setNguoiDaiDienDienThoai(doanhNghiepData.getNguoiDaiDienDienThoai());
		doanhNghiep.setNguoiDaiDienEmail(doanhNghiepData.getNguoiDaiDienEmail());
		
		doanhNghiep = serviceDoanhNghiepService.save(doanhNghiep);
		
		
		return doanhNghiep;
		}
	
	public DoanhNghiep update(Long id, DoanhNghiepData doanhNghiepData) throws EntityNotFoundException {
		Optional<DoanhNghiep> optional = serviceDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DoanhNghiep.class, "id", String.valueOf(id));
		}
		DoanhNghiep doanhNghiep = optional.get();
		doanhNghiep.setDaXoa(false);
		doanhNghiep.setTen(doanhNghiepData.getTen());
		doanhNghiep.setDiaChi(doanhNghiepData.getDiaChi());
		doanhNghiep.setDienThoai(doanhNghiepData.getDienThoai());
		doanhNghiep.setEmail(doanhNghiepData.getEmail());
		doanhNghiep.setWebsite(doanhNghiepData.getWebsite());
		doanhNghiep.setGiayPhepKinhDoanh(doanhNghiepData.getGiayPhepKinhDoanh());
		doanhNghiep.setNgayCap(doanhNghiepData.getNgayCap());
		doanhNghiep.setPhuongXaId(doanhNghiepData.getPhuongXaId());
		doanhNghiep.setQuanHuyenId(doanhNghiepData.getQuanHuyenId());
		doanhNghiep.setTinhThanhId(doanhNghiepData.getTinhThanhId());
		doanhNghiep.setTrangThai(doanhNghiepData.getTrangThai());
		doanhNghiep.setLoaiDoanhNghiepId(doanhNghiepData.getLoaiDoanhNghiepId());
		doanhNghiep.setLoaiHinhId(doanhNghiepData.getLoaiHinhId());
		doanhNghiep.setNganhNgheId(doanhNghiepData.getNganhNgheId());
		doanhNghiep.setChuSoHuu(doanhNghiepData.getChuSoHuu());
		doanhNghiep.setChuSoHuuDienThoai(doanhNghiepData.getChuSoHuuDienThoai());
		doanhNghiep.setChuSoHuuEmail(doanhNghiepData.getChuSoHuuEmail());
		doanhNghiep.setNguoiDaiDien(doanhNghiepData.getNguoiDaiDien());
		doanhNghiep.setNguoiDaiDienDienThoai(doanhNghiepData.getNguoiDaiDienDienThoai());
		doanhNghiep.setNguoiDaiDienEmail(doanhNghiepData.getNguoiDaiDienEmail());
		
		doanhNghiep = serviceDoanhNghiepService.save(doanhNghiep);
		
		
		return doanhNghiep;
	}
	
	public DoanhNghiep delete(Long id) throws EntityNotFoundException {
		Optional<DoanhNghiep> optional = serviceDoanhNghiepService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DoanhNghiep.class, "id", String.valueOf(id));
		}
		DoanhNghiep doanhNghiep = optional.get();
		doanhNghiep.setDaXoa(true);
		doanhNghiep = serviceDoanhNghiepService.save(doanhNghiep);
		return doanhNghiep;
	}
	
	public ModelAndView exportExcelChuThe(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,String ten, String chuSoHuu, Long loaiDoanhNghiepId,
			Long loaiHinhId, Long nganhNgheId , Integer trangThai) {
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

		Page<DoanhNghiep> pageDoanhNghiep = serviceDoanhNghiepService.findAll(ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai, PageRequest.of(page, size, direction, sortBy));
		Page<DoanhNghiepData> pageDoanhNghiepData = pageDoanhNghiep
				.map(this::convertToDoanhNghiepData);

		List<DoanhNghiepData> DoanhNghiepDatas = new ArrayList<>(
				pageDoanhNghiepData.getContent());

	System.out.println(DoanhNghiepDatas+"----------//"+ response);

		// All the remaining employees
		while (pageDoanhNghiep.hasNext()) {
			Page<DoanhNghiep> nextPageOfEmployees = serviceDoanhNghiepService.findAll(ten, chuSoHuu, loaiDoanhNghiepId, loaiHinhId, nganhNgheId, trangThai,
					pageDoanhNghiep.nextPageable());
			Page<DoanhNghiepData> nextPageOfDoanhNghiepData = nextPageOfEmployees
					.map(this::convertToDoanhNghiepData);
			if (Objects.nonNull(nextPageOfDoanhNghiepData.getContent())) {
				DoanhNghiepDatas.addAll(nextPageOfDoanhNghiepData.getContent());
			}
			// update the page reference to the current page
			pageDoanhNghiep = nextPageOfEmployees;
			System.out.println(pageDoanhNghiep+"++++++++++++");
		}

		model.put("DoanhNghiepDatas", DoanhNghiepDatas);
		
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKe "+formattedString+".xls");
		return new ModelAndView(new MyExcelViewChuThe(), model);
		
	}
}
