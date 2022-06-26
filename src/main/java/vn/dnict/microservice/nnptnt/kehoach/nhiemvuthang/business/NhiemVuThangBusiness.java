package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business;

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

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangLogData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business.view.MyExcelViewThongKeThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model.FileDinhKemNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.NhiemVuThangLogService;
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
	
	private Map<Integer, String> mapTrangThai = new HashMap<Integer, String>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7353045349633081487L;

		{
			put(Constants.QLKH_TINHTRANG_CHUATHUCHIEN, "Chưa thực hiện");
			put(Constants.QLKH_TINHTRANG_DANGTHUCHIEN, "Đang thực hiện");
			put(Constants.QLKH_TINHTRANG_DAHOANTHANH, "Đã hoàn thành");
			put(Constants.QLKH_TINHTRANG_NGUNGTHUCHIEN, "Ngừng thực hiện");
		}
	};

	public Page<NhiemVuThangData> findAll(int page, int size, String sortBy, String sortDir,Long donViChuTriId, List<LocalDate> thangs,
			Integer tinhTrang, String tenNhiemVu, LocalDate tuNgay, LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, tinhTrang, tenNhiemVu, tuNgay,
				denNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuThangData> pageNhiemVuThangData = pageNhiemVuThang.map(this::convertToNhiemVuThangData);
		return pageNhiemVuThangData;
	}

	private NhiemVuThangData convertToNhiemVuThangData(NhiemVuThang nhiemVuThang) {
		NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
		nhiemVuThangData.setId(nhiemVuThang.getId());
		nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
		nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
		nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
		nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
		nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
		nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
		nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
		if (Objects.nonNull(nhiemVuThang.getCanBoThucHienId())) {
			Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
			if (optionalDmCanBo.isPresent()) {
				nhiemVuThangData.setCanBoThucHienId(optionalDmCanBo.get().getId());
				nhiemVuThangData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
			}
		}

		TienDoNhiemVuThang tienDoNhiemVuThang = new TienDoNhiemVuThang();
		if (Objects.nonNull(nhiemVuThang.getTienDoNhiemVuId())) {
			Optional<TienDoNhiemVuThang> optionalTienDoNhiemVuThang = serviceTienDoNhiemVuThangService
					.findById(nhiemVuThang.getTienDoNhiemVuId());
			if (optionalTienDoNhiemVuThang.isPresent()) {
				tienDoNhiemVuThang = optionalTienDoNhiemVuThang.get();
			}
		}
		TienDoNhiemVuThangData tienDoNhiemVuThangData = new TienDoNhiemVuThangData();
		tienDoNhiemVuThangData.setId(tienDoNhiemVuThang.getId());
		tienDoNhiemVuThangData.setKetQua(tienDoNhiemVuThang.getKetQua());
		tienDoNhiemVuThangData.setMucDoHoanThanh(tienDoNhiemVuThang.getMucDoHoanThanh());
		tienDoNhiemVuThangData.setTenNguoiCapNhat(tienDoNhiemVuThang.getTenNguoiCapNhat());
		tienDoNhiemVuThangData.setNgayCapNhat(LocalDate.now());
		nhiemVuThangData.setTienDoNhiemVuThangData(tienDoNhiemVuThangData);
		// log
		List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService.findByNhiemVuThangId(nhiemVuThang.getId());
		List<NhiemVuThangLogData> nhiemVuThangLogDatas = new ArrayList<NhiemVuThangLogData>();
		if (Objects.nonNull(nhiemVuThangLogs) && !nhiemVuThangLogs.isEmpty()) {
			for (NhiemVuThangLog nhiemVuThangLog : nhiemVuThangLogs) {
				NhiemVuThangLogData nhiemVuThangLogData = new NhiemVuThangLogData();
				nhiemVuThangLogData.setId(nhiemVuThangLog.getId());
				nhiemVuThangLogData.setKetQua(nhiemVuThangLog.getKetQua());
				nhiemVuThangLogData.setMucDoHoanThanh(nhiemVuThangLog.getMucDoHoanThanh());
				nhiemVuThangLogData.setNhiemVuThangId(nhiemVuThangLog.getNhiemVuThangId());
				nhiemVuThangLogData.setTenNguoiCapNhat(nhiemVuThangLog.getTenNguoiCapNhat());
				nhiemVuThangLogData.setTenNhiemVu(nhiemVuThangLog.getTenNhiemVu());
				nhiemVuThangLogData.setTinhTrang(nhiemVuThangLog.getTinhTrang());
				nhiemVuThangLogData.setNgayCapNhat(nhiemVuThangLog.getNgayCapNhat().toLocalDate());
				if (Objects.nonNull(nhiemVuThangLog.getCanBoThucHienId())) {
					Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThangLog.getCanBoThucHienId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVuThangLogData.setCanBoThucHienId(optionalDmCanBo.get().getId());
						nhiemVuThangLogData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
					}
				}
				nhiemVuThangLogDatas.add(nhiemVuThangLogData);
			}
		}
		nhiemVuThangData.setNhiemVuThangLogDatas(nhiemVuThangLogDatas);
		return nhiemVuThangData;
	}
	
	public Page<ThongKeKeHoachThangData> thongKeKeHoachThang(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			List<LocalDate> thangs, String tenNhiemVu, Integer tinhTrang, Long canBoThucHienId, LocalDate tuNgay,
			LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, tinhTrang, tenNhiemVu,
				tuNgay, denNgay, PageRequest.of(page, size, direction, sortBy));
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
		
		Page<NhiemVuThang> pageNhiemVu = serviceNhiemVuThangService.getThongKeSoLuong(donViChuTriId, thangs, tenNhiemVu, tinhTrang,
				canBoThucHienId, thoiHanTuNgay, thoiHanDenNgay, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeKeHoachThangData> pageThongKe = pageNhiemVu.map(this::convertToThongKeKeHoachThangDaTa);
		
		List<ThongKeKeHoachThangData> thongKeThangDatas = new ArrayList<>(pageThongKe.getContent());
		
		while(pageNhiemVu.hasNext()) {
			Page<NhiemVuThang> nextPageOfEmployees = serviceNhiemVuThangService.getThongKeSoLuong(donViChuTriId, thangs,
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
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}

	public NhiemVuThangData saveTienDo(Long nhiemVuId, NhiemVuThangData nhiemVuThangData)
			throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(nhiemVuId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(nhiemVuId));
		}
		NhiemVuThang nhiemVuThang = optional.get();

		TienDoNhiemVuThangData tienDoNhiemVuThangData = nhiemVuThangData.getTienDoNhiemVuThangData();

		TienDoNhiemVuThang tienDoNhiemVuThang = new TienDoNhiemVuThang();
		if (Objects.nonNull(tienDoNhiemVuThangData.getId())) {
			Optional<TienDoNhiemVuThang> optionalTienDoNhiemVuThang = serviceTienDoNhiemVuThangService
					.findById(tienDoNhiemVuThangData.getId());
			if (optionalTienDoNhiemVuThang.isPresent()) {
				tienDoNhiemVuThang = optionalTienDoNhiemVuThang.get();
			}
		}
		tienDoNhiemVuThang.setDaXoa(false);
		tienDoNhiemVuThang.setKetQua(tienDoNhiemVuThangData.getKetQua());
		tienDoNhiemVuThang.setMucDoHoanThanh(tienDoNhiemVuThangData.getMucDoHoanThanh());
		tienDoNhiemVuThang = serviceTienDoNhiemVuThangService.save(tienDoNhiemVuThang);

		// save tiến độ
		nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
		nhiemVuThang.setTienDoNhiemVuId(tienDoNhiemVuThang.getId());
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);

		// log
		NhiemVuThangLog nhiemVuThangLog = new NhiemVuThangLog();
		nhiemVuThangLog.setNhiemVuThangId(nhiemVuThang.getId());
		nhiemVuThangLog.setCanBoThucHienId(nhiemVuThang.getCanBoThucHienId());
		nhiemVuThangLog.setKetQua(tienDoNhiemVuThang.getKetQua());
		nhiemVuThangLog.setMucDoHoanThanh(tienDoNhiemVuThang.getMucDoHoanThanh());
		nhiemVuThangLog.setTenNguoiCapNhat(nhiemVuThang.getNguoiCapNhat());
		nhiemVuThangLog.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
		nhiemVuThangLog.setTinhTrang(nhiemVuThang.getTinhTrang());
		serviceNhiemVuThangLogService.save(nhiemVuThangLog);

		// đính kèm
		serviceFileDinhKemNhiemVuThangService.setFixedDaXoaForTienDoNvThangId(true, tienDoNhiemVuThang.getId());
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		List<Long> fileDinhKemIds = tienDoNhiemVuThangData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_NHIEU_FILE;
		long objectId = tienDoNhiemVuThang.getId();
		String appCode = TienDoNhiemVuThang.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					FileDinhKemNhiemVuThang fileDinhKemNhiemVuThang = new FileDinhKemNhiemVuThang();
					List<FileDinhKemNhiemVuThang> fileDinhKemNhiemVuThangs = serviceFileDinhKemNhiemVuThangService
							.findByTienDoNvThangIdAndFileDinhKemId(tienDoNhiemVuThang.getId(), fileDinhKemId);
					if (Objects.nonNull(fileDinhKemNhiemVuThangs) && !fileDinhKemNhiemVuThangs.isEmpty()) {
						fileDinhKemNhiemVuThang = fileDinhKemNhiemVuThangs.get(0);
					}
					fileDinhKemNhiemVuThang.setDaXoa(false);
					fileDinhKemNhiemVuThang.setTienDoNvThangId(tienDoNhiemVuThang.getId());
					fileDinhKemNhiemVuThang.setFileDinhKemId(coreAttachment.getId());
					fileDinhKemNhiemVuThang = serviceFileDinhKemNhiemVuThangService.save(fileDinhKemNhiemVuThang);

					coreAttachmentBusiness.saveAndMove(coreAttachment);
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End đính kèm file **********************************************************/

		return this.convertToNhiemVuThangData(nhiemVuThang);
	}

	public NhiemVuThangData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
		nhiemVuThang.setDaXoa(true);
		nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
		return this.convertToNhiemVuThangData(nhiemVuThang);
	}
}