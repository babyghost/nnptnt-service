package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.view.MyExcelViewThongKeKeHoachNam;
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

	private NhiemVuNamData convertToNhiemVuNamData(NhiemVuNam nhiemVuNam) {
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(optionalKeHoachNam.isPresent()) {
				nhiemVuNamData.setKeHoachNamTen(optionalKeHoachNam.get().getTenKeHoach());
				nhiemVuNamData.setDonViChuTriId(optionalKeHoachNam.get().getDonViChuTriId());
				if(optionalKeHoachNam.get().getDonViChuTriId() != null && optionalKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(optionalKeHoachNam.get().getDonViChuTriId());
					if(optionalDmDonVi.isPresent()) {
						nhiemVuNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
					}
				}
				nhiemVuNamData.setNam(optionalKeHoachNam.get().getNam());
				nhiemVuNamData.setSoKyHieu(optionalKeHoachNam.get().getSoKyHieu());
				nhiemVuNamData.setNgayBanHanh(optionalKeHoachNam.get().getNgayBanHanh());;
			}
		}
		nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
		if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
			Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
			if(optLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
				nhiemVuNamData.setLoaiNhiemVuMa(optLoaiNhiemVu.get().getMa());
			}
		}
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		nhiemVuNamData.setDanhSo(nhiemVuNam.getDanhSo());
		
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService
				.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		if(Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for (TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());
				
				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
		return nhiemVuNamData;
	}
	
	public Page<ThongKeKeHoachNamData> thongKe(int page, int size, String sortBy, String sortDir, Long donViChuTriId, Integer nam,
			Long keHoachNamId, List<Integer> tinhTrangs, LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.findAll(donViChuTriId, tinhTrangs, nam, keHoachNamId, tuNgay,
				denNgay, tenNhiemVu, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeKeHoachNamData> pageThongKeKeHoach = pageNhiemVuNam.map(this::convertToThongKeKeHoachNamData);
		return pageThongKeKeHoach;
	}

	private ThongKeKeHoachNamData convertToThongKeKeHoachNamData(NhiemVuNam nhiemVuNam) {
		ThongKeKeHoachNamData thongKeKeHoachData = new ThongKeKeHoachNamData();
		thongKeKeHoachData.setId(nhiemVuNam.getId());
		thongKeKeHoachData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(optionalKeHoachNam.isPresent()) {
				thongKeKeHoachData.setKeHoachNamTen(optionalKeHoachNam.get().getTenKeHoach());
				thongKeKeHoachData.setKhDonViChuTriId(optionalKeHoachNam.get().getDonViChuTriId());
				if(optionalKeHoachNam.get().getDonViChuTriId() != null && optionalKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(optionalKeHoachNam.get().getDonViChuTriId());
					if(optionalDmDonVi.isPresent()) {
						thongKeKeHoachData.setKhDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
					}
				}
				thongKeKeHoachData.setKhNam(optionalKeHoachNam.get().getNam());
				thongKeKeHoachData.setKhSoKyHieu(optionalKeHoachNam.get().getSoKyHieu());
				thongKeKeHoachData.setKhNgayBanHanh(optionalKeHoachNam.get().getNgayBanHanh());
			}
		}
		thongKeKeHoachData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		thongKeKeHoachData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		thongKeKeHoachData.setSapXep(nhiemVuNam.getSapXep());
		thongKeKeHoachData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		thongKeKeHoachData.setTuNgay(nhiemVuNam.getTuNgay());
		thongKeKeHoachData.setDenNgay(nhiemVuNam.getDenNgay());
		thongKeKeHoachData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
		if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
			Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
			if(optLoaiNhiemVu.isPresent()) {
				thongKeKeHoachData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
				thongKeKeHoachData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
			}
		}
		thongKeKeHoachData.setGhiChu(nhiemVuNam.getGhiChu());
		thongKeKeHoachData.setDanhSo(nhiemVuNam.getDanhSo());
		
		List<TienDoNhiemVuNam> tienDoNams = serviceTienDoNhiemVuNamService.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		List<ThongKeKeHoachNamData> thongKeDatas = new ArrayList<ThongKeKeHoachNamData>();
		if(Objects.nonNull(tienDoNams) && !tienDoNams.isEmpty()) {
			for(TienDoNhiemVuNam tienDoNam : tienDoNams) {
				thongKeKeHoachData.setMucDoHoanThanh(tienDoNam.getMucDoHoanThanh());
				thongKeKeHoachData.setKetQua(tienDoNam.getKetQua());
				thongKeKeHoachData.setTinhTrang(tienDoNam.getTinhTrang());
				
				thongKeDatas.add(thongKeKeHoachData);
			}
		}
