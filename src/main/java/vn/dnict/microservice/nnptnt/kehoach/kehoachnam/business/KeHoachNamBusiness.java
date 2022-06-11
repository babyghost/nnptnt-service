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
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service.KeHoachNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;

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
	
	public Page<KeHoachNamData> findAll(int page, int size, String sortBy, String sortDir, Integer nam, String tenKeHoach, Boolean trangThai,
			String soKyHieu, LocalDate ngayBanHanhTuNgay, LocalDate ngayBanHanhDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachNam> pageKeHoachNam = serviceKeHoachNamService.findAll(nam, tenKeHoach, trangThai, soKyHieu, ngayBanHanhTuNgay,
				ngayBanHanhDenNgay, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachNamData> pageKeHoachNamData = pageKeHoachNam.map(this::convertToKeHoachNamData);
		return pageKeHoachNamData;
	}
	
	private KeHoachNamData convertToKeHoachNamData(KeHoachNam keHoachNam) {
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
				
				nhiemVuNamDatas.add(nhiemVuNamData);
			}
		}		
		return keHoachNamData;
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
		
		List<NhiemVuNam> listNhiemVu = serviceNhiemVuNamService.getByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachNam.getId(), false);
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
			nhiemVuData.setNhiemVuChaId(nhiemVuNam.getNhiemVuChaId());
			nhiemVuData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
			nhiemVuData.setSapXep(nhiemVuNam.getSapXep());
			nhiemVuData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
			nhiemVuData.setTuNgay(nhiemVuNam.getTuNgay());
			nhiemVuData.setDenNgay(nhiemVuNam.getDenNgay());
			nhiemVuData.setLoaiNhiemVuId(nhiemVuNam.getLoaiNhiemVuId());
			nhiemVuData.setGhiChu(nhiemVuNam.getGhiChu());
			
			List<NhiemVuNam> listNhiemVuChildren = serviceNhiemVuNamService.getByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(keHoachNamId,
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
		
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachNamId(false, keHoachNam.getId());		
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
				System.out.println(optNhiemVuNam+"*****************"+nhiemVuNamData.getId());
				if(optNhiemVuNam.isPresent()) {
					nhiemVuNam = optNhiemVuNam.get();
				}
			}
			System.out.println("++++++++++++++++++++++");
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
		return this.convertToKeHoachNamData(keHoachNam);
	}
}
