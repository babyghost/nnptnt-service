package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business.view.MyExcelViewThongKeThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.NhiemVuThangLogService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.utils.Constants;

@Service
public class NhiemVuThangBusiness {
	@Autowired
	NhiemVuThangService serviceNhiemVuThangService;
	
	@Autowired
	KeHoachThangService serviceKeHoachThangService;
	
	@Autowired
	DmCanBoService serviceDmCanBoService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	TienDoNhiemVuThangService serviceTienDoNhiemVuThangService;
	
	@Autowired
	FileDinhKemNhiemVuThangService serviceFileDinhKemNhiemVuThangService;
	
	@Autowired
	CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	NhiemVuThangLogService serviceNhiemVuThangLogService;
	
	public Page<NhiemVuThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, List<LocalDate> thangs,
			 String tenNhiemVu, Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay, Integer tinhTrang) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, tenNhiemVu,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuThangData> pageNhiemVuThangData = pageNhiemVuThang.map(this::convertToNhiemVuThangData);
		return pageNhiemVuThangData;
	}
	
	private NhiemVuThangData convertToNhiemVuThangData(NhiemVuThang nhiemVuThang) {
		NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
		nhiemVuThangData.setId(nhiemVuThang.getId());
		nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
		if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
			Optional<KeHoachThang> optKeHoach = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
			if(optKeHoach.isPresent()) {
				nhiemVuThangData.setKeHoachDonViChuTriId(optKeHoach.get().getDonViChuTriId());
				if(optKeHoach.get().getDonViChuTriId() != null && optKeHoach.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoach.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuThangData.setKeHoachDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuThangData.setKeHoachthang(optKeHoach.get().getThang());
			}
		}
		nhiemVuThangData.setCanBoThucHienId(nhiemVuThang.getCanBoThucHienId());
		if(nhiemVuThang.getCanBoThucHienId() != null && nhiemVuThang.getCanBoThucHienId() > 0) {
			Optional<DmCanBo> optCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
			if(optCanBo.isPresent()) {
				nhiemVuThangData.setCanBoThucHienTen(optCanBo.get().getHoTen());
			}
		}
		nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
		nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
		nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
		nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
		nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
		nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
		nhiemVuThangData.setTienDoNhiemVuId(nhiemVuThang.getTienDoNhiemVuId());
		if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0 ) {
			Optional<TienDoNhiemVuThang> optTienDo = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
			if(optTienDo.isPresent()) {
				nhiemVuThangData.setTienDoNhiemVuId(optTienDo.get().getId());
				nhiemVuThangData.setTienDoMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
				nhiemVuThangData.setTienDoKetQua(optTienDo.get().getKetQua());
				nhiemVuThangData.setTienDoTenNguoiCapNhat(optTienDo.get().getTenNguoiCapNhat());
				
				if(Objects.nonNull(optTienDo)) {
					int type = Constants.DINH_KEM_1_FILE;
					Optional<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService
							.findByTienDoNhiemVuThangId(optTienDo.get().getId());
					Long fileDinhKemId = null;
					Long objectId = optTienDo.get().getId();
					String appCode = TienDoNhiemVuNam.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( fileDinhKemNhiemVuThang.get().getFileDinhKemId(), appCode, objectId, type);
					nhiemVuThangData.setFileDinhKem(fileDinhKem);
					nhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
				}
			}
		}
		
		List<Long> canBoThucHienIds = new ArrayList<Long>();
		List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService
				.findByNhiemVuThangIdAndDaXoa(nhiemVuThang.getId(), false);
		if(Objects.nonNull(nhiemVuThangLogs) && !nhiemVuThangLogs.isEmpty()) {
			canBoThucHienIds = nhiemVuThangLogs.stream().filter(e -> e.getCanBoThucHienId() != null)
					.map(NhiemVuThangLog::getCanBoThucHienId).collect(Collectors.toList());
		}
		nhiemVuThangData.setCanBoThucHienIds(canBoThucHienIds);;
		return nhiemVuThangData;
	}
	
	public Page<ThongKeKeHoachThangData> thongKeKeHoachThang(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			List<LocalDate> thangs, String tenNhiemVu, Integer tinhTrang, Long canBoThucHienId, LocalDate thoiHanTuNgay,
			LocalDate thoiHanDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, tenNhiemVu,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeKeHoachThangData> pageThongKe = pageNhiemVuThang.map(this::convertToThongKeKeHoachThangDaTa);
		return pageThongKe;
	}
	
	private ThongKeKeHoachThangData convertToThongKeKeHoachThangDaTa(NhiemVuThang nhiemVuThang) {
		ThongKeKeHoachThangData thongKeKeHoachThangData = new ThongKeKeHoachThangData();
		thongKeKeHoachThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
		if(nhiemVuThang.getKeHoachThangId() != null && nhiemVuThang.getKeHoachThangId() > 0) {
			Optional<KeHoachThang> optKeHoach = serviceKeHoachThangService.findById(nhiemVuThang.getKeHoachThangId());
			if(optKeHoach.isPresent()) {
				thongKeKeHoachThangData.setDonViChuTriId(optKeHoach.get().getDonViChuTriId());
				if(optKeHoach.get().getDonViChuTriId() != null && optKeHoach.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoach.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						thongKeKeHoachThangData.setDoViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				thongKeKeHoachThangData.setThang(optKeHoach.get().getThang());
			}
		}
		thongKeKeHoachThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		thongKeKeHoachThangData.setCanBoThucHienId(nhiemVuThang.getCanBoThucHienId());
		if(nhiemVuThang.getCanBoThucHienId() != null && nhiemVuThang.getCanBoThucHienId() > 0) {
			Optional<DmCanBo> optCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
			if(optCanBo.isPresent()) {
				thongKeKeHoachThangData.setCanBoThucHienTen(optCanBo.get().getHoTen());
			}
		}
		thongKeKeHoachThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
		thongKeKeHoachThangData.setThoiGian(nhiemVuThang.getThoiGian());
		thongKeKeHoachThangData.setGhiChu(nhiemVuThang.getGhiChu());
		thongKeKeHoachThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
		thongKeKeHoachThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
		thongKeKeHoachThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
		thongKeKeHoachThangData.setTienDoNhiemVuId(nhiemVuThang.getTienDoNhiemVuId());
		if(nhiemVuThang.getTienDoNhiemVuId() != null && nhiemVuThang.getTienDoNhiemVuId() > 0) {
			Optional<TienDoNhiemVuThang> optTienDo = serviceTienDoNhiemVuThangService.findById(nhiemVuThang.getTienDoNhiemVuId());
			if(optTienDo.isPresent()) {
				thongKeKeHoachThangData.setTienDoMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
				thongKeKeHoachThangData.setTienDoKetQua(optTienDo.get().getKetQua());
			}
		}
		return thongKeKeHoachThangData;
	}
	
	public ModelAndView exportExcelThongKeKeHoachThang(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir, Long donViChuTriId, List<LocalDate> thangs, String tenNhiemVu, Integer tinhTrang,
			Long canBoThucHienId, LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay) {
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
		
		Page<NhiemVuThang> pageNhiemVu = serviceNhiemVuThangService.tongHopKeHoachThang(donViChuTriId, thangs, tenNhiemVu, tinhTrang,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeKeHoachThangData> pageThongKe = pageNhiemVu.map(this::convertToThongKeKeHoachThangDaTa);
		
		List<ThongKeKeHoachThangData> thongKeThangDatas = new ArrayList<>(pageThongKe.getContent());
		
		while(pageNhiemVu.hasNext()) {
			Page<NhiemVuThang> nextPageOfEmployees = serviceNhiemVuThangService.tongHopKeHoachThang(donViChuTriId, thangs,
					tenNhiemVu, tinhTrang, canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay,
					PageRequest.of(page, size, direction, sortBy));
			Page<ThongKeKeHoachThangData> nextPageOfThongKeThangData = nextPageOfEmployees.map(this::convertToThongKeKeHoachThangDaTa);
			if(Objects.nonNull(nextPageOfThongKeThangData) && !nextPageOfThongKeThangData.isEmpty()) {
				thongKeThangDatas.addAll(nextPageOfThongKeThangData.getContent());
			}
			pageNhiemVu = nextPageOfEmployees;
		}
		model.put("thongKeThangDatas", thongKeThangDatas);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKeKeHoachRiengCuaPhong.xls");
		return new ModelAndView(new MyExcelViewThongKeThang(), model);
	}
	
	public NhiemVuThangData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
	
	public NhiemVuThangData create(NhiemVuThangData nhiemVuThangData) throws MethodArgumentNotValidException {
		NhiemVuThang nhiemVuThang = new NhiemVuThang();
		nhiemVuThang.setDaXoa(false);
		nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
		nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getKeHoachThangId());
		nhiemVuThang.setCanBoThucHienId(nhiemVuThangData.getCanBoThucHienId());
		nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
		nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
		nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
		nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
		nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
		nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
		nhiemVuThang.setTienDoNhiemVuId(nhiemVuThangData.getTienDoNhiemVuId());
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		
		serviceNhiemVuThangLogService.setFixedDaXoaForNhiemVuThangId(false, nhiemVuThang.getId());
		List<Long> canBoThucHienIds = nhiemVuThangData.getCanBoThucHienIds();
		for(Long canBoThucHienId : canBoThucHienIds) {
			NhiemVuThangLog nhiemVuThangLog = new NhiemVuThangLog();
			List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService
					.findByNhiemVuThangIdAndCanBoThucHienIdAndDaXoa(nhiemVuThangData.getId(), canBoThucHienId, false);
			if(Objects.nonNull(nhiemVuThangLogs) && !nhiemVuThangLogs.isEmpty()) {
				nhiemVuThangLog = nhiemVuThangLogs.get(0);
			}
			nhiemVuThangLog.setDaXoa(false);
			nhiemVuThangLog.setNhiemVuThangId(nhiemVuThang.getId());
			nhiemVuThangLog.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
			nhiemVuThangLog.setCanBoThucHienId(canBoThucHienId);
			nhiemVuThangLog.setTenNguoiCapNhat(nhiemVuThangData.getTienDoTenNguoiCapNhat());
			nhiemVuThangLog.setTinhTrang(nhiemVuThangData.getTinhTrang());
			nhiemVuThangLog.setMucDoHoanThanh(nhiemVuThangData.getTienDoMucDoHoanThanh());
			nhiemVuThangLog.setKetQua(nhiemVuThangData.getTienDoKetQua());
			serviceNhiemVuThangLogService.save(nhiemVuThangLog);
		}
		return nhiemVuThangData;
	}
	
	public NhiemVuThangData update(Long id, NhiemVuThangData nhiemVuThangData) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
		nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getKeHoachThangId());
		nhiemVuThang.setCanBoThucHienId(nhiemVuThangData.getCanBoThucHienId());
		nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
		nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
		nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
		nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
		nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
		nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
		nhiemVuThang.setTienDoNhiemVuId(nhiemVuThangData.getTienDoNhiemVuId());
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		
		serviceNhiemVuThangLogService.setFixedDaXoaForNhiemVuThangId(true, nhiemVuThang.getId());
		List<Long> canBoThucHienIds = nhiemVuThangData.getCanBoThucHienIds();
		for(Long canBoThucHienId : canBoThucHienIds) {
			NhiemVuThangLog nhiemVuThangLog = new NhiemVuThangLog();
			List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService
					.findByNhiemVuThangIdAndCanBoThucHienIdAndDaXoa(nhiemVuThangData.getId(), canBoThucHienId, false);
			if(Objects.nonNull(nhiemVuThangLogs) && !nhiemVuThangLogs.isEmpty()) {
				nhiemVuThangLog = nhiemVuThangLogs.get(0);
			}
			nhiemVuThangLog.setDaXoa(false);
			nhiemVuThangLog.setNhiemVuThangId(nhiemVuThang.getId());
			nhiemVuThangLog.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
			nhiemVuThangLog.setCanBoThucHienId(canBoThucHienId);
			nhiemVuThangLog.setTenNguoiCapNhat(nhiemVuThangData.getTienDoTenNguoiCapNhat());
			nhiemVuThangLog.setTinhTrang(nhiemVuThangData.getTinhTrang());
			nhiemVuThangLog.setMucDoHoanThanh(nhiemVuThangData.getTienDoMucDoHoanThanh());
			nhiemVuThangLog.setKetQua(nhiemVuThangData.getTienDoKetQua());
			serviceNhiemVuThangLogService.save(nhiemVuThangLog);
		}
		return nhiemVuThangData;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public NhiemVuThangData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		nhiemVuThang.setDaXoa(true);
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
}
