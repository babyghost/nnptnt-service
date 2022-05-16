package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.bussiness;

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

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.ChiTieuService;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.ChiTieuNamService;
import vn.dnict.microservice.nnptnt.baocao.data.ThongKeData;
import vn.dnict.microservice.nnptnt.baocao.data.ThucHienBaoCaoData;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model.KeHoach;
import vn.dnict.microservice.nnptnt.baocao.kehoach.dao.service.KeHoachService;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.bussiness.view.MyExcelViewThongKeSoLieuChuyenNganh;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;
import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service.ThucHienBaoCaoService;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;
@Slf4j
@Service
public class ThucHienBaoCaoBussiness {

	@Autowired
	ThucHienBaoCaoService serviceThucHienBaoCaoService;

	@Autowired
	ChiTieuService serviceChiTieuService;

	@Autowired
	ChiTieuNamService serviceChiTieuNamService;

	@Autowired
	DmLinhVucService serviceDmLinhVucService;

	@Autowired
	KeHoachService serviceKeHoachService;
	
	public Page<ThucHienBaoCaoData> findAll(int page, int size, String sortBy, String sortDir, Long linhVucId,
			LocalDate thangNam, LocalDate ngayThucHien) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThucHienBaoCao> pageThucHienBaoCao = serviceThucHienBaoCaoService.findAll(linhVucId, thangNam,
				ngayThucHien, PageRequest.of(page, size, direction, sortBy));
		final Page<ThucHienBaoCaoData> pageThucHienBaoCaoData = pageThucHienBaoCao
				.map(this::convertToThucHienBaoCaoData);

