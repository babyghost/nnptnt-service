package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangLogData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
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

	public Page<NhiemVuThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			List<LocalDate> thangs, Long canBoThucHienId, List<Integer> tinhTrangs, String tenNhiemVu, LocalDate tuNgay, LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuThang> pageNhiemVuThang = serviceNhiemVuThangService.findAll(donViChuTriId, thangs, canBoThucHienId,
				tinhTrangs, tenNhiemVu, tuNgay, denNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuThangData> pageNhiemVuThangData = pageNhiemVuThang.map(this::convertToNhiemVuThangData);
		return pageNhiemVuThangData;
	}

	public String getThongKeSoLuong(Long donViChuTriId, List<LocalDate> thangs, Long keHoachThangId, String tenNhiemVu,
			List<Integer> tinhTrangs, Long canBoThucHienId, LocalDate tuNgay, LocalDate denNgay) {

		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService.getThongKeSoLuong(donViChuTriId, thangs,
				keHoachThangId, tenNhiemVu, tinhTrangs, canBoThucHienId, tuNgay, denNgay);

		String thongKe = "Tổng số: " + nhiemVuThangs.size();

		if (Objects.nonNull(tinhTrangs) && !tinhTrangs.isEmpty()) {
			for (Integer tinhTrang : tinhTrangs) {
				if (Objects.nonNull(tinhTrang)) {
					if (tinhTrang.equals(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
						thongKe += "; " + mapTrangThai.get(tinhTrang) + ": "
								+ (nhiemVuThangs.stream().filter(t -> Objects.isNull(t.getTinhTrang())).count()
										+ nhiemVuThangs.stream().filter(t -> Objects.nonNull(t.getTinhTrang()))
												.filter(t -> t.getTinhTrang().equals(tinhTrang)).count());
					} else {
						thongKe += "; " + mapTrangThai.get(tinhTrang) + ": "
								+ nhiemVuThangs.stream().filter(t -> Objects.nonNull(t.getTinhTrang()))
										.filter(t -> t.getTinhTrang().equals(tinhTrang)).count();
					}
				}
			}
		} else {
			for (Map.Entry<Integer, String> entry : mapTrangThai.entrySet()) {
				if (entry.getKey().equals(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
					thongKe += "; " + entry.getValue() + ": "
							+ (nhiemVuThangs.stream().filter(t -> Objects.isNull(t.getTinhTrang())).count()
									+ nhiemVuThangs.stream().filter(t -> Objects.nonNull(t.getTinhTrang()))
											.filter(t -> t.getTinhTrang().equals(entry.getKey())).count());
				} else {
					thongKe += "; " + entry.getValue() + ": "
							+ nhiemVuThangs.stream().filter(t -> Objects.nonNull(t.getTinhTrang()))
									.filter(t -> t.getTinhTrang().equals(entry.getKey())).count();
				}
			}
		}

		return thongKe;
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
		if(Objects.nonNull(tienDoNhiemVuThang)) {
			int type = Constants.DINH_KEM_1_FILE;
			Optional<FileDinhKemNhiemVuThang> fileDinhKemThang = serviceFileDinhKemNhiemVuThangService
					.findByTienDoNhiemVuThangIdAndDaXoa(tienDoNhiemVuThang.getId(), false);
			if(fileDinhKemThang.isPresent()) {
				Long fileDinhKemId = null;
				Long objectId = tienDoNhiemVuThang.getId();
				String appCode = TienDoNhiemVuThang.class.getSimpleName();
				FileDinhKem fileDinhKem = coreAttachmentBusiness
						.getAttachments(fileDinhKemThang.get().getFileDinhKemId(), appCode, objectId, type);
				tienDoNhiemVuThangData.setFileDinhKem(fileDinhKem);
				tienDoNhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
			}
		}
		nhiemVuThangData.setTienDoNhiemVuThangData(tienDoNhiemVuThangData);
		// log
		List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService
				.findByNhiemVuThangId(nhiemVuThang.getId());
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

	public NhiemVuThangData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuThang> optional = serviceNhiemVuThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuThang.class, "id", String.valueOf(id));
		}
		NhiemVuThang nhiemVuThang = optional.get();
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
		
		if(Objects.nonNull(tienDoNhiemVuThang)) {
			int type = Constants.DINH_KEM_1_FILE;
			Optional<FileDinhKemNhiemVuThang> fileDinhKemThang = serviceFileDinhKemNhiemVuThangService
					.findByTienDoNhiemVuThangIdAndDaXoa(tienDoNhiemVuThang.getId(), false);
			if(fileDinhKemThang.isPresent()) {
				Long fileDinhKemId = null;
				Long objectId = tienDoNhiemVuThang.getId();
				String appCode = TienDoNhiemVuThang.class.getSimpleName();
				FileDinhKem fileDinhKem = coreAttachmentBusiness
						.getAttachments(fileDinhKemThang.get().getFileDinhKemId(), appCode, objectId, type);
				tienDoNhiemVuThangData.setFileDinhKem(fileDinhKem);
				tienDoNhiemVuThangData.setFileDinhKemIds(fileDinhKem.getIds());
			}
		}
		nhiemVuThangData.setTienDoNhiemVuThangData(tienDoNhiemVuThangData);
		// log
		List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService
				.findByNhiemVuThangId(nhiemVuThang.getId());
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
		serviceFileDinhKemNhiemVuThangService.setFixedDaXoaForTienDoNhiemVuThangId(true, tienDoNhiemVuThang.getId());
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
							.findByTienDoNhiemVuThangIdAndFileDinhKemId(tienDoNhiemVuThang.getId(), fileDinhKemId);
					if (Objects.nonNull(fileDinhKemNhiemVuThangs) && !fileDinhKemNhiemVuThangs.isEmpty()) {
						fileDinhKemNhiemVuThang = fileDinhKemNhiemVuThangs.get(0);
					}
					fileDinhKemNhiemVuThang.setDaXoa(false);
					fileDinhKemNhiemVuThang.setTienDoNhiemVuThangId(tienDoNhiemVuThang.getId());
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
