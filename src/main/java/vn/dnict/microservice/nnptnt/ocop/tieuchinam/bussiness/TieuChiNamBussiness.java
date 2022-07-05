package vn.dnict.microservice.nnptnt.ocop.tieuchinam.bussiness;

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

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.model.DmNganhHang;
import vn.dnict.microservice.nnptnt.dm.nganhhang.dao.service.DmNganhHangService;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;
import vn.dnict.microservice.nnptnt.dm.nhom.dao.service.DmNhomService;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.model.DmPhanNhom;
import vn.dnict.microservice.nnptnt.dm.phannhom.dao.service.DmPhanNhomService;
import vn.dnict.microservice.nnptnt.ocop.data.TieuChiData;
import vn.dnict.microservice.nnptnt.ocop.data.TieuChiNamData;
import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.model.TieuChi;
import vn.dnict.microservice.nnptnt.ocop.tieuchi.dao.service.TieuChiService;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.bussiness.view.MyExcelViewTieuChiNam;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;
import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service.TieuChiNamService;
@Service
public class TieuChiNamBussiness {

	@Autowired
	TieuChiNamService serviceTieuChiNamService;

	@Autowired
	TieuChiService serviceTieuChiService;

	@Autowired
	DmNganhHangService serviceNganhHangService;

	@Autowired
	DmPhanNhomService servicePhanNhomService;

	@Autowired
	DmNhomService serviceNhomService;