		return pageThucHienBaoCaoData;
	}

	private ThucHienBaoCaoData convertToThucHienBaoCaoData(ThucHienBaoCao thucHienBaoCao) {
		ThucHienBaoCaoData thucHienBaoCaoData = new ThucHienBaoCaoData();
		thucHienBaoCaoData.setId(thucHienBaoCao.getId());
		thucHienBaoCaoData.setChiTieuId(thucHienBaoCao.getChiTieuId());
		thucHienBaoCaoData.setThucHien(thucHienBaoCao.getThucHien());
		thucHienBaoCaoData.setNgayThucHien(thucHienBaoCao.getNgayThucHien());
		thucHienBaoCaoData.setThangNam(thucHienBaoCao.getThangNam());
		if (Objects.nonNull(thucHienBaoCaoData)) {
			Optional<ChiTieu> optChiTieu = serviceChiTieuService.findById(thucHienBaoCao.getChiTieuId());

			if (optChiTieu.isPresent()) {
				Optional<ChiTieuNam> optChiTieuNam = serviceChiTieuNamService
						.findById(optChiTieu.get().getChiTieuNamId());
				if (optChiTieuNam.get().getId() != null) {

					Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService
							.findById(optChiTieuNam.get().getLinhVucId());
					if (optLinhVuc.isPresent()) {

						thucHienBaoCaoData.setLinhVucId(optLinhVuc.get().getId());
						thucHienBaoCaoData.setLinhVucTen(optLinhVuc.get().getTen());

					}

				}
			}
		}

		return thucHienBaoCaoData;
	}

	public Page<ThongKeData> thongKe(int page, int size, String sortBy, String sortDir, Long linhVucId,
			LocalDate thangNam, LocalDate ngayThucHien, LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThucHienBaoCao> pageThucHienBaoCao = serviceThucHienBaoCaoService.thongKe(linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc , thangBatDauTN, thangKetThucTN,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ThongKeData> pageThongKeData = pageThucHienBaoCao
				.map(e -> (this.convertToThongKeData(e, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN)));

		return pageThongKeData;
	}

	
	
	
	private ThongKeData convertToThongKeData(ThucHienBaoCao thucHienBaoCao , LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN) {
		ThongKeData thongKeData = new ThongKeData();
		thongKeData.setChiTieuId(thucHienBaoCao.getChiTieuId());
		thongKeData.setThucHien(thucHienBaoCao.getThucHien());
		thongKeData.setThangNam(thucHienBaoCao.getThangNam());
		if (Objects.nonNull(thongKeData)) {
			Optional<ChiTieu> optChiTieu = serviceChiTieuService.findById(thucHienBaoCao.getChiTieuId());
			thongKeData.setChiTieuTen(optChiTieu.get().getTen());
			thongKeData.setDonViTinh(optChiTieu.get().getDonViTinh());
			if (optChiTieu.isPresent()) {
				Optional<ChiTieuNam> optChiTieuNam = serviceChiTieuNamService
						.findById(optChiTieu.get().getChiTieuNamId());
				if (optChiTieuNam.get().getId() != null) {
					thongKeData.setNam(optChiTieuNam.get().getNam());
					Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService
							.findById(optChiTieuNam.get().getLinhVucId());
					if (optLinhVuc.isPresent()) {

						thongKeData.setLinhVucId(optLinhVuc.get().getId());
						thongKeData.setLinhVucTen(optLinhVuc.get().getTen());

					}

				}
				
			
			}
			
			thongKeData.setThangBatDau(thangBatDau);
			thongKeData.setThangKetThuc(thangKetThuc);
			thongKeData.setThangBatDauTN(thangBatDauTN);
			thongKeData.setThangKetThucTN(thangKetThucTN);
			
			Integer namCu = thongKeData.getNam()-1;
			Float tongSoTN = 0.0f;
			Float tongUocThang = 0.0f;
			Float sumCungKy =0.0f;
		Float tongSo = serviceThucHienBaoCaoService.TongThucHienNamTruoc(thongKeData.getThangBatDau(), thongKeData.getThangKetThuc(), thongKeData.getChiTieuTen(),thongKeData.getLinhVucId(),namCu);
		if(Objects.isNull(tongSo)) {
			tongSo = 0.0f;
		}
		tongSoTN = serviceThucHienBaoCaoService.TongThucHienTrongNam(thongKeData.getThangBatDauTN(), thongKeData.getThangKetThucTN(),thongKeData.getChiTieuId());
		
		if(Objects.isNull(tongSoTN)) {
			tongSoTN = 0.0f;
		}
		Long countThang = serviceThucHienBaoCaoService.CountSoThangThucHienNamCu(thongKeData.getThangBatDau(), thongKeData.getThangKetThuc(), thongKeData.getChiTieuTen(),thongKeData.getLinhVucId(),namCu);
		
		Long countThangTN =  serviceThucHienBaoCaoService.CountSoThangThucHien(thongKeData.getThangBatDauTN(), thongKeData.getThangKetThucTN(), thongKeData.getChiTieuId());
		
		
		thongKeData.setCountThang(countThang);
		thongKeData.setCountThangTN(countThangTN);
		thongKeData.setTongThucHienTN(tongSoTN);	
		thongKeData.setTongThucHienCu(tongSo);
		if(Objects.isNull(thongKeData.getThucHien())) {
			thongKeData.setThucHien(0);		}
		Optional<KeHoach> optKeHoach = serviceKeHoachService.findByNamAndLinhVucIdAndChiTieuId(thongKeData.getNam(), thongKeData.getLinhVucId(), thongKeData.getChiTieuId());
			if(optKeHoach.isPresent()) {
				thongKeData.setKeHoach(optKeHoach.get().getKeHoach());
			}else {
				thongKeData.setKeHoach(0.0f);
			}
			log.info("kehoach :", optKeHoach);
//		System.out.println(optKeHoach);
		 tongUocThang = thongKeData.getTongThucHienTN() + thongKeData.getThucHien();		
	thongKeData.setUocThang(tongUocThang);
//		
		 sumCungKy = tongUocThang/tongSo * 100;
//		
	thongKeData.setCungKy(sumCungKy);
	
	thongKeData.setKeHoachNam(tongUocThang/thongKeData.getKeHoach()*100);
		}
		return thongKeData;
	}

	public ThucHienBaoCaoData create(ThucHienBaoCaoData thucHienBaoCaoData) {
		ThucHienBaoCao thucHienBaoCao = new ThucHienBaoCao();
		thucHienBaoCao.setDaXoa(false);
		thucHienBaoCao.setChiTieuId(thucHienBaoCaoData.getChiTieuId());
		thucHienBaoCao.setNgayThucHien(thucHienBaoCaoData.getNgayThucHien());
		thucHienBaoCao.setThangNam(thucHienBaoCaoData.getThangNam());
		thucHienBaoCao.setThucHien(thucHienBaoCaoData.getThucHien());
		thucHienBaoCao = serviceThucHienBaoCaoService.save(thucHienBaoCao);
		return this.convertToThucHienBaoCaoData(thucHienBaoCao);
	}

	public ThucHienBaoCaoData update(Long id, ThucHienBaoCaoData thucHienBaoCaoData) throws EntityNotFoundException {
		Optional<ThucHienBaoCao> optional = serviceThucHienBaoCaoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThucHienBaoCao.class, "id", String.valueOf(id));
		}
		ThucHienBaoCao thucHienBaoCao = optional.get();

		thucHienBaoCao.setChiTieuId(thucHienBaoCaoData.getChiTieuId());
		thucHienBaoCao.setNgayThucHien(thucHienBaoCaoData.getNgayThucHien());
		thucHienBaoCao.setThangNam(thucHienBaoCaoData.getThangNam());
		thucHienBaoCao.setThucHien(thucHienBaoCaoData.getThucHien());
		thucHienBaoCao = serviceThucHienBaoCaoService.save(thucHienBaoCao);

		return this.convertToThucHienBaoCaoData(thucHienBaoCao);

	}

	public ThucHienBaoCaoData findById(Long id) throws EntityNotFoundException {
		Optional<ThucHienBaoCao> optional = serviceThucHienBaoCaoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThucHienBaoCao.class, "id", String.valueOf(id));
		}
		ThucHienBaoCao thucHienBaoCao = optional.get();
		return this.convertToThucHienBaoCaoData(thucHienBaoCao);
	}

	public ThucHienBaoCao delete(Long id) throws EntityNotFoundException {
		Optional<ThucHienBaoCao> optional = serviceThucHienBaoCaoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThucHienBaoCao.class, "id", String.valueOf(id));
		}
		ThucHienBaoCao thucHienBaoCao = optional.get();
		thucHienBaoCao.setDaXoa(true);
		thucHienBaoCao = serviceThucHienBaoCaoService.save(thucHienBaoCao);
		return thucHienBaoCao;
	}

	public ThucHienBaoCaoData findChiTieu(LocalDate thangNam, long linhVucId, Integer nam)
			throws EntityNotFoundException {
		ThucHienBaoCao thucHienBaoCao = new ThucHienBaoCao();
		Optional<ThucHienBaoCao> optThucHienBaoCao = serviceThucHienBaoCaoService
				.findByLinhVucIdAndThangNamAndDaXoa(thangNam, linhVucId, nam);

		return this.convertToThucHienBaoCaoData(thucHienBaoCao);
		
		


	}
	public ModelAndView exportExcelThongKeSoLieuChuyenNganh(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,Long linhVucId,
			LocalDate thangNam, LocalDate ngayThucHien, LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN) {
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

		Page<ThucHienBaoCao> pageThucHienBaoCao = serviceThucHienBaoCaoService.thongKe(linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN,  PageRequest.of(page, size, direction, sortBy));
		Page<ThongKeData> pageThongKeData = pageThucHienBaoCao
				.map(e -> (this.convertToThongKeData(e, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN)));

		List<ThongKeData> thongKeDatas = new ArrayList<>(
				pageThongKeData.getContent());
		
	System.out.println(thongKeDatas+"----------//"+ response);

		// All the remaining employees
		while (pageThucHienBaoCao.hasNext()) {
			Page<ThucHienBaoCao> nextPageOfEmployees = serviceThucHienBaoCaoService.thongKe(linhVucId, thangNam, ngayThucHien, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN, 
					pageThucHienBaoCao.nextPageable());
			Page<ThongKeData> nextPageOfThongKeData = nextPageOfEmployees
					.map(e -> (this.convertToThongKeData(e, thangBatDau, thangKetThuc, thangBatDauTN, thangKetThucTN)));
			if (Objects.nonNull(nextPageOfThongKeData.getContent())) {
				thongKeDatas.addAll(nextPageOfThongKeData.getContent());
			}
			// update the page reference to the current page
			pageThucHienBaoCao = nextPageOfEmployees;
			System.out.println(pageThucHienBaoCao+"++++++++++++");
		}

		model.put("thongKeDatas", thongKeDatas);
		System.out.println("?????????????????????????????????"+ model);
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKe.xls");
		return new ModelAndView(new MyExcelViewThongKeSoLieuChuyenNganh(), model);
		
	}


}
