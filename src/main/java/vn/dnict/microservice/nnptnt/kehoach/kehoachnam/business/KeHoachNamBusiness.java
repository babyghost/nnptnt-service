package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service.TienDoNhiemVuNamService;

@Service
public class KeHoachNamBusiness {
	@Autowired
	KeHoachNamService serviceKeHoachNamService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	NhiemVuNamService serviceNhiemVuNamService;
	
	@Autowired
	DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;
	
	@Autowired
	TienDoNhiemVuNamService serviceTienDoNhiemVuNamService;
	
	public Page<KeHoachNamData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId,
			Integer nam, String tenKeHoach, String soKyHieu, Boolean trangThai, LocalDate tuNgayBanHanh,
			LocalDate denNgayBanHanh) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachNam> pageKeHoachNam = serviceKeHoachNamService.findAll(donViChuTriId, nam, tenKeHoach,
				soKyHieu, trangThai, tuNgayBanHanh, denNgayBanHanh, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachNamData> pageKeHoachNamData = pageKeHoachNam
				.map(this::convertToKeHoachNamIdAndNhiemVuNamId);
		return pageKeHoachNamData;
	}
	
	private KeHoachNamData convertToKeHoachNamIdAndNhiemVuNamId(KeHoachNam keHoachNam) {
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setDonViChuTriId(keHoachNam.getDonViChuTriId());
		if(keHoachNam.getDonViChuTriId() != null && keHoachNam.getDonViChuTriId() > 0) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if(optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());
		
		List<NhiemVuNam> listNhiemVu = serviceNhiemVuNamService.findByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachNam.getId(), false);
		if(Objects.nonNull(listNhiemVu) && !listNhiemVu.isEmpty()) {
			keHoachNamData.setNhiemVuNamDatas(setNhiemVuNamDatas(listNhiemVu, keHoachNam.getId()));
		}
		
		return keHoachNamData;
	}
	
	private List<NhiemVuNamData> setNhiemVuNamDatas(List<NhiemVuNam> listNhiemVu, Long keHoachNamId) {
		List<NhiemVuNamData> listNhiemVuDatas = new ArrayList<NhiemVuNamData>();
		for (NhiemVuNam nhiemVuNam : listNhiemVu) {
			NhiemVuNamData nhiemVuData = new NhiemVuNamData();
			nhiemVuData.setId(nhiemVuNam.getId());
			nhiemVuData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
			if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
				Optional<KeHoachNam> optKeHoach = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
				if(optKeHoach.isPresent()) {
					nhiemVuData.setKeHoachNamTen(optKeHoach.get().getTenKeHoach());
					nhiemVuData.setDonViChuTriId(optKeHoach.get().getDonViChuTriId());
					if(optKeHoach.get().getDonViChuTriId() != null && optKeHoach.get().getDonViChuTriId() > 0) {
						Optional<DmDonVi> optDonVi = serviceDmDonViService.findById(optKeHoach.get().getDonViChuTriId());
						if(optDonVi.isPresent()) {
							nhiemVuData.setDonViChuTriTen(optDonVi.get().getTenDonVi());
						}
					}
					nhiemVuData.setNam(optKeHoach.get().getNam());
					nhiemVuData.setSoKyHieu(optKeHoach.get().getSoKyHieu());
					nhiemVuData.setNgayBanHanh(optKeHoach.get().getNgayBanHanh());
				}
			}
			nhiemVuData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
			nhiemVuData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
			nhiemVuData.setSapXep(nhiemVuNam.getSapXep());
			nhiemVuData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
			nhiemVuData.setTuNgay(nhiemVuNam.getTuNgay());
			nhiemVuData.setDenNgay(nhiemVuNam.getDenNgay());
			nhiemVuData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
			if(nhiemVuNam.getLoaiNhiemVuId() != null && nhiemVuNam.getLoaiNhiemVuId() > 0) {
				Optional<DmLoaiNhiemVu> optLoaiNhiemVu = serviceDmLoaiNhiemVuService.findById(nhiemVuNam.getLoaiNhiemVuId());
				if(optLoaiNhiemVu.isPresent()) {
					nhiemVuData.setLoaiNhiemVuTen(optLoaiNhiemVu.get().getTen());
					nhiemVuData.setLoaiNhiemVuMa(optLoaiNhiemVu.get().getMa());
				}
			}
			nhiemVuData.setGhiChu(nhiemVuNam.getGhiChu());
			nhiemVuData.setDanhSo(nhiemVuNam.getDanhSo());
			
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
			nhiemVuData.setTienDoNhiemVuNamDatas(tienDoNhiemVuNamDatas);
			
			List<NhiemVuNam> listNhiemVuChildren = serviceNhiemVuNamService.findByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(keHoachNamId,
					nhiemVuNam.getId(), false);
			List<NhiemVuNamData> children = new ArrayList<NhiemVuNamData>();
			if(Objects.nonNull(listNhiemVuChildren) && !listNhiemVuChildren.isEmpty()) {
				children = setNhiemVuNamDatas(listNhiemVuChildren, keHoachNamId);
			}
			nhiemVuData.setChildren(children);
			listNhiemVuDatas.add(nhiemVuData);
		}
		
		return listNhiemVuDatas;
	}
	
	public KeHoachNamData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optional = serviceKeHoachNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachNamData.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optional.get();
		return this.convertToKeHoachNamIdAndNhiemVuNamId(keHoachNam);
	}
	
	public KeHoachNamData create(KeHoachNamData keHoachNamData) throws MethodArgumentNotValidException {
		KeHoachNam keHoachNam = new KeHoachNam();
		keHoachNam.setDaXoa(false);
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setDonViChuTriId(keHoachNamData.getDonViChuTriId());
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachNamId(false, keHoachNam.getId());		
		List<NhiemVuNamData> nhiemVuNamDatas = keHoachNamData.getNhiemVuNamDatas();
		if(Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
			saveNhiemVuNamDatas(nhiemVuNamDatas, keHoachNam, null);
		}
		return this.convertToKeHoachNamIdAndNhiemVuNamId(keHoachNam);
	}
	
	public KeHoachNamData update(Long id, KeHoachNamData keHoachNamData) throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if(!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNamData.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setDonViChuTriId(keHoachNamData.getDonViChuTriId());
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachNamId(true, keHoachNam.getId());		
		List<NhiemVuNamData> nhiemVuNamDatas = keHoachNamData.getNhiemVuNamDatas();
		if(Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
			saveNhiemVuNamDatas(nhiemVuNamDatas, keHoachNam, null);
		}
		return this.convertToKeHoachNamIdAndNhiemVuNamId(keHoachNam);
	}
	
	private void saveNhiemVuNamDatas(List<NhiemVuNamData> nhiemVuNamDatas, KeHoachNam keHoachNam, Long nhiemVuChaId) {
		int sapXep = 0;
		for(NhiemVuNamData nhiemVuNamData : nhiemVuNamDatas) {
			sapXep++;
			NhiemVuNam nhiemVuNam = new NhiemVuNam();
			if(Objects.nonNull(nhiemVuNamData.getId())) {
				Optional<NhiemVuNam> optNhiemVuNam = serviceNhiemVuNamService.findById(nhiemVuNamData.getId());
				if(optNhiemVuNam.isPresent()) {
					nhiemVuNam = optNhiemVuNam.get();
				}
			}
			nhiemVuNam.setDaXoa(false);
			nhiemVuNam.setKeHoachNamId(keHoachNam.getId());
			nhiemVuNam.setNhiemVuChaId(nhiemVuChaId);
			nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
			nhiemVuNam.setSapXep(sapXep);
			nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
			nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
			nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
			nhiemVuNam.setLoaiNhiemVuId(nhiemVuNamData.getLoaiNhiemVuId());
			nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());	
			nhiemVuNam.setDanhSo(nhiemVuNamData.getDanhSo());
			nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
			
			List<NhiemVuNamData> children = nhiemVuNamData.getChildren();
			if(Objects.nonNull(children) && !children.isEmpty()) {
				saveNhiemVuNamDatas(children, keHoachNam, nhiemVuNam.getId());
			}
		}
	}
	
	@DeleteMapping(value = { "/{id}" })
	public KeHoachNamData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if(!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNamData.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		keHoachNam.setDaXoa(true);
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		return this.convertToKeHoachNamIdAndNhiemVuNamId(keHoachNam);
	}
	//-----------------Thong ke ke hoach------------------------------
	public Page<KeHoachNamData> thongKe(int page, int size, String sortBy, String sortDir, Long donViChuTriId, Integer nam,
			Long keHoachNamId, List<Integer> tinhTrangs, LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<KeHoachNam> pageKeHoachNam = serviceKeHoachNamService.thongke(donViChuTriId, nam, keHoachNamId, tinhTrangs, tuNgay,
				denNgay, tenNhiemVu, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachNamData> pageThongKe = pageKeHoachNam.map(this::convertToKeHoachNamIdAndNhiemVuNamId);
		return pageThongKe;
	}
}
