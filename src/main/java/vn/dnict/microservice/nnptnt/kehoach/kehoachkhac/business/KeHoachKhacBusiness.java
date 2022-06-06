package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.business;

import java.text.DecimalFormat;
import java.time.LocalDate;
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

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.danhmuc.dao.service.DmNguonKinhPhiService;
import vn.dnict.microservice.danhmuc.dao.service.DmPhongBanService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model.DanhSachKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.sevice.DanhSachKeHoachService;
import vn.dnict.microservice.nnptnt.kehoach.data.ExcelColumnsData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoach2NhiemVuData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoach2QuyetDinhData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachKhacData;
import vn.dnict.microservice.nnptnt.kehoach.data.KinhPhi2ThanhToanKhacData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2GiaHanData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2KinhPhiData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2ThongTinThucHienData;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model.KeHoach2NhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.KeHoach2NhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.model.KeHoach2QuyetDinh;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2quyetdinh.dao.service.KeHoach2QuyetDinhService;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.KeHoachKhacService;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.model.KinhPhi2ThanhToanKhac;
import vn.dnict.microservice.nnptnt.kehoach.kinhphi2thanhtoankhac.dao.service.KinhPhi2ThanhToanKhacService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.model.NhiemVu2DonViPhoiHop;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2donviphoihop.service.NhiemVu2DonViPhoiHopService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.model.NhiemVu2GiaHan;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2giahan.dao.service.NhiemVu2GiaHanService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.model.NhiemVu2KinhPhi;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2kinhphi.dao.service.NhiemVu2KinhPhiService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.model.NhiemVu2SanPhamDauRa;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service.NhiemVu2SanPhamDauRaService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.model.NhiemVu2ThongTinThucHien;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2thongtinthuchien.dao.service.NhiemVu2ThongTinThucHienService;
import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.model.ThucHien2VanBanLienQuan;
import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service.ThucHien2VanBanLienQuanService;
import vn.dnict.microservice.uaa.dao.service.auth.UaaUserService;
import vn.dnict.microservice.utils.Constants;

@Service
@Slf4j
public class KeHoachKhacBusiness {
	@Autowired
	private KeHoachKhacService serviceKeHoachKhacService;
	@Autowired
	private DmDonViService dmDonViService;
	@Autowired
	private DmPhongBanService dmPhongBanService;
	@Autowired
	private DmCanBoService dmCanBoService;
	@Autowired
	private KeHoach2NhiemVuService serviceKeHoach2NhiemVuService;
	@Autowired
	private DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	@Autowired
	private NhiemVu2DonViPhoiHopService serviceNhiemVu2DonViPhoiHopService;
	@Autowired
	private NhiemVu2KinhPhiService serviceNhiemVu2KinhPhiService;
	@Autowired
	private DmNguonKinhPhiService dmNguonKinhPhiService;
	@Autowired
	private NhiemVu2GiaHanService serviceNhiemVu2GiaHanService;
	@Autowired
	private NhiemVu2ThongTinThucHienService serviceNhiemVu2ThongTinThucHienService;
	@Autowired
	private UaaUserService uaaUserService;
	@Autowired
	private DanhSachKeHoachService serviceDanhSachKeHoachService;
	@Autowired
	private KeHoach2QuyetDinhService serviceKeHoach2QuyetDinhService;
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	@Autowired
	private KinhPhi2ThanhToanKhacService serviceKinhPhi2ThanhToanKhacService;
	@Autowired
	private NhiemVu2SanPhamDauRaService serviceNhiemVu2SanPhamDauRaService;
	@Autowired
	private ThucHien2VanBanLienQuanService serviceThucHien2VanBanLienQuanService;

	public Page<KeHoachKhacData> findAll(int page, int size, String sortBy, String sortDir, String tenKeHoach, Integer nam) {
		Direction direction;
		if ("ASC".equals(sortDir)) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachKhac> pageKeHoachKhac = serviceKeHoachKhacService.findAll(tenKeHoach, nam, PageRequest.of(page, size, direction, sortBy));
		return pageKeHoachKhac
				.map(e -> convertToKeHoachKhacData(false, e, null, null, null, null, null, null, null, null, null, null, null, null));
	}

	public List<DanhSachKeHoach> danhSachKeHoach(String tenKeHoach, Integer nam, Integer loaiKeHoach, Integer trangThai, Boolean isDieuChinh,
			Boolean isBanHanh) {

		String email = uaaUserService.getUsername();
		Long donViId = -1L;
		Long phongBanId = -1L;
		Long canBoId = -1L;
		List<DmCanBo> listDmCanBo = dmCanBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		if (listDmCanBo != null && !listDmCanBo.isEmpty()) {
			DmCanBo dmCanBo = listDmCanBo.get(0);
			canBoId = dmCanBo.getId();
			if (Objects.nonNull(dmCanBo.getDonVi())) {
				if (Constants.DON_VI_SU_DUNG != dmCanBo.getDonVi().getId()) {
					donViId = dmCanBo.getDonVi().getId();
				} else if (Objects.nonNull(dmCanBo.getPhongBan())) {
					phongBanId = dmCanBo.getPhongBan().getId();
				}
			}
		}
		return serviceDanhSachKeHoachService.findAll(tenKeHoach, nam, phongBanId, donViId, canBoId, loaiKeHoach, trangThai, isDieuChinh, isBanHanh);
	}

	public KeHoachKhacData convertToKeHoachKhacData(Boolean isDaGui, KeHoachKhac keHoachKhac, Long donViThucHienId, Long phongBanThucHienId,
			Boolean isBanHanh, Boolean isThucHien, List<Long> nguonKinhPhiIds, List<Integer> tinhTrangs, LocalDate thoiGianThucHienTuNgay,
			LocalDate thoiGianThucHienDenNgay, LocalDate thoiGianThanhToanTuNgay, LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds,
			List<Long> phongBanPhoiHopIds) {
		KeHoachKhacData keHoachKhacData = new KeHoachKhacData();
		keHoachKhacData.setId(keHoachKhac.getId());
		keHoachKhacData.setTenKeHoach(keHoachKhac.getTenKeHoach());
		keHoachKhacData.setNam(keHoachKhac.getNam());
		keHoachKhacData.setTrangThai(keHoachKhac.getTrangThai());
		keHoachKhacData.setTongKinhPhi(keHoachKhac.getTongKinhPhi());
		keHoachKhacData.setIsDieuChinh(keHoachKhac.getIsDieuChinh());
		keHoachKhacData.setIsLoaiNhiemVu(keHoachKhac.getIsLoaiNhiemVu());
		keHoachKhacData.setLoaiKeHoach(keHoachKhac.getLoaiKeHoach());
		if (Objects.nonNull(keHoachKhac.getDonViLapKhId())) {
			Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoachKhac.getDonViLapKhId());
			if (optionalDmDonVi.isPresent()) {
				keHoachKhacData.setDonViLapKhId(optionalDmDonVi.get().getId());
				keHoachKhacData.setDonViLapKhTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		if (Objects.nonNull(keHoachKhac.getPhongBanLapKhId())) {
			Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoachKhac.getPhongBanLapKhId());
			if (optionalDmPhongBan.isPresent()) {
				keHoachKhacData.setPhongBanLapKhId(optionalDmPhongBan.get().getId());
				keHoachKhacData.setPhongBanLapKhTen(optionalDmPhongBan.get().getTen());
			}
		}
		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = new ArrayList<>();
		int sapXep = 0;
		List<KeHoach2NhiemVu> keHoach2NhiemVus = serviceKeHoach2NhiemVuService.findAll(keHoachKhac.getId(), -1L, isBanHanh, isThucHien,
				nguonKinhPhiIds, tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay,
				donViPhoiHopIds, phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId);

		if (Objects.nonNull(keHoach2NhiemVus) && !keHoach2NhiemVus.isEmpty()) {
			keHoach2NhiemVuDatas = setNhiemVuDataByKeHoach(isDaGui, keHoach2NhiemVus, isBanHanh, isThucHien, nguonKinhPhiIds, tinhTrangs,
					thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds,
					phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId, sapXep, "");
		}
		keHoachKhacData.setKeHoach2NhiemVuDatas(keHoach2NhiemVuDatas);

		// quyết định
		List<KeHoach2QuyetDinh> keHoach2QuyetDinhs = serviceKeHoach2QuyetDinhService.findByIsHienTaiAndKeHoachKhacIdAndDaXoa(true, keHoachKhac.getId(),
				false);
		KeHoach2QuyetDinhData keHoach2QuyetDinhData = new KeHoach2QuyetDinhData();
		if (Objects.nonNull(keHoach2QuyetDinhs) && !keHoach2QuyetDinhs.isEmpty()) {
			KeHoach2QuyetDinh keHoach2QuyetDinh = keHoach2QuyetDinhs.get(0);
			keHoach2QuyetDinhData.setId(keHoach2QuyetDinh.getId());
			keHoach2QuyetDinhData.setIsHienTai(keHoach2QuyetDinh.getIsHienTai());
			keHoach2QuyetDinhData.setKeHoachKhacId(keHoach2QuyetDinh.getKeHoachKhacId());
			keHoach2QuyetDinhData.setNgayBanHanh(keHoach2QuyetDinh.getNgayBanHanh());
			keHoach2QuyetDinhData.setSoQuyetDinh(keHoach2QuyetDinh.getSoQuyetDinh());
			int type = Constants.DINH_KEM_1_FILE;
			Long fileDinhKemId = keHoach2QuyetDinh.getQuyetDinhId();
			Long objectId = keHoach2QuyetDinh.getId();
			String appCode = KeHoach2QuyetDinh.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
			keHoach2QuyetDinhData.setQuyetDinhIds(fileDinhKem.getIds());
			keHoach2QuyetDinhData.setFileDinhKem(fileDinhKem);
		}
		keHoachKhacData.setKeHoach2QuyetDinhData(keHoach2QuyetDinhData);
		return keHoachKhacData;
	}

