package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business;

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

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuNamData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
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

	public NhiemVuNamData findById(Long id) throws EntityNotFoundException {
		Optional<NhiemVuNam> optional = serviceNhiemVuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NhiemVuNam.class, "id", String.valueOf(id));
		}
		System.out.println("qqqqqqqqqqqq" + optional);
		NhiemVuNam nhiemVuNam = optional.get();
		NhiemVuNamData nhiemVuNamData = new NhiemVuNamData();
		nhiemVuNamData.setId(nhiemVuNam.getId());
		nhiemVuNamData.setKeHoachNamId(nhiemVuNam.getKeHoachNamId());
		if(nhiemVuNam.getKeHoachNamId() != null && nhiemVuNam.getKeHoachNamId() > 0) {
			Optional<KeHoachNam> optKeHoachNam = serviceKeHoachNamService.findById(nhiemVuNam.getKeHoachNamId());
			System.out.println(nhiemVuNam.getKeHoachNamId() + "aaaaaaaaaaaaa" + optKeHoachNam);
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
			System.out.println("-------------"+optLoaiNhiemVu);
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
					System.out.println(tienDoNhiemVuNam.getId() + "00000000000000" );
					Optional<FileDinhKemNhiemVuNam> fileDinhKemNam = serviceFileDinhKemNhiemVuNamService
							.findByTienDoNhiemVuNamIdAndDaXoa(tienDoNhiemVuNam.getId(), false);
					if(fileDinhKemNam.isPresent()) {
						Long fileDinhKemId = null;
						Long objectId = tienDoNhiemVuNam.getId();
						String appCode = TienDoNhiemVuNam.class.getSimpleName();
						System.out.println("cehaks"+fileDinhKemNam.get().getFileDinhKemId());
						FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(fileDinhKemNam.get().getFileDinhKemId(), appCode,
								objectId, type);
						tienDoNhiemVuNamData.setFileDinhKem(fileDinhKem);
						tienDoNhiemVuNamData.setFileDinhKemIds(fileDinhKem.getIds());
					}
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
		
		serviceTienDoNhiemVuNamService.setFixedDaXoaForNhiemVuNamId(true, nhiemVuNam.getId());
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
				
				// ????nh k??m
				serviceFileDinhKemNhiemVuNamService.setFixedDaXoaForTienDoNhiemVuNamId(true, tienDo.getId());
				/* Begin ????nh k??m file *******************************************************/

				/*
				 * Kh???i t???o bi???n **************************************************************
				 * - fileDinhKemIds: danh s??ch id file ???? ????nh k??m ****************************
				 * - type: lo???i ????nh k??m (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
				 * - objectId: id ?????i t?????ng ????nh k??m ******************************************
				 * - appCode: t??n model c???a ?????i t?????ng ????nh k??m*********************************
				 */
				List<Long> fileDinhKemIds = tienDoData.getFileDinhKemIds();
				int type = Constants.DINH_KEM_NHIEU_FILE;
				long objectId = tienDo.getId();
				String appCode = TienDoNhiemVuNam.class.getSimpleName();
				/* X??a m???m ????nh k??m c?? n???u c?? tr?????c khi set file ????nh k??m n???u ????nh k??m nhi???u */
				if (type == Constants.DINH_KEM_NHIEU_FILE) {
					coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
				}
				if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
					for (Long fileDinhKemId : fileDinhKemIds) {
						CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
								appCode);

						/* set db n???u c?? tr?????ng l??u v?? chuy???n file t??? temp sang th?? m???c ch??nh */
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

						/* tho??t n???u ????nh k??m 1 file */
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
