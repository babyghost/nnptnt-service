package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.api;

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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.danhmuc.dao.service.DmNguonKinhPhiService;
import vn.dnict.microservice.danhmuc.dao.service.DmPhongBanService;
import vn.dnict.microservice.danhmuc.data.DmNguonKinhPhiInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model.DanhSachKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.data.ExcelColumnsData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoach2NhiemVuData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoach2QuyetDinhData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachKhacData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2GiaHanData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2ThongTinThucHienData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.business.KeHoachKhacBusiness;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.business.view.MyExcelViewKhKeHoachKhacThucHien;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;
import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service.KeHoachKhacService;

@RestController
@RequestMapping(value = "/kehoach/kehoachkhac")
public class KeHoachKhacController {
	@Autowired
	private KeHoachKhacBusiness businessKeHoachKhacBusiness;
	@Autowired
	private DmNguonKinhPhiService dmNguonKinhPhiService;
	@Autowired
	private KeHoachKhacService serviceKeHoachKhacService;
	@Autowired
	private DmDonViService dmDonViService;
	@Autowired
	private DmPhongBanService dmPhongBanService;

	@GetMapping(value = {"/", ""})
	public ResponseEntity<Page<KeHoachKhacData>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "tenKeHoach", required = false) String tenKeHoach,
			@RequestParam(name = "nam", defaultValue = "-1", required = false) Integer nam) {

		Page<KeHoachKhacData> pageKeHoachKhacData = businessKeHoachKhacBusiness.findAll(page, size, sortBy, sortDir, tenKeHoach, nam);
		return ResponseEntity.ok(pageKeHoachKhacData);
	}

	@GetMapping(value = {"/danhsach"})
	public ResponseEntity<List<DanhSachKeHoach>> danhSachKeHoach(@RequestParam(name = "tenKeHoach", required = false) String tenKeHoach,
			@RequestParam(name = "nam", defaultValue = "-1", required = false) Integer nam,
			@RequestParam(name = "loaiKeHoach", defaultValue = "-1", required = false) Integer loaiKeHoach,
			@RequestParam(name = "trangThai", defaultValue = "-1", required = false) Integer trangThai,
			@RequestParam(name = "isDieuChinh", required = false) Boolean isDieuChinh,
			@RequestParam(name = "isBanHanh", required = false) Boolean isBanHanh) {

		List<DanhSachKeHoach> danhSachKeHoachs = businessKeHoachKhacBusiness.danhSachKeHoach(tenKeHoach, nam, loaiKeHoach, trangThai, isDieuChinh,
				isBanHanh);
		return ResponseEntity.ok(danhSachKeHoachs);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<KeHoachKhacData> findById(@PathVariable("id") Long id,
			@RequestParam(name = "isDaGui", defaultValue = "false", required = false) Boolean isDaGui) throws EntityNotFoundException {
		KeHoachKhacData keHoachKhacData = businessKeHoachKhacBusiness.findById(id, isDaGui);
		return ResponseEntity.ok(keHoachKhacData);
	}

	@PostMapping(value = {"/save"})
	public ResponseEntity<KeHoachKhacData> save(@Valid @RequestBody KeHoachKhacData keHoachKhacData) {
		keHoachKhacData = businessKeHoachKhacBusiness.save(keHoachKhacData);
		return ResponseEntity.ok(keHoachKhacData);
	}

	@DeleteMapping(value = {"/{id}"})
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		businessKeHoachKhacBusiness.delete(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = {"/{id}/banHanh"})
	public ResponseEntity<KeHoach2QuyetDinhData> banHanh(@PathVariable("id") Long id,
			@Valid @RequestBody KeHoach2QuyetDinhData keHoach2QuyetDinhData) throws EntityNotFoundException {
		keHoach2QuyetDinhData = businessKeHoachKhacBusiness.banHanh(id, keHoach2QuyetDinhData);
		return ResponseEntity.ok(keHoach2QuyetDinhData);
	}

	@PostMapping(value = {"/nhiemvu/update/giahan/{nhiemVuId}"})
	public ResponseEntity<NhiemVu2GiaHanData> saveGiaHan(@PathVariable("nhiemVuId") Long nhiemVuId,
			@Valid @RequestBody NhiemVu2GiaHanData nhiemVu2GiaHanData) throws EntityNotFoundException {
		nhiemVu2GiaHanData = businessKeHoachKhacBusiness.saveGiaHan(nhiemVuId, nhiemVu2GiaHanData);
		return ResponseEntity.ok(nhiemVu2GiaHanData);
	}

	@PostMapping(value = {"/nhiemvu/update/isdagui/{nhiemVuId}"})
	public ResponseEntity<Void> saveIsDaGui(@PathVariable("nhiemVuId") Long nhiemVuId) throws EntityNotFoundException {
		businessKeHoachKhacBusiness.saveIsDaGui(nhiemVuId);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = {"/nhiemvu/update/tiendo"})
	public ResponseEntity<Long> saveNhiemVu(@RequestParam(name = "keHoachKhacId", required = true) Long keHoachKhacId,
			@RequestParam(name = "nhiemVuChaId", required = false) Long nhiemVuChaId,
			@RequestParam(name = "loaiNhiemVuId", required = true) Long loaiNhiemVuId,
			@Valid @RequestBody KeHoach2NhiemVuData keHoach2NhiemVuData) {
		Long nhiemVuId = businessKeHoachKhacBusiness.saveNhiemVu(keHoach2NhiemVuData, keHoachKhacId, nhiemVuChaId, loaiNhiemVuId);
		return ResponseEntity.ok(nhiemVuId);
	}

	@DeleteMapping(value = {"/nhiemvu/{nhiemVuId}"})
	public ResponseEntity<Void> deleteNhiemVu(@PathVariable("nhiemVuId") Long nhiemVuId) throws EntityNotFoundException {
		businessKeHoachKhacBusiness.deleteNhiemVu(nhiemVuId);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = {"/nhiemvu/update/thongtinthuchien/{nhiemVuId}"})
	public ResponseEntity<NhiemVu2ThongTinThucHienData> saveThongTinThucHien(@PathVariable("nhiemVuId") Long nhiemVuId,

			@Valid @RequestBody NhiemVu2ThongTinThucHienData nhiemVu2ThongTinThucHienData) throws EntityNotFoundException {
		nhiemVu2ThongTinThucHienData = businessKeHoachKhacBusiness.saveThongTinThucHien(nhiemVuId, nhiemVu2ThongTinThucHienData);
		return ResponseEntity.ok(nhiemVu2ThongTinThucHienData);
	}

	@GetMapping(value = {"/{id}/banhanh/filedinhkem"})
	public ResponseEntity<FileDinhKem> getFileDinhKemBanHanh(@PathVariable("id") Long id) throws EntityNotFoundException {
		FileDinhKem fileDinhKem = businessKeHoachKhacBusiness.getFileDinhKemBanHanh(id);
		return ResponseEntity.ok(fileDinhKem);
	}

	@GetMapping(value = "/export/thuchien")
	public ModelAndView exportExcelThucHien(@RequestParam(name = "keHoachKhacId", required = true) Long keHoachKhacId,
			@RequestParam(name = "isBanHanh", required = false) Boolean isBanHanh, @RequestParam(name = "isDaGui", required = false) Boolean isDaGui,
			@RequestParam(name = "isThucHien", required = false) Boolean isThucHien,
			@RequestParam(name = "nguonKinhPhiIds", required = false) List<Long> nguonKinhPhiIds,
			@RequestParam(name = "tinhTrangs", required = false) List<Integer> tinhTrangs,
			@RequestParam(name = "donViPhoiHopIds", required = false) List<Long> donViPhoiHopIds,
			@RequestParam(name = "phongBanPhoiHopIds", required = false) List<Long> phongBanPhoiHopIds,
			@RequestParam(name = "donViThucHienId", required = false) Long donViThucHienId,
			@RequestParam(name = "phongBanThucHienId", required = false) Long phongBanThucHienId,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThucHienTuNgay", required = false) LocalDate thoiGianThucHienTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThucHienDenNgay", required = false) LocalDate thoiGianThucHienDenNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThanhToanTuNgay", required = false) LocalDate thoiGianThanhToanTuNgay,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "thoiGianThanhToanDenNgay", required = false) LocalDate thoiGianThanhToanDenNgay,
			@RequestParam(name = "khExcelColumnsCodes", required = false) List<String> khExcelColumnsCodes, HttpServletRequest request,
			HttpServletResponse response) throws EntityNotFoundException {

		Optional<KeHoachKhac> optionalKeHoachKhac = serviceKeHoachKhacService.findById(keHoachKhacId);
		if (!optionalKeHoachKhac.isPresent()) {
			throw new EntityNotFoundException(KeHoachKhac.class, "id", String.valueOf(keHoachKhacId));
		}
		KeHoachKhac keHoachKhac = optionalKeHoachKhac.get();

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		Map<String, Object> model = new HashMap<>();
		List<DmNguonKinhPhi> dmNguonKinhPhis = dmNguonKinhPhiService.getDmNguonKinhPhisByKeHoachKhacId(keHoachKhacId);
		List<DmNguonKinhPhiInput> dmNguonKinhPhiInputs = new ArrayList<>();
		if (Objects.nonNull(nguonKinhPhiIds) && !nguonKinhPhiIds.isEmpty()) {
			for (Long nguonKinhPhiId : nguonKinhPhiIds) {
				if (Objects.nonNull(nguonKinhPhiId)) {
					Optional<DmNguonKinhPhi> optional = dmNguonKinhPhiService.findById(nguonKinhPhiId);
					if (optional.isPresent()) {
						DmNguonKinhPhi dmNguonKinhPhi = optional.get();
						DmNguonKinhPhiInput dmNguonKinhPhiInput = new DmNguonKinhPhiInput();
						dmNguonKinhPhiInput.setId(dmNguonKinhPhi.getId());
						dmNguonKinhPhiInput.setTen(dmNguonKinhPhi.getTen());
						dmNguonKinhPhiInput.setMa(dmNguonKinhPhi.getMa());
						dmNguonKinhPhiInput.setTrangThai(dmNguonKinhPhi.getTrangThai());
						dmNguonKinhPhiInputs.add(dmNguonKinhPhiInput);
					}
				}
			}
		} else if (Objects.nonNull(dmNguonKinhPhis) && !dmNguonKinhPhis.isEmpty()) {
			for (DmNguonKinhPhi dmNguonKinhPhi : dmNguonKinhPhis) {
				DmNguonKinhPhiInput dmNguonKinhPhiInput = new DmNguonKinhPhiInput();
				dmNguonKinhPhiInput.setId(dmNguonKinhPhi.getId());
				dmNguonKinhPhiInput.setTen(dmNguonKinhPhi.getTen());
				dmNguonKinhPhiInput.setMa(dmNguonKinhPhi.getMa());
				dmNguonKinhPhiInput.setTrangThai(dmNguonKinhPhi.getTrangThai());
				dmNguonKinhPhiInputs.add(dmNguonKinhPhiInput);
			}
		}

		KeHoachKhacData keHoachKhacData = businessKeHoachKhacBusiness.convertToKeHoachKhacDataExport(isDaGui, keHoachKhac, donViThucHienId,
				phongBanThucHienId, isBanHanh, isThucHien, nguonKinhPhiIds, tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay,
				thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds, phongBanPhoiHopIds);
		List<ExcelColumnsData> excelColumnsDatas;
		List<ExcelColumnsData> khColumnsDatas = businessKeHoachKhacBusiness.getExcelColumns();
		if (Objects.isNull(khExcelColumnsCodes) || khExcelColumnsCodes.isEmpty()) {
			excelColumnsDatas = khColumnsDatas;
		} else {
			excelColumnsDatas = khColumnsDatas.stream().filter(p -> khExcelColumnsCodes.contains(p.getColumnCode())).collect(Collectors.toList());
		}
		model.put("dmNguonKinhPhiInputs", dmNguonKinhPhiInputs);
		model.put("khKeHoachKhacData", excelColumnsDatas);
		model.put("khExcelColumnsDatas", excelColumnsDatas);
		String donViThucHien = "";
		if (Objects.nonNull(donViThucHienId) && donViThucHienId > -1) {
			Optional<DmDonVi> optionalDmDonVi = dmDonViService.findById(donViThucHienId);
			if (optionalDmDonVi.isPresent()) {
				donViThucHien = optionalDmDonVi.get().getTenDonVi();
			}
		} else if (Objects.nonNull(phongBanThucHienId) && phongBanThucHienId > -1) {
			Optional<DmPhongBan> optionalDmPhongBan = dmPhongBanService.findById(phongBanThucHienId);
			if (optionalDmPhongBan.isPresent()) {
				donViThucHien = optionalDmPhongBan.get().getTen();
			}
		}
		model.put("donViThucHien", donViThucHien);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=" + formattedString + "_KeHoachKhac.xls");
		return new ModelAndView(new MyExcelViewKhKeHoachKhacThucHien(), model);
	}

	@GetMapping(value = "/maxnam")
	public ResponseEntity<Integer> getMaxNam() {
		return ResponseEntity.ok(businessKeHoachKhacBusiness.getMaxNam());
	}

}