	public List<KeHoach2NhiemVuData> setNhiemVuDataByKeHoach(Boolean isDaGui, List<KeHoach2NhiemVu> keHoach2NhiemVus,
			Boolean isBanHanh, Boolean isThucHien, List<Long> nguonKinhPhiIds, List<Integer> tinhTrangs, LocalDate thoiGianThucHienTuNgay,
			LocalDate thoiGianThucHienDenNgay, LocalDate thoiGianThanhToanTuNgay, LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds,
			List<Long> phongBanPhoiHopIds, Long donViThucHienId, Long phongBanThucHienId, Integer sapXep, String stt) {
		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = new ArrayList<>();

		for (KeHoach2NhiemVu keHoach2NhiemVu : keHoach2NhiemVus) {
			if (Boolean.TRUE.equals(isDaGui) && Boolean.FALSE.equals(keHoach2NhiemVu.getIsDaGui())) {
				continue;
			}
			sapXep++;
			String sttCon = "";
			if (Objects.nonNull(stt) && !stt.isEmpty()) {
				sttCon = stt + "." + sapXep;
			} else {
				sttCon = String.valueOf(sapXep);
			}
			KeHoach2NhiemVuData keHoach2NhiemVuData = new KeHoach2NhiemVuData();
			keHoach2NhiemVuData.setId(keHoach2NhiemVu.getId());
			keHoach2NhiemVuData.setKeHoachKhacId(keHoach2NhiemVu.getKeHoachKhacId());
			keHoach2NhiemVuData.setGhiChu(keHoach2NhiemVu.getGhiChu());
			keHoach2NhiemVuData.setNhiemVuChaId(keHoach2NhiemVu.getNhiemVuChaId());
			keHoach2NhiemVuData.setNoiDung(keHoach2NhiemVu.getNoiDung());
			keHoach2NhiemVuData.setThoiGianThanhToan(keHoach2NhiemVu.getThoiGianThanhToan());
			keHoach2NhiemVuData.setThoiGianThucHienDenNgay(keHoach2NhiemVu.getThoiGianThucHienDenNgay());
			keHoach2NhiemVuData.setThoiGianThucHienTuNgay(keHoach2NhiemVu.getThoiGianThucHienTuNgay());
			if (Objects.nonNull(keHoach2NhiemVu.getDonViThucHienId())) {
				Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoach2NhiemVu.getDonViThucHienId());
				if (optionalDmDonVi.isPresent()) {
					keHoach2NhiemVuData.setDonViThucHienId(optionalDmDonVi.get().getId());
					keHoach2NhiemVuData.setDonViThucHienTen(optionalDmDonVi.get().getTenDonVi());
				}
			}
			if (Objects.nonNull(keHoach2NhiemVu.getPhongBanThucHienId())) {
				Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoach2NhiemVu.getPhongBanThucHienId());
				if (optionalDmPhongBan.isPresent()) {
					keHoach2NhiemVuData.setPhongBanThucHienId(optionalDmPhongBan.get().getId());
					keHoach2NhiemVuData.setPhongBanThucHienTen(optionalDmPhongBan.get().getTen());
				}
			}
			keHoach2NhiemVuData.setIsBanHanh(keHoach2NhiemVu.getIsBanHanh());
			keHoach2NhiemVuData.setIsThemMoiThucHien(keHoach2NhiemVu.getIsThemMoiThucHien());
			if (Objects.nonNull(keHoach2NhiemVu.getDmLoaiNhiemVuId())) {
				Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(keHoach2NhiemVu.getDmLoaiNhiemVuId());
				if (optionalDmLoaiNhiemVu.isPresent()) {
					keHoach2NhiemVuData.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
					keHoach2NhiemVuData.setDmLoaiNhiemVuTen(optionalDmLoaiNhiemVu.get().getTen());
				}
			}
			// đơn vị phối hợp
			List<Long> phongBanPhoiHopIdxs = new ArrayList<>();
			List<Long> donViPhoiHopIdxs = new ArrayList<>();
			List<String> phongBanPhoiHopTens = new ArrayList<>();
			List<String> donViPhoiHopTens = new ArrayList<>();
			List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
				for (NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop : nhiemVu2DonViPhoiHops) {
					if (Objects.nonNull(nhiemVu2DonViPhoiHop.getDonViId())) {
						Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(nhiemVu2DonViPhoiHop.getDonViId());
						if (optionalDmDonVi.isPresent()) {
							donViPhoiHopIdxs.add(optionalDmDonVi.get().getId());
							donViPhoiHopTens.add(optionalDmDonVi.get().getTenDonVi());
						}
					}
					if (Objects.nonNull(nhiemVu2DonViPhoiHop.getPhongBanId())) {
						Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(nhiemVu2DonViPhoiHop.getPhongBanId());
						if (optionalDmPhongBan.isPresent()) {
							phongBanPhoiHopIdxs.add(optionalDmPhongBan.get().getId());
							phongBanPhoiHopTens.add(optionalDmPhongBan.get().getTen());
						}
					}
				}
			}
			keHoach2NhiemVuData.setDonViPhoiHopIds(donViPhoiHopIdxs);
			keHoach2NhiemVuData.setDonViPhoiHopTens(donViPhoiHopTens);
			keHoach2NhiemVuData.setPhongBanPhoiHopIds(phongBanPhoiHopIdxs);
			keHoach2NhiemVuData.setPhongBanPhoiHopTens(phongBanPhoiHopTens);
			keHoach2NhiemVuData.setIsDaGui(keHoach2NhiemVu.getIsDaGui());
			keHoach2NhiemVuData.setTinhTrang(keHoach2NhiemVu.getTinhTrang());
			keHoach2NhiemVuData.setSanPhamDauRa(keHoach2NhiemVu.getSanPhamDauRa());
			if (Objects.nonNull(keHoach2NhiemVu.getLanhDaoId())) {
				Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(keHoach2NhiemVu.getLanhDaoId());
				if (optionalDmCanBo.isPresent()) {
					keHoach2NhiemVuData.setLanhDaoId(optionalDmCanBo.get().getId());
					keHoach2NhiemVuData.setLanhDaoTen(optionalDmCanBo.get().getHoTen());
				}
			}

			int type = Constants.DINH_KEM_NHIEU_FILE;
			Long fileDinhKemId = null;
			Long objectId = keHoach2NhiemVu.getId();
			String appCode = KeHoach2NhiemVu.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
			keHoach2NhiemVuData.setFileDinhKemIds(fileDinhKem.getIds());
			keHoach2NhiemVuData.setFileDinhKem(fileDinhKem);

			// kinh phí
			List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiDatas = new ArrayList<>();
			List<NhiemVu2KinhPhi> nhiemVu2KinhPhis = serviceNhiemVu2KinhPhiService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2KinhPhis) && !nhiemVu2KinhPhis.isEmpty()) {
				for (NhiemVu2KinhPhi nhiemVu2KinhPhi : nhiemVu2KinhPhis) {
					NhiemVu2KinhPhiData nhiemVu2KinhPhiData = new NhiemVu2KinhPhiData();
					nhiemVu2KinhPhiData.setId(nhiemVu2KinhPhi.getId());
					nhiemVu2KinhPhiData.setKeHoach2NhiemVuId(nhiemVu2KinhPhi.getKeHoach2NhiemVuId());
					if (Objects.nonNull(nhiemVu2KinhPhiData.getSoTien())) {
						nhiemVu2KinhPhiData.setSoTien(new DecimalFormat("#").format(nhiemVu2KinhPhi.getSoTien()));
					}
					if (Objects.nonNull(nhiemVu2KinhPhi.getSoTienDieuChinh())) {
						nhiemVu2KinhPhiData.setSoTienDieuChinh(new DecimalFormat("#").format(nhiemVu2KinhPhi.getSoTienDieuChinh()));
					}
					nhiemVu2KinhPhiData.setTangGiamKinhPhi(nhiemVu2KinhPhi.getTangGiamKinhPhi());
					if (Objects.nonNull(nhiemVu2KinhPhi.getDmNguonKinhPhiId())) {
						Optional<DmNguonKinhPhi> optionalDmNguonKinhPhi = dmNguonKinhPhiService.findById(nhiemVu2KinhPhi.getDmNguonKinhPhiId());
						if (optionalDmNguonKinhPhi.isPresent()) {
							nhiemVu2KinhPhiData.setDmNguonKinhPhiId(optionalDmNguonKinhPhi.get().getId());
							nhiemVu2KinhPhiData.setDmNguonKinhPhiTen(optionalDmNguonKinhPhi.get().getTen());
						}
					}

					List<KinhPhi2ThanhToanKhac> kinhPhi2ThanhToanKhacs = serviceKinhPhi2ThanhToanKhacService
							.findByNhiemVu2KinhPhiIdAndDaXoa(nhiemVu2KinhPhi.getId(), false);
					List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = new ArrayList<>();
					if (Objects.nonNull(kinhPhi2ThanhToanKhacs) && !kinhPhi2ThanhToanKhacs.isEmpty()) {
						for (KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac : kinhPhi2ThanhToanKhacs) {
							KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData = new KinhPhi2ThanhToanKhacData();
							kinhPhi2ThanhToanKhacData.setId(kinhPhi2ThanhToanKhac.getId());
							kinhPhi2ThanhToanKhacData.setNgayThanhToan(kinhPhi2ThanhToanKhac.getNgayThanhToan());
							kinhPhi2ThanhToanKhacData.setNhiemVu2KinhPhiId(kinhPhi2ThanhToanKhac.getNhiemVu2KinhPhiId());
							if (Objects.nonNull(kinhPhi2ThanhToanKhac.getSoTienThanhToan())) {
								kinhPhi2ThanhToanKhacData
										.setSoTienThanhToan(new DecimalFormat("#").format(kinhPhi2ThanhToanKhac.getSoTienThanhToan()));
							}
							type = Constants.DINH_KEM_1_FILE;
							fileDinhKemId = kinhPhi2ThanhToanKhac.getChungTuId();
							objectId = kinhPhi2ThanhToanKhac.getId();
							appCode = KinhPhi2ThanhToanKhac.class.getSimpleName();
							fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
							kinhPhi2ThanhToanKhacData.setChungTuIds(fileDinhKem.getIds());
							kinhPhi2ThanhToanKhacData.setFileDinhKem(fileDinhKem);
							kinhPhi2ThanhToanKhacDatas.add(kinhPhi2ThanhToanKhacData);
						}
					}
					nhiemVu2KinhPhiData.setKinhPhi2ThanhToanKhacDatas(kinhPhi2ThanhToanKhacDatas);
					nhiemVu2KinhPhiDatas.add(nhiemVu2KinhPhiData);
				}
			}
			keHoach2NhiemVuData.getNhiemVu2KinhPhiDatas().addAll(nhiemVu2KinhPhiDatas);
			// gia hạn
			List<NhiemVu2GiaHanData> nhiemVu2GiaHanDatas = new ArrayList<>();
			List<NhiemVu2GiaHan> nhiemVu2GiaHans = serviceNhiemVu2GiaHanService.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2GiaHans) && !nhiemVu2GiaHans.isEmpty()) {
				for (NhiemVu2GiaHan nhiemVu2GiaHan : nhiemVu2GiaHans) {
					NhiemVu2GiaHanData nhiemVu2GiaHanData = new NhiemVu2GiaHanData();
					nhiemVu2GiaHanData.setId(nhiemVu2GiaHan.getId());
					nhiemVu2GiaHanData.setKeHoach2NhiemVuId(nhiemVu2GiaHan.getNguoiGiaHanId());
					nhiemVu2GiaHanData.setNgayHoanThanh(nhiemVu2GiaHan.getNgayHoanThanh());
					nhiemVu2GiaHanData.setSoLanGiaHan(nhiemVu2GiaHan.getSoLanGiaHan());
					if (Objects.nonNull(nhiemVu2GiaHan.getNguoiGiaHanId())) {
						Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(nhiemVu2GiaHan.getNguoiGiaHanId());
						if (optionalDmCanBo.isPresent()) {
							nhiemVu2GiaHanData.setNguoiGiaHanId(optionalDmCanBo.get().getId());
							nhiemVu2GiaHanData.setNguoiGiaHanTen(optionalDmCanBo.get().getHoTen());
						}
					}
					nhiemVu2GiaHanData.setNgayHoanThanhLanDau(nhiemVu2GiaHan.getNgayHoanThanhLanDau());
					nhiemVu2GiaHanData.setNgayThucHienGiaHan(nhiemVu2GiaHan.getNgayThucHienGiaHan());
					nhiemVu2GiaHanDatas.add(nhiemVu2GiaHanData);
				}
			}
			keHoach2NhiemVuData.setNhiemVu2GiaHanDatas(nhiemVu2GiaHanDatas);
			// thông tin thực hiện
			List<NhiemVu2ThongTinThucHienData> nhiemVu2ThongTinThucHienDatas = new ArrayList<>();
			List<NhiemVu2ThongTinThucHien> nhiemVu2ThongTinThucHiens = serviceNhiemVu2ThongTinThucHienService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2ThongTinThucHiens) && !nhiemVu2ThongTinThucHiens.isEmpty()) {
				for (NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien : nhiemVu2ThongTinThucHiens) {
					if (Boolean.TRUE.equals(isDaGui) && Boolean.FALSE.equals(nhiemVu2ThongTinThucHien.getIsDaGui())) {
						continue;
					}
					NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData = new NhiemVu2ThongTinThucHienData();
					nhiemVu2ThongTinThucHienData.setId(nhiemVu2ThongTinThucHien.getId());
					nhiemVu2ThongTinThucHienData.setKeHoach2NhiemVuId(nhiemVu2ThongTinThucHien.getKeHoach2NhiemVuId());
					nhiemVu2ThongTinThucHienData.setKetQuaThucHien(nhiemVu2ThongTinThucHien.getKetQuaThucHien());
					nhiemVu2ThongTinThucHienData.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHien.getNgayThucHienCapNhat());
					nhiemVu2ThongTinThucHienData.setTienDo(nhiemVu2ThongTinThucHien.getTienDo());
					nhiemVu2ThongTinThucHienData.setIsDaGui(nhiemVu2ThongTinThucHien.getIsDaGui());
					nhiemVu2ThongTinThucHienData.setNgayHoanThanh(nhiemVu2ThongTinThucHien.getNgayHoanThanh());

					type = Constants.DINH_KEM_NHIEU_FILE;
					fileDinhKemId = null;
					objectId = nhiemVu2ThongTinThucHien.getId();
					appCode = NhiemVu2ThongTinThucHien.class.getSimpleName();
					fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
					nhiemVu2ThongTinThucHienData.setFileDinhKemIds(fileDinhKem.getIds());
					nhiemVu2ThongTinThucHienData.setFileDinhKem(fileDinhKem);
					nhiemVu2ThongTinThucHienDatas.add(nhiemVu2ThongTinThucHienData);
				}
			}
			keHoach2NhiemVuData.setNhiemVu2ThongTinThucHienDatas(nhiemVu2ThongTinThucHienDatas);
			keHoach2NhiemVuData.setStt(sttCon);
			int sapXepCon = 0;
			List<KeHoach2NhiemVuData> children = new ArrayList<>();
			List<KeHoach2NhiemVu> kNhiemVuCons = serviceKeHoach2NhiemVuService.findAll(keHoach2NhiemVu.getKeHoachKhacId(),
					keHoach2NhiemVu.getId(), isBanHanh, isThucHien, nguonKinhPhiIds, tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay,
					thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds, phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId);

			if (Objects.nonNull(kNhiemVuCons) && !kNhiemVuCons.isEmpty()) {
				children = setNhiemVuDataByKeHoach(isDaGui, kNhiemVuCons, isBanHanh, isThucHien, nguonKinhPhiIds, tinhTrangs, thoiGianThucHienTuNgay,
						thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds, phongBanPhoiHopIds,
						donViThucHienId, phongBanThucHienId, sapXepCon, sttCon);
			}
			keHoach2NhiemVuData.getChildren().addAll(children);
			keHoach2NhiemVuDatas.add(keHoach2NhiemVuData);
		}

		return keHoach2NhiemVuDatas;
	}

	public KeHoachKhacData convertToKeHoachKhacDataExport(Boolean isDaGui, KeHoachKhac keHoachKhac, Long donViThucHienId,
			Long phongBanThucHienId, Boolean isBanHanh, Boolean isThucHien, List<Long> nguonKinhPhiIds, List<Integer> tinhTrangs,
			LocalDate thoiGianThucHienTuNgay, LocalDate thoiGianThucHienDenNgay, LocalDate thoiGianThanhToanTuNgay,
			LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds, List<Long> phongBanPhoiHopIds) {
		KeHoachKhacData keHoachKhacData = new KeHoachKhacData();
		keHoachKhacData.setId(keHoachKhac.getId());
		keHoachKhacData.setTenKeHoach(keHoachKhac.getTenKeHoach());
		keHoachKhacData.setNam(keHoachKhac.getNam());
		keHoachKhacData.setTrangThai(keHoachKhac.getTrangThai());
		keHoachKhacData.setTongKinhPhi(keHoachKhac.getTongKinhPhi());
		keHoachKhacData.setIsDieuChinh(keHoachKhac.getIsDieuChinh());
		keHoachKhacData.setIsLoaiNhiemVu(keHoachKhac.getIsLoaiNhiemVu());
		keHoachKhacData.setLoaiKeHoach(keHoachKhac.getLoaiKeHoach());
		if (Objects.nonNull(keHoachKhac.getDonViLapKhId())) {
			Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoachKhac.getDonViLapKhId());
			if (optionalDmDonVi.isPresent()) {
				keHoachKhacData.setDonViLapKhId(optionalDmDonVi.get().getId());
				keHoachKhacData.setDonViLapKhTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		if (Objects.nonNull(keHoachKhac.getPhongBanLapKhId())) {
			Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoachKhac.getPhongBanLapKhId());
			if (optionalDmPhongBan.isPresent()) {
				keHoachKhacData.setPhongBanLapKhId(optionalDmPhongBan.get().getId());
				keHoachKhacData.setPhongBanLapKhTen(optionalDmPhongBan.get().getTen());
			}
		}
		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = new ArrayList<>();
		int sapXep = 0;
		List<KeHoach2NhiemVu> khNhiemVus = serviceKeHoach2NhiemVuService.findAll(keHoachKhac.getId(), null, isBanHanh, isThucHien,
				nguonKinhPhiIds, tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay,
				donViPhoiHopIds, phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId);

		List<KeHoach2NhiemVu> keHoach2NhiemVus = khNhiemVus.stream().filter(e -> Objects.isNull(e.getNhiemVuChaId()))
				.collect(Collectors.toList());

		if (Objects.nonNull(keHoach2NhiemVus) && !keHoach2NhiemVus.isEmpty()) {
			keHoach2NhiemVuDatas = setNhiemVuDataByKeHoachExport(isDaGui, keHoach2NhiemVus, isBanHanh, isThucHien, nguonKinhPhiIds,
					tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds,
					phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId, sapXep, "", khNhiemVus);
		}
		keHoachKhacData.setKeHoach2NhiemVuDatas(keHoach2NhiemVuDatas);

		// quyết định
		List<KeHoach2QuyetDinh> keHoach2QuyetDinhs = serviceKeHoach2QuyetDinhService.findByIsHienTaiAndKeHoachKhacIdAndDaXoa(true, keHoachKhac.getId(),
				false);
		KeHoach2QuyetDinhData keHoach2QuyetDinhData = new KeHoach2QuyetDinhData();
		if (Objects.nonNull(keHoach2QuyetDinhs) && !keHoach2QuyetDinhs.isEmpty()) {
			KeHoach2QuyetDinh keHoach2QuyetDinh = keHoach2QuyetDinhs.get(0);
			keHoach2QuyetDinhData.setId(keHoach2QuyetDinh.getId());
			keHoach2QuyetDinhData.setIsHienTai(keHoach2QuyetDinh.getIsHienTai());
			keHoach2QuyetDinhData.setKeHoachKhacId(keHoach2QuyetDinh.getKeHoachKhacId());
			keHoach2QuyetDinhData.setNgayBanHanh(keHoach2QuyetDinh.getNgayBanHanh());
			keHoach2QuyetDinhData.setSoQuyetDinh(keHoach2QuyetDinh.getSoQuyetDinh());
			// int type = Constants.DINH_KEM_1_FILE;
			// Long fileDinhKemId = khKhKhac2QuyetDinh.getQuyetDinhId();
			// Long objectId = khKhKhac2QuyetDinh.getId();
			// String appCode = KhKhKhac2QuyetDinh.class.getSimpleName();
			// FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
			// khKhKhac2QuyetDinhData.setQuyetDinhIds(fileDinhKem.getIds());
			// khKhKhac2QuyetDinhData.setFileDinhKem(fileDinhKem);
		}
		keHoachKhacData.setKeHoach2QuyetDinhData(keHoach2QuyetDinhData);
		return keHoachKhacData;
	}

	public List<KeHoach2NhiemVuData> setNhiemVuDataByKeHoachExport(Boolean isDaGui, List<KeHoach2NhiemVu> keHoach2NhiemVus,
			Boolean isBanHanh, Boolean isThucHien, List<Long> nguonKinhPhiIds, List<Integer> tinhTrangs, LocalDate thoiGianThucHienTuNgay,
			LocalDate thoiGianThucHienDenNgay, LocalDate thoiGianThanhToanTuNgay, LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds,
			List<Long> phongBanPhoiHopIds, Long donViThucHienId, Long phongBanThucHienId, Integer sapXep, String stt,
			List<KeHoach2NhiemVu> khNhiemVus) {
		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = new ArrayList<>();
		Long loaiNhiemVu = 0L;
		for (KeHoach2NhiemVu keHoach2NhiemVu : keHoach2NhiemVus) {
			if (Boolean.TRUE.equals(isDaGui) && Boolean.FALSE.equals(keHoach2NhiemVu.getIsDaGui())) {
				continue;
			}
			if (!loaiNhiemVu.equals(keHoach2NhiemVu.getDmLoaiNhiemVuId())) {
				loaiNhiemVu = keHoach2NhiemVu.getDmLoaiNhiemVuId();
				sapXep = 0;
			}
			sapXep++;
			String sttCon = "";
			if (Objects.nonNull(stt) && !stt.isEmpty()) {
				sttCon = stt + "." + sapXep;
			} else {
				sttCon = String.valueOf(sapXep);
			}
			KeHoach2NhiemVuData keHoach2NhiemVuData = new KeHoach2NhiemVuData();
			keHoach2NhiemVuData.setId(keHoach2NhiemVu.getId());
			keHoach2NhiemVuData.setKeHoachKhacId(keHoach2NhiemVu.getKeHoachKhacId());
			keHoach2NhiemVuData.setGhiChu(keHoach2NhiemVu.getGhiChu());
			keHoach2NhiemVuData.setNhiemVuChaId(keHoach2NhiemVu.getNhiemVuChaId());
			keHoach2NhiemVuData.setNoiDung(keHoach2NhiemVu.getNoiDung());
			keHoach2NhiemVuData.setThoiGianThanhToan(keHoach2NhiemVu.getThoiGianThanhToan());
			keHoach2NhiemVuData.setThoiGianThucHienDenNgay(keHoach2NhiemVu.getThoiGianThucHienDenNgay());
			keHoach2NhiemVuData.setThoiGianThucHienTuNgay(keHoach2NhiemVu.getThoiGianThucHienTuNgay());
			if (Objects.nonNull(keHoach2NhiemVu.getDonViThucHienId())) {
				Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoach2NhiemVu.getDonViThucHienId());
				if (optionalDmDonVi.isPresent()) {
					keHoach2NhiemVuData.setDonViThucHienId(optionalDmDonVi.get().getId());
					keHoach2NhiemVuData.setDonViThucHienTen(optionalDmDonVi.get().getTenDonVi());
				}
			}
			if (Objects.nonNull(keHoach2NhiemVu.getPhongBanThucHienId())) {
				Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoach2NhiemVu.getPhongBanThucHienId());
				if (optionalDmPhongBan.isPresent()) {
					keHoach2NhiemVuData.setPhongBanThucHienId(optionalDmPhongBan.get().getId());
					keHoach2NhiemVuData.setPhongBanThucHienTen(optionalDmPhongBan.get().getTen());
				}
			}
			keHoach2NhiemVuData.setIsBanHanh(keHoach2NhiemVu.getIsBanHanh());
			keHoach2NhiemVuData.setIsThemMoiThucHien(keHoach2NhiemVu.getIsThemMoiThucHien());
			if (Objects.nonNull(keHoach2NhiemVu.getDmLoaiNhiemVuId())) {
				Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(keHoach2NhiemVu.getDmLoaiNhiemVuId());
				if (optionalDmLoaiNhiemVu.isPresent()) {
					keHoach2NhiemVuData.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
					keHoach2NhiemVuData.setDmLoaiNhiemVuTen(optionalDmLoaiNhiemVu.get().getTen());
				}
			}
			// đơn vị phối hợp
			List<Long> phongBanPhoiHopIdxs = new ArrayList<>();
			List<Long> donViPhoiHopIdxs = new ArrayList<>();
			List<String> phongBanPhoiHopTens = new ArrayList<>();
			List<String> donViPhoiHopTens = new ArrayList<>();
			List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
				for (NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop : nhiemVu2DonViPhoiHops) {
					if (Objects.nonNull(nhiemVu2DonViPhoiHop.getDonViId())) {
						Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(nhiemVu2DonViPhoiHop.getDonViId());
						if (optionalDmDonVi.isPresent()) {
							donViPhoiHopIdxs.add(optionalDmDonVi.get().getId());
							donViPhoiHopTens.add(optionalDmDonVi.get().getTenDonVi());
						}
					}
					if (Objects.nonNull(nhiemVu2DonViPhoiHop.getPhongBanId())) {
						Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(nhiemVu2DonViPhoiHop.getPhongBanId());
						if (optionalDmPhongBan.isPresent()) {
							phongBanPhoiHopIdxs.add(optionalDmPhongBan.get().getId());
							phongBanPhoiHopTens.add(optionalDmPhongBan.get().getTen());
						}
					}
				}
			}
			keHoach2NhiemVuData.setDonViPhoiHopIds(donViPhoiHopIdxs);
			keHoach2NhiemVuData.setDonViPhoiHopTens(donViPhoiHopTens);
			keHoach2NhiemVuData.setPhongBanPhoiHopIds(phongBanPhoiHopIdxs);
			keHoach2NhiemVuData.setPhongBanPhoiHopTens(phongBanPhoiHopTens);
			keHoach2NhiemVuData.setIsDaGui(keHoach2NhiemVu.getIsDaGui());
			keHoach2NhiemVuData.setTinhTrang(keHoach2NhiemVu.getTinhTrang());
			keHoach2NhiemVuData.setSanPhamDauRa(keHoach2NhiemVu.getSanPhamDauRa());

			// int type = Constants.DINH_KEM_NHIEU_FILE;
			// Long fileDinhKemId = null;
			// Long objectId = khKhKhac2NhiemVuKhac.getId();
			// String appCode = KhKhKhac2NhiemVuKhac.class.getSimpleName();
			// FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
			// khKhac2NhiemVuKhacData.setFileDinhKemIds(fileDinhKem.getIds());
			// khKhac2NhiemVuKhacData.setFileDinhKem(fileDinhKem);

			// kinh phí
			List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiDatas = new ArrayList<>();
			List<NhiemVu2KinhPhi> nhiemVu2KinhPhis = serviceNhiemVu2KinhPhiService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2KinhPhis) && !nhiemVu2KinhPhis.isEmpty()) {
				for (NhiemVu2KinhPhi nhiemVu2KinhPhi : nhiemVu2KinhPhis) {
					NhiemVu2KinhPhiData nhiemVu2KinhPhiData = new NhiemVu2KinhPhiData();
					nhiemVu2KinhPhiData.setId(nhiemVu2KinhPhi.getId());
					nhiemVu2KinhPhiData.setKeHoach2NhiemVuId(nhiemVu2KinhPhi.getKeHoach2NhiemVuId());
					if (Objects.nonNull(nhiemVu2KinhPhi.getSoTien())) {
						nhiemVu2KinhPhiData.setSoTien(new DecimalFormat("#").format(nhiemVu2KinhPhi.getSoTien()));
					}
					if (Objects.nonNull(nhiemVu2KinhPhi.getSoTienDieuChinh())) {
						nhiemVu2KinhPhiData.setSoTienDieuChinh(new DecimalFormat("#").format(nhiemVu2KinhPhi.getSoTienDieuChinh()));
					}
					nhiemVu2KinhPhiData.setTangGiamKinhPhi(nhiemVu2KinhPhi.getTangGiamKinhPhi());
					if (Objects.nonNull(nhiemVu2KinhPhi.getDmNguonKinhPhiId())) {
						Optional<DmNguonKinhPhi> optionalDmNguonKinhPhi = dmNguonKinhPhiService.findById(nhiemVu2KinhPhi.getDmNguonKinhPhiId());
						if (optionalDmNguonKinhPhi.isPresent()) {
							nhiemVu2KinhPhiData.setDmNguonKinhPhiId(optionalDmNguonKinhPhi.get().getId());
							nhiemVu2KinhPhiData.setDmNguonKinhPhiTen(optionalDmNguonKinhPhi.get().getTen());
						}
					}

					List<KinhPhi2ThanhToanKhac> kinhPhi2ThanhToanKhacs = serviceKinhPhi2ThanhToanKhacService
							.findByNhiemVu2KinhPhiIdAndDaXoa(nhiemVu2KinhPhi.getId(), false);
					List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = new ArrayList<>();
					if (Objects.nonNull(kinhPhi2ThanhToanKhacs) && !kinhPhi2ThanhToanKhacs.isEmpty()) {
						for (KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac : kinhPhi2ThanhToanKhacs) {
							KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData = new KinhPhi2ThanhToanKhacData();
							kinhPhi2ThanhToanKhacData.setId(kinhPhi2ThanhToanKhac.getId());
							kinhPhi2ThanhToanKhacData.setNgayThanhToan(kinhPhi2ThanhToanKhac.getNgayThanhToan());
							kinhPhi2ThanhToanKhacData.setNhiemVu2KinhPhiId(kinhPhi2ThanhToanKhac.getNhiemVu2KinhPhiId());
							if (Objects.nonNull(kinhPhi2ThanhToanKhac.getSoTienThanhToan())) {
								kinhPhi2ThanhToanKhacData
										.setSoTienThanhToan(new DecimalFormat("#").format(kinhPhi2ThanhToanKhac.getSoTienThanhToan()));
							}
							// type = Constants.DINH_KEM_1_FILE;
							// fileDinhKemId = khKpKhac2ThanhToanKhac.getChungTuId();
							// objectId = khKpKhac2ThanhToanKhac.getId();
							// appCode = KhKpKhac2ThanhToanKhac.class.getSimpleName();
							// fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
							// khKpKhac2ThanhToanKhacData.setChungTuIds(fileDinhKem.getIds());
							// khKpKhac2ThanhToanKhacData.setFileDinhKem(fileDinhKem);
							kinhPhi2ThanhToanKhacDatas.add(kinhPhi2ThanhToanKhacData);
						}
					}
					nhiemVu2KinhPhiData.setKinhPhi2ThanhToanKhacDatas(kinhPhi2ThanhToanKhacDatas);
					nhiemVu2KinhPhiDatas.add(nhiemVu2KinhPhiData);
				}
			}
			keHoach2NhiemVuData.getNhiemVu2KinhPhiDatas().addAll(nhiemVu2KinhPhiDatas);
			// gia hạn
			List<NhiemVu2GiaHanData> nhiemVu2GiaHanDatas = new ArrayList<>();
			List<NhiemVu2GiaHan> nhiemVu2GiaHans = serviceNhiemVu2GiaHanService.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2GiaHans) && !nhiemVu2GiaHans.isEmpty()) {
				for (NhiemVu2GiaHan nhiemVu2GiaHan : nhiemVu2GiaHans) {
					NhiemVu2GiaHanData nhiemVu2GiaHanData = new NhiemVu2GiaHanData();
					nhiemVu2GiaHanData.setId(nhiemVu2GiaHan.getId());
					nhiemVu2GiaHanData.setKeHoach2NhiemVuId(nhiemVu2GiaHan.getNguoiGiaHanId());
					nhiemVu2GiaHanData.setNgayHoanThanh(nhiemVu2GiaHan.getNgayHoanThanh());
					nhiemVu2GiaHanData.setSoLanGiaHan(nhiemVu2GiaHan.getSoLanGiaHan());
					if (Objects.nonNull(nhiemVu2GiaHan.getNguoiGiaHanId())) {
						Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(nhiemVu2GiaHan.getNguoiGiaHanId());
						if (optionalDmCanBo.isPresent()) {
							nhiemVu2GiaHanData.setNguoiGiaHanId(optionalDmCanBo.get().getId());
							nhiemVu2GiaHanData.setNguoiGiaHanTen(optionalDmCanBo.get().getHoTen());
						}
					}
					nhiemVu2GiaHanData.setNgayHoanThanhLanDau(nhiemVu2GiaHan.getNgayHoanThanhLanDau());
					nhiemVu2GiaHanData.setNgayThucHienGiaHan(nhiemVu2GiaHan.getNgayThucHienGiaHan());
					nhiemVu2GiaHanDatas.add(nhiemVu2GiaHanData);
				}
			}
			keHoach2NhiemVuData.setNhiemVu2GiaHanDatas(nhiemVu2GiaHanDatas);
			// thông tin thực hiện
			List<NhiemVu2ThongTinThucHienData> nhiemVu2ThongTinThucHienDatas = new ArrayList<>();
			List<NhiemVu2ThongTinThucHien> nhiemVu2ThongTinThucHiens = serviceNhiemVu2ThongTinThucHienService
					.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
			if (Objects.nonNull(nhiemVu2ThongTinThucHiens) && !nhiemVu2ThongTinThucHiens.isEmpty()) {
				for (NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien : nhiemVu2ThongTinThucHiens) {
					NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData = new NhiemVu2ThongTinThucHienData();
					nhiemVu2ThongTinThucHienData.setId(nhiemVu2ThongTinThucHien.getId());
					nhiemVu2ThongTinThucHienData.setKeHoach2NhiemVuId(nhiemVu2ThongTinThucHien.getKeHoach2NhiemVuId());
					nhiemVu2ThongTinThucHienData.setKetQuaThucHien(nhiemVu2ThongTinThucHien.getKetQuaThucHien());
					nhiemVu2ThongTinThucHienData.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHien.getNgayThucHienCapNhat());
					nhiemVu2ThongTinThucHienData.setTienDo(nhiemVu2ThongTinThucHien.getTienDo());
					nhiemVu2ThongTinThucHienData.setIsDaGui(nhiemVu2ThongTinThucHien.getIsDaGui());
					nhiemVu2ThongTinThucHienData.setNgayHoanThanh(nhiemVu2ThongTinThucHien.getNgayHoanThanh());

					// type = Constants.DINH_KEM_NHIEU_FILE;
					// fileDinhKemId = null;
					// objectId = khNvKhac2ThongTinThucHien.getId();
					// appCode = KhNvKhac2ThongTinThucHien.class.getSimpleName();
					// fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
					// khNvKhac2ThongTinThucHienData.setFileDinhKemIds(fileDinhKem.getIds());
					// khNvKhac2ThongTinThucHienData.setFileDinhKem(fileDinhKem);
					nhiemVu2ThongTinThucHienDatas.add(nhiemVu2ThongTinThucHienData);
				}
			}
			keHoach2NhiemVuData.setNhiemVu2ThongTinThucHienDatas(nhiemVu2ThongTinThucHienDatas);
			keHoach2NhiemVuData.setStt(sttCon);
			int sapXepCon = 0;
			List<KeHoach2NhiemVuData> children = new ArrayList<>();
			List<KeHoach2NhiemVu> kNhiemVuCons = khNhiemVus.stream().filter(e -> Objects.nonNull(e.getNhiemVuChaId()))
					.filter(e -> e.getNhiemVuChaId().equals(keHoach2NhiemVu.getId())).collect(Collectors.toList());

			if (Objects.nonNull(kNhiemVuCons) && !kNhiemVuCons.isEmpty()) {
				children = setNhiemVuDataByKeHoachExport(isDaGui, kNhiemVuCons, isBanHanh, isThucHien, nguonKinhPhiIds, tinhTrangs,
						thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds,
						phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId, sapXepCon, sttCon, khNhiemVus);
			}
			keHoach2NhiemVuDatas.add(keHoach2NhiemVuData);
			keHoach2NhiemVuDatas.addAll(children);
		}

		return keHoach2NhiemVuDatas;
	}

	public KeHoachKhacData findById(Long id, Boolean isDaGui) throws EntityNotFoundException {
		Optional<KeHoachKhac> optional = serviceKeHoachKhacService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(id));
		}
		KeHoachKhac keHoachKhac = optional.get();
		return convertToKeHoachKhacData(isDaGui, keHoachKhac, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public KeHoachKhacData save(KeHoachKhacData keHoachKhacData) {
		KeHoachKhac keHoachKhac = new KeHoachKhac();
		if (Objects.nonNull(keHoachKhacData.getId())) {
			Optional<KeHoachKhac> optionalKeHoachKhac = serviceKeHoachKhacService.findById(keHoachKhacData.getId());
			if (optionalKeHoachKhac.isPresent()) {
				keHoachKhac = optionalKeHoachKhac.get();
			}
		}
		keHoachKhac.setDaXoa(false);
		String email = uaaUserService.getUsername();
		Long donViLapKhId = null;
		Long phongBanLapKhId = null;
		List<DmCanBo> listDmCanBo = dmCanBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		if (listDmCanBo != null && !listDmCanBo.isEmpty()) {
			DmCanBo dmCanBo = listDmCanBo.get(0);
			if (Objects.nonNull(dmCanBo.getDonVi())) {
				if (Constants.DON_VI_SU_DUNG != dmCanBo.getDonVi().getId()) {
					donViLapKhId = dmCanBo.getDonVi().getId();
				} else if (Objects.nonNull(dmCanBo.getPhongBan())) {
					phongBanLapKhId = dmCanBo.getPhongBan().getId();
				}
			}
		}
		if (Objects.isNull(keHoachKhac.getId())) {
			keHoachKhac.setDonViLapKhId(donViLapKhId);
			keHoachKhac.setPhongBanLapKhId(phongBanLapKhId);
		}
		keHoachKhac.setNam(keHoachKhacData.getNam());
		keHoachKhac.setTenKeHoach(keHoachKhacData.getTenKeHoach());
		keHoachKhac.setTongKinhPhi(keHoachKhacData.getTongKinhPhi());
		keHoachKhac.setTrangThai(keHoachKhacData.getTrangThai());
		keHoachKhac.setIsDieuChinh(keHoachKhacData.getIsDieuChinh());
		keHoachKhac.setIsLoaiNhiemVu(keHoachKhacData.getIsLoaiNhiemVu());
		keHoachKhac.setLoaiKeHoach(keHoachKhacData.getLoaiKeHoach());
		serviceKeHoachKhacService.save(keHoachKhac);
		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = keHoachKhacData.getKeHoach2NhiemVuDatas();
		serviceKeHoach2NhiemVuService.setFixedDaXoaForKeHoachKhacId(true, keHoachKhac.getId());
		if (Objects.nonNull(keHoach2NhiemVuDatas) && !keHoach2NhiemVuDatas.isEmpty()) {
			saveNhiemVuByKeHoach(keHoachKhac, keHoach2NhiemVuDatas, null, null);
		}
		// quyết định ban hành
		KeHoach2QuyetDinhData keHoach2QuyetDinhData = keHoachKhacData.getKeHoach2QuyetDinhData();
		if (Objects.nonNull(keHoach2QuyetDinhData)) {
			KeHoach2QuyetDinh keHoach2QuyetDinh = new KeHoach2QuyetDinh();
			List<KeHoach2QuyetDinh> keHoach2QuyetDinhs = serviceKeHoach2QuyetDinhService.findByIsHienTaiAndKeHoachKhacIdAndDaXoa(true,
					keHoachKhac.getId(), false);
			if (Objects.nonNull(keHoach2QuyetDinhs) && !keHoach2QuyetDinhs.isEmpty()) {
				keHoach2QuyetDinh = keHoach2QuyetDinhs.get(0);
			}
			keHoach2QuyetDinh.setDaXoa(false);
			keHoach2QuyetDinh.setKeHoachKhacId(keHoachKhac.getId());
			keHoach2QuyetDinh.setNgayBanHanh(keHoach2QuyetDinhData.getNgayBanHanh());
			keHoach2QuyetDinh.setSoQuyetDinh(keHoach2QuyetDinhData.getSoQuyetDinh());
			keHoach2QuyetDinh.setIsHienTai(true);
			keHoach2QuyetDinh = serviceKeHoach2QuyetDinhService.save(keHoach2QuyetDinh);
			serviceKeHoach2QuyetDinhService.setFixedIsHienTaiForKeHoachKhacId(true, keHoachKhac.getId());
			/*
			 * Begin đính kèm file
			 *******************************************************/

			/*
			 * Khởi tạo biến
			 * ************************************************************** -
			 * fileDinhKemIds: danh sách id file đã đính kèm
			 * **************************** - type: loại đính kèm
			 * (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) ************* -
			 * objectId: id đối tượng đính kèm
			 * ****************************************** - appCode: tên model
			 * của đối tượng đính kèm*********************************
			 */
			List<Long> fileDinhKemIds = keHoach2QuyetDinhData.getQuyetDinhIds();
			int type = Constants.DINH_KEM_1_FILE;
			long objectId = keHoach2QuyetDinh.getId();
			String appCode = KeHoach2QuyetDinh.class.getSimpleName();
			/*
			 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính
			 * kèm nhiều
			 */
			if (type == Constants.DINH_KEM_NHIEU_FILE) {
				coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
			}
			if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
				for (Long fileDinhKemId : fileDinhKemIds) {
					CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

					/*
					 * set db nếu có trường lưu và chuyển file từ temp sang thư
					 * mục chính
					 */
					if (coreAttachment.getId() > 0) {

						keHoach2QuyetDinh.setQuyetDinhId(coreAttachment.getId());
						serviceKeHoach2QuyetDinhService.save(keHoach2QuyetDinh);
						coreAttachmentBusiness.saveAndMove(coreAttachment);
					}

					/* thoát nếu đính kèm 1 file */
					if (type == Constants.DINH_KEM_1_FILE) {
						break;
					}
				}
			}
			/*
			 * End đính kèm file
			 **********************************************************/
		}

		return convertToKeHoachKhacData(false, keHoachKhac, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public void saveNhiemVuByKeHoach(KeHoachKhac keHoachKhac, List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas, Long nhiemVuChaId,
			Long loaiNhiemVuId) {
		int sapXep = 0;
		for (KeHoach2NhiemVuData keHoach2NhiemVuData : keHoach2NhiemVuDatas) {
			sapXep++;
			KeHoach2NhiemVu keHoach2NhiemVu = new KeHoach2NhiemVu();
			if (Objects.nonNull(keHoach2NhiemVuData.getId())) {
				Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVuKhac = serviceKeHoach2NhiemVuService.findById(keHoach2NhiemVuData.getId());
				if (optionalKeHoach2NhiemVuKhac.isPresent()) {
					keHoach2NhiemVu = optionalKeHoach2NhiemVuKhac.get();
				}
			}
			keHoach2NhiemVu.setDaXoa(false);
			keHoach2NhiemVu.setKeHoachKhacId(keHoachKhac.getId());
			keHoach2NhiemVu.setNhiemVuChaId(nhiemVuChaId);
			keHoach2NhiemVu.setDmLoaiNhiemVuId(null);
			keHoach2NhiemVu.setSapXep(sapXep);
			if (Objects.nonNull(keHoach2NhiemVuData.getDmLoaiNhiemVuId())) {
				Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(keHoach2NhiemVuData.getDmLoaiNhiemVuId());
				if (optionalDmLoaiNhiemVu.isPresent()) {
					keHoach2NhiemVu.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
					loaiNhiemVuId = optionalDmLoaiNhiemVu.get().getId();
				}
			} else if (Objects.nonNull(loaiNhiemVuId)) {
				Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(loaiNhiemVuId);
				if (optionalDmLoaiNhiemVu.isPresent()) {
					keHoach2NhiemVu.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
				}
			}
			keHoach2NhiemVu.setNoiDung(keHoach2NhiemVuData.getNoiDung());
			keHoach2NhiemVu.setThoiGianThucHienTuNgay(keHoach2NhiemVuData.getThoiGianThucHienTuNgay());
			keHoach2NhiemVu.setThoiGianThucHienDenNgay(keHoach2NhiemVuData.getThoiGianThucHienDenNgay());
			keHoach2NhiemVu.setThoiGianThanhToan(keHoach2NhiemVuData.getThoiGianThanhToan());
			keHoach2NhiemVu.setGhiChu(keHoach2NhiemVuData.getGhiChu());
			if (Objects.nonNull(keHoach2NhiemVuData.getIsDaGui())) {
				keHoach2NhiemVu.setIsDaGui(keHoach2NhiemVuData.getIsDaGui());
			} else {
				keHoach2NhiemVu.setIsDaGui(false);
			}
			keHoach2NhiemVu.setTinhTrang(keHoach2NhiemVuData.getTinhTrang());
			keHoach2NhiemVu.setLanhDaoId(null);
			if (Objects.nonNull(keHoach2NhiemVuData.getLanhDaoId())) {
				Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(keHoach2NhiemVuData.getLanhDaoId());
				if (optionalDmCanBo.isPresent()) {
					keHoach2NhiemVu.setLanhDaoId(optionalDmCanBo.get().getId());
				}
			}
			if (Objects.nonNull(keHoach2NhiemVuData.getIsBanHanh())) {
				keHoach2NhiemVu.setIsBanHanh(keHoach2NhiemVuData.getIsBanHanh());
			} else {
				keHoach2NhiemVu.setIsBanHanh(false);
			}
			if (Objects.nonNull(keHoach2NhiemVuData.getIsThemMoiThucHien())) {
				keHoach2NhiemVu.setIsThemMoiThucHien(keHoach2NhiemVuData.getIsThemMoiThucHien());
			} else {
				keHoach2NhiemVu.setIsThemMoiThucHien(false);
			}
			if (Boolean.FALSE.equals(keHoach2NhiemVu.getIsThemMoiThucHien())) {
				keHoach2NhiemVu.setIsBanHanh(true);
			}
			keHoach2NhiemVu.setDonViThucHienId(null);
			if (Objects.nonNull(keHoach2NhiemVuData.getDonViThucHienId())) {
				Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoach2NhiemVuData.getDonViThucHienId());
				if (optionalDmDonVi.isPresent()) {
					keHoach2NhiemVu.setDonViThucHienId(optionalDmDonVi.get().getId());
				}
			}
			keHoach2NhiemVu.setPhongBanThucHienId(null);
			if (Objects.nonNull(keHoach2NhiemVuData.getPhongBanThucHienId())) {
				Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoach2NhiemVuData.getPhongBanThucHienId());
				if (optionalDmPhongBan.isPresent()) {
					keHoach2NhiemVu.setPhongBanThucHienId(optionalDmPhongBan.get().getId());
				}
			}
			keHoach2NhiemVu.setSanPhamDauRa(keHoach2NhiemVuData.getSanPhamDauRa());
			keHoach2NhiemVu = serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);
			serviceNhiemVu2SanPhamDauRaService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
			/*
			 * Begin đính kèm file
			 *******************************************************/

			/*
			 * Khởi tạo biến
			 * ************************************************************** -
			 * fileDinhKemIds: danh sách id file đã đính kèm
			 * **************************** - type: loại đính kèm
			 * (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) ************* -
			 * objectId: id đối tượng đính kèm
			 * ****************************************** - appCode: tên model
			 * của đối tượng đính kèm*********************************
			 */
			List<Long> fileDinhKemIds = keHoach2NhiemVuData.getFileDinhKemIds();
			int type = Constants.DINH_KEM_NHIEU_FILE;
			long objectId = keHoach2NhiemVu.getId();
			String appCode = KeHoach2NhiemVu.class.getSimpleName();
			/*
			 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính
			 * kèm nhiều
			 */
			if (type == Constants.DINH_KEM_NHIEU_FILE) {
				coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
			}
			if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
				for (Long fileDinhKemId : fileDinhKemIds) {
					CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

					/*
					 * set db nếu có trường lưu và chuyển file từ temp sang thư
					 * mục chính
					 */
					if (coreAttachment.getId() > 0) {
						NhiemVu2SanPhamDauRa nhiemVu2SanPhamDauRa = new NhiemVu2SanPhamDauRa();
						Optional<NhiemVu2SanPhamDauRa> optionalNhiemVu2SanPhamDauRa = serviceNhiemVu2SanPhamDauRaService
								.findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(keHoach2NhiemVu.getId(), coreAttachment.getId());
						if (optionalNhiemVu2SanPhamDauRa.isPresent()) {
							nhiemVu2SanPhamDauRa = optionalNhiemVu2SanPhamDauRa.get();
						}
						nhiemVu2SanPhamDauRa.setDaXoa(false);
						nhiemVu2SanPhamDauRa.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
						nhiemVu2SanPhamDauRa.setSanPhamDinhKemId(coreAttachment.getId());
						serviceNhiemVu2SanPhamDauRaService.save(nhiemVu2SanPhamDauRa);
						coreAttachmentBusiness.saveAndMove(coreAttachment);
					}

					/* thoát nếu đính kèm 1 file */
					if (type == Constants.DINH_KEM_1_FILE) {
						break;
					}
				}
			}
			/*
			 * End đính kèm file
			 **********************************************************/

			// đơn vị phối hợp
			serviceNhiemVu2DonViPhoiHopService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
			List<Long> donViPhoiHopIds = keHoach2NhiemVuData.getDonViPhoiHopIds();
			List<Long> phongBanPhoiHopIds = keHoach2NhiemVuData.getPhongBanPhoiHopIds();

			if (donViPhoiHopIds != null && !donViPhoiHopIds.isEmpty()) {
				for (Long donViPhoiHopId : donViPhoiHopIds) {
					if (Objects.nonNull(donViPhoiHopId)) {
						Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(donViPhoiHopId);
						if (optionalDmDonVi.isPresent()) {
							NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop = new NhiemVu2DonViPhoiHop();
							List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
									.findByKeHoach2NhiemVuIdAndDonViId(keHoach2NhiemVu.getId(), donViPhoiHopId);
							if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
								nhiemVu2DonViPhoiHop = nhiemVu2DonViPhoiHops.get(0);
							}
							nhiemVu2DonViPhoiHop.setDaXoa(false);
							nhiemVu2DonViPhoiHop.setDonViId(optionalDmDonVi.get().getId());
							nhiemVu2DonViPhoiHop.setPhongBanId(null);
							nhiemVu2DonViPhoiHop.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
							serviceNhiemVu2DonViPhoiHopService.save(nhiemVu2DonViPhoiHop);
						}
					}
				}
			}

			if (phongBanPhoiHopIds != null && !phongBanPhoiHopIds.isEmpty()) {
				for (Long phongBanPhoiHopId : phongBanPhoiHopIds) {
					if (Objects.nonNull(phongBanPhoiHopId)) {
						Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(phongBanPhoiHopId);
						if (optionalDmPhongBan.isPresent()) {
							NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop = new NhiemVu2DonViPhoiHop();
							List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
									.findByKeHoach2NhiemVuIdAndPhongBanId(keHoach2NhiemVu.getId(), phongBanPhoiHopId);
							if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
								nhiemVu2DonViPhoiHop = nhiemVu2DonViPhoiHops.get(0);
							}
							nhiemVu2DonViPhoiHop.setDaXoa(false);
							nhiemVu2DonViPhoiHop.setDonViId(null);
							nhiemVu2DonViPhoiHop.setPhongBanId(optionalDmPhongBan.get().getId());
							nhiemVu2DonViPhoiHop.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
							serviceNhiemVu2DonViPhoiHopService.save(nhiemVu2DonViPhoiHop);
						}
					}
				}
			}
			// check trạng xác nhận đã duyệt ko cập nhật dữ liệu

			// kinhphi
			serviceNhiemVu2KinhPhiService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
			List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiDatas = keHoach2NhiemVuData.getNhiemVu2KinhPhiDatas();
			if (Objects.nonNull(nhiemVu2KinhPhiDatas) && !nhiemVu2KinhPhiDatas.isEmpty()) {
				for (NhiemVu2KinhPhiData nhiemVu2KinhPhiData : nhiemVu2KinhPhiDatas) {
					NhiemVu2KinhPhi nhiemVu2KinhPhi = new NhiemVu2KinhPhi();
					if (Objects.nonNull(nhiemVu2KinhPhiData.getId())) {
						Optional<NhiemVu2KinhPhi> optionalNhiemVu2KinhPhi = serviceNhiemVu2KinhPhiService
								.findById(nhiemVu2KinhPhiData.getId());
						if (optionalNhiemVu2KinhPhi.isPresent()) {
							nhiemVu2KinhPhi = optionalNhiemVu2KinhPhi.get();
						}
					}
					nhiemVu2KinhPhi.setDaXoa(false);
					nhiemVu2KinhPhi.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
					nhiemVu2KinhPhi.setSoTien(null);
					if (Objects.nonNull(nhiemVu2KinhPhiData.getSoTien()) && !nhiemVu2KinhPhiData.getSoTien().isEmpty()) {
						String soTien = nhiemVu2KinhPhiData.getSoTien().replace(".", "");
						nhiemVu2KinhPhi.setSoTien(Double.valueOf(soTien));
					}
					nhiemVu2KinhPhi.setSoTienDieuChinh(null);
					if (Objects.nonNull(nhiemVu2KinhPhiData.getSoTienDieuChinh()) && !nhiemVu2KinhPhiData.getSoTienDieuChinh().isEmpty()) {
						String soTienDieuChinh = nhiemVu2KinhPhiData.getSoTienDieuChinh().replace(".", "");
						nhiemVu2KinhPhi.setSoTienDieuChinh(Double.valueOf(soTienDieuChinh));
					}
					nhiemVu2KinhPhi.setTangGiamKinhPhi(nhiemVu2KinhPhiData.getTangGiamKinhPhi());
					nhiemVu2KinhPhi.setDmNguonKinhPhiId(null);
					if (Objects.nonNull(nhiemVu2KinhPhiData.getDmNguonKinhPhiId())) {
						Optional<DmNguonKinhPhi> optionalDmNguonKinhPhi = dmNguonKinhPhiService
								.findById(nhiemVu2KinhPhiData.getDmNguonKinhPhiId());
						if (optionalDmNguonKinhPhi.isPresent()) {
							nhiemVu2KinhPhi.setDmNguonKinhPhiId(optionalDmNguonKinhPhi.get().getId());
						}
					}
					nhiemVu2KinhPhi = serviceNhiemVu2KinhPhiService.save(nhiemVu2KinhPhi);

					// thanh toán
					serviceKinhPhi2ThanhToanKhacService.setFixedDaXoaForNhiemVu2KinhPhiId(true, nhiemVu2KinhPhi.getId());
					List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = nhiemVu2KinhPhiData.getKinhPhi2ThanhToanKhacDatas();
					if (Objects.nonNull(kinhPhi2ThanhToanKhacDatas) && !kinhPhi2ThanhToanKhacDatas.isEmpty()) {
						for (KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData : kinhPhi2ThanhToanKhacDatas) {
							KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac = new KinhPhi2ThanhToanKhac();
							if (Objects.nonNull(kinhPhi2ThanhToanKhac.getId())) {
								Optional<KinhPhi2ThanhToanKhac> optionalKhKpKhac2ThanhToanKhac = serviceKinhPhi2ThanhToanKhacService
										.findById(kinhPhi2ThanhToanKhac.getId());
								if (optionalKhKpKhac2ThanhToanKhac.isPresent()) {
									kinhPhi2ThanhToanKhac = optionalKhKpKhac2ThanhToanKhac.get();
								}
							}
							kinhPhi2ThanhToanKhac.setDaXoa(false);
							kinhPhi2ThanhToanKhac.setNhiemVu2KinhPhiId(nhiemVu2KinhPhiData.getId());
							kinhPhi2ThanhToanKhac.setNgayThanhToan(kinhPhi2ThanhToanKhacData.getNgayThanhToan());
							kinhPhi2ThanhToanKhac.setSoTienThanhToan(null);
							if (Objects.nonNull(kinhPhi2ThanhToanKhacData.getSoTienThanhToan())
									&& !kinhPhi2ThanhToanKhacData.getSoTienThanhToan().isEmpty()) {
								String soTienThanhToan = kinhPhi2ThanhToanKhacData.getSoTienThanhToan().replace(".", "");
								Double soTien = Double.valueOf(soTienThanhToan);
								kinhPhi2ThanhToanKhac.setSoTienThanhToan(soTien);
							}
							serviceKinhPhi2ThanhToanKhacService.save(kinhPhi2ThanhToanKhac);

							/*
							 * Begin đính kèm file
							 *******************************************************/

							/*
							 * Khởi tạo biến
							 * *************************************************
							 * ************* - fileDinhKemIds: danh sách id file
							 * đã đính kèm **************************** - type:
							 * loại đính kèm (DINH_KEM_1_FILE ||
							 * DINH_KEM_NHIEU_FILE) ************* - objectId: id
							 * đối tượng đính kèm
							 * ****************************************** -
							 * appCode: tên model của đối tượng đính
							 * kèm*********************************
							 */
							fileDinhKemIds = kinhPhi2ThanhToanKhacData.getChungTuIds();
							type = Constants.DINH_KEM_1_FILE;
							objectId = kinhPhi2ThanhToanKhacData.getId();
							appCode = KinhPhi2ThanhToanKhacData.class.getSimpleName();
							/*
							 * Xóa mềm đính kèm cũ nếu có trước khi set file
							 * đính kèm nếu đính kèm nhiều
							 */
							if (type == Constants.DINH_KEM_NHIEU_FILE) {
								coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
							}
							if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
								for (Long fileDinhKemId : fileDinhKemIds) {
									CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

									/*
									 * set db nếu có trường lưu và chuyển file
									 * từ temp sang thư mục chính
									 */
									if (coreAttachment.getId() > 0) {
										kinhPhi2ThanhToanKhac.setChungTuId(coreAttachment.getId());
										serviceKinhPhi2ThanhToanKhacService.save(kinhPhi2ThanhToanKhac);
										coreAttachmentBusiness.saveAndMove(coreAttachment);
										log.info("coreAttachment id :" + coreAttachment.getId());
									}

									/* thoát nếu đính kèm 1 file */
									if (type == Constants.DINH_KEM_1_FILE) {
										break;
									}
								}
							}
							/*
							 * End đính kèm file
							 **********************************************************/
						}
					}
				}
			}

			// gia hạn
			List<NhiemVu2GiaHanData> nhiemVu2GiaHanDatas = keHoach2NhiemVuData.getNhiemVu2GiaHanDatas();
			serviceNhiemVu2GiaHanService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
			if (Objects.nonNull(nhiemVu2GiaHanDatas) && !nhiemVu2GiaHanDatas.isEmpty()) {
				for (NhiemVu2GiaHanData nhiemVu2GiaHanData : nhiemVu2GiaHanDatas) {
					NhiemVu2GiaHan nhiemVu2GiaHan = new NhiemVu2GiaHan();
					if (Objects.nonNull(nhiemVu2GiaHanData.getId())) {
						Optional<NhiemVu2GiaHan> optionalNhiemVu2GiaHan = serviceNhiemVu2GiaHanService.findById(nhiemVu2GiaHanData.getId());
						if (optionalNhiemVu2GiaHan.isPresent()) {
							nhiemVu2GiaHan = optionalNhiemVu2GiaHan.get();
						}
					}
					nhiemVu2GiaHan.setDaXoa(false);
					nhiemVu2GiaHan.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
					nhiemVu2GiaHan.setNgayHoanThanh(nhiemVu2GiaHanData.getNgayHoanThanh());
					nhiemVu2GiaHan.setSoLanGiaHan(nhiemVu2GiaHanData.getSoLanGiaHan());
					nhiemVu2GiaHan.setNguoiGiaHanId(null);
					if (Objects.nonNull(nhiemVu2GiaHanData.getNguoiGiaHanId())) {
						Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(nhiemVu2GiaHanData.getNguoiGiaHanId());
						if (optionalDmCanBo.isPresent()) {
							nhiemVu2GiaHan.setNguoiGiaHanId(optionalDmCanBo.get().getId());
						}
					}
					nhiemVu2GiaHan.setNgayHoanThanhLanDau(nhiemVu2GiaHanData.getNgayHoanThanhLanDau());
					nhiemVu2GiaHan.setNgayThucHienGiaHan(nhiemVu2GiaHanData.getNgayThucHienGiaHan());
					serviceNhiemVu2GiaHanService.save(nhiemVu2GiaHan);
				}
			}
			// thông tin thực hiện
			List<NhiemVu2ThongTinThucHienData> nhiemVu2ThongTinThucHienDatas = keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas();
			serviceNhiemVu2ThongTinThucHienService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVuData.getId());
			if (Objects.nonNull(nhiemVu2ThongTinThucHienDatas) && !nhiemVu2ThongTinThucHienDatas.isEmpty()) {
				for (NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData : nhiemVu2ThongTinThucHienDatas) {
					NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien = new NhiemVu2ThongTinThucHien();
					if (Objects.nonNull(nhiemVu2ThongTinThucHienData.getId())) {
						Optional<NhiemVu2ThongTinThucHien> optionalNhiemVu2ThongTinThucHien = serviceNhiemVu2ThongTinThucHienService
								.findById(nhiemVu2ThongTinThucHienData.getId());
						if (optionalNhiemVu2ThongTinThucHien.isPresent()) {
							nhiemVu2ThongTinThucHien = optionalNhiemVu2ThongTinThucHien.get();
						}
					}
					nhiemVu2ThongTinThucHien.setDaXoa(false);
					nhiemVu2ThongTinThucHien.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
					nhiemVu2ThongTinThucHien.setKetQuaThucHien(nhiemVu2ThongTinThucHienData.getKetQuaThucHien());
					nhiemVu2ThongTinThucHien.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHienData.getNgayThucHienCapNhat());
					nhiemVu2ThongTinThucHien.setTienDo(nhiemVu2ThongTinThucHienData.getTienDo());
					nhiemVu2ThongTinThucHien.setIsDaGui(nhiemVu2ThongTinThucHienData.getIsDaGui());
					nhiemVu2ThongTinThucHien.setNgayHoanThanh(nhiemVu2ThongTinThucHienData.getNgayHoanThanh());
					nhiemVu2ThongTinThucHien = serviceNhiemVu2ThongTinThucHienService.save(nhiemVu2ThongTinThucHien);
					serviceThucHien2VanBanLienQuanService.setFixedDaXoaForNhiemVu2ThucHienId(true, nhiemVu2ThongTinThucHien.getId());
					/*
					 * Begin đính kèm file
					 *******************************************************/

					/*
					 * Khởi tạo biến
					 * *********************************************************
					 * ***** - fileDinhKemIds: danh sách id file đã đính kèm
					 * **************************** - type: loại đính kèm
					 * (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) ************* -
					 * objectId: id đối tượng đính kèm
					 * ****************************************** - appCode: tên
					 * model của đối tượng đính
					 * kèm*********************************
					 */
					fileDinhKemIds = nhiemVu2ThongTinThucHienData.getFileDinhKemIds();
					type = Constants.DINH_KEM_NHIEU_FILE;
					objectId = nhiemVu2ThongTinThucHien.getId();
					appCode = NhiemVu2ThongTinThucHien.class.getSimpleName();
					/*
					 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm
					 * nếu đính kèm nhiều
					 */
					if (type == Constants.DINH_KEM_NHIEU_FILE) {
						coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
					}
					if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
						for (Long fileDinhKemId : fileDinhKemIds) {
							CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

							/*
							 * set db nếu có trường lưu và chuyển file từ temp
							 * sang thư mục chính
							 */
							if (coreAttachment.getId() > 0) {
								ThucHien2VanBanLienQuan thucHien2VanBanLienQuan = new ThucHien2VanBanLienQuan();
								Optional<ThucHien2VanBanLienQuan> optionalThucHien2VanBanLienQuan = serviceThucHien2VanBanLienQuanService
										.findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(nhiemVu2ThongTinThucHien.getId(), coreAttachment.getId());
								if (optionalThucHien2VanBanLienQuan.isPresent()) {
									thucHien2VanBanLienQuan = optionalThucHien2VanBanLienQuan.get();
								}
								thucHien2VanBanLienQuan.setDaXoa(false);
								thucHien2VanBanLienQuan.setNhiemVu2ThucHienId(nhiemVu2ThongTinThucHien.getId());
								thucHien2VanBanLienQuan.setVanBanDinhKemId(coreAttachment.getId());
								serviceThucHien2VanBanLienQuanService.save(thucHien2VanBanLienQuan);

								coreAttachmentBusiness.saveAndMove(coreAttachment);
								log.info("coreAttachment id :" + coreAttachment.getId());
							}

							/* thoát nếu đính kèm 1 file */
							if (type == Constants.DINH_KEM_1_FILE) {
								break;
							}
						}
					}
					/*
					 * End đính kèm file
					 **********************************************************/
				}
			}

			if (Objects.nonNull(keHoach2NhiemVuData.getChildren()) && !keHoach2NhiemVuData.getChildren().isEmpty()) {
				saveNhiemVuByKeHoach(keHoachKhac, keHoach2NhiemVuData.getChildren(), keHoach2NhiemVuData.getId(), loaiNhiemVuId);
			}
		}
	}

	public KeHoach2QuyetDinhData banHanh(Long id, KeHoach2QuyetDinhData keHoach2QuyetDinhData) throws EntityNotFoundException {
		Optional<KeHoachKhac> optional = serviceKeHoachKhacService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(id));
		}
		KeHoachKhac keHoachKhac = optional.get();
		KeHoach2QuyetDinh keHoach2QuyetDinh = new KeHoach2QuyetDinh();
		keHoach2QuyetDinh.setDaXoa(false);
		keHoach2QuyetDinh.setKeHoachKhacId(keHoachKhac.getId());
		keHoach2QuyetDinh.setNgayBanHanh(keHoach2QuyetDinhData.getNgayBanHanh());
		keHoach2QuyetDinh.setSoQuyetDinh(keHoach2QuyetDinhData.getSoQuyetDinh());
		keHoach2QuyetDinh.setIsHienTai(true);
		keHoach2QuyetDinh = serviceKeHoach2QuyetDinhService.save(keHoach2QuyetDinh);
		serviceKeHoach2QuyetDinhService.setFixedIsHienTaiForKeHoachKhacId(true, keHoachKhac.getId());
		/*
		 * Begin đính kèm file
		 *******************************************************/

		/*
		 * Khởi tạo biến
		 * ************************************************************** -
		 * fileDinhKemIds: danh sách id file đã đính kèm
		 * **************************** - type: loại đính kèm (DINH_KEM_1_FILE
		 * || DINH_KEM_NHIEU_FILE) ************* - objectId: id đối tượng đính
		 * kèm ****************************************** - appCode: tên model
		 * của đối tượng đính kèm*********************************
		 */
		List<Long> fileDinhKemIds = keHoach2QuyetDinhData.getQuyetDinhIds();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = keHoach2QuyetDinh.getId();
		String appCode = KeHoach2QuyetDinh.class.getSimpleName();
		/*
		 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm
		 * nhiều
		 */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/*
				 * set db nếu có trường lưu và chuyển file từ temp sang thư mục
				 * chính
				 */
				if (coreAttachment.getId() > 0) {

					keHoach2QuyetDinh.setQuyetDinhId(coreAttachment.getId());
					serviceKeHoach2QuyetDinhService.save(keHoach2QuyetDinh);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/*
		 * End đính kèm file
		 **********************************************************/

		// set so tiền điều chỉnh
		List<KeHoach2NhiemVu> keHoach2NhiemVus = serviceKeHoach2NhiemVuService.findByKeHoachKhacIdAndDaXoa(keHoachKhac.getId(), false);
		if (Objects.nonNull(keHoach2NhiemVus) && !keHoach2NhiemVus.isEmpty()) {
			for (KeHoach2NhiemVu keHoach2NhiemVu : keHoach2NhiemVus) {
				if (Boolean.FALSE.equals(keHoach2NhiemVu.getIsThemMoiThucHien())) {
					keHoach2NhiemVu.setIsBanHanh(true);
				}
				serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);

				// set so tiền điều chỉnh
				List<NhiemVu2KinhPhi> nhiemVu2KinhPhis = serviceNhiemVu2KinhPhiService
						.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);
				if (Objects.nonNull(nhiemVu2KinhPhis) && !nhiemVu2KinhPhis.isEmpty()) {
					for (NhiemVu2KinhPhi nhiemVu2KinhPhi : nhiemVu2KinhPhis) {
						nhiemVu2KinhPhi.setSoTienDieuChinh(nhiemVu2KinhPhi.getSoTien());
						serviceNhiemVu2KinhPhiService.save(nhiemVu2KinhPhi);
					}
				}
			}
		}
		return keHoach2QuyetDinhData;
	}

	public FileDinhKem getFileDinhKemBanHanh(Long id) throws EntityNotFoundException {
		Optional<KeHoachKhac> optional = serviceKeHoachKhacService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(id));
		}
		FileDinhKem fileDinhKem = new FileDinhKem();
		List<KeHoach2QuyetDinh> keHoach2QuyetDinhs = serviceKeHoach2QuyetDinhService.findByIsHienTaiAndKeHoachKhacIdAndDaXoa(true, id, false);
		if (Objects.nonNull(keHoach2QuyetDinhs) && !keHoach2QuyetDinhs.isEmpty()) {
			KeHoach2QuyetDinh keHoach2QuyetDinh = keHoach2QuyetDinhs.get(0);
			int type = Constants.DINH_KEM_1_FILE;
			Long fileDinhKemId = keHoach2QuyetDinh.getQuyetDinhId();
			Long objectId = keHoach2QuyetDinh.getId();
			String appCode = KeHoach2QuyetDinh.class.getSimpleName();
			fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
		}
		return fileDinhKem;
	}

	public void saveIsDaGui(Long nhiemVuId) throws EntityNotFoundException {
		Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVu = serviceKeHoach2NhiemVuService.findById(nhiemVuId);
		if (!optionalKeHoach2NhiemVu.isPresent()) {
			throw new EntityNotFoundException(KeHoach2NhiemVu.class, "id", String.valueOf(nhiemVuId));
		}
		KeHoach2NhiemVu keHoach2NhiemVu = optionalKeHoach2NhiemVu.get();
		keHoach2NhiemVu.setDaXoa(false);
		keHoach2NhiemVu.setIsDaGui(true);
		serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);
	}

	public NhiemVu2GiaHanData saveGiaHan(Long nhiemVuId, NhiemVu2GiaHanData nhiemVu2GiaHanData) throws EntityNotFoundException {
		Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVu = serviceKeHoach2NhiemVuService.findById(nhiemVuId);
		if (!optionalKeHoach2NhiemVu.isPresent()) {
			throw new EntityNotFoundException(KeHoach2NhiemVu.class, "id", String.valueOf(nhiemVuId));
		}
		KeHoach2NhiemVu keHoach2NhiemVu = optionalKeHoach2NhiemVu.get();
		keHoach2NhiemVu.setDaXoa(false);
		List<NhiemVu2GiaHan> nhiemVu2GiaHans = serviceNhiemVu2GiaHanService.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVu.getId(), false);

		NhiemVu2GiaHan nhiemVu2GiaHan = new NhiemVu2GiaHan();
		nhiemVu2GiaHan.setDaXoa(false);
		nhiemVu2GiaHan.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
		nhiemVu2GiaHan.setNgayHoanThanh(nhiemVu2GiaHanData.getNgayHoanThanh());
		nhiemVu2GiaHan.setNgayThucHienGiaHan(LocalDate.now());
		String email = uaaUserService.getUsername();
		List<DmCanBo> listDmCanBo = dmCanBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		if (listDmCanBo != null && !listDmCanBo.isEmpty()) {
			DmCanBo dmCanBo = listDmCanBo.get(0);
			nhiemVu2GiaHan.setNguoiGiaHanId(dmCanBo.getId());
		}
		if (Objects.nonNull(nhiemVu2GiaHans) && !nhiemVu2GiaHans.isEmpty()) {
			nhiemVu2GiaHan.setNgayHoanThanhLanDau(nhiemVu2GiaHans.get(0).getNgayHoanThanhLanDau());
		} else {
			nhiemVu2GiaHan.setNgayHoanThanhLanDau(keHoach2NhiemVu.getThoiGianThucHienDenNgay());
		}
		serviceNhiemVu2GiaHanService.save(nhiemVu2GiaHan);
		keHoach2NhiemVu.setThoiGianThucHienDenNgay(nhiemVu2GiaHanData.getNgayHoanThanh());
		serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);
		return convertToNhiemVu2GiaHanData(nhiemVu2GiaHan);
	}

	private NhiemVu2GiaHanData convertToNhiemVu2GiaHanData(NhiemVu2GiaHan nhiemVu2GiaHan) {
		NhiemVu2GiaHanData nhiemVu2GiaHanData = new NhiemVu2GiaHanData();
		nhiemVu2GiaHanData.setId(nhiemVu2GiaHan.getId());
		nhiemVu2GiaHanData.setNgayHoanThanh(nhiemVu2GiaHan.getNgayHoanThanh());
		nhiemVu2GiaHanData.setNgayHoanThanhLanDau(nhiemVu2GiaHan.getNgayHoanThanhLanDau());
		nhiemVu2GiaHanData.setNgayThucHienGiaHan(nhiemVu2GiaHan.getNgayThucHienGiaHan());
		nhiemVu2GiaHanData.setKeHoach2NhiemVuId(nhiemVu2GiaHan.getKeHoach2NhiemVuId());
		nhiemVu2GiaHanData.setSoLanGiaHan(nhiemVu2GiaHan.getSoLanGiaHan());
		if (Objects.nonNull(nhiemVu2GiaHan.getNguoiGiaHanId())) {
			Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(nhiemVu2GiaHan.getNguoiGiaHanId());
			if (optionalDmCanBo.isPresent()) {
				nhiemVu2GiaHanData.setNguoiGiaHanId(optionalDmCanBo.get().getId());
				nhiemVu2GiaHanData.setNguoiGiaHanTen(optionalDmCanBo.get().getHoTen());
			}
		}
		return nhiemVu2GiaHanData;
	}

	public NhiemVu2ThongTinThucHienData saveThongTinThucHien(Long nhiemVuId, NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData)
			throws EntityNotFoundException {
		Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVu = serviceKeHoach2NhiemVuService.findById(nhiemVuId);
		if (!optionalKeHoach2NhiemVu.isPresent()) {
			throw new EntityNotFoundException(KeHoach2NhiemVu.class, "id", String.valueOf(nhiemVuId));
		}
		KeHoach2NhiemVu khKhKhac2NhiemVuKhac = optionalKeHoach2NhiemVu.get();

		NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien = new NhiemVu2ThongTinThucHien();
		if (Objects.nonNull(nhiemVu2ThongTinThucHienData.getId())) {
			Optional<NhiemVu2ThongTinThucHien> optionalNhiemVu2ThongTinThucHien = serviceNhiemVu2ThongTinThucHienService
					.findById(nhiemVu2ThongTinThucHienData.getId());
			if (optionalNhiemVu2ThongTinThucHien.isPresent()) {
				nhiemVu2ThongTinThucHien = optionalNhiemVu2ThongTinThucHien.get();
			}
		}
		nhiemVu2ThongTinThucHien.setDaXoa(false);
		nhiemVu2ThongTinThucHien.setKeHoach2NhiemVuId(khKhKhac2NhiemVuKhac.getId());
		nhiemVu2ThongTinThucHien.setKetQuaThucHien(nhiemVu2ThongTinThucHienData.getKetQuaThucHien());
		nhiemVu2ThongTinThucHien.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHienData.getNgayThucHienCapNhat());
		nhiemVu2ThongTinThucHien.setTienDo(nhiemVu2ThongTinThucHienData.getTienDo());
		return convertToNhiemVu2ThongTinThucHienData(nhiemVu2ThongTinThucHien);
	}

	private NhiemVu2ThongTinThucHienData convertToNhiemVu2ThongTinThucHienData(NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien) {
		NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData = new NhiemVu2ThongTinThucHienData();
		nhiemVu2ThongTinThucHienData.setId(nhiemVu2ThongTinThucHien.getId());
		nhiemVu2ThongTinThucHienData.setKeHoach2NhiemVuId(nhiemVu2ThongTinThucHien.getKeHoach2NhiemVuId());
		nhiemVu2ThongTinThucHienData.setKetQuaThucHien(nhiemVu2ThongTinThucHien.getKetQuaThucHien());
		nhiemVu2ThongTinThucHienData.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHien.getNgayThucHienCapNhat());
		nhiemVu2ThongTinThucHienData.setTienDo(nhiemVu2ThongTinThucHien.getTienDo());
		nhiemVu2ThongTinThucHienData.setNgayHoanThanh(nhiemVu2ThongTinThucHien.getNgayHoanThanh());

		int type = Constants.DINH_KEM_NHIEU_FILE;
		Long fileDinhKemId = null;
		Long objectId = nhiemVu2ThongTinThucHien.getId();
		String appCode = NhiemVu2ThongTinThucHien.class.getSimpleName();
		FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
		nhiemVu2ThongTinThucHienData.setFileDinhKemIds(fileDinhKem.getIds());
		nhiemVu2ThongTinThucHienData.setFileDinhKem(fileDinhKem);
		return nhiemVu2ThongTinThucHienData;
	}

	public void deleteNhiemVu(Long nhiemVuId) throws EntityNotFoundException {
		Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVu = serviceKeHoach2NhiemVuService.findById(nhiemVuId);
		if (!optionalKeHoach2NhiemVu.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(nhiemVuId));
		}
		KeHoach2NhiemVu keHoach2NhiemVu = optionalKeHoach2NhiemVu.get();
		keHoach2NhiemVu.setDaXoa(true);
		serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);
	}

	public Long saveNhiemVu(KeHoach2NhiemVuData keHoach2NhiemVuData, Long keHoachKhacId, Long nhiemVuChaId, Long loaiNhiemVuId) {
		KeHoach2NhiemVu keHoach2NhiemVu = new KeHoach2NhiemVu();
		if (Objects.nonNull(keHoach2NhiemVuData.getId())) {
			Optional<KeHoach2NhiemVu> optionalKeHoach2NhiemVu = serviceKeHoach2NhiemVuService.findById(keHoach2NhiemVuData.getId());
			if (optionalKeHoach2NhiemVu.isPresent()) {
				keHoach2NhiemVu = optionalKeHoach2NhiemVu.get();
			}
		}
		keHoach2NhiemVu.setDaXoa(false);
		keHoach2NhiemVu.setKeHoachKhacId(keHoachKhacId);
		keHoach2NhiemVu.setNhiemVuChaId(nhiemVuChaId);
		keHoach2NhiemVu.setDmLoaiNhiemVuId(null);
		if (Objects.nonNull(keHoach2NhiemVuData.getDmLoaiNhiemVuId())) {
			Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(keHoach2NhiemVuData.getDmLoaiNhiemVuId());
			if (optionalDmLoaiNhiemVu.isPresent()) {
				keHoach2NhiemVu.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
			}
		} else if (Objects.nonNull(loaiNhiemVuId)) {
			Optional<DmLoaiNhiemVu> optionalDmLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(loaiNhiemVuId);
			if (optionalDmLoaiNhiemVu.isPresent()) {
				keHoach2NhiemVu.setDmLoaiNhiemVuId(optionalDmLoaiNhiemVu.get().getId());
			}
		}
		keHoach2NhiemVu.setNoiDung(keHoach2NhiemVuData.getNoiDung());
		keHoach2NhiemVu.setThoiGianThucHienTuNgay(keHoach2NhiemVuData.getThoiGianThucHienTuNgay());
		keHoach2NhiemVu.setThoiGianThucHienDenNgay(keHoach2NhiemVuData.getThoiGianThucHienDenNgay());
		keHoach2NhiemVu.setIsDaGui(keHoach2NhiemVuData.getIsDaGui());
		keHoach2NhiemVu.setTinhTrang(keHoach2NhiemVuData.getTinhTrang());
		keHoach2NhiemVu.setSanPhamDauRa(keHoach2NhiemVuData.getSanPhamDauRa());
		keHoach2NhiemVu.setLanhDaoId(null);
		if (Objects.nonNull(keHoach2NhiemVuData.getLanhDaoId())) {
			Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(keHoach2NhiemVuData.getLanhDaoId());
			if (optionalDmCanBo.isPresent()) {
				keHoach2NhiemVu.setLanhDaoId(optionalDmCanBo.get().getId());
			}
		}
		if (Objects.nonNull(keHoach2NhiemVuData.getIsBanHanh())) {
			keHoach2NhiemVu.setIsBanHanh(keHoach2NhiemVuData.getIsBanHanh());
		} else {
			keHoach2NhiemVu.setIsBanHanh(false);
		}
		if (Objects.nonNull(keHoach2NhiemVuData.getIsThemMoiThucHien())) {
			keHoach2NhiemVu.setIsThemMoiThucHien(keHoach2NhiemVuData.getIsThemMoiThucHien());
		} else {
			keHoach2NhiemVu.setIsThemMoiThucHien(false);
		}
		if (Boolean.FALSE.equals(keHoach2NhiemVu.getIsThemMoiThucHien())) {
			keHoach2NhiemVu.setIsBanHanh(true);
		}
		keHoach2NhiemVu.setDonViThucHienId(null);
		if (Objects.nonNull(keHoach2NhiemVuData.getDonViThucHienId())) {
			Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(keHoach2NhiemVuData.getDonViThucHienId());
			if (optionalDmDonVi.isPresent()) {
				keHoach2NhiemVu.setDonViThucHienId(optionalDmDonVi.get().getId());
			}
		}
		keHoach2NhiemVu.setPhongBanThucHienId(null);
		if (Objects.nonNull(keHoach2NhiemVuData.getPhongBanThucHienId())) {
			Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(keHoach2NhiemVuData.getPhongBanThucHienId());
			if (optionalDmPhongBan.isPresent()) {
				keHoach2NhiemVu.setPhongBanThucHienId(optionalDmPhongBan.get().getId());
			}
		}
		keHoach2NhiemVu = serviceKeHoach2NhiemVuService.save(keHoach2NhiemVu);
		serviceNhiemVu2SanPhamDauRaService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
		/*
		 * Begin đính kèm file
		 *******************************************************/

		/*
		 * Khởi tạo biến
		 * ************************************************************** -
		 * fileDinhKemIds: danh sách id file đã đính kèm
		 * **************************** - type: loại đính kèm
		 * (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) ************* -
		 * objectId: id đối tượng đính kèm
		 * ****************************************** - appCode: tên model
		 * của đối tượng đính kèm*********************************
		 */
		List<Long> fileDinhKemIds = keHoach2NhiemVuData.getFileDinhKemIds();
		int type = Constants.DINH_KEM_NHIEU_FILE;
		long objectId = keHoach2NhiemVu.getId();
		String appCode = KeHoach2NhiemVu.class.getSimpleName();
		/*
		 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính
		 * kèm nhiều
		 */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

				/*
				 * set db nếu có trường lưu và chuyển file từ temp sang thư
				 * mục chính
				 */
				if (coreAttachment.getId() > 0) {
					NhiemVu2SanPhamDauRa nhiemVu2SanPhamDauRa = new NhiemVu2SanPhamDauRa();
					Optional<NhiemVu2SanPhamDauRa> optionalNhiemVu2SanPhamDauRa = serviceNhiemVu2SanPhamDauRaService
							.findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(keHoach2NhiemVu.getId(), coreAttachment.getId());
					if (optionalNhiemVu2SanPhamDauRa.isPresent()) {
						nhiemVu2SanPhamDauRa = optionalNhiemVu2SanPhamDauRa.get();
					}
					nhiemVu2SanPhamDauRa.setDaXoa(false);
					nhiemVu2SanPhamDauRa.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
					nhiemVu2SanPhamDauRa.setSanPhamDinhKemId(coreAttachment.getId());
					serviceNhiemVu2SanPhamDauRaService.save(nhiemVu2SanPhamDauRa);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/*
		 * End đính kèm file
		 **********************************************************/

		// đơn vị phối hợp
		serviceNhiemVu2DonViPhoiHopService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
		List<Long> donViPhoiHopIds = keHoach2NhiemVuData.getDonViPhoiHopIds();
		List<Long> phongBanPhoiHopIds = keHoach2NhiemVuData.getPhongBanPhoiHopIds();

		if (donViPhoiHopIds != null && !donViPhoiHopIds.isEmpty()) {
			for (Long donViPhoiHopId : donViPhoiHopIds) {
				if (Objects.nonNull(donViPhoiHopId)) {
					Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(donViPhoiHopId);
					if (optionalDmDonVi.isPresent()) {
						NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop = new NhiemVu2DonViPhoiHop();
						List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
								.findByKeHoach2NhiemVuIdAndDonViId(keHoach2NhiemVu.getId(), donViPhoiHopId);
						if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
							nhiemVu2DonViPhoiHop = nhiemVu2DonViPhoiHops.get(0);
						}
						nhiemVu2DonViPhoiHop.setDaXoa(false);
						nhiemVu2DonViPhoiHop.setDonViId(optionalDmDonVi.get().getId());
						nhiemVu2DonViPhoiHop.setPhongBanId(null);
						nhiemVu2DonViPhoiHop.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
						serviceNhiemVu2DonViPhoiHopService.save(nhiemVu2DonViPhoiHop);
					}
				}
			}
		}

		if (phongBanPhoiHopIds != null && !phongBanPhoiHopIds.isEmpty()) {
			for (Long phongBanPhoiHopId : phongBanPhoiHopIds) {
				if (Objects.nonNull(phongBanPhoiHopId)) {
					Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(phongBanPhoiHopId);
					if (optionalDmPhongBan.isPresent()) {
						NhiemVu2DonViPhoiHop nhiemVu2DonViPhoiHop = new NhiemVu2DonViPhoiHop();
						List<NhiemVu2DonViPhoiHop> nhiemVu2DonViPhoiHops = serviceNhiemVu2DonViPhoiHopService
								.findByKeHoach2NhiemVuIdAndPhongBanId(keHoach2NhiemVu.getId(), phongBanPhoiHopId);
						if (Objects.nonNull(nhiemVu2DonViPhoiHops) && !nhiemVu2DonViPhoiHops.isEmpty()) {
							nhiemVu2DonViPhoiHop = nhiemVu2DonViPhoiHops.get(0);
						}
						nhiemVu2DonViPhoiHop.setDaXoa(false);
						nhiemVu2DonViPhoiHop.setDonViId(null);
						nhiemVu2DonViPhoiHop.setPhongBanId(optionalDmPhongBan.get().getId());
						nhiemVu2DonViPhoiHop.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
						serviceNhiemVu2DonViPhoiHopService.save(nhiemVu2DonViPhoiHop);
					}
				}
			}
		}

		// kinhphi
		serviceNhiemVu2KinhPhiService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
		List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiKhacDatas = keHoach2NhiemVuData.getNhiemVu2KinhPhiDatas();
		if (Objects.nonNull(nhiemVu2KinhPhiKhacDatas) && !nhiemVu2KinhPhiKhacDatas.isEmpty()) {
			for (NhiemVu2KinhPhiData nhiemVu2KinhPhiData : nhiemVu2KinhPhiKhacDatas) {
				NhiemVu2KinhPhi nhiemVu2KinhPhi = new NhiemVu2KinhPhi();
				if (Objects.nonNull(nhiemVu2KinhPhiData.getId())) {
					Optional<NhiemVu2KinhPhi> optionalKhNvKhac2KinhPhiKhac = serviceNhiemVu2KinhPhiService
							.findById(nhiemVu2KinhPhiData.getId());
					if (optionalKhNvKhac2KinhPhiKhac.isPresent()) {
						nhiemVu2KinhPhi = optionalKhNvKhac2KinhPhiKhac.get();
					}
				}
				nhiemVu2KinhPhi.setDaXoa(false);
				nhiemVu2KinhPhi.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
				nhiemVu2KinhPhi.setSoTien(null);
				if (Objects.nonNull(nhiemVu2KinhPhiData.getSoTien()) && !nhiemVu2KinhPhiData.getSoTien().isEmpty()) {
					String soTien = nhiemVu2KinhPhiData.getSoTien().replace(".", "");
					nhiemVu2KinhPhi.setSoTien(Double.valueOf(soTien));
				}
				nhiemVu2KinhPhi.setSoTienDieuChinh(null);
				if (Objects.nonNull(nhiemVu2KinhPhiData.getSoTienDieuChinh()) && !nhiemVu2KinhPhiData.getSoTienDieuChinh().isEmpty()) {
					String soTienDieuChinh = nhiemVu2KinhPhiData.getSoTienDieuChinh().replace(".", "");
					nhiemVu2KinhPhi.setSoTienDieuChinh(Double.valueOf(soTienDieuChinh));
				}
				nhiemVu2KinhPhi.setTangGiamKinhPhi(nhiemVu2KinhPhiData.getTangGiamKinhPhi());
				nhiemVu2KinhPhi.setDmNguonKinhPhiId(null);
				if (Objects.nonNull(nhiemVu2KinhPhiData.getDmNguonKinhPhiId())) {
					Optional<DmNguonKinhPhi> optionalDmNguonKinhPhi = dmNguonKinhPhiService.findById(nhiemVu2KinhPhiData.getDmNguonKinhPhiId());
					if (optionalDmNguonKinhPhi.isPresent()) {
						nhiemVu2KinhPhi.setDmNguonKinhPhiId(optionalDmNguonKinhPhi.get().getId());
					}
				}
				nhiemVu2KinhPhi = serviceNhiemVu2KinhPhiService.save(nhiemVu2KinhPhi);

				// thanh toán
				serviceKinhPhi2ThanhToanKhacService.setFixedDaXoaForNhiemVu2KinhPhiId(true, nhiemVu2KinhPhi.getId());
				List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = nhiemVu2KinhPhiData.getKinhPhi2ThanhToanKhacDatas();
				if (Objects.nonNull(kinhPhi2ThanhToanKhacDatas) && !kinhPhi2ThanhToanKhacDatas.isEmpty()) {
					for (KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData : kinhPhi2ThanhToanKhacDatas) {
						KinhPhi2ThanhToanKhac kinhPhi2ThanhToanKhac = new KinhPhi2ThanhToanKhac();
						if (Objects.nonNull(kinhPhi2ThanhToanKhac.getId())) {
							Optional<KinhPhi2ThanhToanKhac> optionalKinhPhi2ThanhToanKhac = serviceKinhPhi2ThanhToanKhacService
									.findById(kinhPhi2ThanhToanKhac.getId());
							if (optionalKinhPhi2ThanhToanKhac.isPresent()) {
								kinhPhi2ThanhToanKhac = optionalKinhPhi2ThanhToanKhac.get();
							}
						}
						kinhPhi2ThanhToanKhac.setDaXoa(false);
						kinhPhi2ThanhToanKhac.setNhiemVu2KinhPhiId(nhiemVu2KinhPhi.getId());
						kinhPhi2ThanhToanKhac.setNgayThanhToan(kinhPhi2ThanhToanKhacData.getNgayThanhToan());
						kinhPhi2ThanhToanKhac.setSoTienThanhToan(null);
						if (Objects.nonNull(kinhPhi2ThanhToanKhacData.getSoTienThanhToan())
								&& !kinhPhi2ThanhToanKhacData.getSoTienThanhToan().isEmpty()) {
							String soTienThanhToan = kinhPhi2ThanhToanKhacData.getSoTienThanhToan().replace(".", "");
							Double soTien = Double.valueOf(soTienThanhToan);
							kinhPhi2ThanhToanKhac.setSoTienThanhToan(soTien);
						}
						serviceKinhPhi2ThanhToanKhacService.save(kinhPhi2ThanhToanKhac);

						/*
						 * Begin đính kèm file
						 *******************************************************/

						/*
						 * Khởi tạo biến
						 * *****************************************************
						 * ********* - fileDinhKemIds: danh sách id file đã đính
						 * kèm **************************** - type: loại đính
						 * kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE)
						 * ************* - objectId: id đối tượng đính kèm
						 * ****************************************** - appCode:
						 * tên model của đối tượng đính
						 * kèm*********************************
						 */
						fileDinhKemIds = kinhPhi2ThanhToanKhacData.getChungTuIds();
						type = Constants.DINH_KEM_1_FILE;
						objectId = kinhPhi2ThanhToanKhac.getId();
						appCode = KinhPhi2ThanhToanKhac.class.getSimpleName();
						/*
						 * Xóa mềm đính kèm cũ nếu có trước khi set file đính
						 * kèm nếu đính kèm nhiều
						 */
						if (type == Constants.DINH_KEM_NHIEU_FILE) {
							coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
						}
						if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
							for (Long fileDinhKemId : fileDinhKemIds) {
								CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

								/*
								 * set db nếu có trường lưu và chuyển file từ
								 * temp sang thư mục chính
								 */
								if (coreAttachment.getId() > 0) {
									kinhPhi2ThanhToanKhac.setChungTuId(coreAttachment.getId());
									serviceKinhPhi2ThanhToanKhacService.save(kinhPhi2ThanhToanKhac);
									coreAttachmentBusiness.saveAndMove(coreAttachment);
									log.info("coreAttachment id :" + coreAttachment.getId());
								}

								/* thoát nếu đính kèm 1 file */
								if (type == Constants.DINH_KEM_1_FILE) {
									break;
								}
							}
						}
						/*
						 * End đính kèm file
						 **********************************************************/
					}
				}
			}
		}

		// gia hạn
		List<NhiemVu2GiaHanData> nhiemVu2GiaHanDatas = keHoach2NhiemVuData.getNhiemVu2GiaHanDatas();
		serviceNhiemVu2GiaHanService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
		if (Objects.nonNull(nhiemVu2GiaHanDatas) && !nhiemVu2GiaHanDatas.isEmpty()) {
			for (NhiemVu2GiaHanData nhiemVu2GiaHanData : nhiemVu2GiaHanDatas) {
				NhiemVu2GiaHan nhiemVu2GiaHan = new NhiemVu2GiaHan();
				if (Objects.nonNull(nhiemVu2GiaHanData.getId())) {
					Optional<NhiemVu2GiaHan> optionalNhiemVu2GiaHan = serviceNhiemVu2GiaHanService.findById(nhiemVu2GiaHanData.getId());
					if (optionalNhiemVu2GiaHan.isPresent()) {
						nhiemVu2GiaHan = optionalNhiemVu2GiaHan.get();
					}
				}
				nhiemVu2GiaHan.setDaXoa(false);
				nhiemVu2GiaHan.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
				nhiemVu2GiaHan.setNgayHoanThanh(nhiemVu2GiaHanData.getNgayHoanThanh());
				nhiemVu2GiaHan.setSoLanGiaHan(nhiemVu2GiaHanData.getSoLanGiaHan());
				nhiemVu2GiaHan.setNguoiGiaHanId(null);
				if (Objects.nonNull(nhiemVu2GiaHanData.getNguoiGiaHanId())) {
					Optional<DmCanBo> optionalDmCanBo = dmCanBoService.findById(nhiemVu2GiaHanData.getNguoiGiaHanId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVu2GiaHan.setNguoiGiaHanId(optionalDmCanBo.get().getId());
					}
				}
				nhiemVu2GiaHan.setNgayHoanThanhLanDau(nhiemVu2GiaHanData.getNgayHoanThanhLanDau());
				nhiemVu2GiaHan.setNgayThucHienGiaHan(nhiemVu2GiaHanData.getNgayThucHienGiaHan());
				serviceNhiemVu2GiaHanService.save(nhiemVu2GiaHan);
			}
		}

		// thông tin thực hiện
		List<NhiemVu2ThongTinThucHienData> nhiemVu2ThongTinThucHienDatas = keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas();
		serviceNhiemVu2ThongTinThucHienService.setFixedDaXoaForKeHoach2NhiemVuId(true, keHoach2NhiemVu.getId());
		if (Objects.nonNull(nhiemVu2ThongTinThucHienDatas) && !nhiemVu2ThongTinThucHienDatas.isEmpty()) {
			for (NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData : nhiemVu2ThongTinThucHienDatas) {
				NhiemVu2ThongTinThucHien nhiemVu2ThongTinThucHien = new NhiemVu2ThongTinThucHien();
				if (Objects.nonNull(nhiemVu2ThongTinThucHienData.getId())) {
					Optional<NhiemVu2ThongTinThucHien> optionalNhiemVu2ThongTinThucHien = serviceNhiemVu2ThongTinThucHienService
							.findById(nhiemVu2ThongTinThucHienData.getId());
					if (optionalNhiemVu2ThongTinThucHien.isPresent()) {
						nhiemVu2ThongTinThucHien = optionalNhiemVu2ThongTinThucHien.get();
					}
				}
				nhiemVu2ThongTinThucHien.setDaXoa(false);
				nhiemVu2ThongTinThucHien.setKeHoach2NhiemVuId(keHoach2NhiemVu.getId());
				nhiemVu2ThongTinThucHien.setKetQuaThucHien(nhiemVu2ThongTinThucHienData.getKetQuaThucHien());
				nhiemVu2ThongTinThucHien.setNgayThucHienCapNhat(nhiemVu2ThongTinThucHienData.getNgayThucHienCapNhat());
				nhiemVu2ThongTinThucHien.setTienDo(nhiemVu2ThongTinThucHienData.getTienDo());
				nhiemVu2ThongTinThucHien.setIsDaGui(nhiemVu2ThongTinThucHienData.getIsDaGui());
				nhiemVu2ThongTinThucHien.setNgayHoanThanh(nhiemVu2ThongTinThucHienData.getNgayHoanThanh());
				nhiemVu2ThongTinThucHien = serviceNhiemVu2ThongTinThucHienService.save(nhiemVu2ThongTinThucHien);
				serviceThucHien2VanBanLienQuanService.setFixedDaXoaForNhiemVu2ThucHienId(true, nhiemVu2ThongTinThucHien.getId());
				/*
				 * Begin đính kèm file
				 *******************************************************/

				/*
				 * Khởi tạo biến
				 * *************************************************************
				 * * - fileDinhKemIds: danh sách id file đã đính kèm
				 * **************************** - type: loại đính kèm
				 * (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) ************* -
				 * objectId: id đối tượng đính kèm
				 * ****************************************** - appCode: tên
				 * model của đối tượng đính kèm*********************************
				 */
				fileDinhKemIds = nhiemVu2ThongTinThucHienData.getFileDinhKemIds();
				type = Constants.DINH_KEM_NHIEU_FILE;
				objectId = nhiemVu2ThongTinThucHien.getId();
				appCode = NhiemVu2ThongTinThucHien.class.getSimpleName();
				/*
				 * Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu
				 * đính kèm nhiều
				 */
				if (type == Constants.DINH_KEM_NHIEU_FILE) {
					coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
				}
				if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
					for (Long fileDinhKemId : fileDinhKemIds) {
						CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type, appCode);

						/*
						 * set db nếu có trường lưu và chuyển file từ temp sang
						 * thư mục chính
						 */
						if (coreAttachment.getId() > 0) {
							ThucHien2VanBanLienQuan thucHien2VanBanLienQuan = new ThucHien2VanBanLienQuan();
							Optional<ThucHien2VanBanLienQuan> optionalThucHien2VanBanLienQuan = serviceThucHien2VanBanLienQuanService
									.findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(nhiemVu2ThongTinThucHien.getId(), coreAttachment.getId());
							if (optionalThucHien2VanBanLienQuan.isPresent()) {
								thucHien2VanBanLienQuan = optionalThucHien2VanBanLienQuan.get();
							}
							thucHien2VanBanLienQuan.setDaXoa(false);
							thucHien2VanBanLienQuan.setNhiemVu2ThucHienId(nhiemVu2ThongTinThucHien.getId());
							thucHien2VanBanLienQuan.setVanBanDinhKemId(coreAttachment.getId());
							serviceThucHien2VanBanLienQuanService.save(thucHien2VanBanLienQuan);
							coreAttachmentBusiness.saveAndMove(coreAttachment);
							log.info("coreAttachment id :" + coreAttachment.getId());
						}

						/* thoát nếu đính kèm 1 file */
						if (type == Constants.DINH_KEM_1_FILE) {
							break;
						}
					}
				}
				/*
				 * End đính kèm file
				 **********************************************************/
			}
		}
		return keHoach2NhiemVu.getId();
	}

	public KeHoachKhacData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachKhac> optional = serviceKeHoachKhacService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(id));
		}
		KeHoachKhac keHoachKhac = optional.get();
		keHoachKhac.setDaXoa(true);
		keHoachKhac = serviceKeHoachKhacService.save(keHoachKhac);
		return convertToKeHoachKhacData(false, keHoachKhac, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public List<ExcelColumnsData> getExcelColumns() {

		List<ExcelColumnsData> excelColumnsDatas = new ArrayList<>();
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("stt").columnName("STT").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("noiDung").columnName("Nhiệm vụ").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("donViThucHien").columnName("Đơn vị thực hiện").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("donViPhoiHop").columnName("Đơn vị phối hợp").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("nguonKinhPhi").columnName("Nguồn kinh phí").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("giaTriThanhToan").columnName("Thanh toán").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("giaTriConLai").columnName("Còn lại").isHidden(false).build());
		excelColumnsDatas
				.add(ExcelColumnsData.builder().columnCode("thoiGianThucHien").columnName("Thời gian thực hiện").isHidden(false).build());
		excelColumnsDatas
				.add(ExcelColumnsData.builder().columnCode("thoiHanThanhToan").columnName("Thời hạn thanh toán").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("ghiChu").columnName("Ghi chú").isHidden(false).build());
		excelColumnsDatas
				.add(ExcelColumnsData.builder().columnCode("thoiHanHoanThanh").columnName("Thời hạn hoàn thành").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("tienDo").columnName("Tiến độ %").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("ketQua").columnName("Kết quả").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("giaHan").columnName("Gia hạn").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("ngayHoanThanh").columnName("Ngày hoàn thành").isHidden(false).build());
		excelColumnsDatas.add(ExcelColumnsData.builder().columnCode("tinhTrang").columnName("Tình trạng").isHidden(false).build());

		return excelColumnsDatas;
	}

	public Integer getMaxNam() {
		return serviceKeHoachKhacService.getMaxNam();
	}
}