//		
//		Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findByNhiemVuNamId(nhiemVuNam.getId());
//		if(optTienDo.isPresent()) {
//			thongKeKeHoachData.setTinhTrang(optTienDo.get().getTinhTrang());
//			thongKeKeHoachData.setMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
//			thongKeKeHoachData.setKetQua(optTienDo.get().getKetQua());
//		}

		return thongKeKeHoachData;
	}
	
	public ModelAndView exportExcelThongKeKeHoachNam(HttpServletRequest request, HttpServletResponse response, int page, int size,
			String sortBy, String sortDir, Long donViChuTriId, Integer nam, Long keHoachNamId, List<Integer> tinhTrangs,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu) {
		
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
		
		Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.thongke(donViChuTriId, nam, keHoachNamId, tinhTrangs, tuNgay, denNgay,
				tenNhiemVu, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeKeHoachNamData> pageThongKeKeHoachData = pageNhiemVuNam.map(this::convertToThongKeKeHoachNamData);
		
		List<ThongKeKeHoachNamData> thongKeKeHoachDatas = new ArrayList<>(pageThongKeKeHoachData.getContent());
		
		while(pageNhiemVuNam.hasNext()) {
			Page<NhiemVuNam> nextPageOfEmployees = serviceNhiemVuNamService.thongke(donViChuTriId, nam, keHoachNamId, tinhTrangs,
					tuNgay, denNgay, tenNhiemVu, pageNhiemVuNam.nextPageable());
			Page<ThongKeKeHoachNamData> nextPageOfThongKeKeHoachNamData = nextPageOfEmployees.map(this::convertToThongKeKeHoachNamData);
			if(Objects.nonNull(nextPageOfThongKeKeHoachNamData.getContent())) {
				thongKeKeHoachDatas.addAll(nextPageOfThongKeKeHoachNamData.getContent());
			}
			pageNhiemVuNam = nextPageOfEmployees;
		}
		model.put("thongKeKeHoachDatas", thongKeKeHoachDatas);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKeKeHoachNam.xls");
		return new ModelAndView(new MyExcelViewThongKeKeHoachNam(), model);
	}

	public NhiemVuNamData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			if(optKeHoachNam.isPresent()) {
				nhiemVuNamData.setKeHoachNamTen(optKeHoachNam.get().getTenKeHoach());
				nhiemVuNamData.setDonViChuTriId(optKeHoachNam.get().getDonViChuTriId());
				if(optKeHoachNam.get().getDonViChuTriId() != null && optKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoachNam.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuNamData.setDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuNamData.setNam(optKeHoachNam.get().getNam());
			}
		}
		nhiemVuNamData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
		nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
		nhiemVuNamData.setSapXep(nhiemVuNam.getSapXep());
		nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
		nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
		nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
		nhiemVuNamData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
		if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
			Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
			if(optLoaiNhiemVu.isPresent()) {
				nhiemVuNamData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
				nhiemVuNamData.setLoaiNhiemVuMa(optLoaiNhiemVu.get().getMa());
			}
		}
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		nhiemVuNamData.setDanhSo(nhiemVuNam.getDanhSo());
		
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		if(Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for(TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());;
				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
				
				if(Objects.nonNull(tienDoNhiemVuNam)) {
					int type = Constants.DINH_KEM_1_FILE;
					Optional<FileDinhKemNhiemVuNam> fileDinhKemNam = serviceFileDinhKemNhiemVuNamService
							.findByTienDoNhiemVuNamId(tienDoNhiemVuNam.getId());
					Long fileDinhKemId = null;
					Long objectId = tienDoNhiemVuNam.getId();
					String appCode = TienDoNhiemVuNam.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemNam.get().getFileDinhKemId(), appCode,
							objectId, type);
					tienDoNhiemVuNamData.setFileDinhKem(fileDinhKem);
					tienDoNhiemVuNamData.setFileDinhKemIds(fileDinhKem.getIds());
				}
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
		return nhiemVuNamData;
 	}

	public NhiemVuNamData saveTienDo(Long nhiemVuNamId, NhiemVuNamData nhiemVuNamData)
			throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(nhiemVuNamId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(nhiemVuNamId));
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
				tienDo.setId(tienDoData.getId());
				tienDo.setNhiemVuNamId(tienDoData.getNhiemVuNamId());
				tienDo.setKetQua(tienDoData.getKetQua());
				tienDo.setMucDoHoanThanh(tienDoData.getMucDoHoanThanh());
				tienDo.setNgayBaoCao(tienDoData.getNgayBaoCao());
				tienDo.setNhiemVuNamId(nhiemVuNamId);
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
									.findByFileDinhKemIdAndTienDoNhiemVuNamId(fileDinhKemId, nhiemVuNam.getId());
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
