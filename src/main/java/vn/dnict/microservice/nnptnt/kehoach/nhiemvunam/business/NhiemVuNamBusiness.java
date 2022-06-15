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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.view.MyExcelViewThongKeKeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam2filedinhkem.dao.service.FileDinhKemNhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.TienDoNhiemVuNamService;

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
	
	public Page<NhiemVuNamData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, Long keHoachNamId,
			Integer nam, Integer tinhTrang, String tenNhiemVu, LocalDate tuNgay, LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.findAll(donViChuTriId, keHoachNamId, nam, tinhTrang, 
				tenNhiemVu, tuNgay, denNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<NhiemVuNamData> pageNhiemVuNamData = pageNhiemVuNam.map(this::convertToNhiemVuNamData);
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
				nhiemVuNamData.setKhDonViChuTriId(optionalKeHoachNam.get().getDonViChuTriId());
				if(optionalKeHoachNam.get().getDonViChuTriId() != null && optionalKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(optionalKeHoachNam.get().getDonViChuTriId());
					if(optionalDmDonVi.isPresent()) {
						nhiemVuNamData.setKhDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
						nhiemVuNamData.setKhDonViChuTriId(optionalDmDonVi.get().getId());
					}
				}
				nhiemVuNamData.setKhNam(optionalKeHoachNam.get().getNam());
				nhiemVuNamData.setKhSoKyHieu(optionalKeHoachNam.get().getSoKyHieu());
				nhiemVuNamData.setKhNgayBanHanh(optionalKeHoachNam.get().getNgayBanHanh());
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
				nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
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
				tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
				if(tienDoNhiemVuNam.getNhiemVuNamId() != null && tienDoNhiemVuNam.getNhiemVuNamId() > 0) {
					Optional<NhiemVuNam> optNhiemVu = serviceNhiemVuNamService.findById(tienDoNhiemVuNam.getNhiemVuNamId());
					if(optNhiemVu.isPresent()) {
						tienDoNhiemVuNamData.setNhiemVuNamTen(optNhiemVu.get().getTenNhiemVu());
						tienDoNhiemVuNamData.setNvNamNhiemVuChaId(optNhiemVu.get().getNhiemVuChaId());
						tienDoNhiemVuNamData.setNvNamDonViPhoiHop(optNhiemVu.get().getDonViPhoiHop());
						tienDoNhiemVuNamData.setNvNamGhiChu(optNhiemVu.get().getGhiChu());
						tienDoNhiemVuNamData.setNvNamTuNgay(optNhiemVu.get().getTuNgay());
						tienDoNhiemVuNamData.setNvNamDenNgay(optNhiemVu.get().getDenNgay());
					}
				}
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
	
	public Page<ThongKeKeHoachNamData> thongKeKeHoachNam(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			Long keHoachNamId, Integer nam, Integer tinhTrang,  String tenNhiemVu, LocalDate tuNgay, LocalDate denNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.findAll(donViChuTriId, keHoachNamId, nam, tinhTrang,
				tenNhiemVu, tuNgay, denNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeKeHoachNamData> pageThongKeKeHoach = pageNhiemVuNam.map(this::convertToThongKeKeHoachNamData);
		return pageThongKeKeHoach;
	}
	
	private ThongKeKeHoachNamData convertToThongKeKeHoachNamData(NhiemVuNam nhiemVuNam) {
		ThongKeKeHoachNamData thongKeKeHoachData = new ThongKeKeHoachNamData();
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
		Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findByNhiemVuNamId(nhiemVuNam.getId());
		if(optTienDo.isPresent()) {
			thongKeKeHoachData.setTinhTrang(optTienDo.get().getTinhTrang());
			thongKeKeHoachData.setMucDoHoanThanh(optTienDo.get().getMucDoHoanThanh());
			thongKeKeHoachData.setKetQua(optTienDo.get().getKetQua());
		}

		return thongKeKeHoachData;
	}
	
	public ModelAndView exportExcelThongKeKeHoachNam(HttpServletRequest request, HttpServletResponse response, int page, int size,
			String sortBy, String sortDir, Long donViChuTriId, Long keHoachNamId, Integer nam, Integer tinhTrang, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay) {
		
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
		
		Page<NhiemVuNam> pageNhiemVuNam = serviceNhiemVuNamService.tongHopKeHoachNam(donViChuTriId, keHoachNamId, nam, tinhTrang,
				tenNhiemVu, tuNgay, denNgay, PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeKeHoachNamData> pageThongKeKeHoachData = pageNhiemVuNam.map(this::convertToThongKeKeHoachNamData);
		
		List<ThongKeKeHoachNamData> thongKeKeHoachDatas = new ArrayList<>(pageThongKeKeHoachData.getContent());
		
		while(pageNhiemVuNam.hasNext()) {
			Page<NhiemVuNam> nextPageOfEmployees = serviceNhiemVuNamService.tongHopKeHoachNam(donViChuTriId, keHoachNamId, nam,
					tinhTrang, tenNhiemVu, tuNgay, denNgay, pageNhiemVuNam.nextPageable());
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
				nhiemVuNamData.setKhDonViChuTriId(optKeHoachNam.get().getDonViChuTriId());
				if(optKeHoachNam.get().getDonViChuTriId() != null && optKeHoachNam.get().getDonViChuTriId() > 0) {
					Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoachNam.get().getDonViChuTriId());
					if(optDonVi.isPresent()) {
						nhiemVuNamData.setKhDonViChuTriTen(optDonVi.get().getTenDonVi());
					}
				}
				nhiemVuNamData.setKhNam(optKeHoachNam.get().getNam());
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
				nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
			}
		}
		nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
		
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = new ArrayList<>();
		List<TienDoNhiemVuNam> tienDoNhiemVuNams = serviceTienDoNhiemVuNamService.findByNhiemVuNamIdAndDaXoa(nhiemVuNam.getId(), false);
		if(Objects.nonNull(tienDoNhiemVuNams) && !tienDoNhiemVuNams.isEmpty()) {
			for(TienDoNhiemVuNam tienDoNhiemVuNam : tienDoNhiemVuNams) {
				TienDoNhiemVuNamData tienDoNhiemVuNamData = new TienDoNhiemVuNamData();
				tienDoNhiemVuNamData.setId(tienDoNhiemVuNam.getId());
				tienDoNhiemVuNamData.setNhiemVuNamId(tienDoNhiemVuNam.getNhiemVuNamId());
				if(tienDoNhiemVuNam.getNhiemVuNamId() != null && tienDoNhiemVuNam.getNhiemVuNamId() > 0) {
					Optional<NhiemVuNam> optNhiemVu = serviceNhiemVuNamService.findById(tienDoNhiemVuNam.getNhiemVuNamId());
					if(optNhiemVu.isPresent()) {
						tienDoNhiemVuNamData.setNhiemVuNamTen(optNhiemVu.get().getTenNhiemVu());
						tienDoNhiemVuNamData.setNvNamNhiemVuChaId(optNhiemVu.get().getNhiemVuChaId());
						tienDoNhiemVuNamData.setNvNamDonViPhoiHop(optNhiemVu.get().getDonViPhoiHop());
						tienDoNhiemVuNamData.setNvNamGhiChu(optNhiemVu.get().getGhiChu());
						tienDoNhiemVuNamData.setNvNamTuNgay(optNhiemVu.get().getTuNgay());
						tienDoNhiemVuNamData.setNvNamDenNgay(optNhiemVu.get().getDenNgay());
					}
				}
				tienDoNhiemVuNamData.setTinhTrang(tienDoNhiemVuNam.getTinhTrang());
				tienDoNhiemVuNamData.setMucDoHoanThanh(tienDoNhiemVuNam.getMucDoHoanThanh());
				tienDoNhiemVuNamData.setNgayBaoCao(tienDoNhiemVuNam.getNgayBaoCao());
				tienDoNhiemVuNamData.setKetQua(tienDoNhiemVuNam.getKetQua());;
				tienDoNhiemVuNamDatas.add(tienDoNhiemVuNamData);
			}
		}
		nhiemVuNamData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
		return nhiemVuNamData;
	}
	
	public KeHoachNamData findByKeHoachNamId(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if(!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setDonViChuTriId(keHoachNam.getDonViChuTriId());
		if(keHoachNam.getDonViChuTriId() != null && keHoachNam.getDonViChuTriId() > 0) {
			Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if(!optDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriTen(optDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());
		
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<>();
		List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService.findByKeHoachNamIdAndDaXoa(keHoachNam.getId(), false);
		if(Objects.nonNull(nhiemVuNams) && !nhiemVuNams.isEmpty()) {
			for(NhiemVuNam nhiemVuNam : nhiemVuNams) {
				NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
				nhiemVuNamData.setId(nhiemVuNam.getId());
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
						nhiemVuNamData.setLoaiNvMa(optLoaiNhiemVu.get().getMa());
					}
				}
				nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());
				nhiemVuNamData.setDanhSo(nhiemVuNam.getDanhSo());
				
				nhiemVuNamDatas.add(nhiemVuNamData);
			}
		}
		keHoachNamData.setNhiemVuNamDatas(nhiemVuNamDatas);
		return keHoachNamData;
	}
	
	public NhiemVuNam create(NhiemVuNamData nhiemVuNamData) throws MethodArgumentNotValidException {	
		NhiemVuNam nhiemVuNam = new NhiemVuNam();
		nhiemVuNam.setDaXoa(false);
		nhiemVuNam.setKeHoachNamId(nhiemVuNamData.getKeHoachNamId());
		nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
		nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
		nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
		nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
		nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
		nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
		nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
		nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
		nhiemVuNam.setDanhSo(nhiemVuNamData.getDanhSo());
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);

		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(false, nhiemVuNam.getId());
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = nhiemVuNamData.getTienDoNhiemVuNamDatas();
		if(Objects.nonNull(tienDoNhiemVuNamDatas) && !tienDoNhiemVuNamDatas.isEmpty()) {
			for(TienDoNhiemVuNamData tienDoNhiemVuNamData : tienDoNhiemVuNamDatas) {
				TienDoNhiemVuNam tienDoNhiemVuNam = new TienDoNhiemVuNam();
				if(Objects.nonNull(tienDoNhiemVuNam.getId())) {
					Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findById(tienDoNhiemVuNamData.getId());
					if(optTienDo.isPresent()) {
						tienDoNhiemVuNam = optTienDo.get();
					}
				}
				tienDoNhiemVuNam.setId(tienDoNhiemVuNamData.getId());
				tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
				tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
				tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
				tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
				tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
				serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
			}
		}		
		return nhiemVuNam;		
	}
	
	public NhiemVuNam update(Long id, NhiemVuNamData nhiemVuNamData) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optional.get();
		nhiemVuNam.setKeHoachNamId(nhiemVuNamData.getKeHoachNamId());
		nhiemVuNam.setNhiemVuChaId(nhiemVuNamData.getNhiemVuChaId());
		nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
		nhiemVuNam.setSapXep(nhiemVuNamData.getSapXep());
		nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
		nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
		nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
		nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
		nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
		nhiemVuNam.setDanhSo(nhiemVuNamData.getDanhSo());
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
		
		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(false, nhiemVuNam.getId());
		List<TienDoNhiemVuNamData> tienDoNhiemVuNamDatas = nhiemVuNamData.getTienDoNhiemVuNamDatas();
		if(Objects.nonNull(tienDoNhiemVuNamDatas) && !tienDoNhiemVuNamDatas.isEmpty()) {
			for(TienDoNhiemVuNamData tienDoNhiemVuNamData : tienDoNhiemVuNamDatas) {
				TienDoNhiemVuNam tienDoNhiemVuNam = new TienDoNhiemVuNam();
				if(Objects.nonNull(tienDoNhiemVuNam.getId())) {
					Optional<TienDoNhiemVuNam> optTienDo = serviceTienDoNhiemVuNamService.findById(tienDoNhiemVuNamData.getId());
					if(optTienDo.isPresent()) {
						tienDoNhiemVuNam = optTienDo.get();
					}
				}
				tienDoNhiemVuNam.setId(tienDoNhiemVuNamData.getId());
				tienDoNhiemVuNam.setNhiemVuNamId(tienDoNhiemVuNamData.getNhiemVuNamId());
				tienDoNhiemVuNam.setTinhTrang(tienDoNhiemVuNamData.getTinhTrang());
				tienDoNhiemVuNam.setMucDoHoanThanh(tienDoNhiemVuNamData.getMucDoHoanThanh());
				tienDoNhiemVuNam.setNgayBaoCao(tienDoNhiemVuNamData.getNgayBaoCao());
				tienDoNhiemVuNam.setKetQua(tienDoNhiemVuNamData.getKetQua());
				serviceTienDoNhiemVuNamService.save(tienDoNhiemVuNam);
			}
		}
		return nhiemVuNam;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public NhiemVuNamData delete(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService.findById(id);
		if (!optionalNhiemVuNam.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNamData.class, "id", String.valueOf(id));
		}
		NhiemVuNam nhiemVuNam = optionalNhiemVuNam.get();
		nhiemVuNam.setDaXoa(true);
		nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
		return this.convertToNhiemVuNamData(nhiemVuNam);
	}
}
