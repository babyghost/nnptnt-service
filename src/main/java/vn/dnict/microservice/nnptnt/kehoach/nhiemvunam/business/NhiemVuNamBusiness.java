package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.model.FileDinhKemNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.FileDinhKemNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.TienDoNhiemVuNamService;
import vn.dnict.microservice.utils.Constants;

@Service
public class NhiemVuNamBusiness {
	@Autowired
	NhiemVuNamService serviceNhiemVuNamService;
	
	@Autowired
	KeHoachNamService serviceKeHoachNamService;
	
	@Autowired
	DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	TienDoNhiemVuNamService serviceTienDoNhiemVuNamService;
	
	@Autowired
	FileDinhKemNhiemVuNamService serviceFileDinhKemNhiemVuNamService;
	
	@Autowired
	CoreAttachmentBusiness coreAttachmentBusiness;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Map<Integer, String> mapTrangThai = new HashMap<Integer, String>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3483264819808030205L;

		{
			put(Constants.QLKH_TINHTRANG_CHUATHUCHIEN, "Chưa thực hiện");
			put(Constants.QLKH_TINHTRANG_DANGTHUCHIEN, "Đang thực hiện");
			put(Constants.QLKH_TINHTRANG_DAHOANTHANH, "Đã hoàn thành");
			put(Constants.QLKH_TINHTRANG_NGUNGTHUCHIEN, "Ngừng thực hiện");
		}
	};

	public Page<NhiemVuNamData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, List<Integer> tinhTrangs,
			Integer nam, Long keHoachId, LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.findAll(donViChuTriId, tinhTrangs, nam, keHoachId,
				tuNgay, denNgay, tenNhiemVu, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuNamData> pageNhiemVuNamData = pageNhiemVuNam
				.map(this::convertToNhiemVuNamData);
		return pageNhiemVuNamData;
	}

	public String getThongKeSoLuong(Long donViChuTriId, Integer nam, Long keHoachId, List<Integer> tinhTrangs, LocalDate tuNgay,
			LocalDate denNgay, String tenNhiemVu) {

		List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService.getThongKeSoLuong(donViChuTriId, nam, keHoachId, tinhTrangs,
				tuNgay, denNgay, tenNhiemVu);

		String thongKe = "Tổng số: " + nhiemVuNams.size();

		if (Objects.nonNull(tinhTrangs) && !tinhTrangs.isEmpty()) {
			for (Integer tinhTrang : tinhTrangs) {
				if (Objects.nonNull(tinhTrang)) {
					if (tinhTrang.equals(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
						thongKe += "; " + mapTrangThai.get(tinhTrang) + ": " + (nhiemVuNams.stream()
								.filter(t -> Objects.isNull(t.getTienDoNhiemVuNams())).count()
								+ nhiemVuNams.stream().filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
										.filter(t -> t.getTienDoNhiemVuNams().size() == 0).count()
								+ nhiemVuNams.stream().filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
										.filter(t -> t.getTienDoNhiemVuNams().size() > 0)
										.filter(t -> Objects
												.nonNull(t.getTienDoNhiemVuNams().get(0).getTinhTrang()))
										.filter(t -> t.getTienDoNhiemVuNams().get(0).getTinhTrang()
												.equals(tinhTrang))
										.count());
					} else {
						thongKe += "; " + mapTrangThai.get(tinhTrang) + ": " + nhiemVuNams.stream()
								.filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
								.filter(t -> t.getTienDoNhiemVuNams().size() > 0)
								.filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams().get(0).getTinhTrang()))
								.filter(t -> t.getTienDoNhiemVuNams().get(0).getTinhTrang().equals(tinhTrang))
								.count();
					}
				}
			}
		} else {
			for (Map.Entry<Integer, String> entry : mapTrangThai.entrySet()) {
				if (entry.getKey().equals(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
					thongKe += "; " + entry.getValue() + ": " + (nhiemVuNams.stream()
							.filter(t -> Objects.isNull(t.getTienDoNhiemVuNams())).count()
							+ nhiemVuNams.stream().filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
									.filter(t -> t.getTienDoNhiemVuNams().size() == 0).count()
							+ nhiemVuNams.stream().filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
									.filter(t -> t.getTienDoNhiemVuNams().size() > 0)
									.filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams().get(0).getTinhTrang()))
									.filter(t -> t.getTienDoNhiemVuNams().get(0).getTinhTrang()
											.equals(entry.getKey()))
									.count());
				} else {
					thongKe += "; " + entry.getValue() + ": " + nhiemVuNams.stream()
							.filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams()))
							.filter(t -> t.getTienDoNhiemVuNams().size() > 0)
							.filter(t -> Objects.nonNull(t.getTienDoNhiemVuNams().get(0).getTinhTrang()))
							.filter(t -> t.getTienDoNhiemVuNams().get(0).getTinhTrang().equals(entry.getKey()))
							.count();
				}
			}
		}

		return thongKe;
	}

	private NhiemVuNamData convertToNhiemVuNamData(NhiemVuNam nhiemVuNam) {
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachId(nhiemVuNam.getKeHoachId());
		if (Objects.nonNull(nhiemVuNam.getLoaiNhiemVuId())) {
			Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService
					.findById(nhiemVuNam.getLoaiNhiemVuId());
			if (optionalDmLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
				nhiemVuNamData.setLoaiNhiemVuTen(optionalDmLoaiNhiemVu.get().getTen());
			}
		}
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setNhiemVuChaId(null);
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		String thoiGianThucHien = "";
		if (Objects.nonNull(nhiemVuNam.getTuNgay())) {
			thoiGianThucHien = formatter.format(nhiemVuNam.getTuNgay());
		}
		if (Objects.nonNull(nhiemVuNam.getDenNgay())) {
			if (Objects.nonNull(thoiGianThucHien) && !thoiGianThucHien.isEmpty()) {
				thoiGianThucHien += " - ";
			}
			thoiGianThucHien += formatter.format(nhiemVuNam.getDenNgay());
		}
		nhiemVuNamData.setThoiGianThucHien(thoiGianThucHien);

		// tiến độ
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService
				.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<TienDoNhiemVuNamData>();
		if (Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for (TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());

				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);

		return nhiemVuNamData;
	}

	public NhiemVuNamData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachId(nhiemVuNam.getKeHoachId());
		if (Objects.nonNull(nhiemVuNam.getLoaiNhiemVuId())) {
			Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService
					.findById(nhiemVuNam.getLoaiNhiemVuId());
			if (optionalDmLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
				nhiemVuNamData.setLoaiNhiemVuTen(optionalDmLoaiNhiemVu.get().getTen());
			}
		}
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setNhiemVuChaId(null);
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		String thoiGianThucHien = "";
		if (Objects.nonNull(nhiemVuNam.getTuNgay())) {
			thoiGianThucHien = formatter.format(nhiemVuNam.getTuNgay());
		}
		if (Objects.nonNull(nhiemVuNam.getDenNgay())) {
			if (Objects.nonNull(thoiGianThucHien) && !thoiGianThucHien.isEmpty()) {
				thoiGianThucHien += " - ";
			}
			thoiGianThucHien += formatter.format(nhiemVuNam.getDenNgay());
		}
		nhiemVuNamData.setThoiGianThucHien(thoiGianThucHien);

		// tiến độ
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService
				.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<TienDoNhiemVuNamData>();
		if (Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for (TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());

				// xử lý đính kèm
				if(Objects.nonNull(tienDoNhiemVuNam)) {
					int type = Constants.DINH_KEM_1_FILE;
					Optional<FileDinhKemNhiemVuNam> fileDinhKemNhiemVuNam = serviceFileDinhKemNhiemVuNamService
							.findByTienDoNhiemVuNamId(tienDoNhiemVuNam.getId());
					Long fileDinhKemId = null;
					Long objectId = tienDoNhiemVuNam.getId();
					String appCode = TienDoNhiemVuNam.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness
							.getAttachments(fileDinhKemNhiemVuNam.get().getFileDinhKemId(), appCode, objectId, type);
					tienDoNhiemVuNamData.setFileDinhKem(fileDinhKem);
					tienDoNhiemVuNamData.setFileDinhKemIds(fileDinhKem.getIds());
				}

				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);

		return nhiemVuNamData;
	}

	public NhiemVuNamData saveTienDo(Long nhiemVuId, NhiemVuNamData nhiemVuNamData)
			throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(nhiemVuId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(nhiemVuId));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		
		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(false, nhiemVuNam.getId());
		List<TienDoNhiemVuNamData> tienDoDatas = nhiemVuNamData.getTienDoNhiemVuNamDatas();
		if(Objects.nonNull(tienDoDatas) && !tienDoDatas.isEmpty()) {
			for(TienDoNhiemVuNamData tienDoData : tienDoDatas) {
				TienDoNhiemVuNam tienDo = new TienDoNhiemVuNam();
				if(Objects.nonNull(tienDo.getId())) {
					Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findById(tienDoData.getId());
					if(optTienDo.isPresent()) {
						tienDo = optTienDo.get();
					}
				}
				tienDo.setDaXoa(false);
				tienDo.setKetQua(tienDoData.getKetQua());
				tienDo.setMucDoHoanThanh(tienDoData.getMucDoHoanThanh());
				tienDo.setNgayBaoCao(tienDoData.getNgayBaoCao());
				tienDo.setNhiemVuNamId(nhiemVuId);
				tienDo.setTinhTrang(tienDoData.getTinhTrang());
				tienDo = serviceTienDoNhiemVuNamService.save(tienDo);
				
				// đính kèm
				serviceFileDinhKemNhiemVuNamService.setFixedDaXoaForTienDoNhiemVuNamId(false, tienDo.getId());
				/* Begin đính kèm file *******************************************************/

				/*
				 * Khởi tạo biến **************************************************************
				 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
				 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
				 * - objectId: id đối tượng đính kèm ******************************************
				 * - appCode: tên model của đối tượng đính kèm*********************************
				 */
				List<Long> fileDinhKemIds = tienDoData.getFileDinhKemIds();
				int type = Constants.DINH_KEM_NHIEU_FILE;
				long objectId = tienDo.getId();
				String appCode = TienDoNhiemVuNam.class.getSimpleName();
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
							FileDinhKemNhiemVuNam fileDinhKemNhiemVuNam = new FileDinhKemNhiemVuNam();
							List<FileDinhKemNhiemVuNam> fileDinhKemNhiemVuNams = serviceFileDinhKemNhiemVuNamService
									.findByTienDoNhiemVuNamIdAndFileDinhKemId(tienDo.getId(), fileDinhKemId);
							if (Objects.nonNull(fileDinhKemNhiemVuNams) && !fileDinhKemNhiemVuNams.isEmpty()) {
								fileDinhKemNhiemVuNam = fileDinhKemNhiemVuNams.get(0);
							}
							fileDinhKemNhiemVuNam.setDaXoa(false);
							fileDinhKemNhiemVuNam.setTienDoNhiemVuNamId(tienDo.getId());
							fileDinhKemNhiemVuNam.setFileDinhKemId(coreAttachment.getId());
							fileDinhKemNhiemVuNam = serviceFileDinhKemNhiemVuNamService.save(fileDinhKemNhiemVuNam);

							coreAttachmentBusiness.saveAndMove(coreAttachment);
						}

						/* thoát nếu đính kèm 1 file */
						if (type == Constants.DINH_KEM_1_FILE) {
							break;
						}
					}
				}
			}
		}
		return this.convertToNhiemVuNamData(nhiemVuNam);
	}

	public NhiemVuNamData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		nhiemVuNam.setDaXoa(true);
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
		return this.convertToNhiemVuNamData(nhiemVuNam);
	}

	public void deleteTienDo(Long tienDoId) throws EntityNotFoundException {
		Optional<TienDoNhiemVuNam> optional = serviceTienDoNhiemVuNamService.findById(tienDoId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TienDoNhiemVuNam.class, "id", String.valueOf(tienDoId));
		}
		TienDoNhiemVuNam tienDoNhiemVuNam = optional.get();
		tienDoNhiemVuNam.setDaXoa(true);
		tienDoNhiemVuNam = serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
	}
}
