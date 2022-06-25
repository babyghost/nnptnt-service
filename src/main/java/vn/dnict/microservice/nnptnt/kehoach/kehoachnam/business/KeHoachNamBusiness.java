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
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;
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
				.map(this::convertToKeHoachNamData);
		return pageKeHoachNamData;
	}

	private KeHoachNamData convertToKeHoachNamData(KeHoachNam keHoachNam) {
		KeHoachNamData keHoachNamData = new KeHoachNamData();
		keHoachNamData.setId(keHoachNam.getId());
		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachNamData.setNam(keHoachNam.getNam());
		keHoachNamData.setTenKeHoach(keHoachNam.getTenKeHoach());
		keHoachNamData.setSoKyHieu(keHoachNam.getSoKyHieu());
		keHoachNamData.setNgayBanHanh(keHoachNam.getNgayBanHanh());
		keHoachNamData.setTrangThai(keHoachNam.getTrangThai());

		if (Objects.nonNull(keHoachNam.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNam.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNamData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachNamData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		List<DmLoaiNhiemVu> dmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByTrangThaiAndDaXoaOrderBySapXepAsc(true, false);
		List<DmLoaiNhiemVuData> dmLoaiNhiemVuDatas = new ArrayList<DmLoaiNhiemVuData>();
		if (Objects.nonNull(dmLoaiNhiemVus) && !dmLoaiNhiemVus.isEmpty()) {
			for (DmLoaiNhiemVu dmLoaiNhiemVu : dmLoaiNhiemVus) {
				DmLoaiNhiemVuData dmLoaiNhiemVuData = new DmLoaiNhiemVuData();
				dmLoaiNhiemVuData.setId(dmLoaiNhiemVu.getId());
				dmLoaiNhiemVuData.setTen(dmLoaiNhiemVu.getTen());
				dmLoaiNhiemVuData.setMa(dmLoaiNhiemVu.getMa());
				dmLoaiNhiemVuData.setTrangThai(dmLoaiNhiemVu.getTrangThai());
				dmLoaiNhiemVuData.setSapXep(dmLoaiNhiemVu.getSapXep());
				List<NhiemVuNam> nhiemVuNams = serviceNhiemVuNamService
						.findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachNam.getId(),
								dmLoaiNhiemVu.getId(), false);
				List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
				if (Objects.nonNull(nhiemVuNams) && !nhiemVuNams.isEmpty()) {
					nhiemVuNamDatas = setNhiemVuNamByKeHoachNam(nhiemVuNams);
				}
				dmLoaiNhiemVuData.setChildren(nhiemVuNamDatas);
				dmLoaiNhiemVuDatas.add(dmLoaiNhiemVuData);
			}
		}
		keHoachNamData.setDmLoaiNhiemVuDatas(dmLoaiNhiemVuDatas);
		return keHoachNamData;
	}

	private List<NhiemVuNamData> setNhiemVuNamByKeHoachNam(List<NhiemVuNam> nhiemVuNams) {
		List<NhiemVuNamData> nhiemVuNamDatas = new ArrayList<NhiemVuNamData>();
		for (NhiemVuNam nhiemVuNam : nhiemVuNams) {
			NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
			nhiemVuNamData.setId(nhiemVuNam.getId());
			nhiemVuNamData.setKeHoachId(nhiemVuNam.getKeHoachId());
			nhiemVuNamData.setTenNhiemVu(nhiemVuNam.getTenNhiemVu());
			nhiemVuNamData.setTuNgay(nhiemVuNam.getTuNgay());
			nhiemVuNamData.setDenNgay(nhiemVuNam.getDenNgay());
			nhiemVuNamData.setDonViPhoiHop(nhiemVuNam.getDonViPhoiHop());
			nhiemVuNamData.setGhiChu(nhiemVuNam.getGhiChu());

			List<NhiemVuNamData> children = new ArrayList<NhiemVuNamData>();
			List<NhiemVuNam> nhiemVuNamChildrens = serviceNhiemVuNamService
					.findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(nhiemVuNam.getKeHoachId(),
							nhiemVuNam.getLoaiNhiemVuId(), nhiemVuNam.getId(), false);
			if (Objects.nonNull(nhiemVuNamChildrens) && !nhiemVuNamChildrens.isEmpty()) {
				children = setNhiemVuNamByKeHoachNam(nhiemVuNamChildrens);
			}
			nhiemVuNamData.setChildren(children);
			nhiemVuNamDatas.add(nhiemVuNamData);
		}
		return nhiemVuNamDatas;
	}

	public KeHoachNamData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optional = serviceKeHoachNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optional.get();
		return this.convertToKeHoachNamData(keHoachNam);
	}

	public KeHoachNamData getKeHoachNamDaTa() {
		KeHoachNam keHoachNam = new KeHoachNam();
		return this.convertToKeHoachNamData(keHoachNam);
	}

	public KeHoachNamData create(KeHoachNamData keHoachNamData) throws EntityNotFoundException {

		KeHoachNam keHoachNam = new KeHoachNam();
		keHoachNam.setDaXoa(false);
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		if (Objects.nonNull(keHoachNamData.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNamData.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNam.setDonViChuTriId(optionalDmDonVi.get().getId());
			}
		}
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachId(true, keHoachNam.getId());
		List<DmLoaiNhiemVuData> dmLoaiNhiemVuDatas = keHoachNamData.getDmLoaiNhiemVuDatas();
		if (Objects.nonNull(dmLoaiNhiemVuDatas) && !dmLoaiNhiemVuDatas.isEmpty()) {
			for (DmLoaiNhiemVuData dmLoaiNhiemVuData : dmLoaiNhiemVuDatas) {
				List<NhiemVuNamData> nhiemVuNamDatas = dmLoaiNhiemVuData.getChildren();
				if (Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
					saveNhiemVuNamDatas(nhiemVuNamDatas, keHoachNam.getId(), dmLoaiNhiemVuData.getId(), null);
				}
			}
		}

		return this.convertToKeHoachNamData(keHoachNam);
	}

	private void saveNhiemVuNamDatas(List<NhiemVuNamData> nhiemVuNamDatas, Long keHoachId,
			Long loaiNhiemVuId, Long nhiemVuChaId) {
		for (NhiemVuNamData nhiemVuNamData : nhiemVuNamDatas) {
			NhiemVuNam nhiemVuNam = new NhiemVuNam();
			if (Objects.nonNull(nhiemVuNamData.getId())) {
				Optional<NhiemVuNam> optionalNhiemVuNam = serviceNhiemVuNamService
						.findById(nhiemVuNamData.getId());
				if (optionalNhiemVuNam.isPresent()) {
					nhiemVuNam = optionalNhiemVuNam.get();
				}
			}
			nhiemVuNam.setDaXoa(false);
			nhiemVuNam.setKeHoachId(keHoachId);
			nhiemVuNam.setTenNhiemVu(nhiemVuNamData.getTenNhiemVu());
			nhiemVuNam.setLoaiNhiemVuId(loaiNhiemVuId);
			nhiemVuNam.setTuNgay(nhiemVuNamData.getTuNgay());
			nhiemVuNam.setDenNgay(nhiemVuNamData.getDenNgay());
			nhiemVuNam.setDonViPhoiHop(nhiemVuNamData.getDonViPhoiHop());
			nhiemVuNam.setGhiChu(nhiemVuNamData.getGhiChu());
			nhiemVuNam.setNhiemVuChaId(nhiemVuChaId);
			nhiemVuNam = serviceNhiemVuNamService.save(nhiemVuNam);
			List<NhiemVuNamData> children = nhiemVuNamData.getChildren();
			if (Objects.nonNull(children) && !children.isEmpty()) {
				saveNhiemVuNamDatas(children, keHoachId, loaiNhiemVuId, nhiemVuNam.getId());
			}
		}
	}

	public KeHoachNamData update(Long id, KeHoachNamData keHoachNamData) throws EntityNotFoundException {
		Optional<KeHoachNam> optionalKeHoachNam = serviceKeHoachNamService.findById(id);
		if (!optionalKeHoachNam.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optionalKeHoachNam.get();
		keHoachNam.setDaXoa(false);
		keHoachNam.setNam(keHoachNamData.getNam());
		keHoachNam.setTenKeHoach(keHoachNamData.getTenKeHoach());
		keHoachNam.setSoKyHieu(keHoachNamData.getSoKyHieu());
		keHoachNam.setNgayBanHanh(keHoachNamData.getNgayBanHanh());
		keHoachNam.setTrangThai(keHoachNamData.getTrangThai());
		if (Objects.nonNull(keHoachNamData.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachNamData.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachNam.setDonViChuTriId(optionalDmDonVi.get().getId());
			}
		}
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		serviceNhiemVuNamService.setFixedDaXoaForKeHoachId(true, keHoachNam.getId());
		List<DmLoaiNhiemVuData> dmLoaiNhiemVuDatas = keHoachNamData.getDmLoaiNhiemVuDatas();
		if (Objects.nonNull(dmLoaiNhiemVuDatas) && !dmLoaiNhiemVuDatas.isEmpty()) {
			for (DmLoaiNhiemVuData dmLoaiNhiemVuData : dmLoaiNhiemVuDatas) {
				List<NhiemVuNamData> nhiemVuNamDatas = dmLoaiNhiemVuData.getChildren();
				if (Objects.nonNull(nhiemVuNamDatas) && !nhiemVuNamDatas.isEmpty()) {
					saveNhiemVuNamDatas(nhiemVuNamDatas, keHoachNam.getId(), dmLoaiNhiemVuData.getId(), null);
				}
			}
		}
		return this.convertToKeHoachNamData(keHoachNam);
	}

	public KeHoachNamData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachNam> optional = serviceKeHoachNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachNam.class, "id", String.valueOf(id));
		}
		KeHoachNam keHoachNam = optional.get();
		keHoachNam.setDaXoa(true);
		keHoachNam = serviceKeHoachNamService.save(keHoachNam);
		return this.convertToKeHoachNamData(keHoachNam);
	}
}