	public Page<TieuChiNamData> findAll(int page, int size, String sortBy, String sortDir, Integer nam, Long phanNhomId,
			Long nganhHangId, Long nhomId, Integer trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<TieuChiNam> pageTieuChiNam = serviceTieuChiNamService.findAll(nam, phanNhomId, nganhHangId, nhomId,trangThai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<TieuChiNamData> pageTieuChiNamData = pageTieuChiNam.map(this::convertToTieuChiNamData);

		return pageTieuChiNamData;
	}

	public Page<TieuChiNamData> findChiTiet(int page, int size, String sortBy, String sortDir, Integer nam, Long phanNhomId,
			Long nganhHangId, Long nhomId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<TieuChiNam> pageTieuChiNam = serviceTieuChiNamService.findChiTiet(nam, phanNhomId, nganhHangId, nhomId,
				PageRequest.of(page, size, direction, sortBy));
		final Page<TieuChiNamData> pageTieuChiNamData = pageTieuChiNam.map(this::convertToTieuChiNamDataAndTieuChiData);

		return pageTieuChiNamData;
	}

	
	private TieuChiNamData convertToTieuChiNamData(TieuChiNam tieuChiNam) {
		TieuChiNamData tieuChiNamData = new TieuChiNamData();
		tieuChiNamData.setId(tieuChiNam.getId());
		tieuChiNamData.setNam(tieuChiNam.getNam());
		tieuChiNamData.setTrangThai(tieuChiNam.getTrangThai());
		if (Objects.nonNull(tieuChiNam.getNganhHangId())) {
			Optional<DmNganhHang> optNganhHang = serviceNganhHangService.findById(tieuChiNam.getNganhHangId());
			if (optNganhHang.isPresent()) {
				tieuChiNamData.setNganhHangId(optNganhHang.get().getId());
				tieuChiNamData.setNganhHangTen(optNganhHang.get().getTen());
			}
		}

		if (Objects.nonNull(tieuChiNam.getPhanNhomId())) {
			Optional<DmPhanNhom> optPhanNhom = servicePhanNhomService.findById(tieuChiNam.getPhanNhomId());
			if (optPhanNhom.isPresent()) {
				tieuChiNamData.setPhanNhomId(optPhanNhom.get().getId());
				tieuChiNamData.setPhanNhomTen(optPhanNhom.get().getTen());

			}
		}

		if (Objects.nonNull(tieuChiNam.getNhomId())) {
			Optional<DmNhom> optNhom = serviceNhomService.findById(tieuChiNam.getNhomId());
			if (optNhom.isPresent()) {
				tieuChiNamData.setNhomId(optNhom.get().getId());
				tieuChiNamData.setNhomTen(optNhom.get().getTen());
			}
		}

		return tieuChiNamData;

	}

	private TieuChiNamData convertToTieuChiNamDataAndTieuChiData(TieuChiNam tieuChiNam) {
		TieuChiNamData tieuChiNamData = new TieuChiNamData();
		tieuChiNamData.setId(tieuChiNam.getId());
		tieuChiNamData.setNam(tieuChiNam.getNam());
		tieuChiNamData.setTrangThai(tieuChiNam.getTrangThai());
		if (Objects.nonNull(tieuChiNam.getNganhHangId())) {
			Optional<DmNganhHang> optNganhHang = serviceNganhHangService.findById(tieuChiNam.getNganhHangId());
			if (optNganhHang.isPresent()) {
				tieuChiNamData.setNganhHangId(optNganhHang.get().getId());
				tieuChiNamData.setNganhHangTen(optNganhHang.get().getTen());
			}
		}

		if (Objects.nonNull(tieuChiNam.getPhanNhomId())) {
			Optional<DmPhanNhom> optPhanNhom = servicePhanNhomService.findById(tieuChiNam.getPhanNhomId());
			if (optPhanNhom.isPresent()) {
				tieuChiNamData.setPhanNhomId(optPhanNhom.get().getId());
				tieuChiNamData.setPhanNhomTen(optPhanNhom.get().getTen());

			}
		}

		if (Objects.nonNull(tieuChiNam.getNhomId())) {
			Optional<DmNhom> optNhom = serviceNhomService.findById(tieuChiNam.getNhomId());
			if (optNhom.isPresent()) {
				tieuChiNamData.setNhomId(optNhom.get().getId());
				tieuChiNamData.setNhomTen(optNhom.get().getTen());
			}
		}

		List<TieuChi> tieuChiList = serviceTieuChiService.getByTieuChiNamIdAndChaIdIsNullAndDaXoa(tieuChiNam.getId(),
				false);
		if (Objects.nonNull(tieuChiList) && !tieuChiList.isEmpty()) {
			tieuChiNamData.setTieuChiDatas(setTieuChiDatas(tieuChiList, tieuChiNam.getId()));
		}

		return tieuChiNamData;
	}

	private List<TieuChiData> setTieuChiDatas(List<TieuChi> tieuChiList, Long tieuChiNamId) {
		List<TieuChiData> tieuChiDatas = new ArrayList<TieuChiData>();
		for (TieuChi tieuChi : tieuChiList) {
			TieuChiData tieuChiData = new TieuChiData();
			tieuChiData.setId(tieuChi.getId());
			tieuChiData.setNam(tieuChi.getNam());
			tieuChiData.setDiem(tieuChi.getDiem());
			tieuChiData.setIsTinhTong(tieuChi.getIsTinhTong());
			tieuChiData.setIsTinhDiem(tieuChi.getIsTinhDiem());
			tieuChiData.setKieuDanhSo(tieuChi.getKieuDanhSo());
			tieuChiData.setChaId(tieuChi.getChaId());
			tieuChiData.setTieuChiNamId(tieuChiNamId);
			tieuChiData.setTen(tieuChi.getTen());
			List<TieuChi> tieuChiListChildren = serviceTieuChiService.getByTieuChiNamIdAndChaIdAndDaXoa(tieuChiNamId,
					tieuChi.getId(), false);

			List<TieuChiData> children = new ArrayList<TieuChiData>();
			if (Objects.nonNull(tieuChiListChildren) && !tieuChiListChildren.isEmpty()) {
				children = setTieuChiDatas(tieuChiListChildren, tieuChiNamId);
			}
			tieuChiData.setChildren(children);
			tieuChiDatas.add(tieuChiData);
		}

		return tieuChiDatas;

	}

	public TieuChiNamData findById(Long id) throws EntityNotFoundException {
		Optional<TieuChiNam> optional = serviceTieuChiNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TieuChiNam.class, "id", String.valueOf(id));
		}
		TieuChiNam tieuChiNam = optional.get();
		System.out.println(tieuChiNam+"////////////////////////");
		return this.convertToTieuChiNamDataAndTieuChiData(tieuChiNam);
	}
	private void saveTieuChiDatas(List<TieuChiData> tieuChiDatas, TieuChiNam tieuChiNam, Long chaId) {
	
		for (TieuChiData tieuChiData : tieuChiDatas) {
		
			TieuChi tieuChi = new TieuChi();
			System.out.println("+++++++++++++++++++++"+tieuChiData.getId());
			if (Objects.nonNull(tieuChiData.getId())) {
				Optional<TieuChi> optionalTieuChi = serviceTieuChiService.findById(tieuChiData.getId());
				if (optionalTieuChi.isPresent()) {
					tieuChi = optionalTieuChi.get();
				}
				
			}
		
			tieuChi.setDaXoa(false);
			tieuChi.setChaId(chaId);
			tieuChi.setDiem(tieuChiData.getDiem());
			tieuChi.setIsTinhDiem(tieuChiData.getIsTinhDiem());
			tieuChi.setIsTinhTong(tieuChiData.getIsTinhTong());
			tieuChi.setKieuDanhSo(tieuChiData.getKieuDanhSo());
			tieuChi.setNam(tieuChiData.getNam());
			tieuChi.setTen(tieuChiData.getTen());
			tieuChi.setTieuChiNamId(tieuChiNam.getId());
			
			tieuChi = serviceTieuChiService.save(tieuChi);
			List<TieuChiData> children = tieuChiData.getChildren();
			if (Objects.nonNull(children) && !children.isEmpty()) {
				saveTieuChiDatas(children, tieuChiNam, tieuChi.getId());
			}
		}
	
	}
	
	public TieuChiNamData create(TieuChiNamData tieuChiNamData) {
		TieuChiNam tieuChiNam = new TieuChiNam();
		tieuChiNam.setDaXoa(false);
		tieuChiNam.setNganhHangId(tieuChiNamData.getNganhHangId());
		tieuChiNam.setPhanNhomId(tieuChiNamData.getPhanNhomId());
		tieuChiNam.setNganhHangId(tieuChiNamData.getNganhHangId());
		tieuChiNam.setTrangThai(tieuChiNamData.getTrangThai());
		tieuChiNam.setNam(tieuChiNamData.getNam());
		tieuChiNam.setNhomId(tieuChiNamData.getNhomId());
		tieuChiNam = serviceTieuChiNamService.save(tieuChiNam);
		serviceTieuChiService.setFixedDaXoaForTieuChiNamId(false, tieuChiNam.getId());
		List<TieuChiData> tieuChiDatas = tieuChiNamData.getTieuChiDatas();
		if (Objects.nonNull(tieuChiDatas) && !tieuChiDatas.isEmpty()) {
			saveTieuChiDatas(tieuChiDatas, tieuChiNam, null);
		}

		return this.convertToTieuChiNamDataAndTieuChiData(tieuChiNam);

	}
	
	public TieuChiNamData update(Long id, TieuChiNamData tieuChiNamData) 
			throws EntityNotFoundException {
				Optional<TieuChiNam> optionalTieuChiNam = serviceTieuChiNamService.findById(id);
				if (!optionalTieuChiNam.isPresent()) {
					throw new EntityNotFoundException(TieuChiNam.class, "id", String.valueOf(id));
				}
				
				TieuChiNam tieuChiNam = optionalTieuChiNam.get();
				
				tieuChiNam.setDaXoa(false);
				tieuChiNam.setNganhHangId(tieuChiNamData.getNganhHangId());
				tieuChiNam.setPhanNhomId(tieuChiNamData.getPhanNhomId());
				tieuChiNam.setNganhHangId(tieuChiNamData.getNganhHangId());
				tieuChiNam.setTrangThai(tieuChiNamData.getTrangThai());
				tieuChiNam.setNam(tieuChiNamData.getNam());
				tieuChiNam.setNhomId(tieuChiNamData.getNhomId());
				tieuChiNam = serviceTieuChiNamService.save(tieuChiNam);
				serviceTieuChiService.setFixedDaXoaForTieuChiNamId(true, tieuChiNam.getId());
				List<TieuChiData> tieuChiDatas = tieuChiNamData.getTieuChiDatas();
				if (Objects.nonNull(tieuChiDatas) && !tieuChiDatas.isEmpty()) {
					saveTieuChiDatas(tieuChiDatas, tieuChiNam, null);
				}

				return this.convertToTieuChiNamDataAndTieuChiData(tieuChiNam);
	}
	
	public TieuChiNam delete(Long id) throws EntityNotFoundException {
		Optional<TieuChiNam> optional = serviceTieuChiNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TieuChiNam.class, "id", String.valueOf(id));
		}
		TieuChiNam tieuChiNam = optional.get();
		tieuChiNam.setDaXoa(true);
		tieuChiNam = serviceTieuChiNamService.save(tieuChiNam);
		return tieuChiNam;
	}
	
	
	public TieuChiNamData findTieuChi(Integer nam, Long phanNhomId, Long nganhHangId, Long nhomId ) throws EntityNotFoundException {
		TieuChiNam tieuChiNam= new TieuChiNam();
		Optional<TieuChiNam> optTieuChiNam = serviceTieuChiNamService.findByNamAndPhanNhomAndNhomAndNganhHang(nam, phanNhomId, nganhHangId, nhomId);
		if(optTieuChiNam.isPresent()) {
			tieuChiNam = optTieuChiNam.get();
		}
		return this.convertToTieuChiNamDataAndTieuChiData(tieuChiNam);
		
	}
	
	public ModelAndView exportExcelTieuChiNam(HttpServletRequest request, HttpServletResponse response, int page,
			int size, String sortBy, String sortDir,Integer nam, Long phanNhomId,
			Long nganhHangId, Long nhomId, Integer trangThai) {
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

		Page<TieuChiNam> pageTieuChiNam = serviceTieuChiNamService.findAll(nam, phanNhomId, nganhHangId, nhomId, trangThai,  PageRequest.of(page, size, direction, sortBy));
		Page<TieuChiNamData> pageTieuChiNamData = pageTieuChiNam
				.map(this::convertToTieuChiNamData);

		List<TieuChiNamData> tieuChiNamDatas = new ArrayList<>(
				pageTieuChiNamData.getContent());

	System.out.println(tieuChiNamDatas+"----------//"+ response);

		// All the remaining employees
		while (pageTieuChiNam.hasNext()) {
			Page<TieuChiNam> nextPageOfEmployees = serviceTieuChiNamService.findAll(nam, phanNhomId, nganhHangId, nhomId, trangThai, 
					pageTieuChiNam.nextPageable());
			Page<TieuChiNamData> nextPageOfTieuChiNamData = nextPageOfEmployees
					.map(this::convertToTieuChiNamData);
			if (Objects.nonNull(nextPageOfTieuChiNamData.getContent())) {
				tieuChiNamDatas.addAll(nextPageOfTieuChiNamData.getContent());
			}
			// update the page reference to the current page
			pageTieuChiNam = nextPageOfEmployees;
			System.out.println(pageTieuChiNam+"++++++++++++");
		}

		model.put("tieuChiNamDatas", tieuChiNamDatas);
		
		response.setContentType("application/ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=ThongKe "+formattedString+".xls");
		return new ModelAndView(new MyExcelViewTieuChiNam(), model);
		
	}
}
